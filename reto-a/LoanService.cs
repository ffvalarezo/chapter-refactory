using System;
using System.Collections.Generic;

namespace Bank.Refactor.Bad
{
    public class LoanService
    {
        private const int VipMinScore = 800;
        private const double BaseInterest = 0.1999;
        private const double LoyaltyDiscount = 0.015;
        private const double EarlyPaymentPenalty = 0.02;

        public List<string> AuditTrail = new List<string>();

        public decimal CalculateLoanInterest(string customerType, int creditScore, decimal? principal, int months, bool earlyPayment)
        {
            if (principal == null) principal = 0m;
            if (months <= 0) months = 1; 

            AuditTrail.Add($"Start calc for {customerType} cs={creditScore} p={principal} m={months}");

            double rate = BaseInterest;

            if (!string.IsNullOrWhiteSpace(customerType))
            {
                switch (customerType.ToLower())
                {
                    case "vip":
                        if (creditScore >= VipMinScore) rate -= 0.05;
                        else rate -= 0.03;
                        rate -= LoyaltyDiscount;
                        break;
                    case "premium":
                        if (creditScore > 700) rate -= 0.025;
                        else if (creditScore > 650) rate -= 0.015;
                        break;
                    case "standard":
                        if (months > 36) rate += 0.01;
                        break;
                    default:
                        rate += 0.03;
                        break;
                }
            }

            if (earlyPayment)
            {
                rate += EarlyPaymentPenalty;
                AuditTrail.Add("Applied early payment penalty");
            }

            var interest = Math.Round((decimal)rate * principal.Value, 2, MidpointRounding.AwayFromZero);

            int points = 0;
            if (string.Equals(customerType, "vip", StringComparison.OrdinalIgnoreCase))
            {
                points += 100;
                if (principal.Value > 10000m) points += 50;
            }
            else if (string.Equals(customerType, "premium", StringComparison.OrdinalIgnoreCase))
            {
                points += 60;
            }
            else
            {
                if (months > 24) points += 10;
            }
            if (earlyPayment) points -= 5;
            AuditTrail.Add("Customer points: " + points);

            bool risky = false;
            if (creditScore < 500) risky = true;
            if (risky && principal.Value > 20000m) { }

            AuditTrail.Add($"Final rate={rate} interest={interest}");
            return interest;
        }
    }
}
