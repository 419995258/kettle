package org.my431.util;

import java.util.Date;

@SuppressWarnings( { "deprecation", "serial" })
public class FormatedDate extends Date {
	String partten = "yyyy-MM-dd HH:mm:ss";

	public FormatedDate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FormatedDate(long date) {
		super(date);
		// TODO Auto-generated constructor stub
	}

	public FormatedDate(long date, String partten) {
		super(date);
		this.partten = partten;
		// TODO Auto-generated constructor stub
	}

	public FormatedDate(int year, int month, int date) {
		super(year, month, date);
		// TODO Auto-generated constructor stub
	}

	public FormatedDate(int year, int month, int date, int hrs, int min) {
		super(year, month, date, hrs, min);
		// TODO Auto-generated constructor stub
	}

	public FormatedDate(int year, int month, int date, int hrs, int min, int sec) {
		super(year, month, date, hrs, min, sec);
		// TODO Auto-generated constructor stub
	}

	public FormatedDate(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		String t = null;
		try {
			t = DateFormater.DateToString(this, partten);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
