package br.com.sigen.werp.app;

import java.math.BigDecimal;

public class MathOp {

	public static BigDecimal sum(BigDecimal number1, BigDecimal number2) {
		return number1.add(number2);
	}

	public static BigDecimal subtract(BigDecimal number1, BigDecimal number2) {
		return number1.subtract(number2);
	}

	public static BigDecimal multiply(BigDecimal number1, BigDecimal number2) {
		return number1.multiply(number2);
	}

}
