package ravi.labs.springbootreactaws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ravi.labs.springbootreactaws.entity.Address;
import ravi.labs.springbootreactaws.entity.Person;
import ravi.labs.springbootreactaws.exceptionsHandler.ConstraintsViolationException;
import ravi.labs.springbootreactaws.exceptionsHandler.EntityNotFoundException;
import ravi.labs.springbootreactaws.repository.AddressRepo;
import ravi.labs.springbootreactaws.repository.PersonRepo;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepo personRepo;

    @Autowired
    AddressRepo addressRepo;

    @Override
    public Person addPerson(Person person) throws ConstraintsViolationException {
        Person newPerson = personRepo.save(person);
        return newPerson;
    }

    @Override
    public Person updatePerson(Person person) throws ConstraintsViolationException, EntityNotFoundException {

        Person oldPerson = personRepo.findById(person.getId())
                .orElseThrow( () ->
                        new EntityNotFoundException("Could not find Person with id: " + person.getId()));
        oldPerson.setFirstName(person.getFirstName());
        oldPerson.setLastName(person.getLastName());
        // TODO : Add new address
        if( null != person.getAddresses())
        oldPerson.setAddresses(person.getAddresses());
        return personRepo.save(oldPerson);
    }

    @Override
    public boolean deletePerson(Long id) throws EntityNotFoundException {
        Person person = this.getPersonById(id);
        personRepo.deleteById(person.getId());
        return true;
    }

    @Override
    public Person addOrUpdateAddress(Address address, Long personId) throws EntityNotFoundException {
        Person person = this.getPersonById(personId);
        person.getAddresses().add(address);
        Person savedPerson = personRepo.save(person);
        return savedPerson;
    }

    @Override
    public Person getPersonById(Long id) throws EntityNotFoundException {
        return personRepo.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Could not find Person with id: " + id));
    }


    @Override
    public Set<Person> getAllPersons() {
        return StreamSupport.stream(personRepo.findAll().spliterator(),true).collect(Collectors.toSet());
    }

    @Override
    public Set<Person> getPersonByFirstNameOrLastNameLike(String partialName) {
        return null;
    }

    @Override
    public Set<Person> getPersonByCity(String city) {
        return null;
    }
}
