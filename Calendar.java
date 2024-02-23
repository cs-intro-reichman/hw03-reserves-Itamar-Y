
public class Calendar {

    static int dayOfMonth = 1;   
	static int month = 1;
	static int year = 1900;
	static int dayOfWeek = 2;     // 1.1.1900 was a Monday
	static int nDaysInMonth = 31;

	public static void main(String[] args) {
		int inputYear = Integer.parseInt(args[0]);

        while (year != inputYear) {
            advance();
			if (year == year) {System.out.println(year);
        }
    }
}
	
	private static void advance() {
        int nDaysInMonth = nDaysInMonth(year);

        for (; month <= 12; month++) {
            for (dayOfMonth = 1; dayOfMonth <= nDaysInMonth; dayOfMonth++) {
                System.out.printf("%d/%d/%d - %s%n", dayOfMonth, month, year);

                if (dayOfWeek == 1) {
                    System.out.printf("%d/%d/%d Sunday%n", dayOfMonth, month, year);
                }
            }
        }
        year++;
    }
    
	
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
	
		private static int nDaysInMonth(int year) {
			int days =0;

	
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
						break;
					}
					default: ;
						break; 
				}
			}
			return days;
		}
	}


