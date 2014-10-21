package com.hulljosh.calc;

public class Operation {

	double number1;
	char operator;
	double number2;
	
	Operation(double inNumber1, char inOperator, double inNumber2) {
		number1 = inNumber1;
		number2 = inNumber2;
		operator = inOperator;
	}
	
	public double executeOperation() {
		double returnVal = 0;
		switch(this.operator) {
			case '-':
				returnVal = this.number1 - this.number2;
				break;
			case '+':
				returnVal =  this.number1 + this.number2;
				break;
			case '*':
				returnVal = this.number1 * this.number2;
				break;
			case '/':
				returnVal = number1 / number2;
				break;
		}
		return returnVal;
	}
	

	@Override
	public String toString() {
		return "Operation [number1=" + number1 + ", operator=" + operator
				+ ", number2=" + number2 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(number1);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(number2);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + operator;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operation other = (Operation) obj;
		if (Double.doubleToLongBits(number1) != Double
				.doubleToLongBits(other.number1))
			return false;
		if (Double.doubleToLongBits(number2) != Double
				.doubleToLongBits(other.number2))
			return false;
		if (operator != other.operator)
			return false;
		return true;
	}


	
}
