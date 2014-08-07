package com.schmapty.guvnah.model.expression.operator.impl;

import com.schmapty.guvnah.model.expression.Expression;
import com.schmapty.guvnah.model.expression.ExpressionParameter;
import com.schmapty.guvnah.model.expression.operator.NaryExpression;

import java.util.List;

public class Or extends NaryExpression {

    public Or(List<Expression> expressions) {
        this.setExpressions(expressions);
    }

    public boolean execute(ExpressionParameter<?>... parameters) {
        for(Expression e: getExpressions()) {
            if (e.execute(parameters)) {
                return true;
            }
        }
        return false;
    }

}
