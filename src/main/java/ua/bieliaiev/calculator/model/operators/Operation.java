package ua.bieliaiev.calculator.model.operators;

import java.util.Arrays;

public enum Operation {
	PLUS("+",  1),
	MINUS("-", 1),
	MULTIPLY("*", 2),
	DIVIDE("/",  2);

	private final String operationSign;

	private final int priority;

	Operation(String operationSign, int priority) {
		this.operationSign = operationSign;
		this.priority = priority;
	}

	public static Operation getInstanceByChar(String operationSign) {
		return Arrays.stream(Operation.values())
				.filter(operation -> operation.operationSign.equals(operationSign))
				.findAny()
				.orElse(null);
	}
	public static int comparePriorities(String operationSign1, String operationSign2) {

		Operation operation1 = getInstanceByChar(operationSign1);
		Operation operation2 = getInstanceByChar(operationSign2);
		return Integer.compare(operation1.priority, operation2.priority);
	}
}
