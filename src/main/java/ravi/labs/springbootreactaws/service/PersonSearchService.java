package ravi.labs.springbootreactaws.service;

import ravi.labs.springbootreactaws.entity.Person;
import ravi.labs.springbootreactaws.exceptionsHandler.EntityNotFoundException;

import java.util.Set;

public interface PersonSearchService {
    Person getPersonById(Long id) throws EntityNotFoundException;
    Set<Person> getPersonByFirstNameOrLastNameLike(String partialName);
    Set<Person> getPersonByCity(String city);
    Set<Person> getAllPersons();
}
