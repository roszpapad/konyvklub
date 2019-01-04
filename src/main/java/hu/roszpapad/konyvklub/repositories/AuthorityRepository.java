package hu.roszpapad.konyvklub.repositories;

import hu.roszpapad.konyvklub.model.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {

    Optional<Authority> findAuthorityByAuthority(String authority);
}
