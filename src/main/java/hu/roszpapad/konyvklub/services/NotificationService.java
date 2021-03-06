package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.model.Notification;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.model.User;

import java.util.List;

public interface NotificationService {

    void deleteExpiredNotifications(User user);

    Notification createRejectedOfferNotification(Offer offer);

    Notification createAcceptedTicketNotification(Offer offer, Long channelId);

    Notification createAcceptedOfferNotification(Offer offer, Long channelId);

    Notification createExpiredTicketNotification(Ticket ticket);

    List<Notification> getNotificationsByUser(Long userId);

    int getNumberOfNotificationsByUser(Long userId);
}
