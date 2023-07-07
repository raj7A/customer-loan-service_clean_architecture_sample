package com.cc.customer.loan.service.entities;

import com.cc.customer.loan.service.entities.enums.LoanType;

import java.util.HashMap;
import java.util.Map;

public class LoanFactory {

    private static final Map<LoanType, QuadFunction<String, Integer, Double, Double, Loan>> LOAN_TYPE_QUAD_FUNCTION_MAP = new HashMap<>();

    static {
        LOAN_TYPE_QUAD_FUNCTION_MAP.put(LoanType.CAR, CarLoan::new);
        LOAN_TYPE_QUAD_FUNCTION_MAP.put(LoanType.HOUSING, HomeLoan::new);
    }

    public static Loan createLoan(LoanType loanType, String customerId, Integer period, Double principle, Double initialDiscountAmount) {
        return LOAN_TYPE_QUAD_FUNCTION_MAP.get(loanType).apply(customerId, period, principle, initialDiscountAmount);
    }

    @FunctionalInterface
    private interface QuadFunction<S, T, U, V, R> {
        R apply(S s, T t, U u, V v);
    }
}
