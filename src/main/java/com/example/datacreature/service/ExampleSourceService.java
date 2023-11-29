package com.example.datacreature.service;

import com.example.datacreature.model.ExampleSource;
import com.example.datacreature.repository.ExampleSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleSourceService {
    private final ExampleSourceRepository repository;

    @Autowired
    public ExampleSourceService(ExampleSourceRepository repository) {
        this.repository = repository;
    }

    public List<ExampleSource> getAllExampleSources() {
        return repository.findAll();
    }

    public ExampleSource getExampleSourceById(int id) {
        return repository.findById(id).orElse(null);
    }
}
