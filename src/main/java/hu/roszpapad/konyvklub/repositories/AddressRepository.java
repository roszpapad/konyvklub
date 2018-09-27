package hu.roszpapad.konyvklub.repositories;

import hu.roszpapad.konyvklub.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address,Long> {
}
