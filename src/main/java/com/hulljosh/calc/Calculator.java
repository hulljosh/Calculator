package com.hulljosh.calc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * Traditional Calculator:
1. Get input 
2. Parse value.  Valid values are operators, parenthesis, numbers and a decimal point (preceded or suffixed by numbers)
3. determine the order of operations (multiply+divide then subtract+add)
4. within the order of operations, apply the operators
5. spit out the result. 
 */
public class Calculator {

	public Object calculate(String input) {
		if (!hasValidInputValue(input)) {
			throw new IllegalArgumentException("invalid characters combinations found in input string " + input);
		}
		
		while (!isNumeric(input)) {
			input = reduceString(input);
		}
		
		return Double.valueOf(input);
	}
	
	String reduceString(String rootNode) {
		String compressed = rootNode.replace(" ", "");
		String operationStr = getNextOperationString(compressed);
		Operation  op = parseOperation(operationStr);
		double opValue = op.executeOperation();
		String reduced = compressed.replace(operationStr, String.valueOf(opValue));
		return reduced;
	}
	
	
	boolean hasValidInputValue(String s) {
		s = s.replace(" ","");
		return !Pattern.matches("[\\+\\-\\*\\/]{2,}+", s)//Don't allow multiple operators in a row.
		&&!Pattern.matches("\\(\\)", s)//No empty parenthesis
		&&Pattern.matches("[\\d\\+\\-\\*\\/\\.]+", s);//input text must be numeric or an operator. 
	}
	
	Operation parseOperation(String rootCalculationNode) {
			String compressed = rootCalculationNode.replaceAll(" ", "");
			int negatizor = 1;
			
			if (compressed.charAt(0) == '-') {
				negatizor = -1;
				compressed = compressed.substring(1);
			}
			
			//At this point, the string should be in a compressed single operator state.
			String[] parsed = compressed.split("[\\+\\-\\*\\/]");
			Pattern p = Pattern.compile("([\\+\\-\\*\\/])");
			Matcher m = p.matcher(compressed);
			m.find();
			char op = m.group().charAt(0);
			
			String strNum1 = parsed[0];
			double dub1 = Double.valueOf(strNum1) * negatizor;
			String strNum2 = parsed[1];
			double dub2 = Double.valueOf(strNum2);
			return new Operation(dub1, op, dub2);
	}
	
	boolean isNumeric(String str) {
		try {  
			Double.parseDouble(str);  
		} catch(NumberFormatException nfe) {  
			return false;  
		}
		return true;  
	}

	String getNextOperationString(String outerNode) {
		String operator = getNextOperator(outerNode);
		String leftSide = getNextLeftSideOfOperator(outerNode);
		String rightSide = getNextRightSideOfOperator(outerNode);
		
		String leftRightSide = rightSide;
		while (!isNumeric(leftRightSide)) {
			leftRightSide = getNextLeftSideOfOperator(leftRightSide);
		}
		
		String rightLeftSide = leftSide;
		while (!isNumeric(rightLeftSide)) {
			rightLeftSide = getNextRightSideOfOperator(rightLeftSide);
		}
		
		return rightLeftSide + operator + leftRightSide;
	}
	
	String getNextLeftSideOfOperator(String node) {
		String compressed = node.replace(" ", "");
		int idx = getNextOperatorIndex(compressed);
		if (idx < 0) {
			return node;
		}
		String leftSide = compressed.substring(0, idx);
		return leftSide;
	}
	
	String getNextRightSideOfOperator(String node) {
		String compressed = node.replace(" ", "");
		int idx = getNextOperatorIndex(compressed);
		if (idx < 0) {
			return node;
		}
		String rightSide = compressed.substring(idx+1);
		return rightSide;
	}
	
	String getNextOperator(String node) {
		String compressed = node.replace(" ", "");
		int idx = getNextOperatorIndex(compressed);
		return compressed.substring(idx, idx+1);
	}
	
	int getNextOperatorIndex(String node) {
		String compressed = node.replace(" ", "");
		int opIdx = compressed.indexOf("*");
		
		if (opIdx <= 0) {
			opIdx = compressed.indexOf("/");
		}
		if (opIdx <= 0) {
			opIdx = compressed.indexOf("+");
		}
		if (opIdx <= 0) {
			opIdx = compressed.indexOf("-");
		}
		return opIdx;
	}
	
	boolean hasSubOperators(String value) {
		int count = StringUtils.countOccurrencesOf(value, "*")
				+ StringUtils.countOccurrencesOf(value, "/")
				+ StringUtils.countOccurrencesOf(value, "+")
				+ StringUtils.countOccurrencesOf(value, "-");
		return count > 0;
	}
	
}
