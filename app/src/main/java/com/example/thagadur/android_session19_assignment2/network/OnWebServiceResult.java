package com.example.thagadur.android_session19_assignment2.network;



import com.example.thagadur.android_session19_assignment2.utils.CommonUtilities;

/**
 * Created by Thagadur on 11/2/2017.
 */

public interface OnWebServiceResult {
	public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type);
}
