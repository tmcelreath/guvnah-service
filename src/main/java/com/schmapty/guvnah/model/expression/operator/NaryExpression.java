package com.schmapty.guvnah.model.expression.operator;

import com.schmapty.guvnah.model.expression.Expression;

import java.util.List;

/**
 * Created by timm on 1/31/14.
 */
public abstract class NaryExpression implements Expression {

    private List<Expression> expressions;

    public void setExpressions(List<Expression> expressions) {
        this.expressions=expressions;
    }
    public List<Expression> getExpressions()  {
        return this.expressions;
    }

}
