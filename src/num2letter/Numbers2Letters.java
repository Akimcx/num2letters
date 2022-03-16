package num2letter;

import java.util.Scanner;

public class Numbers2Letters {

	final private static String[] simpleNumbers = {
			"zéro", "un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf", "dix", "onze", "douze", "treize", "quatorze", "quinze", "seize", "vingt", "trente", "quarante",
			"cinquante", "soixante", "cent", "mille"
	};
	
	public static void main(String[] args) {
		Scanner keyb = new Scanner(System.in);
		System.out.print("Please provide your number (0-9): ");
		
		int userInput = 0;
		userInput = keyb.nextInt(10);

		String result = convert(userInput);
		
		System.out.printf("%d: %s\n", userInput, result);
		
		keyb.close();
	}

	private static String convert(int number) {
		if (isSimpleNumber(number)) return toSimpleNumber(number);
		return "";
	}
	
	private static boolean isSimpleNumber(int number) {
		return switch (number) {
			case 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,20,30,40,50,60,100,1000 ->  true;
			default ->  false;
		};
	}
	
	private static String toSimpleNumber(int number) {
		return switch (number) {
			case 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16 ->  simpleNumbers[number];
			case 20 -> simpleNumbers[17];
			case 30 -> simpleNumbers[18];
			case 40 -> simpleNumbers[19];
			case 50 -> simpleNumbers[20];
			case 60 -> simpleNumbers[21];
			case 100 -> simpleNumbers[22];
			case 1000 -> simpleNumbers[23];
			default -> throw new IllegalArgumentException("Unexpected value: " + number);
		};
	}
}
