package com.shoppingcart.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class RandomTests {

  @Test
  public void Test1() {
	  int a = 10;
	  int b = 10;
	  
	  assertEquals(a, b , "Failure");
	  
  }
 
  @Test
  public void Test2() {
	  int a = 20;
	  int b = 20;
	  
	  assertEquals(a, b , "Failure");
	  
  }
 
  @Test
  public void Test3() {	  
	  assertEquals("Hello World!", "Hello World!");
  }

  @BeforeMethod
  public void beforeMethod() {
  }

  @BeforeClass
  public void beforeClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }

}