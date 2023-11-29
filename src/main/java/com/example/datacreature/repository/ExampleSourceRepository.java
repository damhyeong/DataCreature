package com.example.datacreature.repository;

import com.example.datacreature.dto.object.ExampleSourceListItem;
import com.example.datacreature.model.ExampleSource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class ExampleSourceRepository implements JpaRepository<ExampleSource, Integer> {

    @Override
    public void flush() {

    }

    @Override
    public <S extends ExampleSource> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends ExampleSource> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<ExampleSource> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ExampleSource getOne(Integer integer) {
        return null;
    }

    @Override
    public ExampleSource getById(Integer integer) {
        return null;
    }

    @Override
    public ExampleSource getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends ExampleSource> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ExampleSource> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ExampleSource> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ExampleSource> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ExampleSource> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ExampleSource> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ExampleSource, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends ExampleSource> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ExampleSource> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ExampleSource> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<ExampleSource> findAll() {
        return null;
    }

    @Override
    public List<ExampleSource> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(ExampleSource entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends ExampleSource> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<ExampleSource> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ExampleSource> findAll(Pageable pageable) {
        return null;
    }
}
