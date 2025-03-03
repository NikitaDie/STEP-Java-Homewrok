package com.example.nikitadev.hs02172025.service;

import com.example.nikitadev.hs02172025.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPersonService {
    List<Person> getAllPeople();

    Person addPerson(Person person);

    Optional<Person> getPersonById(UUID id);

    Optional<Person> updatePerson(UUID id, Person updatedPerson);

    boolean deletePerson(UUID id);

    List<Person> searchPeopleByName(String name);
}
