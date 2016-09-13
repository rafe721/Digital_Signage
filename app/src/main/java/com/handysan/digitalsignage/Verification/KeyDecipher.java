package com.handysan.digitalsignage.Verification;

import java.io.*;

public class KeyDecipher {

	public String resolveIP(String codeText) {
		StringBuilder aStringBuilder = new StringBuilder();
		String[] codes = codeText.split("");
		if ( codes.length > 8 ) {
			System.out.println("Oh yeah!!");
		}
		for (int i = 0; i < codes.length; i++) {
			if ((i+1)%2 == 1) {
				aStringBuilder.append(this.decodeOddLetter(codes[i]));
				// System.out.println("String: " + this.decodeOddLetter(codes[i]));
			} else {
				aStringBuilder.append(this.decodeEvenLetter(codes[i]));
				if ((i+1) != codes.length) {
					aStringBuilder.append(":");
				}
				//System.out.println("String: " + this.decodeEvenLetter(codes[i]));
			}
		}
		
		return aStringBuilder.toString();
	}

	private int decodeOddLetter(String oddCode) {
		switch (oddCode) {
			case "A":
				return 0;
			case "B":
				return 1;
			case "C":
				return 2;
			case "D":
				return 3;
			case "E":
				return 4;
			case "F":
				return 5;
			case "G":
				return 6;
			case "H":
				return 7;
			case "I":
				return 8;
			case "J":
				return 9;
			case "K":
				return 10;
			case "L":
				return 11;
			case "M":
				return 12;
			case "N":
				return 13;
			case "O":
				return 14;
			case "P":
				return 15;
			case "0":
				return 16;
			case "9":
				return 17;
			case "8":
				return 18;
			case "7":
				return 19;
			case "6":
				return 20;
			case "5":
				return 21;
			case "4":
				return 22;
			case "3":
				return 23;
			case "2":
				return 24;
		}
		// In case the code is "1"
		return 25;
	}

	private int decodeEvenLetter(String evenCode) {
		switch (evenCode) {
			case "Z":
				return 0;
			case "Y":
				return 1;
			case "X":
				return 2;
			case "W":
				return 3;
			case "V":
				return 4;
			case "U":
				return 5;
			case "T":
				return 6;
			case "S":
				return 7;
			case "R":
				return 8;
			case "Q":
				return 9;
			case "9":
				return 0;
			case "8":
				return 1;
			case "7":
				return 2;
			case "6":
				return 3;
			case "5":
				return 4;
			case "4":
				return 5;
			case "3":
				return 6;
			case "2":
				return 7;
			case "1":
				return 8;
		}
		// In case the code is "0"
		return 9;
	}

}