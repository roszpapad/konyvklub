package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.Converter;
import hu.roszpapad.konyvklub.dtos.UserToBeCreatedDTO;
import hu.roszpapad.konyvklub.dtos.UserToBeDisplayedDTO;
import hu.roszpapad.konyvklub.model.User;
import hu.roszpapad.konyvklub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final Converter<User, UserToBeCreatedDTO> userToBeCreatedConverter;

    private final Converter<User, UserToBeDisplayedDTO> userToBeDisplayedConverter;

    @GetMapping("/register")
    public String registerUser(Model model){

        model.addAttribute("user",userService.prepareUserForCreation());
        return "user/registration";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserToBeCreatedDTO userToBeCreated, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "user/registration";
        }

        userService.registerUser(userToBeCreatedConverter.toEntity(userToBeCreated));
        return "redirect:/";
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

        model.addAttribute("user", userToBeDisplayedConverter.toDTO(userService.findById(userId)));
        return "user/display";
    }

    @GetMapping("/user/{userId}/switchActive")
    public String switchActive(@PathVariable(name = "userId") Long userId){

        userService.switchActive(userId);
        return "redirect:/";
    }
}
