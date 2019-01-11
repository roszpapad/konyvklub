package hu.roszpapad.konyvklub.listeners;

import hu.roszpapad.konyvklub.events.OnRegistrationCompleteEvent;
import hu.roszpapad.konyvklub.model.User;
import hu.roszpapad.konyvklub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final UserService userService;

    private final JavaMailSender javaMailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
        this.confirmRegistration(onRegistrationCompleteEvent);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createRegistrationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Regisztráció megerősítés - konyvklub";
        String confirmationUrl
                = event.getAppUrl() + "/registrationConfirm?token=" + token;
        String message = "Kattintson a következő linkre a fiókja aktiválásahóz.";

        MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(recipientAddress));
                mimeMessage.setSubject(subject);
                mimeMessage.setText(message + "<br/>"
                        + "<a href='http://localhost:8083" + confirmationUrl +  "'>Aktivációs link. Kattintson ide!</a>","UTF-8","html");
            }
        };
        javaMailSender.send(mimeMessagePreparator);
    }
}
