package com.mynt.core.jpa.rql;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;

import java.util.List;

import org.joda.time.DateTime;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;

/**
 * @author Mark Martinez, created Feb 14, 2016
 */
public class DateTimePathExpressionEvaluator extends AbstractExpressionEvaluator {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public BooleanExpression evaluate(Path<?> path, ComparisonOperator operator, List<String> arguments) {
        DateTimePath e = (DateTimePath) path;

        List<DateTime> argsOfExpectedType = cast(e.getType(), arguments);
        switch (operator.getSymbol()) {
            case "==":
                DateTime c = argsOfExpectedType.get(0);
                return e.after(c).and(e.before(c.plusDays(1)));
                //return e.eq(argsOfExpectedType.get(0));
            case "!=":
                return e.ne(argsOfExpectedType.get(0));
            case "=gt=":
            case ">":
                return e.after((Comparable) argsOfExpectedType.get(0));
            case "=ge=":
            case ">=":
                return e.goe((Comparable) argsOfExpectedType.get(0));
            case "=lt=":
            case "<":
                return e.before((Comparable) argsOfExpectedType.get(0));
            case "=le=":
            case "<=":
                return e.loe((Comparable) argsOfExpectedType.get(0));
            case "=in=":
                return e.in(argsOfExpectedType);
            default:
                throw new IllegalArgumentException("Operator not supported: " + operator);
        }
    }

}
