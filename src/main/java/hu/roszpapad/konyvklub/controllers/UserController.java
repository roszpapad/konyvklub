package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.AddressForEverythingDTOConverter;
import hu.roszpapad.konyvklub.converter.Converter;
import hu.roszpapad.konyvklub.dtos.*;
import hu.roszpapad.konyvklub.events.OnRegistrationCompleteEvent;
import hu.roszpapad.konyvklub.model.*;
import hu.roszpapad.konyvklub.repositories.RegistrationTokenRepository;
import hu.roszpapad.konyvklub.services.BookService;
import hu.roszpapad.konyvklub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final BookService bookService;

    private final AddressForEverythingDTOConverter addressForEverythingDTOConverter;

    private final Converter<User, UserToBeDisplayedDTO> userToBeDisplayedConverter;

    private final Converter<Offer, OfferToBeDisplayedDTO> offerToBeDisplayedDTOConverter;

    private final Converter<Ticket, TicketToBeDisplayedDTO> ticketToBeDisplayedDTOConverter;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final RegistrationTokenRepository registrationTokenRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserToBeCreatedDTO userToBeCreated, WebRequest request){

        User user = userService.registerUser(userToBeCreated);
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

    @PostMapping("/users/sendChangePasswordEmail")
    public ResponseEntity<String> sendChangePasswordEmail(@Valid @RequestBody ChangePasswordRequestDTO changePasswordRequestDTO){

        userService.sendChangePasswordEmail(changePasswordRequestDTO.getUsername());
        return ResponseEntity.ok("Jelszó cseréjéhez e-mailt küldtünk Önnek.");
    }

    @PostMapping("/users/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangingPasswordDTO changingPasswordDTO, HttpServletResponse response, HttpServletRequest request) throws IOException{
        String result = userService.changePassword(changingPasswordDTO.getToken(), changingPasswordDTO.getPassword());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/user/{username}/switchActive")
    public ResponseEntity<String> switchActive(@PathVariable(name = "username") String username){

        userService.switchActive(username);
        return ResponseEntity.ok("Atvaltva!");
    }

    @PostMapping("/users/{userId}/books")
    public ResponseEntity<String> addBookToUser(@Valid @RequestBody BookToBeCreatedDTO bookDTO,
                                @PathVariable(name = "userId") Long userId){

        Book createdBook = bookService.createBook(bookDTO);
        userService.addBookToUser(userService.findById(userId), createdBook);
        return ResponseEntity.ok().body("Könyv mentve.");
    }

    @PostMapping("/users/{userId}/changePicture")
    public ResponseEntity<String> changeProfilePicture(@PathVariable(name = "userId") Long userId ,@RequestBody ImageDTO base64){
        userService.changeProfilePicture(userId, base64.getFile());
        return ResponseEntity.ok("Profilkép csere megtörtént!");
    }

    @GetMapping("/users/{userId}/address")
    public ResponseEntity<AddressForEverythingDTO> changeProfilePicture(@PathVariable(name = "userId") Long userId){
        Address address = userService.getAddressByUserId(userId);
        return ResponseEntity.ok(addressForEverythingDTOConverter.toDTO(address));
    }

    @GetMapping("/users/{userId}/picture")
    @ResponseBody
    public ResponseEntity<String> getProfilePicture(@PathVariable(name = "userId") Long userId){

        String picBase64 = userService.findPicture(userId);
        //return ResponseEntity.ok(picBase64);
        return ResponseEntity.status(200).contentType(MediaType.TEXT_PLAIN).body(picBase64);
    }

    @GetMapping("/users/filtered")
    public ResponseEntity<List<UserToBeDisplayedDTO>> getUsersByUsernameFilter(@PathParam(value = "username") String username){
        List<User> users = userService.getUsersByUsernameFilter(username);
        List<UserToBeDisplayedDTO> userDTOs = new ArrayList<>();
        users.forEach(user -> userDTOs.add(userToBeDisplayedConverter.toDTO(user)));
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/users/{username}/pictureByUsername")
    public ResponseEntity<String> getProfilePictureByUsername(@PathVariable(name = "username") String username){

        String picBase64 = userService.findPictureByUsername(username);
        return ResponseEntity.status(200).contentType(MediaType.TEXT_PLAIN).body(picBase64);
    }

    @GetMapping("/users/{userId}/offers")
    public ResponseEntity<List<OfferToBeDisplayedDTO>> getUserOffers(@PathVariable(name = "userId") Long userId){
        List<Offer> offers = userService.getUserOffers(userId);
        List<OfferToBeDisplayedDTO> offerDTOs = new ArrayList<>();
        offers.forEach(offer -> offerDTOs.add(offerToBeDisplayedDTOConverter.toDTO(offer)));
        return ResponseEntity.ok(offerDTOs);
    }

    @GetMapping("/users/{userId}/tickets")
    public ResponseEntity<List<TicketToBeDisplayedDTO>> getUserTickets(@PathVariable(name = "userId") Long userId){
        List<Ticket> tickets = userService.getUserTickets(userId);
        List<TicketToBeDisplayedDTO> ticketDTOs = new ArrayList<>();
        tickets.forEach(ticket -> ticketDTOs.add(ticketToBeDisplayedDTOConverter.toDTO(ticket)));
        return ResponseEntity.ok(ticketDTOs);
    }
}
