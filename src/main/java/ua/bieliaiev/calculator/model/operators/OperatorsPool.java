package ua.bieliaiev.calculator.model.operators;

import java.util.Map;

public class OperatorsPool {
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
