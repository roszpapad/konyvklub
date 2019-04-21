package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.converter.NotificationToBeDisplayedDTOConverter;
import hu.roszpapad.konyvklub.dtos.NotificationToBeDisplayedDTO;
import hu.roszpapad.konyvklub.model.Notification;
import hu.roszpapad.konyvklub.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotificationsController {

    private final NotificationService notificationService;

    private final NotificationToBeDisplayedDTOConverter notificationToBeDisplayedDTOConverter;

    @GetMapping("/notifications/{userId}")
    @PreAuthorize("hasRole('KONYVKLUB_USER')")
    public ResponseEntity<List<NotificationToBeDisplayedDTO>> getUserNotifications(@PathVariable(name = "userId") Long userId){

        List<NotificationToBeDisplayedDTO> notiDTOs = new ArrayList<>();
        List<Notification> notifications = notificationService.getNotificationsByUser(userId);
        notifications.forEach(notification -> notiDTOs.add(notificationToBeDisplayedDTOConverter.toDTO(notification)));
        return ResponseEntity.ok(notiDTOs);
    }

    @GetMapping("/notifications/{userId}/size")
    @PreAuthorize("hasRole('KONYVKLUB_USER')")
    public int getUserNotificationsCount(@PathVariable(name = "userId") Long userId){
        return notificationService.getNumberOfNotificationsByUser(userId);
    }
}
