package com.base.demo.service;

import com.base.demo.dao.model.Person;

public interface PersonService {

    Person searchByUserId(Integer userid);
}
