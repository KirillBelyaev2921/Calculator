package ua.bieliaiev.calculator.model.rpn;

import ua.bieliaiev.calculator.model.ExpressionParser;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Expression parser, based on Reverse polish notation.
 * The result is represented by String value with coma delimiter.
 * Example: for expression '5*2+1', the result will be: '5,2,*,1,+'.
 *
 */
public class RPNExpressionParser implements ExpressionParser<String, String> {

	private String expression;

	public RPNExpressionParser(String expression) {
		Objects.requireNonNull(expression);
		this.expression = expression;
	}

	@Override
	public String parseExpression() {
		Pattern pattern = Pattern.compile("([0-9]+)([+]?)");
		Matcher m = pattern.matcher(expression);

		Deque<String> outputQueue = new LinkedList<>();
		Deque<String> operatorStack = new LinkedList<>();

		while (m.find()) {
			String number = m.group(1);
			if (!number.equals("")) {
				outputQueue.add(number);
			}
			String operator = m.group(2);
			if (!operator.equals("")) {
				operatorStack.push(operator);
			}
		}

		while (!operatorStack.isEmpty()) {
			outputQueue.add(operatorStack.pop());
		}

		return String.join(",", outputQueue);
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
