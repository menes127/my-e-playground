package com.menes.test;

import java.lang.String;

public class JavaxInjectProviderClassTest {
	private static Class javaxInjectProviderClass = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s1 = new String("123");
		
		ClassLoader cl = String.class.getClassLoader();
		try {
			System.out.println("1=" + JavaxInjectProviderClassTest.javaxInjectProviderClass);
			//JavaxInjectProviderClassTest.javaxInjectProviderClass = cl.loadClass("javax.inject.Provider");
			JavaxInjectProviderClassTest.javaxInjectProviderClass = cl.loadClass("java.lang.String");
			System.out.println("2=" + JavaxInjectProviderClassTest.javaxInjectProviderClass);
		}
		catch (ClassNotFoundException ex) {
			// JSR-330 API not available - Provider interface simply not supported then.
		}
	}

}
