package com.schmapty.guvnah.model.expression.check;

import com.schmapty.guvnah.model.expression.Expression;
import com.schmapty.guvnah.model.expression.ExpressionParameter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

import java.util.Properties;

public abstract class Check implements Expression, InitializingBean {

    private String description;

    @Resource
    @Qualifier(value="checkProperties")
    private Properties checkProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.description = checkProperties.getProperty(this.getClass().getName() + ".desc") ;
        if(description==null) {
            description = this.getClass().getName();
        }
    }

    @Override
    public boolean execute(ExpressionParameter<?>... parameters) {
        Element element = (Element)parameters[0].get();
        Document document = (Document)parameters[1].get();
        return execute(element, document);
    }

    public abstract boolean execute(Element element, Document document);

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description=description;
    }
}
