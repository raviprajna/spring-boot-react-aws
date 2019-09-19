package ravi.labs.springbootreactaws.service;

import ravi.labs.springbootreactaws.entity.Address;
import ravi.labs.springbootreactaws.entity.Person;
import ravi.labs.springbootreactaws.exceptionsHandler.ConstraintsViolationException;
import ravi.labs.springbootreactaws.exceptionsHandler.EntityNotFoundException;


public interface PersonService extends PersonSearchService {
    Person addPerson(Person person) throws ConstraintsViolationException;
    Person updatePerson(Person person) throws ConstraintsViolationException, EntityNotFoundException;
    boolean deletePerson(Long id) throws EntityNotFoundException;
    Person addOrUpdateAddress(Address address, Long personId) throws EntityNotFoundException;
}
