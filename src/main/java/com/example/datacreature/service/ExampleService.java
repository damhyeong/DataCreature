package com.example.datacreature.service;

import com.example.datacreature.model.Example;
import com.example.datacreature.model.ExampleSource;
import com.example.datacreature.repository.ExampleRepository;
import com.example.datacreature.repository.ExampleSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleService {
    private final ExampleRepository repository;

    @Autowired
    public ExampleService(ExampleRepository repository) {
        this.repository = repository;
    }

    public List<Example> getAllExample() {
        return repository.findAll();
    }

    public List<Example> getExampleByCurrentList() {
        System.out.println("getExampleByCurrentList");
        return repository.findAll(Sort.by(Sort.Direction.DESC, "example_number"));
    }
}
