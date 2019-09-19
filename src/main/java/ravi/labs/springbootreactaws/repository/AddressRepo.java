package ravi.labs.springbootreactaws.repository;

import org.springframework.data.repository.CrudRepository;
import ravi.labs.springbootreactaws.entity.Address;

public interface AddressRepo extends CrudRepository<Address, Long> {

}
