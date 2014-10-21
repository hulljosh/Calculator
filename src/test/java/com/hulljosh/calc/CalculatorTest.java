package com.hulljosh.calc;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	Calculator c = new Calculator();
	String basicAddition = "1+2";
	String negativeAddition = "-1+2";
	String basicMultiplication = "3*3";
	String negativeMultiplication = "-3*2";
	String basicDivision = "9/3";
	String negativeDivision = "-6/2";
	String bigMultiplication = "300*3";
	String bigDivision = "300/100";
	
	String multipleOperators3p2t5 = "3 + 2 * 5";//13
	String multipleOperatorsEq9 = "3 + 2 * 5 - 12 / 3";//9
	String multipleOperatorsEq6 = "- 3 + 2 * 5 - 12 / 3";//6
	
	@Test
	public void testValidInput() {
		assertTrue("This is a valid input", c.hasValidInputValue("1+2"));
		assertFalse("Cannot use alpha characters", c.hasValidInputValue("x+y"));
		assertFalse("Cannot have multiple operators in a row", c.hasValidInputValue("+++"));
		assertFalse("Cannot have an empty parenthesis", c.hasValidInputValue("()"));
//		assertFalse("Closing parenthesis cannot be preceded by text without an opening parethesis", c.hasValidInputValue(")(1+2)"));
	}
	
	@Test
	public void testParseOperation() {
		Operation baOp = new Operation(1,'+',2);
		Operation nadd = new Operation(-1,'+',2);
		Operation bigMultOp = new Operation(300,'*',3);
		
		assertEquals("basic addition", baOp, c.parseOperation(basicAddition));
		assertEquals("negative addition", nadd, c.parseOperation(negativeAddition));
		assertEquals("big multiplication", bigMultOp, c.parseOperation(bigMultiplication));
	}
	
	@Test
	public void testBasicMath() {
		assertEquals(basicAddition, 3.0, c.calculate(basicAddition));
		assertEquals(negativeAddition, 1.0, c.calculate(negativeAddition));
		assertEquals(basicMultiplication, 9.0, c.calculate(basicMultiplication));
		assertEquals(negativeMultiplication, -6.0, c.calculate(negativeMultiplication));
		assertEquals(basicDivision, 3.0, c.calculate(basicDivision));
		assertEquals(negativeDivision, -3.0, c.calculate(negativeDivision));
		
		assertEquals(bigMultiplication, 900.0, c.calculate(bigMultiplication));
		assertEquals(bigDivision, 3.0, c.calculate(bigDivision));
	}
	
	@Test
	public void testCompoundOperators() {
		assertEquals(multipleOperators3p2t5, 13.0, c.calculate(multipleOperators3p2t5));
		assertEquals(multipleOperatorsEq9, 9.0, c.calculate(multipleOperatorsEq9));
		assertEquals(multipleOperatorsEq6, 3.0, c.calculate(multipleOperatorsEq6));
	}
	
//	@Test
//	public void getRootOperation() {
//		String part = "2*5";
//		assertEquals("Multiply first", part, c.getFirstOperationString(multipleOperators3p2t5));
//	}
//	
	
	@Test
	public void testGetNextOperatorIndex() {
		assertEquals(multipleOperators3p2t5, 3, c.getNextOperatorIndex(multipleOperators3p2t5));
	}
	
	@Test
	public void testGetNextOperator() {
		String part = "*";
		assertEquals(multipleOperators3p2t5, part, c.getNextOperator(multipleOperators3p2t5));
	}
	
	@Test
	public void testGetLeftSide() {
		String part = "3+2";
		assertEquals(multipleOperators3p2t5, part, c.getNextLeftSideOfOperator(multipleOperators3p2t5));
	}
	
	@Test
	public void testGetRightSide() {
		String part = "5";
		assertEquals(multipleOperators3p2t5, part, c.getNextRightSideOfOperator(multipleOperators3p2t5));
	}
	
	@Test
	public void getNextOperation() {
		String nextOp = "2*5";
		assertEquals(multipleOperators3p2t5, nextOp, c.getNextOperationString(multipleOperators3p2t5));
	}
	
	@Test
	public void reduceString() {
		String reduced = "3+10.0";
		assertEquals(multipleOperators3p2t5, reduced, c.reduceString(multipleOperators3p2t5));
	}
	
}
