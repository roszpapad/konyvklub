package hu.roszpapad.konyvklub.repositories;

import hu.roszpapad.konyvklub.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address,Long> {
}
