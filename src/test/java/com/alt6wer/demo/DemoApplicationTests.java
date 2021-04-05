package com.alt6wer.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void calcSquareArea() {
	    Calculator calc = new Calculator();
	    assertEquals(25, calc.calcSquareArea(5));
	}

}
