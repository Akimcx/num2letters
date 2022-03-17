package num2letter;

import java.util.Scanner;

public class Numbers2Letters {

	final private static String[] simpleNumbers = {
			"zéro", "un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf", "dix", "onze", "douze", "treize", "quatorze", "quinze", "seize", "vingt", "trente", "quarante",
			"cinquante", "soixante", "cent", "mille"
	};
	
	public static void main(String[] args) {
		
		for (int i = 0; i <= 999; i++) {
			System.out.printf("%d: %s\n", i, convert(i));
		}
		System.exit(0);
		
		Scanner keyb = new Scanner(System.in);
		System.out.print("Please provide your number (0-9): ");
		
		int userInput = 0;
		userInput = keyb.nextInt(10);

		String result = convert(userInput);
		
		System.out.printf("%d: %s\n", userInput, result);
		
		keyb.close();
	}

	private static String convert(int number) {
		if (isSimpleNumber(number)) return parseSimpleNumber(number);
		return parseCompoundNumber(number);
	}
	
	private static String parseCompoundNumber(int number) {
		if (number < 100) return lessThan100(number);
		int theUndred = number / 100;
		int theRest = number - (theUndred * 100);
		if (theRest == 0) {
			return parseSimpleNumber(theUndred) + " cents";
		}
		return parseSimpleNumber(theUndred) + " cent " + convert(theRest);
	}
	
	private static String lessThan100(int number) {
		int theThen = (number / 10) * 10;
		int theUnit = number - theThen;
		String link = theUnit == 1 ? " et " : "-";
		try {
			return parseSimpleNumber(theThen)+link+parseSimpleNumber(theUnit);			
		} catch (IllegalArgumentException e) {
			return switch (theThen) {
				case 70 -> handleThe70s(number); 
				case 80 -> handleThe80s(number); 
				case 90 -> handleThe90s(number);
				default -> throw new IllegalArgumentException("Unexpected value: " + theThen); 
			};
		}
	}
	
	private static String handleThe70s(int number) {
		int theUnit = number - 70;
		int theThen = 60;
		String link = theUnit == 1 ? " et " : "-";
		return parseSimpleNumber(theThen)+link+convert(theUnit+10);
	}
	
	private static String handleThe80s(int number) {
		int theUnit = number - 80;
		String theThen = "quatre-vingt";
		if (theUnit == 0) return theThen+"s";
		return theThen+"-"+convert(theUnit);
	}
	
	private static String handleThe90s(int number) {
		int theUnit = number - 90;
		String theThen = "quatre-vingt";
		return theThen+"-"+convert(theUnit+10);	
	}
	
	
	private static boolean isSimpleNumber(int number) {
		return switch (number) {
			case 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,20,30,40,50,60,100,1000 ->  true;
			default ->  false;
		};
	}
	
	private static String parseSimpleNumber(int number) {
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
