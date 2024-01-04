package ua.bieliaiev.calculator.model.rpn;

import ua.bieliaiev.calculator.model.ExpressionParser;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
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
		Matcher m = pattern.matcher(this.expression);

		while (m.find()) {
			addNumberToOutputQueue(m.group(1));
			addOperatorToOperatorStack(m.group(2));
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

	public static class OperatorsPool {
		Map<String, Integer> operations;

		public OperatorsPool() {
			this.operations = Map.of(
					"+", 1,
					"-", 1,
					"*", 2,
					"/", 2
			);
		}

		public int isFirstOperatorHasHigherPriority(String operationSign1, String operationSign2) {

			return Integer.compare(operations.get(operationSign1),
					operations.get(operationSign2));
		}

	}

}
