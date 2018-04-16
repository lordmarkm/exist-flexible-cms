package com.mynt.core.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mark Baldwin B. Martinez on 21 Nov 2016
 */
public class BigDecimalUtil {
    public static final int PRECISION = 10;
    private static final Logger LOG = LoggerFactory.getLogger(BigDecimalUtil.class);

    public static BigDecimal tryParse(String string) {
        try {
            return new BigDecimal(string);
        } catch (NumberFormatException e) {
            LOG.error("Error parsing string! returning 0. str=" + string, e);
            return BigDecimal.ZERO;
        }
    }

    //Addition
    public static BigDecimal add(BigDecimal... members) {
        return Arrays.asList(members)
                .stream()
                .filter(member -> null != member)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Average - this will break when one of the members is null!!!
     * @param members
     * @return
     */
    public static BigDecimal average(BigDecimal... members) {
        return average(Arrays.asList(members));
    }
    public static BigDecimal average(List<BigDecimal> members) {
        List<BigDecimal> filtered = members.stream()
                .filter(member -> null != member).collect(Collectors.toList());

        return divide(filtered.stream().reduce(BigDecimal.ZERO, BigDecimal::add), filtered.size());
    }

    //Multiplication
    public static BigDecimal multiply(BigDecimal multiplicand, int multiplier) {
        return multiply(multiplicand, new BigDecimal(multiplier));
    }
    public static BigDecimal multiply(BigDecimal... factors) {
        return Arrays.asList(factors).stream().reduce(BigDecimal.ONE, BigDecimalUtil::multiply);
    }
    public static BigDecimal multiply(BigDecimal multiplicand, BigDecimal multiplier) {
        return multiplicand.multiply(multiplier).setScale(PRECISION, RoundingMode.HALF_UP);
    }

    //Division
    public static BigDecimal divide(BigDecimal dividend, int divisor) {
        return divide(dividend, new BigDecimal(divisor));
    }
    public static BigDecimal divide(int dividend, BigDecimal divisor) {
        return divide(new BigDecimal(dividend), divisor);
    }
    public static BigDecimal divide(int dividend, int divisor) {
        return divide(new BigDecimal(dividend), new BigDecimal(divisor));
    }
    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
        return divide(dividend, divisor, PRECISION);
    }
    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor, int precision) {
        return dividend.divide(divisor, precision, RoundingMode.HALF_UP);
    }

    //Subtraction
    public static BigDecimal subtract(BigDecimal minuend, BigDecimal subtrahend) {
        if (null == subtrahend) {
            return minuend;
        }
        return minuend.subtract(subtrahend).setScale(PRECISION, RoundingMode.HALF_UP);
    }

    //Comparison
    public static boolean lt(BigDecimal comparate1, BigDecimal comparate2) {
        if (null == comparate1 || null == comparate2) {
            throw new IllegalArgumentException();
        }
        return comparate1.compareTo(comparate2) < 0;
    }
    public static boolean gt(BigDecimal comparate1, BigDecimal comparate2) {
        if (null == comparate1 || null == comparate2) {
            throw new IllegalArgumentException();
        }
        return comparate1.compareTo(comparate2) > 0;
    }
    public static boolean gtoe(BigDecimal comparate1, BigDecimal comparate2) {
        if (null == comparate1 || null == comparate2) {
            throw new IllegalArgumentException();
        }
        return comparate1.compareTo(comparate2) >= 0;
    }
    public static boolean equal(BigDecimal comparate1, BigDecimal comparate2) {
        return null != comparate1 && null != comparate2 && comparate1.compareTo(comparate2) == 0;
    }
    public static boolean notEqual(BigDecimal comparate1, BigDecimal comparate2) {
        return null != comparate1 && null != comparate2 && comparate1.compareTo(comparate2) != 0;
    }
    public static boolean isZero(BigDecimal comparate) {
        return equal(comparate, BigDecimal.ZERO);
    }
    public static boolean notZero(BigDecimal comparate) {
        return notEqual(comparate, BigDecimal.ZERO);
    }

    public static Object pretty(Object bd) {
        if (null == bd) {
            return "Not present";
        }
        if (BigDecimal.class.isAssignableFrom(bd.getClass())) {
            return String.format("%,.2f", ((BigDecimal) bd).setScale(2, RoundingMode.HALF_UP));
        }
        return bd;
    }

}