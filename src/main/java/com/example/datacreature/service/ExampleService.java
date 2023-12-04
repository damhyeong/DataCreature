package com.example.datacreature.service;

import com.example.datacreature.model.Example;
import com.example.datacreature.model.ExampleSource;
import com.example.datacreature.repository.ExampleRepository;
import com.example.datacreature.repository.ExampleSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Example createExample(Example example){
        return repository.save(example);
    }

    public Integer findByTitleAndNickname(String title, String nickname){
        Pageable limit = PageRequest.of(0, 1);
        List<Integer> ids = repository.findExampleIdByTitleAndNickName(title, nickname, limit);

        return ids.isEmpty() ? null : ids.get(0);
    }
}
