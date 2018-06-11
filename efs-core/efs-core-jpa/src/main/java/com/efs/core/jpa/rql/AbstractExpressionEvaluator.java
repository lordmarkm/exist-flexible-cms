package com.efs.core.jpa.rql;

import com.google.common.collect.Lists;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import org.apache.commons.lang3.EnumUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author mbmartinez
 */
public abstract class AbstractExpressionEvaluator {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractExpressionEvaluator.class);
    public abstract BooleanExpression evaluate(Path<?> path, ComparisonOperator operator, List<String> arguments);

    @SuppressWarnings("rawtypes")
    protected List cast(Class<?> type, List<String> string) {
        if (type.isAssignableFrom(String.class)) {
            return string;
        }
        List<Object> converted = Lists.newArrayList();
        for (int i = 0; i < string.size(); i++) {
            converted.add(cast(type, string.get(i)));
        }
        return converted;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected Object cast(Class<?> type, String string) {
        if (type.isAssignableFrom(String.class)) {
            return string;
        } else if (type.isAssignableFrom(Long.class) || type.isAssignableFrom(Integer.class)) {
            return Long.valueOf(string);
        } else if (type.isAssignableFrom(Boolean.class)) {
            return Boolean.valueOf(string);
        } else if (type.isAssignableFrom(DateTime.class)) {
            DateTime date = DateTime.parse(string).withZone(DateTimeZone.UTC);
            LOG.debug("Parsed date/time converted to UTC. string={}, converted={}", string, date);
            return date;
        } else if (type.isAssignableFrom(LocalDateTime.class)) {
            LocalDateTime date = LocalDateTime.parse(string);
            return date;
        } else if (type.isEnum()) {
            return EnumUtils.getEnum((Class) type, string);
        }
        throw new IllegalArgumentException("Selector class not supported. selector class=" + type);
    }
}