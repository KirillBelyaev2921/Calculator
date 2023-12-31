package ua.bieliaiev.calculator.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class BigDecimalHolderTest {

	@Test
	void getDouble() {
		BigDecimalHolder number = new BigDecimalHolder();
		assertThat(number.getDouble(), equalTo(0.0));
	}
}