package com.Practice1JUnit5;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Tag("Example1")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JUExample1Test {
	
	@Test
	@Order(1)
	void firstTest() {
		

		System.out.println("First Test");

	}// end firstTest
	
	@Test
	@Order(2)
	void secondTest() {

		System.out.println("Second Test");

	}// end secondTest
	
	@Test
	@Order(3)
	void thirdTest() {

		System.out.println("Third Test");

	}// end thirdTest
	

}// end JUExample1Test
