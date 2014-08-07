package com.schmapty.guvnah.model.expression;

public class ExpressionParameter<T> {

	private T o;
	
	public ExpressionParameter(T o) {
		this.o=o;
	}
	
	public T get() {
		return o;
	}

}
