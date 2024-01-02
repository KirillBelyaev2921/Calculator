package ua.bieliaiev.calculator.model;

public interface ExpressionParser<T, U> {
	T parseExpression(U expression);
}
