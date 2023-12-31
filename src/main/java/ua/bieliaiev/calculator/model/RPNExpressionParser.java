package ua.bieliaiev.calculator.model;

/**
 * Expression parser, based on Reverse polish notation.
 * The result is represented by String value with coma delimiter.
 * Example: for expression '5*2+1', the result will be: '5,2,*,1,+'.
 *
 */
public class RPNExpressionParser implements ExpressionParser<String, String> {

	private String expression;

	@Override
	public String parseExpression(String expression) {
		return null;
	}

	@Override
	public boolean isExpressionValid() {
		return false;
	}

	@Override
	public String getExpression() {
		return null;
	}
}
