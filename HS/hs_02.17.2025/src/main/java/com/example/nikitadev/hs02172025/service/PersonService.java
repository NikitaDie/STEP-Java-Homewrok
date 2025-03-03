package com.example.nikitadev.hs02172025.service;

import com.example.nikitadev.hs02172025.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonService implements IPersonService {

    private final List<Person> people = new ArrayList<>();

    @Override
    public List<Person> getAllPeople() {
        return people;
    }

    @Override
    public Person addPerson(Person person) {
        people.add(person);
        return person;
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
        return people.stream()
            .filter(person -> person.getId().equals(id))
            .findFirst();
    }

    @Override
    public Optional<Person> updatePerson(UUID id, Person updatedPerson) {
        Optional<Person> existingPersonOpt = getPersonById(id);

        if (existingPersonOpt.isEmpty())
            return Optional.empty();

        Person existingPerson = existingPersonOpt.get();

        existingPerson.setFirstName(updatedPerson.getFirstName());
        existingPerson.setLastName(updatedPerson.getLastName());
        existingPerson.setMiddleName(updatedPerson.getMiddleName());
        existingPerson.setPhone(updatedPerson.getPhone());
        existingPerson.setEmail(updatedPerson.getEmail());
        existingPerson.setBlogUrl(updatedPerson.getBlogUrl());
        existingPerson.setNotes(updatedPerson.getNotes());

        return Optional.of(existingPerson);
    }

    @Override
    public boolean deletePerson(UUID id) {
        return people.removeIf(person -> person.getId().equals(id));
    }

    @Override
    public List<Person> searchPeopleByName(String name) {
        String lowerCaseName = name.toLowerCase();

        return people.stream()
            .filter(person -> person.getFullName().toLowerCase().contains(lowerCaseName))
            .collect(Collectors.toList());
    }
}
