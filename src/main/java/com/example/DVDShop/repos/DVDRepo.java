package com.example.DVDShop.repos;

import com.example.DVDShop.domain.DVDisk;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DVDRepo extends CrudRepository<DVDisk, Long>{

}
