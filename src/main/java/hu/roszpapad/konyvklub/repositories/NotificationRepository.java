package hu.roszpapad.konyvklub.repositories;

import hu.roszpapad.konyvklub.model.Notification;
import hu.roszpapad.konyvklub.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {

    List<Notification> findByUser(User user);
}
