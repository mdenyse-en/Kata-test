package main.pack;

import java.util.Scanner;
import java.util.HashMap;

public class Main {
	private static HashMap<String, Integer> roman = new HashMap<String, Integer>();
	public static void main(String[] args){		
		roman.put("I", 1);
		roman.put("II", 2);
		roman.put("III", 3);
		roman.put("IV", 4);
		roman.put("V", 5);
		roman.put("VI", 6);
		roman.put("VII", 7);
		roman.put("VIII", 8);
		roman.put("IX", 9);
		roman.put("X", 10);
		
		Scanner scanner = new Scanner(System.in);
		try {	
			while (true) {
				System.out.println("Input:");
				System.out.printf("\nOutput:\n%s \n\n", calc(scanner.nextLine().trim()));
			}
		}
		catch (Exception ex) {
			System.out.println("\nOutput:");
			System.out.println(ex.getMessage());
		}
		scanner.close();
	}
	
	public static String calc(String input) throws Exception{
		String[] parts = input.split(" ");
		if (parts.length != 3 || 
				!(parts[1].equals("+") || 
				parts[1].equals("-") || 
				parts[1].equals("/") || 
				parts[1].equals("*"))) {
			throw new Exception("throws Exception");
		}
		
		if (roman.containsKey(parts[0])) {
			if (!roman.containsKey(parts[2])) throw new Exception("throws Exception");
			int result = calculate(roman.get(parts[0]), roman.get(parts[2]), parts[1]);
			if (result < 1) throw new Exception("throws Exception");
			return (toRoman(result));
		}
		else {
			try {
				int a = Integer.valueOf(parts[0]);
				int b = Integer.valueOf(parts[2]);
				if (a > 10 || a < 0 || b > 10 || b < 0) throw new Exception ("throws Exception");
				int result = calculate(a, b, parts[1]);
				return (Integer.toString(result));
			}
			catch (Exception ex) {
				throw new Exception("throws Exception");
			}
			
		}
	}
	
	private static String toRoman(int a) {
		String result = new String();
		
		int count = a / 100; 
		a %= 100;
		
		for (int i = 0; i < count; ++i) {
			result += "C";
		}
		count = a / 50;
		a %= 50;
		
		for (int i = 0; i < count; ++i) {
			result += "L";
		}
		
		count = a / 10;
		a %= 10;
		
		for (int i = 0; i < count; ++i) {
			result += "X";
		}
		
		if (a > 0) {
			for (HashMap.Entry<String, Integer> entry: roman.entrySet()) {
				if (entry.getValue() == a) {
					result += entry.getKey();
					break;
				}	
			}
		}
		
		return result;
	}
	
	private static int calculate(int a, int b, String val){
		switch (val) {
			case "+":
				return a + b;
			case "-":
				return a - b;
			case "*":
				return a * b;
			case "/":
				return a / b;
		}
		return -1000;
	}
}
