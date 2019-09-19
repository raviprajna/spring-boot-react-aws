package ravi.labs.springbootreactaws.repository;

import org.springframework.data.repository.CrudRepository;
import ravi.labs.springbootreactaws.entity.Person;

import java.util.Set;

public interface PersonRepo extends CrudRepository<Person, Long> {
}
