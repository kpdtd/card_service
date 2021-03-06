package com.anl.user.util;

import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
//	private final static String TAG = "MD5Util";

	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	protected static MessageDigest messagedigest = null;

	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			messagedigest = null;
		}
	}

	public static String getFileMD5String(File file) {
		String ret = null;
		if (messagedigest != null) {
			FileInputStream in = null;
			FileChannel ch = null;
			try {
				in = new FileInputStream(file);
				ch = in.getChannel();
				MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
				messagedigest.update(byteBuffer);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception e) {

						e.printStackTrace();
					}
				}
				if (ch != null) {
					try {
						ch.close();
					} catch (Exception e) {

						e.printStackTrace();
					}
				}
			}
			ret = bufferToHex(messagedigest.digest());
		}

		return ret;
	}

	public static String getMD5String(String s) {
		return getMD5String(s.getBytes());
	}

	public static String getMD5String(byte[] bytes) {
		String bufferToHex = null;
		if (messagedigest != null) {
			messagedigest.update(bytes);
			bufferToHex = bufferToHex(messagedigest.digest());
		}

		return bufferToHex;
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	public static void main(String[] args) {
		String result = getMD5String("0038A10E0080DD463D0FC68AF8C64C8023201711141834298790ZR5FiVfbFhLmvKge");
		System.out.println(result);
	}
}
