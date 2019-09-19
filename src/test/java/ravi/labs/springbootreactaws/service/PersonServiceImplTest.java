package ravi.labs.springbootreactaws.service;

import org.hibernate.mapping.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ravi.labs.springbootreactaws.entity.Address;
import ravi.labs.springbootreactaws.entity.Person;
import ravi.labs.springbootreactaws.enums.AddressType;
import ravi.labs.springbootreactaws.exceptionsHandler.EntityNotFoundException;
import ravi.labs.springbootreactaws.repository.AddressRepo;
import ravi.labs.springbootreactaws.repository.PersonRepo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersonServiceImplTest {

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepo personRepo;

    @Autowired
    AddressRepo addressRepo;

    private String BANGALORE = "Bangalore";
    private Person expectedPeron;
    private Address homeAddress;
    private Address officeAddress;
    @Before
    public void setUp() throws Exception {

        homeAddress = Address.builder()
                .city(BANGALORE)
                .pinCode("KA-560043")
                .addressType(AddressType.HOME)
                .streetName("KASTURI NAGAR EAST")
                .build();

        officeAddress = Address.builder()
                .city(BANGALORE)
                .pinCode("KA-560043")
                .addressType(AddressType.OFFICE)
                .streetName("KASTURI NAGAR WEST")
                .build();

        Set<Address> addressSet = new HashSet<>();
        addressSet.add(homeAddress);
        addressSet.add(officeAddress);

        expectedPeron = Person.builder()
                .firstName("Ravi")
                .lastName("Prajna")
                .addresses(addressSet)
                .build();

        personRepo.save(expectedPeron);
    }


    @After
    public void tearDown() throws Exception {
        personRepo.deleteAll();
//        addressRepo.deleteAll();
    }

    @Test( expected = EntityNotFoundException.class)
    public void getPersonById_NotFoundCase() throws EntityNotFoundException {
        personService.getPersonById(0L);
    }

    @Test
    public void getPersonById_FoundCase() throws EntityNotFoundException {
        Person person = personService.getPersonById(expectedPeron.getId());
        assertEquals("Name assertion failed",
                person.getFullName(), expectedPeron.getFullName());
        person.getAddresses().forEach( address -> {
            assertEquals("Person City address assertion failed",
                    address.getCity(), BANGALORE);
        } );

    }

    @Test
    public void getAllPerson_checkTotalCount() {
        final Set<Person> allPersons = personService.getAllPersons();
        // One Person is inserted from data.sql
        assertEquals("Total Person Count assertion",
                allPersons.size() , 1);

    }

    @Test
    public void addPerson_withAddress() {
        Set<Address> addressSet = new HashSet<>();
        addressSet.add(homeAddress);
        addressSet.add(officeAddress);

        Person newPerson = Person.builder()
                .firstName("FN")
                .lastName("LN")
                .addresses(addressSet)
                .build();

        Person person = personRepo.save(newPerson);
        assertEquals("Add person assertion", person.getFullName(), newPerson.getFullName());
        assertEquals("Add person address size assertion ",
                person.getAddresses().size() , 2);
    }

    @Test
    public void deletePersonById() throws EntityNotFoundException {
        personService.deletePerson(expectedPeron.getId());
        assertFalse (" Delete Assertion", personRepo.findById(expectedPeron.getId()).isPresent());
    }

    @Test
    public void updatePerson_nameOnly() {
        Optional<Person> oldPerson = personRepo.findById(expectedPeron.getId());
        Person person = oldPerson.get();
        person.setFirstName("Updated");
        Person updatedPerson = personRepo.save(person);
        assertEquals("Add person assertion", "Updated", updatedPerson.getFirstName());
    }

    @Test
    public void updatePerson_withAddress() {
        homeAddress.setCity("MYSORE");

        Set<Address> addressSet = new HashSet<>();
        addressSet.add(homeAddress);

        Optional<Person> oldPerson = personRepo.findById(expectedPeron.getId());
        Person person = oldPerson.get();
        person.setFirstName("Updated");
        person.setAddresses(addressSet);
        Person updatedPerson = personRepo.save(person);
        assertEquals("Add person assertion", "Updated", updatedPerson.getFirstName());
        assertEquals("Add person address assertion", "MYSORE",
                updatedPerson.getAddresses().iterator().next().getCity());
    }


    @Test
    public void getPersonByFirstNameOrLastNameLike() {
    }

    @Test
    public void getPersonByCity() {
    }
}