package com.schmapty.guvnah.model.expression.operator.impl;

import com.schmapty.guvnah.model.expression.Expression;
import com.schmapty.guvnah.model.expression.ExpressionParameter;
import com.schmapty.guvnah.model.expression.operator.WrapperExpression;
public class Not extends WrapperExpression {

    public Not(Expression expression) {
        this.setExpression(expression);
    }

    public boolean execute(ExpressionParameter<?>... parameters) {
       return !getExpression().execute(parameters);
    }

}
