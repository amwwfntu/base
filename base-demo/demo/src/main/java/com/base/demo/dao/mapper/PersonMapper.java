package com.base.demo.dao.mapper;

import com.base.demo.dao.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonMapper {

    Person getById(Integer id);
}
