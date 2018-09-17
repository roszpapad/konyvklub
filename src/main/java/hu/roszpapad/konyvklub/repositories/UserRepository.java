package hu.roszpapad.konyvklub.repositories;

import hu.roszpapad.konyvklub.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
