package com.example.Util;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.security.MessageDigest;
import java.util.Locale;

public class Md5Utils {
	private static final String TAG = "MD5.";
	private static final boolean DEBUG = true;

	public static final int MD5_MODE_FULL = 1;
	public static final int MD5_MODE_PARTLY = 2;
	private static final int READ_LIMITED = 1024 * 8;

	private static final char HEX_DIGITS_LOWER_CACE[] = { '0', '1', '2', '3',
			'4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static boolean md5FileCheck(String imageFileName) {
		String md5FileName = imageFileName + ".md5";
		String imageMd5 = md5sum(imageFileName);
		String md5FileMessage = getMd5FileMessage(md5FileName);
		if (DEBUG) {
			System.out.println(TAG + "md5FileName=" + md5FileName
					+ "|imageMd5={" + imageMd5 + "}" + "|md5FileMessage={"
					+ md5FileMessage + "}");
		}
		if (imageMd5 != null && md5FileMessage != null) {
			return imageMd5.equals(md5FileMessage);
		}
		return false;
	}

	public static boolean generate(String imageFileName) {
		String md5 = md5sum(imageFileName);
		if (md5 != null) {
			Writer writer;
			try {
				writer = new FileWriter(imageFileName + ".md5");
				writer.write(md5);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		return false;
	}

	private static String getMd5FileMessage(String md5fileName) {
		String md5 = null;
		Reader reader;
		char[] buffer = new char[32];
		StringBuilder md5Builder = new StringBuilder();
		int readCount = 0;
		try {
			reader = new FileReader(md5fileName);
			while ((readCount = reader.read(buffer)) > 0) {
				md5Builder.append(buffer, 0, readCount);
			}
			reader.close();
			md5 = md5Builder.toString().trim().toLowerCase(Locale.getDefault());

			if (md5.length() != 32) {
				System.out.println(TAG + ".md5 file size=" + md5.length()
						+ "|md5 file ={" + md5 + "}md5 code is invalid");
				md5 = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return md5;
	}

	private static String md5sum(String filename) {
		return md5sum(filename, MD5_MODE_PARTLY);
	}

	private static String md5sum(String filename, int mode) {
		InputStream fis;
		byte[] buffer = new byte[1024];
		int readCount = 0;
		int totalCount = 0;
		MessageDigest md5;
		try {
			fis = new FileInputStream(filename);
			md5 = MessageDigest.getInstance("MD5");
			while ((readCount = fis.read(buffer)) > 0) {
				totalCount += readCount;
				if (mode == MD5_MODE_PARTLY && totalCount > READ_LIMITED) {
					break;
				}
				md5.update(buffer, 0, readCount);
			}
			fis.close();
			return toHexString(md5.digest());
		} catch (Exception e) {
			System.out.println("error");
			return null;
		}
	}

	private static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEX_DIGITS_LOWER_CACE[(b[i] & 0xf0) >>> 4]);
			sb.append(HEX_DIGITS_LOWER_CACE[b[i] & 0x0f]);
		}
		return sb.toString();
	}
}
