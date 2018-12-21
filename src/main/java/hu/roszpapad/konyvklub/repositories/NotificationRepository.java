package hu.roszpapad.konyvklub.repositories;

import hu.roszpapad.konyvklub.model.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {
}
