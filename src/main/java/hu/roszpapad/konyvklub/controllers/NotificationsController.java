package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.Converter;
import hu.roszpapad.konyvklub.dtos.NotificationToBeDisplayedDTO;
import hu.roszpapad.konyvklub.model.Notification;
import hu.roszpapad.konyvklub.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class NotificationsController {

    private final NotificationService notificationService;

    private final Converter<Notification, NotificationToBeDisplayedDTO> notificationToBeDisplayedDTOConverter;

    @GetMapping("/notifications/{userId}")
    public ResponseEntity<List<NotificationToBeDisplayedDTO>> getUserNotifications(@PathVariable(name = "userId") Long userId){

        List<NotificationToBeDisplayedDTO> notiDTOs = new ArrayList<>();
        List<Notification> notifications = notificationService.getNotificationsByUser(userId);
        notifications.forEach(notification -> notiDTOs.add(notificationToBeDisplayedDTOConverter.toDTO(notification)));
        return ResponseEntity.ok(notiDTOs);
    }

    @GetMapping("/notifications/{userId}/size")
    public int getUserNotificationsCount(@PathVariable(name = "userId") Long userId){
        return notificationService.getNumberOfNotificationsByUser(userId);
    }
}
