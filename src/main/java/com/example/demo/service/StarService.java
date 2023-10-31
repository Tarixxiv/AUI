package com.example.demo.service;

import com.example.demo.entity.Star;
import com.example.demo.repository.StarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StarService {
    private final StarRepository repository;


    public StarService(StarRepository repository) {
        this.repository = repository;
    }

    public Optional<Star> find(UUID uuid){
        return repository.findById(uuid);
    }

    public List<Star> findAll() {
        return repository.findAll();
    }

    public List<Star> findAllByAge(int age){
        return repository.findAllByAge(age);
    }

    public Optional<Star> findByName(String name){
        return repository.findByName(name);
    }

    public void create(Star star){
        repository.save(star);
    }

    public void delete(Star star){
        repository.delete(star);
    }

}
