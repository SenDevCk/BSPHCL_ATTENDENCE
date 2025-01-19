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


	public static void setUserDetails(Context context, User userInfo2,String token) {

		String key = "_USER_DETAILS";

		SharedPreferences prefs = context.getSharedPreferences(key,
				Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		//editor.putString("MessageString", UserInfo2.getUserName());
		editor.putString("staffId", userInfo2.getStaffId());
		editor.putString("staffName", userInfo2.getStaffName());
		editor.putString("mobileNo", userInfo2.getMobileNo());
		editor.putString("emailId", userInfo2.getEmailId());
		editor.putString("authenticate", userInfo2.getAuthenticate());
		editor.putString("latitude", userInfo2.getLatitude());
		editor.putString("longitude", userInfo2.getLongitude());
		editor.putString("entryDate", userInfo2.getEntryDate());
		editor.putString("imageUrl", userInfo2.getImageUrl());
		editor.putString("role", userInfo2.getRole());
		editor.putString("locationId", userInfo2.getLocationId());
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
		UserInfo2.setStaffName(prefs.getString("staffName", ""));
		UserInfo2.setMobileNo(prefs.getString("mobileNo", ""));
		UserInfo2.setEmailId(prefs.getString("emailId", ""));
		UserInfo2.setAuthenticate(prefs.getString("authenticate", ""));
		UserInfo2.setLatitude(prefs.getString("latitude", ""));
		UserInfo2.setLongitude(prefs.getString("longitude", ""));
		UserInfo2.setEntryDate(prefs.getString("entryDate", ""));
		UserInfo2.setImageUrl(prefs.getString("imageUrl", ""));
		UserInfo2.setRole(prefs.getString("role", ""));
		UserInfo2.setLocationId(prefs.getString("locationId", ""));
		//UserInfo2.setPassword(Utiilties.decryption(prefs.getString("Password", "")));
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

		public static String getToken(Activity activity) {

		String key = "_USER_DETAILS";

		SharedPreferences prefs = activity.getSharedPreferences(key,
				Context.MODE_PRIVATE);
		return prefs.getString("token", "");
	}
}
