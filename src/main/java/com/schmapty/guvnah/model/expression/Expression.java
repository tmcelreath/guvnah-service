package com.schmapty.guvnah.model.expression;

public interface Expression {

    public boolean execute(ExpressionParameter<?>... parameters);

}
