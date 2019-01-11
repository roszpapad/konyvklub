package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.Converter;
import hu.roszpapad.konyvklub.dtos.BookToBeCreatedDTO;
import hu.roszpapad.konyvklub.dtos.UserToBeCreatedDTO;
import hu.roszpapad.konyvklub.dtos.UserToBeDisplayedDTO;
import hu.roszpapad.konyvklub.dtos.UserToBeDisplayedWithBooksDTO;
import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.User;
import hu.roszpapad.konyvklub.services.BookService;
import hu.roszpapad.konyvklub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final BookService bookService;

    private final Converter<User, UserToBeCreatedDTO> userToBeCreatedConverter;

    private final Converter<User, UserToBeDisplayedDTO> userToBeDisplayedConverter;

    private final Converter<User, UserToBeDisplayedWithBooksDTO> userToBeDisplayedWithBooksConverter;

    private final Converter<Book, BookToBeCreatedDTO> bookToBeCreatedDTOConverter;

    /*@GetMapping("/register")
    public String registerUser(Model model){

        model.addAttribute("user",userService.prepareUserForCreation());
        return "user/registration";
    }*/

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserToBeCreatedDTO userToBeCreated){

        User user = userService.registerUser(userToBeCreatedConverter.toEntity(userToBeCreated));
        UserToBeDisplayedDTO userDTO = userToBeDisplayedConverter.toDTO(user);
        return ResponseEntity.ok().body(userDTO);
    }

    @GetMapping("/user/{userId}/update")
    public String updateUser(@PathVariable(name = "userId") Long userId, Model model){

        model.addAttribute("user", userToBeDisplayedConverter.toDTO(userService.findById(userId)));
        return "user/update";
    }

    @PutMapping("/update")
    public String update(@Valid @ModelAttribute(name = "user") UserToBeDisplayedDTO userDTO, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "user/update";
        }

        userService.updateUser(userToBeDisplayedConverter.toEntity(userDTO));
        return "redirect:/";
    }

    @GetMapping("/user/{userId}")
    public String getUserById(@PathVariable(name = "userId") Long userId,Model model){

        model.addAttribute("user", userToBeDisplayedWithBooksConverter.toDTO(userService.findById(userId)));
        model.addAttribute("newBook", new BookToBeCreatedDTO());
        return "user/display";
    }

    @GetMapping("/user/{userId}/switchActive")
    public String switchActive(@PathVariable(name = "userId") Long userId){

        userService.switchActive(userId);
        return "redirect:/";
    }

    @PostMapping("/user/{userId}/book")
    public String addBookToUser(@Valid @ModelAttribute(name = "newBook") BookToBeCreatedDTO bookDTO,
                                @PathVariable(name = "userId") Long userId){

        Book createdBook = bookService.createBook(bookToBeCreatedDTOConverter.toEntity(bookDTO));
        userService.addBookToUser(userService.findById(userId), createdBook);
        return "redirect:/user/" + userId;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "user/login";
    }


}
