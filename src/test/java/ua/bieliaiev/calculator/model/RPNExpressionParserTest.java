package ua.bieliaiev.calculator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

class RPNExpressionParserTest {
	private RPNExpressionParser parser;

	@BeforeEach
	void createRPNExpressionParser() {
		parser = new RPNExpressionParser();
	}

	@Test
	void parseExpression() {
		String result = parser.parseExpression("");

		assertThat(result, nullValue());
	}
}