package com.bih.nic.attendenceapp.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.bih.nic.attendenceapp.entities.User;


/**
 * Created by NIC2 on 1/12/2018.
 */
public class CommonPref {

	static Context context;

	CommonPref() {

	}

	CommonPref(Context context) {
		CommonPref.context = context;
	}


	public static void setUserDetails(Context context, User UserInfo2,String token) {

		String key = "_USER_DETAILS";

		SharedPreferences prefs = context.getSharedPreferences(key,
				Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		//editor.putString("MessageString", UserInfo2.getUserName());
		editor.putString("staffId", UserInfo2.getStaffId());
		editor.putString("staffName", UserInfo2.getStaffName());
		editor.putString("mobileNo", UserInfo2.getMobileNo());
		editor.putString("emailId", UserInfo2.getEmailId());
		editor.putString("authenticate", UserInfo2.getAuthenticate());
		editor.putString("latitude", UserInfo2.getLatitude());
		editor.putBoolean("longitude", UserInfo2.getLongitude());
		editor.putString("entryDate", UserInfo2.getAEntryDate());
		editor.putString("imageUrl", UserInfo2.getImageUrl());
		editor.putString("role", UserInfo2.getRole());
		editor.putString("locationId", UserInfo2.getLocationId());
		editor.putString("token", token);
		editor.commit();
	}
	public static User getUserDetails(Context context) {
		String key = "_USER_DETAILS";
		User UserInfo2 = new User();
		SharedPreferences prefs = context.getSharedPreferences(key,
				Context.MODE_PRIVATE);
		//UserInfo2.setMessageString(prefs.getString("MessageString", ""));
		UserInfo2.setStaffId(prefs.getString("staffId", ""));
		UserInfo2.setStaffName(prefs.getString("staffId", ""));
		UserInfo2.setMobileNo(prefs.getString("mobileNo", ""));
		UserInfo2.setEmailId(prefs.getString("emailId", ""));
		UserInfo2.setAuthenticate(prefs.getString("authenticate", ""));
		UserInfo2.setLatitude(prefs.getString("latitude", ""));
		UserInfo2.setlongitude(prefs.getString("longitude", ""));
		UserInfo2.setEntryDate(prefs.getString("entryDate", ""));
		UserInfo2.setImageUrl(prefs.getString("imageUrl", ""));
		UserInfo2.setRole(prefs.getString("role", ""));
		UserInfo2.setLocationId(prefs.getString("locationId", ""));
		//UserInfo2.setPassword(Utiilties.decryption(prefs.getString("Password", "")));
		UserInfo2.setToken(prefs.getString("token", ""));
		//UserInfo2.setACCT_NO(prefs.getString("ACCT_NO", ""));
		return UserInfo2;
	}
	public static void logout(Context context) {

		String key = "_USER_DETAILS";

		SharedPreferences prefs = context.getSharedPreferences(key,
				Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putString("MessageString", "");
		editor.putString("UserID", "");
		editor.putString("UserName", "");
		editor.putString("SubDiv", "");
		editor.putString("SubdivName", "");
		editor.putString("WalletId", "");
		editor.putString("WalletAmount", "");
		editor.putBoolean("Authenticated", false);
		editor.putString("ImeiNo", "");
		editor.putString("ContactNo", "");
		editor.putString("IFSCCode", "");
		editor.putString("SerialNo", "");
		editor.putString("Password","");
		editor.putString("ACCT_NO","");
		editor.commit();
	}

	public static void setCheckUpdate(Context context, long dateTime) {

		String key = "_CheckUpdate";

		SharedPreferences prefs = context.getSharedPreferences(key,
				Context.MODE_PRIVATE);

		Editor editor = prefs.edit();

		dateTime = dateTime + 1 * 3600000;
		editor.putLong("LastVisitedDate", dateTime);

		editor.commit();

	}
	public static void setPrinterMacAddress(Context context, String address) {

		String key = "_MAC_ADDRESS";

		SharedPreferences prefs = context.getSharedPreferences(key,
				Context.MODE_PRIVATE);

		Editor editor = prefs.edit();

		editor.putString("MacAddress", address);
		editor.commit();

	}

	public static String getPrinterMacAddress(Context context) {
		String key = "_MAC_ADDRESS";
		SharedPreferences prefs = context.getSharedPreferences(key,
				Context.MODE_PRIVATE);
		String macAddress = prefs.getString("MacAddress", "");
		return macAddress;
	}

	public static void setPrinterType(Context context, String address) {

		String key = "P_Type";

		SharedPreferences prefs = context.getSharedPreferences(key,
				Context.MODE_PRIVATE);

		Editor editor = prefs.edit();

		editor.putString("PType", address);
		editor.commit();

	}

	public static String getPrinterType(Context context) {

		String key = "P_Type";

		SharedPreferences prefs = context.getSharedPreferences(key,
				Context.MODE_PRIVATE);

		String Ptype = prefs.getString("PType", "");
		return Ptype;
	}

	public static void setCurrentDateForSync(Activity activity,String dateToStr) {
		String key = "CurrentDateS";

		SharedPreferences prefs = activity.getSharedPreferences(key,
				Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putString("date", dateToStr);
		editor.commit();
	}

	public static String getCurrentDateForSync(Activity activity) {

		String key = "CurrentDateS";

		SharedPreferences prefs = activity.getSharedPreferences(key,
				Context.MODE_PRIVATE);
		return prefs.getString("date", "");
	}

    public static void setCurrentDate(Activity activity,String dateToStr) {
		String key = "CurrentDate";

		SharedPreferences prefs = activity.getSharedPreferences(key,
				Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putString("date", dateToStr);
		editor.commit();
    }

	public static String getCurrentDate(Activity activity) {

		String key = "CurrentDate";

		SharedPreferences prefs = activity.getSharedPreferences(key,
				Context.MODE_PRIVATE);
		return prefs.getString("date", "");
	}
}
