package com.silikonm.utilities;

public class PrintableLine {
	String text1, text2;
	boolean singleLine = true;

	public PrintableLine(String text1) {
		this.text1 = text1;
		this.text2 = "";
		//System.out.println(text1);
		this.singleLine = true;
	}

	public PrintableLine(String text1, String text2) {
		this.text1 = text1;
		this.text2 = text2;
		System.out.println(text1 + " " + text2);
		this.singleLine = false;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText1() {
		return this.text1;
	}

	public String getText2() {
		return this.text2;
	}

	public boolean isSingleLine() {
		return this.singleLine;
	}
}
