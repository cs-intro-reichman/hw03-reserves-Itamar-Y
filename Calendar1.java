/** 
 * Prints the calendars of all the years in the 20th century.
 */
public class Calendar1 {	
    // Starting the calendar on 1/1/1900
	static int dayOfMonth = 1;   
	static int month = 1;
	static int year = 1900;
	static int dayOfWeek = 2;     // 1.1.1900 was a Monday
	static int nDaysInMonth = 31; // Number of days in January
	
	/** 
	 * Prints the calendars of all the years in the 20th century. Also prints the  
	 * number of Sundays that occured on the first day of the month during this period.
	 */
	public static void main(String args[]) {
		
	int debugDaysCounter = 0;
	while (year <= 1999) {
		advance();
		debugDaysCounter++;

		// if (debugDaysCounter == 365) {
		// 	break;
		// }
		}
	}
	
	 // Advances the date (day, month, year) and the day-of-the-week.
	 // If the month changes, sets the number of days in this month.
	 // Side effects: changes the static variables dayOfMonth, month, year, dayOfWeek, nDaysInMonth.
	 private static void advance() {
		int SpecialSundays = 0;
		for (; year <= 1999; year++) {

            for (month = 1; month <= 12; month++) {
                nDaysInMonth = nDaysInMonth(year);
				
                for (dayOfMonth = 1; dayOfMonth <= nDaysInMonth; dayOfMonth++) {
                    System.out.printf("%d/%d/%d%n", dayOfMonth, month, year);
                    dayOfWeek++; 

                    if (dayOfWeek == 8) {
                        dayOfWeek = 1; // Reset dayOfWeek to 1 for a new week
                    }

                    if (dayOfWeek == 1) {
                        System.out.printf("%d/%d/%d Sunday%n", dayOfMonth, month, year);
					}
					if (dayOfMonth == 1 && dayOfWeek == 1) {
							SpecialSundays++;
					}
                    }}
                	
		}	System.out.println("During the 20th century, " + SpecialSundays +" Sundays fell on the first day of the month");
    } 


	 
		 
    // Returns true if the given year is a leap year, false otherwise.
	private static boolean isLeapYear(int year) {
	    boolean isLeapYear = false;
		if (year % 4 == 0) {
			if (year % 100 != 0) {
				isLeapYear = true;
			} else {
				if (year % 400 == 0) {
					isLeapYear = true;}
		} }
		return isLeapYear;
	}
	 
	// Returns the number of days in the given month and year.
	// April, June, September, and November have 30 days each.
	// February has 28 days in a common year, and 29 days in a leap year.
	// All the other months have 31 days.
	private static int nDaysInMonth(int year) {
		int days = 0;

		for (int month = 1; month <= 12; month++){
			
			switch (month) {
				case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				days = 31;
					break;

				case 4: case 6: case 9: case 11:
				days = 30;
					break;
				
				case 2:
				if (isLeapYear(year)) {
					days = 29;
				} else {
					days = 28;
				}
			
				default: ;
					break; 
			}
		}
		
		return days;
	}
}