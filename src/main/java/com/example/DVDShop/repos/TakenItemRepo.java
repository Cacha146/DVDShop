package com.example.DVDShop.repos;

import com.example.DVDShop.domain.TakenItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TakenItemRepo extends CrudRepository<TakenItem, Long> {
}
