package com.enigma.ecommerce.service;

import com.enigma.ecommerce.utils.specification.SearchCriteria;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    Iterable<T> findAll();
    T create(T params);
    Optional<T> findById(Integer id);
    void delete(Integer id);
    List<T> listBy(SearchCriteria searchCriteria);
}
