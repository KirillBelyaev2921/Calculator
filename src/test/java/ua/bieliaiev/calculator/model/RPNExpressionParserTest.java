package ua.bieliaiev.calculator.model;

import org.junit.jupiter.api.Test;
import ua.bieliaiev.calculator.model.rpn.RPNExpressionParser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class RPNExpressionParserTest {
	private RPNExpressionParser parser;


	@Test
	void parseExpression() {
		String expression = "1+2";
		parser = new RPNExpressionParser(expression);

		String result = parser.parseExpression();

		assertThat(result, equalTo("1,2,+"));
	}
}