package com.example.datacreature.repository;

import com.example.datacreature.model.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExampleRepository extends JpaRepository<Example, Integer> {

    //title, nickname을 사용해서 해당 ID 를 반환하는 메서드이다.
    @Query("SELECT e.exampleNumber FROM Example e WHERE e.title = :title AND e.writeNickname = :nickname ORDER BY e.writeDatetime DESC")
    List<Integer> findExampleIdByTitleAndNickName(String title, String nickname, Pageable pageable);

}
