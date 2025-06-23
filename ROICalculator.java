package calculator;

public class ROICalculator {
    public static void main(String[] args) {
        double grossAnnual = 85000;
        double loanTotal = 120000;
        double interestRate = 0.0466;

        double monthlyRetirement = grossAnnual * 0.05 / 12;
        double monthlyHealthCare = 340;
        double familyLeave = 20;
        double medicalLeave = 20;
        double disability = 20;

        double AGI = grossAnnual - (monthlyRetirement * 12) - (monthlyHealthCare * 12);
        double monthlyAGI = AGI / 12;

        double taxable = grossAnnual / 12 - monthlyRetirement - monthlyHealthCare;
        double tax = taxable * 0.22;
        double socialSecurity = grossAnnual * 0.062 / 12;
        double medicare = grossAnnual * 0.0145 / 12;

        double takeHome = grossAnnual / 12 - monthlyRetirement - monthlyHealthCare
                - familyLeave - medicalLeave - disability - tax - socialSecurity - medicare;

        double loanPayment = 0.15 * monthlyAGI;
        double yearsToPay = loanTotal / (loanPayment * 12);

        double basicLiving = 1500;
        double leftover = takeHome - basicLiving;
        double fastestYears = loanTotal / (leftover * 12);

        System.out.printf("Gross Annual Salary: $%.2f\n", grossAnnual);
        System.out.printf("Adjusted Gross Income (AGI): $%.2f\n", AGI);
        System.out.printf("Monthly Take-Home Pay: $%.2f\n", takeHome);
        System.out.printf("Monthly Loan Payment (15%% AGI): $%.2f\n", loanPayment);
        System.out.printf("Estimated Years to Pay Loan: %.2f\n", yearsToPay);
        System.out.printf("Fastest Years to Pay (w/ frugal budget): %.2f\n", fastestYears);

        if (yearsToPay <= 7) {
            System.out.println("[OK] Good ROI: Payoff within 5–7 years");
        } else {
            System.out.println("[Warning] ROI Risk: Consider reducing loan or increasing income");
        }
    }
}