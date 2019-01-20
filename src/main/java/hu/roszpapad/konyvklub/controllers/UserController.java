package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.Converter;
import hu.roszpapad.konyvklub.dtos.BookToBeCreatedDTO;
import hu.roszpapad.konyvklub.dtos.UserToBeCreatedDTO;
import hu.roszpapad.konyvklub.dtos.UserToBeDisplayedDTO;
import hu.roszpapad.konyvklub.dtos.UserToBeDisplayedWithBooksDTO;
import hu.roszpapad.konyvklub.events.OnRegistrationCompleteEvent;
import hu.roszpapad.konyvklub.model.Book;
import hu.roszpapad.konyvklub.model.RegistrationToken;
import hu.roszpapad.konyvklub.model.User;
import hu.roszpapad.konyvklub.repositories.RegistrationTokenRepository;
import hu.roszpapad.konyvklub.services.BookService;
import hu.roszpapad.konyvklub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final BookService bookService;

    private final Converter<User, UserToBeCreatedDTO> userToBeCreatedConverter;

    private final Converter<User, UserToBeDisplayedDTO> userToBeDisplayedConverter;

    private final Converter<User, UserToBeDisplayedWithBooksDTO> userToBeDisplayedWithBooksConverter;

    private final Converter<Book, BookToBeCreatedDTO> bookToBeCreatedDTOConverter;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final RegistrationTokenRepository registrationTokenRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserToBeCreatedDTO userToBeCreated, WebRequest request){

        User user = userService.registerUser(userToBeCreatedConverter.toEntity(userToBeCreated));
        UserToBeDisplayedDTO userDTO = userToBeDisplayedConverter.toDTO(user);

        try {
            String appUrl = request.getContextPath();
            applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(userService.findById(user.getId()), appUrl));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nem küldhető el az email.");
        }

        return ResponseEntity.ok().body(userDTO);
    }

    @GetMapping("/registrationConfirm")
    public void confirmRegistration(@RequestParam(value = "token") String token, HttpServletResponse response, WebRequest request) throws IOException {
        Optional<RegistrationToken> registrationToken = registrationTokenRepository.findByToken(token);
        if (!registrationToken.isPresent()){
            response.sendRedirect("http://localhost:4200/login" + "?success=noToken");
        }
        else {
            RegistrationToken regToken = registrationToken.get();
            if (regToken.getExpiryDate().isBefore(LocalDateTime.now())) {

                response.sendRedirect("http://localhost:4200/login" + "?success=expiredToken");

            } else {

                User user = regToken.getUser();
                user.setEnabled(true);
                userService.saveUser(user);
                response.sendRedirect("http://localhost:4200/login" + "?success=success");

            }
        }
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

    @PostMapping("/users/{userId}/books")
    public ResponseEntity<String> addBookToUser(@Valid @RequestBody BookToBeCreatedDTO bookDTO,
                                @PathVariable(name = "userId") Long userId){

        Book createdBook = bookService.createBook(bookToBeCreatedDTOConverter.toEntity(bookDTO));
        userService.addBookToUser(userService.findById(userId), createdBook);
        return ResponseEntity.ok().body("Könyv mentve.");
    }

    @GetMapping("/login")
    public String loginPage(){
        return "user/login";
    }


}
