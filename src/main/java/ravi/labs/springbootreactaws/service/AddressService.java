package ravi.labs.springbootreactaws.service;

import ravi.labs.springbootreactaws.entity.Person;
import ravi.labs.springbootreactaws.exceptionsHandler.EntityNotFoundException;

import java.util.Set;

public interface AddressService {
    Boolean deleteAddressById(Long id) throws EntityNotFoundException;
}
