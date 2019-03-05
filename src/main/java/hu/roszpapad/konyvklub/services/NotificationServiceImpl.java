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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private static final int NUMBER_OF_WEEKS_ACTIVE = 1;

    private final NotificationRepository notificationRepository;

    private final UserService userService;

    @Override
    public void deleteExpiredNotifications(User user) {
        List<Notification> notifications = user.getNotifications();
        List<Notification> notificationsToDelete = new ArrayList<>();
        notifications.forEach(notification -> {
            if (notification.getEndDate().isBefore(LocalDateTime.now())){
                notificationsToDelete.add(notification);
            }
        });

        if (!notificationsToDelete.isEmpty()){
            notificationsToDelete.forEach(notification -> {
                User userOfNotification = notification.getUser();
                userOfNotification.getNotifications().remove(notification);
                notificationRepository.delete(notification);
            });
        }

    }

    @Override
    public Notification createRejectedOfferNotification(Offer offer) {
        Notification notification = new Notification();
        notification.setEndDate(calculateEndDate());
        notification.setMessage("Ajánlat elutasítva:");
        notification.setGivenBookName(offer.getTicket().getBookToSell().getTitle());
        notification.setOfferedBookName(offer.getBookToPay().getTitle());
        notification.setUser(offer.getCustomer());
        notification.setTicketId(offer.getTicket().getId());
        deleteExpiredNotifications(offer.getCustomer());
        return notificationRepository.save(notification);
    }

    @Override
    public Notification createAcceptedTicketNotification(Offer offer, Long channelId) {
        Notification notification = new Notification();
        notification.setEndDate(calculateEndDate());
        notification.setMessage("Ticket elkelt:");
        notification.setGivenBookName(offer.getTicket().getBookToSell().getTitle());
        notification.setOfferedBookName(offer.getBookToPay().getTitle());
        notification.setUser(offer.getTicket().getSeller());
        notification.setChannelId(channelId);
        deleteExpiredNotifications(offer.getCustomer());
        return notificationRepository.save(notification);
    }

    @Override
    public Notification createAcceptedOfferNotification(Offer offer, Long channelId) {
        Notification notification = new Notification();
        notification.setEndDate(calculateEndDate());
        notification.setMessage("Ajánlat elfogadva:");
        notification.setGivenBookName(offer.getTicket().getBookToSell().getTitle());
        notification.setOfferedBookName(offer.getBookToPay().getTitle());
        notification.setUser(offer.getCustomer());
        notification.setChannelId(channelId);
        deleteExpiredNotifications(offer.getCustomer());
        return notificationRepository.save(notification);
    }

    @Override
    public Notification createExpiredTicketNotification(Ticket ticket) {
        Notification notification = new Notification();
        notification.setEndDate(calculateEndDate());
        notification.setMessage("Ticket lejárt:");
        notification.setGivenBookName(ticket.getBookToSell().getTitle());
        notification.setOfferedBookName(null);
        notification.setUser(ticket.getSeller());
        deleteExpiredNotifications(ticket.getSeller());
        return notificationRepository.save(notification);
    }

    public LocalDateTime calculateEndDate(){
        return LocalDateTime.now().plus(NUMBER_OF_WEEKS_ACTIVE,ChronoUnit.WEEKS);
    }

    @Override
    public List<Notification> getNotificationsByUser(Long userId) {
        User user = userService.findById(userId);
        return notificationRepository.findByUser(user);
    }

    @Override
    public int getNumberOfNotificationsByUser(Long userId) {
        return getNotificationsByUser(userId).size();
    }
}
