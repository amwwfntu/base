package com.base.demo;

import com.alibaba.fastjson.JSON;
import com.base.demo.dao.mapper.PersonMapper;
import com.base.demo.dao.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	PersonMapper personMapper;

	@Test
	public void contextLoads() {
		Person person = personMapper.getById(1);
		System.out.println(JSON.toJSONString(person));
	}

}
