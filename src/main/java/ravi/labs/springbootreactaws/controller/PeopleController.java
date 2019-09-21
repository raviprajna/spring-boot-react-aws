package ravi.labs.springbootreactaws.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ravi.labs.springbootreactaws.entity.Address;
import ravi.labs.springbootreactaws.entity.Person;
import ravi.labs.springbootreactaws.exceptionsHandler.ConstraintsViolationException;
import ravi.labs.springbootreactaws.exceptionsHandler.EntityNotFoundException;
import ravi.labs.springbootreactaws.repository.AddressRepo;
import ravi.labs.springbootreactaws.service.PersonService;

import javax.validation.Valid;
import java.util.Set;

@Api(value = "People Operations", description = "People operations")
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT } )
public class PeopleController {

    @Autowired
    PersonService personService;

    @ApiOperation(value = "Get Person by ID")
    @GetMapping(value = "/person/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPersonById(@ApiParam(required = true) @PathVariable Long personId)
            throws EntityNotFoundException {
        return ResponseEntity.ok(personService.getPersonById(personId));
    }

    @ApiOperation(value = "Get All Persons")
    @GetMapping(value = "/persons", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Person>> getPersons()
            throws EntityNotFoundException {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @ApiOperation(value = "Add Person ")
    @PostMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> addPerson(@Valid @RequestBody Person person)
            throws EntityNotFoundException, ConstraintsViolationException {
        return ResponseEntity.ok(personService.addPerson(person));
    }

    @ApiOperation(value = "Update Person ")
    @PutMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> updatePerson(@Valid @RequestBody Person person)
            throws EntityNotFoundException, ConstraintsViolationException {
        return ResponseEntity.ok(personService.updatePerson(person));
    }

    @ApiOperation(value = "add address to Person by ID ")
    @PutMapping(value = "/addOrUpdateAddress", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> updateAddress(@Valid @RequestBody Address address, @RequestParam Long personId)
            throws EntityNotFoundException, ConstraintsViolationException {
        return ResponseEntity.ok(personService.addOrUpdateAddress(address, personId));
    }

    @ApiOperation(value = "Remove Person by ID")
    @DeleteMapping(value = "/person/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deletePerson(@ApiParam(required = true) @PathVariable Long personId)
            throws EntityNotFoundException {
        return ResponseEntity.ok(personService.deletePerson(personId));
    }

    @ApiOperation(value = "Remove Person by ID")
    @DeleteMapping(value = "/address/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteAddress(@ApiParam(required = true) @PathVariable Long addressId)
            throws EntityNotFoundException {
        return ResponseEntity.ok(personService.deleteAddressById(addressId));
    }


}
