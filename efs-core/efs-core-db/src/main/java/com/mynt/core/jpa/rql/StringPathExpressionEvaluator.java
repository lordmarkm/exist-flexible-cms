package com.mynt.core.jpa.rql;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;

import java.util.List;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringExpression;

public class StringPathExpressionEvaluator extends AbstractExpressionEvaluator {

    @SuppressWarnings("unchecked")
    public BooleanExpression evaluate(Path<?> path, ComparisonOperator operator, List<String> arguments) {
        StringExpression e = (StringExpression) path;
        List<String> argsOfExpectedType = cast(e.getType(), arguments);
        switch (operator.getSymbol()) {
        case "==":
            String term = argsOfExpectedType.get(0);
            if (term.startsWith("*")) {
                term = term.substring(1, term.length());
                return e.endsWithIgnoreCase(term);
            } else if (term.endsWith("*")) {
                term = term.substring(0, term.length() - 1);
                return e.startsWithIgnoreCase(term);
            } else {
                return e.equalsIgnoreCase(term);
            }
        case "!=":
            return e.ne(argsOfExpectedType.get(0));
        case "=in=":
            return e.in(argsOfExpectedType);
        case "=out=":
            return e.notIn(argsOfExpectedType);
        case "=like=":
            return e.containsIgnoreCase(argsOfExpectedType.get(0));
        default:
            throw new IllegalArgumentException("Operator not supported: " + operator);
        }
    }

}