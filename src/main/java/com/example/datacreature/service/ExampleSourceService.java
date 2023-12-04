package com.example.datacreature.service;

import com.example.datacreature.dto.request.UploadExampleDto;
import com.example.datacreature.model.ExampleSource;
import com.example.datacreature.repository.ExampleRepository;
import com.example.datacreature.repository.ExampleSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleSourceService {
    private final ExampleSourceRepository exampleSourceRepository;
    private final ExampleRepository exampleRepository;


    @Autowired
    public ExampleSourceService(ExampleSourceRepository exampleSourceRepository, ExampleRepository exampleRepository) {
        this.exampleSourceRepository = exampleSourceRepository;
        this.exampleRepository = exampleRepository;
    }

    public List<ExampleSource> getAllExampleSources() {
        return exampleSourceRepository.findAll();
    }

    public ExampleSource getExampleSourceById(int id) {
        System.out.println("id : " + id);
        return exampleSourceRepository.findById(id).orElse(null);
    }

    public ExampleSource createExampleSource(ExampleSource exampleSource){
        return exampleSourceRepository.save(exampleSource);
    }
}
