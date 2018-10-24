package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.UserToBeCreatedConverter;
import hu.roszpapad.konyvklub.dtos.UserToBeCreated;
import hu.roszpapad.konyvklub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserToBeCreatedConverter userToBeCreatedConverter;

    @GetMapping("/user/register")
    public String registerUser(Model model){

        model.addAttribute("user",new UserToBeCreated());
        return "registration";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserToBeCreated userToBeCreated, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "registration";
        }

        userService.createUser(userToBeCreatedConverter.toEntity(userToBeCreated));
        return "redirect:/";
    }
}
