package ua.bieliaiev.calculator.model;

import org.junit.jupiter.api.Test;
import ua.bieliaiev.calculator.model.rpn.RPNExpressionParser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class RPNExpressionParserTest {

	@Test
	void parseSimplePlusExpression() {
		String expression = "1+2";
		RPNExpressionParser parser = new RPNExpressionParser(expression);

		String result = parser.parseExpression();

		assertThat(result, equalTo("1,2,+"));
	}

	@Test
	void parseSimpleMinusExpression() {
		String expression = "1-2";
		RPNExpressionParser parser = new RPNExpressionParser(expression);

		String result = parser.parseExpression();

		assertThat(result, equalTo("1,2,-"));
	}

	@Test
	void parseSimpleMultiplyExpression() {
		String expression = "1*2";
		RPNExpressionParser parser = new RPNExpressionParser(expression);

		String result = parser.parseExpression();

		assertThat(result, equalTo("1,2,*"));
	}

	@Test
	void parseSimpleDivideExpression() {
		String expression = "1/2";
		RPNExpressionParser parser = new RPNExpressionParser(expression);

		String result = parser.parseExpression();

		assertThat(result, equalTo("1,2,/"));
	}
}