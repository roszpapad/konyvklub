package hu.roszpapad.konyvklub.repositories;

import hu.roszpapad.konyvklub.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findById (Long id);
}
