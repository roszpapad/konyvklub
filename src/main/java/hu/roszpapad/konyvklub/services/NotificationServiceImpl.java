package hu.roszpapad.konyvklub.services;

import hu.roszpapad.konyvklub.model.Notification;
import hu.roszpapad.konyvklub.model.Offer;
import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.model.User;
import hu.roszpapad.konyvklub.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private static final int NUMBER_OF_MONTHS_ACTIVE = 1;

    private final NotificationRepository notificationRepository;

    @Override
    public void deleteExpiredNotifications(User user) {
        List<Notification> notifications = user.getNotifications();
        notifications.forEach(notification -> {
            if (notification.getEndDate().isBefore(LocalDateTime.now())){
                User userOfNotification = notification.getUser();
                userOfNotification.getNotifications().remove(notification);
                notificationRepository.delete(notification);
            }
        });

    }

    @Override
    public Notification createRejectedOfferNotification(Offer offer) {
        Notification notification = new Notification();
        notification.setEndDate(calculateEndDate());
        notification.setMessage("Rejected Offer :");
        notification.setGivenBookName(offer.getTicket().getBookToSell().getTitle());
        notification.setOfferedBookName(offer.getBookToPay().getTitle());
        notification.setUser(offer.getCustomer());
        deleteExpiredNotifications(offer.getCustomer());
        return notificationRepository.save(notification);
    }

    @Override
    public Notification createAcceptedTicketNotification(Offer offer) {
        Notification notification = new Notification();
        notification.setEndDate(calculateEndDate());
        notification.setMessage("Accepted Ticket :");
        notification.setGivenBookName(offer.getTicket().getBookToSell().getTitle());
        notification.setOfferedBookName(offer.getBookToPay().getTitle());
        notification.setUser(offer.getCustomer());
        deleteExpiredNotifications(offer.getCustomer());
        return notificationRepository.save(notification);
    }

    @Override
    public Notification createAcceptedOfferNotification(Offer offer) {
        Notification notification = new Notification();
        notification.setEndDate(calculateEndDate());
        notification.setMessage("Accepted Offer :");
        notification.setGivenBookName(offer.getTicket().getBookToSell().getTitle());
        notification.setOfferedBookName(offer.getBookToPay().getTitle());
        notification.setUser(offer.getCustomer());
        deleteExpiredNotifications(offer.getCustomer());
        return notificationRepository.save(notification);
    }

    @Override
    public Notification createExpiredTicketNotification(Ticket ticket) {
        //TODO: THIS
        return null;
    }

    public LocalDateTime calculateEndDate(){
        return LocalDateTime.now().plus(NUMBER_OF_MONTHS_ACTIVE,ChronoUnit.MONTHS);
    }
}
