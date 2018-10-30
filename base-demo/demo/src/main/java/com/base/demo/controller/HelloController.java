package com.base.demo.controller;

import com.base.demo.dao.model.Person;
import com.base.demo.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@Api(value = "测试",tags = {"测试类"})
@RestController
public class HelloController {

    @Autowired
    PersonService personService;

    @ApiOperation("测试方法")
    @RequestMapping(value = "/hello/{id}",method = RequestMethod.GET)
    public Person hello(@PathVariable Integer id){
        Person person = personService.searchByUserId(id);
        return person;
    }
}
