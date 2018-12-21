package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.model.Notification;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.model.User;

public interface NotificationService {

    void deleteExpiredNotifications(User user);

    Notification createRejectedOfferNotification(Offer offer);

    Notification createAcceptedTicketNotification(Offer offer);

    Notification createAcceptedOfferNotification(Offer offer);

    Notification createExpiredTicketNotification(Ticket ticket);
}
