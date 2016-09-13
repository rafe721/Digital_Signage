package com.handysan.digitalsignage.Verification;

import java.io.*;

public class KeyResolver {

	public String hashInteger(String ipAddress) {
		StringBuilder aStringBuilder = new StringBuilder();
		for (String aString : ipAddress.split("\\.")) {
			int aNumber = Integer.parseInt(aString);
			if (aNumber > 255) {
				aStringBuilder.append("ErrorMessage");
			} else {
				int firstTwoDigits = aNumber / 10;
				int thirdDigit = aNumber % 10;
				aStringBuilder.append(this.getFirstCharacter(firstTwoDigits));
				aStringBuilder.append(this.getSecondCharacter(thirdDigit ,firstTwoDigits));
			}
		}
		// System.out.println(aStringBuilder.toString());
		
		return aStringBuilder.toString();
	}

	private String getFirstCharacter(int firstNumber) {
		switch (firstNumber) {
			case 1:
				return "B";
			case 2:
				return "C";
			case 3:
				return "D";
			case 4:
				return "E";
			case 5:
				return "F";
			case 6:
				return "G";
			case 7:
				return "H";
			case 8:
				return "I";
			case 9:
				return "J";
			case 10:
				return "K";
			case 11:
				return "L";
			case 12:
				return "M";
			case 13:
				return "N";
			case 14:
				return "O";
			case 15:
				return "P";
			case 16:
				return "0";
			case 17:
				return "9";
			case 18:
				return "8";
			case 19:
				return "7";
			case 20:
				return "6";
			case 21:
				return "5";
			case 22:
				return "4";
			case 23:
				return "3";
			case 24:
				return "2";
			case 25:
				return "1";
		}
		// In case the number is 0
		return "A";
	}

	private String getSecondCharacter(int secondNumber, int firstNumber) {
		if (firstNumber > 15) {
			switch (secondNumber) {
				case 1:
					return "Y";
				case 2:
					return "X";
				case 3:
					return "W";
				case 4:
					return "V";
				case 5:
					return "U";
				case 6:
					return "T";
				case 7:
					return "S";
				case 8:
					return "R";
				case 9:
					return "Q";
			}
			// In case the number is 0
			return "Z";
		} else {
			switch (secondNumber) {
				case 1:
					return "8";
				case 2:
					return "7";
				case 3:
					return "6";
				case 4:
					return "5";
				case 5:
					return "4";
				case 6:
					return "3";
				case 7:
					return "2";
				case 8:
					return "1";
				case 9:
					return "0";
			}
			// In case the number is 0
			return "9";
		}
	}

	public static void main(String[] args) {
		KeyResolver keyGen = new KeyResolver();
		KeyDecipher keyDecipher = new KeyDecipher();
		String IP1 = "236:123:112:145";
		String IP2 = "132:123:182:250";
		String code1 = keyGen.hashInteger(IP2);
		System.out.println("Key Generated for IP1: " + code1);
		String genIP = keyDecipher.resolveIP(code1);
		System.out.println("IP Generated from IP1's code: " + genIP);
		if (genIP.equalsIgnoreCase(IP2)) {
			System.out.println("Both IPs are the same.");
		} else {
			System.out.println("Both IPs are different.");
		}
	}
}