package com.example.multimodule.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest("service.message=Hello")
public class MyServiceTest {


	@SpringBootApplication
	static class TestConfiguration {
	}

}
