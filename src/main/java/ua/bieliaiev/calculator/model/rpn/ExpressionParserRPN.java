package ua.bieliaiev.calculator.model.rpn;

import ua.bieliaiev.calculator.model.ExpressionParser;
import ua.bieliaiev.calculator.model.operators.OperatorsPool;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Expression parser, based on Reverse polish notation.
 * The result is represented by String value with coma delimiter.
 * Example: for expression '5*2+1', the result will be: '5,2,*,1,+'.
 */
public class ExpressionParserRPN implements ExpressionParser<String, String> {
	private OperatorsPool operatorsPool = new OperatorsPool();
	private String expression;
	private Deque<String> outputQueue = new LinkedList<>();
	private Deque<String> operatorStack = new LinkedList<>();

	private static final Pattern pattern =
			Pattern.compile("([0-9]+)([-+*/]?)");

	@Override
	public String parseExpression(String expression) {
		removeSpacesAndInitiateExpression(expression);
		iterateExpression();
		addTrailingOperatorsToOutput(() -> !operatorStack.isEmpty());
		return String.join(",", outputQueue);
	}

	private void removeSpacesAndInitiateExpression(String expression) {
		Objects.requireNonNull(expression);
		this.expression = expression.replace(" ", "");
	}

	private void iterateExpression() {
		Matcher m = pattern.matcher(expression);

		int lastCharacterIndex = 0;
		while (m.find()) {
			if (lastCharacterIndex != m.start()) {
				throw new IllegalArgumentException("Illegal substring: \"" + expression.substring(lastCharacterIndex, m.start())
						+ "\" in expression: \"" + expression + "\"");
			}
			addNumberToOutputQueue(m.group(1));
			addOperatorToOperatorStack(m.group(2));
			lastCharacterIndex = m.end();
		}
		if (lastCharacterIndex != expression.length()) {
			throw new IllegalArgumentException("Illegal substring: \"" + expression.substring(lastCharacterIndex)
					+ "\" in expression: \"" + expression + "\"");
		}
	}

	private void addOperatorToOperatorStack(String operator) {
		if (!operator.equals("")) {
			addTrailingOperatorsToOutput(() -> isGreaterPriority(operator));
			operatorStack.push(operator);
		}
	}

	private boolean isGreaterPriority(String currentOperator) {
		if (operatorStack.isEmpty()) {
			return false;
		}
		String operatorFromStack = operatorStack.peek();
		return operatorsPool
				.isFirstOperatorHasHigherPriority(currentOperator,
						operatorFromStack) <= 0;
	}

	private void addNumberToOutputQueue(String number) {
		if (!number.equals("")) {
			outputQueue.add(number);
		}
	}

	private void addTrailingOperatorsToOutput(Supplier<Boolean> conditionToAdd) {
		while (conditionToAdd.get()) {
			outputQueue.add(operatorStack.pop());
		}
	}


}
