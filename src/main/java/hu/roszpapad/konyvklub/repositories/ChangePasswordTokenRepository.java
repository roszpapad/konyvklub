package hu.roszpapad.konyvklub.repositories;

import hu.roszpapad.konyvklub.model.ChangePasswordToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChangePasswordTokenRepository extends CrudRepository<ChangePasswordToken, Long> {

    Optional<ChangePasswordToken> findByToken(String token);
}
