package com.base.demo.service.impl;

import com.base.demo.dao.mapper.PersonMapper;
import com.base.demo.dao.model.Person;
import com.base.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonMapper personMapper;

    @Override
    public Person searchByUserId(Integer userid) {

        return personMapper.getById(userid);
    }
}
