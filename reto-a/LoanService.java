package com.bank.refactor.bad;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class LoanService {

    private static final int VIP_MIN_SCORE = 800;
    private static final double BASE_INTEREST = 0.1999;
    private static final double LOYALTY_DISCOUNT = 0.015;
    private static final double EARLY_PAYMENT_PENALTY = 0.02;

    public List<String> auditTrail = new ArrayList<>();

    public BigDecimal calculateLoanInterest(String customerType,
                                            int creditScore,
                                            BigDecimal principal,
                                            int months,
                                            boolean earlyPayment) {

        if (principal == null) principal = new BigDecimal("0");
        if (months <= 0) months = 1;

        auditTrail.add("Start calc for " + customerType + " cs=" + creditScore + " p=" + principal + " m=" + months);

        double rate = BASE_INTEREST;

        if (customerType != null) {
            switch (customerType.toLowerCase()) {
                case "vip":
                    if (creditScore >= VIP_MIN_SCORE) {
                        rate = rate - 0.05; 
                    } else {
                        rate = rate - 0.03;
                    }
                    rate = rate - LOYALTY_DISCOUNT;
                    break;
                case "premium":
                    if (creditScore > 700) {
                        rate = rate - 0.025;
                    } else if (creditScore > 650) {
                        rate = rate - 0.015;
                    } else {
                    }
                    break;
                case "standard":
                    if (months > 36) {
                        rate = rate + 0.01;
                    }
                    break;
                default:
                    rate = rate + 0.03;
            }
        }

        if (earlyPayment) {
            rate = rate + EARLY_PAYMENT_PENALTY;
            auditTrail.add("Applied early payment penalty");
        }

        BigDecimal interest = principal.multiply(BigDecimal.valueOf(rate));
        interest = interest.setScale(2, RoundingMode.HALF_UP);

        int points = 0;
        if ("vip".equalsIgnoreCase(customerType)) {
            points += 100;
            if (principal.compareTo(new BigDecimal("10000")) > 0) {
                points += 50;
            }
        } else if ("premium".equalsIgnoreCase(customerType)) {
            points += 60;
        } else {
            if (months > 24) points += 10;
        }
        if (earlyPayment) points -= 5;
        auditTrail.add("Customer points: " + points);

        boolean risky = false;
        if (creditScore < 500) {
            risky = true;
        }
        if (risky && principal.doubleValue() > 20000) {
        }

        auditTrail.add("Final rate=" + rate + " interest=" + interest);
        return interest;
    }

    public int calculateCustomerPoints(String customerType, BigDecimal principal, int months, boolean earlyPayment) {
        int p = 0;
        if ("vip".equalsIgnoreCase(customerType)) {
            p += 100;
            if (principal != null && principal.compareTo(new BigDecimal("10000")) > 0) {
                p += 50;
            }
        } else if ("premium".equalsIgnoreCase(customerType)) {
            p += 60;
        } else {
            if (months > 24) p += 10;
        }
        if (earlyPayment) p -= 5;
        return p;
    }
}