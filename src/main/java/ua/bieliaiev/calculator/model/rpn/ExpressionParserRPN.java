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
 */
public class ExpressionParserRPN implements ExpressionParser<String, String> {

	private String expression;
	Deque<String> outputQueue = new LinkedList<>();
	Deque<String> operatorStack = new LinkedList<>();
	private static final Pattern pattern =
			Pattern.compile("([0-9]+)([-+*/]?)");

	@Override
	public String parseExpression(String expression) {
		removeSpacesAndInitiateExpression(expression);
		iterateExpression();
		addTrailingOperatorsToOutput();
		return String.join(",", outputQueue);
	}

	private void removeSpacesAndInitiateExpression(String expression) {
		Objects.requireNonNull(expression);
		this.expression = expression.replace(" ", "");
	}

	private void iterateExpression() {
		Matcher m = pattern.matcher(this.expression);

		while (m.find()) {
			addNumberToOutputQueue(m.group(1));
			addOperatorToOperatorStack(m.group(2));
		}
	}

	private void addOperatorToOperatorStack(String operator) {
		if (!operator.equals("")) {
			operatorStack.push(operator);
		}
	}

	private void addNumberToOutputQueue(String number) {
		if (!number.equals("")) {
			outputQueue.add(number);
		}
	}

	private void addTrailingOperatorsToOutput() {
		while (!operatorStack.isEmpty()) {
			outputQueue.add(operatorStack.pop());
		}

	}

}
