package ua.bieliaiev.calculator.model;

public interface ExpressionParser<T, U> {
	T parseExpression();
	boolean isExpressionValid();
	U getExpression();
}
