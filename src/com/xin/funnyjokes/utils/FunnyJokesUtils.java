package com.xin.funnyjokes.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class FunnyJokesUtils {
	private static final String TAG = "FunnyJokesUtils";
	private static final String SERVER_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final TimeZone SERVER_TIME_ZONE = TimeZone.getTimeZone("GMT");
	private static final long MILLISECONDS_IN_A_DAY = 86400000l;

	@SuppressLint("SimpleDateFormat")
	public static Date getDateFromString(String str) {
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat(SERVER_DATE_FORMAT);
		dateFormatGmt.setTimeZone(SERVER_TIME_ZONE);
		try {
			return dateFormatGmt.parse(str);
		} catch (ParseException e) {
			Log.e(TAG, "wrong date format returned");
			return null;
		}
	}

	@SuppressLint("SimpleDateFormat")
	public static String getStringFromDate(Date date) {
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat(SERVER_DATE_FORMAT);
		dateFormatGmt.setTimeZone(SERVER_TIME_ZONE);

		return dateFormatGmt.format(date);
	}

	public static Date minusDays(Date date, int days) {
		Date newDate = new Date();
		newDate.setTime(date.getTime() - days * MILLISECONDS_IN_A_DAY);

		return newDate;
	}
}
