package com.schmapty.guvnah.model.expression.operator;

import com.schmapty.guvnah.model.expression.Expression;

/**
 * Created by timm on 1/31/14.
 */
public abstract class WrapperExpression implements Expression {

    private Expression expression;

    public WrapperExpression(Expression expression) {
         this.expression=expression;
    }

    public WrapperExpression() {}

    public void setExpression(Expression expression) {
        this.expression=expression;
    }

    public Expression getExpression() {
        return  expression;
    }

}
