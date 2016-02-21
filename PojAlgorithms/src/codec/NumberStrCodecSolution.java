package codec;

import java.math.BigInteger;
import java.util.Scanner;

public class NumberStrCodecSolution {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int length = 0;
		String code = "";
		
		while ((length = scanner.nextInt()) != 0) { 
			scanner.nextLine();
			code = scanner.nextLine();
			// int number = 0;
			BigInteger number = new BigInteger(new byte[] { 0 });
			char[] chars = new char[26];
			for (int i = 0; i < 26; i++) {
				chars[i] = 0;
			}
			
			for (int i = 0; i < length; i++) {
				char base = 'A';
				boolean foundBase = false;
				int biggerThanBase= 0;
				for (int j = 0; j < 26; j++) { 
					if (chars[j] == 0) {
						if (!foundBase) {
							base += j;
							foundBase = true;
							continue;	
						}
					} else if (chars[j] == 1) {
						if (foundBase) {
							if (j+ 'A' < code.charAt(i)) {
								biggerThanBase++;	
							}
						}
					}
				}
				
				number = number.add( new BigInteger(new byte[]  {(byte)(code.charAt(i) - base - biggerThanBase)}).multiply(pow(i, length)));
				chars[code.charAt(i) - 'A'] = 1;
			}
			System.out.println(number);
		}
	}
	
	public static BigInteger pow(int i, int length) {
		int dis = 26 - i;
		BigInteger number = new BigInteger(new byte[] {1});
		i++;
		dis--;
		for (; i < length; i++) {
			number = number.multiply(new BigInteger(new byte[] { (byte) dis }));
			dis --;
		}
		
		return number;
	}
}
