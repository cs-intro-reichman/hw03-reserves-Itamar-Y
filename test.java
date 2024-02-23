/**
* Computes the periodical payment necessary to re-pay a given loan.
*/
public class Test {
	
	static double epsilon = 0.001;  // The computation tolerance (estimation error)
	static int iterationCounter;    // Monitors the efficiency of the calculation
	
    /** 
     * Gets the loan data and computes the periodical payment.
     * Expects to get three command-line arguments: sum of the loan (double),
     * interest rate (double, as a percentage), and number of payments (int).  
     */
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan sum = " + loan + ", interest rate = " + rate + "%, periods = " + n);
		
		// Computes the periodical payment using brute force search
		System.out.print("Periodical payment, using brute force: ");
		System.out.printf("%.2f", bruteForceSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("Periodical payment, using bi-section search: ");
		System.out.printf("%.2f", bisectionSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);
	}
	
	/**
	* Uses a sequential search method  ("brute force") to compute an approximation
	* of the periodical payment that will bring the ending balance of a loan close to 0.
	* Given: the sum of the loan, the periodical interest rate (as a percentage),
	* the number of periods (n), and epsilon, a tolerance level.
	*/
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {  
        iterationCounter = 0;
        double periodicalPayment = 0.0;
        double endBalance = endBalance(loan, rate, n, periodicalPayment);
        
        while (Math.abs(endBalance) > epsilon) {
            periodicalPayment += epsilon;  // Increment the payment by epsilon
            endBalance = endBalance(loan, rate, n, periodicalPayment);
            iterationCounter++;
        }

        return periodicalPayment;
    }
    
    /**
	* Uses bisection search to compute an approximation of the periodical payment 
	* that will bring the ending balance of a loan close to 0.
	* Given: the sum of the loan, the periodical interest rate (as a percentage),
	* the number of periods (n), and epsilon, a tolerance level.
	*/
	// Side effect: modifies the class variable iterationCounter.
    // public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
    //     iterationCounter = 0;
    //     double low = 0.0;
    //     double high = loan * (1 + rate / 12.0);
    //     double periodicalPayment = (low + high) / 2;
    //     double endBalance = endBalance(loan, rate, n, periodicalPayment);
    
    //     // Check if the end balance changes sign between lo and hi
    //     if (Math.signum(endBalance(loan, rate, n, low)) == Math.signum(endBalance(loan, rate, n, high))) {
    //         return -1; // Return -1 to indicate that bisection search failed
    //     }
    
    //     while ((high - low) > epsilon) {
    //         if (endBalance(loan, rate, n, periodicalPayment) * endBalance(loan, rate, n, high) > 0) {
    //             high = periodicalPayment;
    //         } else {
    //             low = periodicalPayment;
    //         }
    
    //         periodicalPayment = (low + high) / 2;
    //         endBalance = endBalance(loan, rate, n, periodicalPayment);
    //         iterationCounter++;
    //     }
    
    //     return periodicalPayment;
    // }

    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double lo = 0.0;
        double hi = loan * (1 + rate / 12.0);
        double periodicalPayment = (lo + hi) / 2;
        double endBalanceLo = endBalance(loan, rate, n, lo);
        double endBalanceHi = endBalance(loan, rate, n, hi);
    
        // Check if the end balance changes sign between lo and hi
        if (endBalanceLo * endBalanceHi >= 0) {
            return -1; // Return -1 to indicate that bisection search failed
        }
    
        while ((hi - lo) > epsilon) {
            double endBalancePeriodical = endBalance(loan, rate, n, periodicalPayment);
    
            if (endBalancePeriodical * endBalanceHi > 0) {
                hi = periodicalPayment;
            } else {
                lo = periodicalPayment;
            }
    
            periodicalPayment = (lo + hi) / 2;
            iterationCounter++;
        }
    
        return periodicalPayment;
    }
	
	/**
	* Computes the ending balance of a loan, given the sum of the loan, the periodical
	* interest rate (as a percentage), the number of periods (n), and the periodical payment.
	*/
	private static double endBalance(double loan, double rate, int n, double payment) {
		double monthlyRate = rate / 12.0 / 100.0;
        double balance = loan;

        for (int i = 0; i < n; i++) {
            balance -= payment;
            balance += balance * monthlyRate;
        }

        return balance;
	}
}
