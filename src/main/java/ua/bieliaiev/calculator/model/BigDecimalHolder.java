package ua.bieliaiev.calculator.model;

import java.math.BigDecimal;

public class BigDecimalHolder implements DoubleValue {

	private final BigDecimal bigDecimal;

	public BigDecimalHolder(String number) {
		this.bigDecimal = new BigDecimal(number);
	}

	@Override
	public double getDouble() {
		return bigDecimal.doubleValue();
	}
}
