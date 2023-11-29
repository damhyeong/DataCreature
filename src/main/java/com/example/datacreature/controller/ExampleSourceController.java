package com.example.datacreature.controller;

import com.example.datacreature.model.ExampleSource;
import com.example.datacreature.service.ExampleSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/examples")
public class ExampleSourceController {
    private final ExampleSourceService service;

    @Autowired
    public ExampleSourceController(ExampleSourceService service) {
        this.service = service;
    }

    @GetMapping
    public List<ExampleSource> getAllExampleSources() {
        return service.getAllExampleSources();
    }

    @GetMapping("/{id}")
    public ExampleSource getExampleSourceById(@PathVariable int id) {
        return service.getExampleSourceById(id);
    }
}
