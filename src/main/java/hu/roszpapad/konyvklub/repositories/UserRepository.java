package hu.roszpapad.konyvklub.repositories;

import hu.roszpapad.konyvklub.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
