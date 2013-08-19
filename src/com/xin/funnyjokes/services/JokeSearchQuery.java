package com.xin.funnyjokes.services;

import java.util.Date;

import com.xin.funnyjokes.utils.FunnyJokesUtils;

import android.os.Parcel;
import android.os.Parcelable;

public class JokeSearchQuery implements Parcelable {

	// Default is to get the latest jokes
	public static final int MAX_DAYS = 365 * 3;
	
	private int days = 0;
	private String authorId;
	private String category;

	public JokeSearchQuery() {
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		if(days > MAX_DAYS)
			this.days = MAX_DAYS;
		else
			this.days = days;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getWhere() {
		String where = "";
		if (category != null) {
			where = String.format("\"category\" : \"%s\"", category);
		}
		if (authorId != null) {
			where = String.format("\"author_id\" : \"%s\"", authorId);
		}
		if (days > 0)
		{
			Date endDate = FunnyJokesUtils.minusDays(new Date(), days);
			String endDateStr = FunnyJokesUtils.getStringFromDate(endDate);
			String dateRange = String.format("\"created\" : {\"$gt\" : \"%s\"}", endDateStr);
			where = String.format("%s,  %s", where, dateRange);
		}		
		return String.format("{%s}", where);
	}

	public String getSort()
	{
		if(days > 0)
			return "[(\"likes_number\", -1)]";
		else
			return "[(\"created\", -1)]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(category);
		dest.writeString(authorId);
		dest.writeInt(days);
	}

	private void readFromParcel(Parcel in) {
		this.category = in.readString();
		this.authorId = in.readString();
		this.days = in.readInt();
	}

	public static final Parcelable.Creator<JokeSearchQuery> CREATOR = new Parcelable.Creator<JokeSearchQuery>() {
		public JokeSearchQuery createFromParcel(Parcel in) {
			return new JokeSearchQuery(in);
		}

		public JokeSearchQuery[] newArray(int size) {
			return new JokeSearchQuery[size];
		}
	};

	private JokeSearchQuery(Parcel in) {
		this.readFromParcel(in);
	}
	
	@Override
	public String toString()
	{
		
		return "Days: " + days;
		
	}

}