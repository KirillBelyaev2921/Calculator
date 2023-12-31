package ua.bieliaiev.calculator.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BigDecimalHolderTest {

	@Test
	void getZeroFromIntegerString() {
		BigDecimalHolder number = new BigDecimalHolder("0");

		assertThat(number.getDouble(), equalTo(0.0));
	}

	@Test
	void getZeroFromDoubleString() {
		BigDecimalHolder number = new BigDecimalHolder("0.0");

		assertThat(number.getDouble(), equalTo(0.0));
	}

	@Test
	void getNumberFromNumberString() {
		double value = 1.1;
		BigDecimalHolder number = new BigDecimalHolder("" + value);

		assertThat(number.getDouble(), equalTo(value));
	}

	@Test
	void getExceptionWhenImproperStringParsed() {
		String improperValue = "123a";

		assertThrows(NumberFormatException.class,
				() -> new BigDecimalHolder(improperValue));

	}

	@Test
	void getExceptionWhenNullStringParsed() {
		String improperValue = null;

		assertThrows(NullPointerException.class,
				() -> new BigDecimalHolder(improperValue));

	}

}