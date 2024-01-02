package ua.bieliaiev.calculator.model;

import org.junit.jupiter.api.Test;
import ua.bieliaiev.calculator.model.rpn.ExpressionParserRPN;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ExpressionParserRPNTest {

	@Test
	void parseSimplePlusExpression() {
		String expression = "1+2";

		String result = getResultFromParser(expression);

		assertThat(result, equalTo("1,2,+"));
	}

	@Test
	void parseSimpleMinusExpression() {
		String expression = "1-2";

		String result = getResultFromParser(expression);

		assertThat(result, equalTo("1,2,-"));
	}

	@Test
	void parseSimpleMultiplyExpression() {
		String expression = "1*2";

		String result = getResultFromParser(expression);

		assertThat(result, equalTo("1,2,*"));
	}

	@Test
	void parseSimpleDivideExpression() {
		String expression = "1/2";

		String result = getResultFromParser(expression);

		assertThat(result, equalTo("1,2,/"));
	}

	@Test
	void parseExpressionWithSpaces() {
		String expression = "1 + 2";

		String result = getResultFromParser(expression);

		assertThat(result, equalTo("1,2,+"));
	}

	@Test
	void parsePlusOperatorAfterMultiplyOperator() {
		String expression = "1*2+3";

		String result = getResultFromParser(expression);

		assertThat(result, equalTo("1,2,*,3,+"));
	}

	@Test
	void parseMultiplyOperatorAfterPlusOperator() {
		String expression = "1+2*3";

		String result = getResultFromParser(expression);

		assertThat(result, equalTo("1,2,3,*,+"));
	}

	private String getResultFromParser(String expression) {
		ExpressionParserRPN parser = new ExpressionParserRPN();
		return parser.parseExpression(expression);
	}
}