package be.ipl.projet_ejb.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Util {
	public static void checkObject(Object o) {
		if (o == null)
			throw new NullPointerException();
	}

	public static void checkString(String s) {
		checkObject(s);
		if (s.trim().equals(""))
			throw new IllegalArgumentException();
	}

	public static void checkNegativeOrZero(double d) {
		if (d > 0.0)
			throw new IllegalArgumentException();
	}

	public static void checkPositiveOrZero(double d) {
		if (d < 0)
			throw new IllegalArgumentException();
	}

	public static void checkPositive(double d) {
		if (d <= 0.00001)
			throw new IllegalArgumentException();
	}

	public static void checkPositive(int i) {
		if (i <= 0)
			throw new IllegalArgumentException();
	}

	public static String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(input.getBytes());

			byte byteData[] = md.digest();

			// convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
