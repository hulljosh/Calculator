package com.hulljosh.calc;

import static org.junit.Assert.*;

import org.junit.Test;

public class OperationTest {

	@Test
	public void testExecutionBasicMath() {
		Operation basicAddition = new Operation(1,'+',2);
		Operation negAddition = new Operation(-1,'+',2);
		Operation basicMultiply = new Operation(4,'*',2);
		Operation basicDivide = new Operation(6,'/',2);
		Operation negDivide = new Operation(-6,'/',2);
		
		assertEquals(basicAddition.toString(), 3.0, basicAddition.executeOperation(), 0);
		assertEquals(negAddition.toString(), 1.0, negAddition.executeOperation(), 0);
		assertEquals(basicMultiply.toString(), 8.0, basicMultiply.executeOperation(), 0);
		assertEquals(basicDivide.toString(), 3.0, basicDivide.executeOperation(), 0);
		assertEquals(negDivide.toString(), -3.0, negDivide.executeOperation(), 0);
		
	}
	
	@Test
	public void testExecutionDecimalMath() {
		Operation decimalMath1 = new Operation(1.5, '*', 4);
		Operation decimalMath2 = new Operation(1.5, '/', 4);
		Operation decimalMath3 = new Operation(1.5, '+', 4);
		Operation decimalMath4 = new Operation(1.5, '-', 4);
		
		assertEquals(decimalMath1.toString(), 6.0, decimalMath1.executeOperation(), 0);
		assertEquals(decimalMath2.toString(), 0.375, decimalMath2.executeOperation(), 0);
		assertEquals(decimalMath3.toString(), 5.5, decimalMath3.executeOperation(), 0);
		assertEquals(decimalMath4.toString(), -2.5, decimalMath4.executeOperation(), 0);
	}

}
