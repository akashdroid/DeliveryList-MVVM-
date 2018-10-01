
package com.airpay.airpaylendingapp.data.remote;

import android.content.Context;

import com.airpay.airpaylendingapp.data.local.db.dao.DataManager;
import com.airpay.airpaylendingapp.data.model.api.BlogResponse;
import com.airpay.airpaylendingapp.data.model.api.LoginRequest;
import com.airpay.airpaylendingapp.data.model.api.LoginResponse;
import com.airpay.airpaylendingapp.data.model.api.LogoutResponse;
import com.airpay.airpaylendingapp.data.model.api.OpenSourceResponse;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;


@Singleton
public class AppDataManager implements DataManager {

    private final ApiHelper mApiHelper;

    private final Context mContext;



    private final Gson mGson;



    @Inject
    public AppDataManager(Context context,  ApiHelper apiHelper, Gson gson) {
        mContext = context;
        mApiHelper = apiHelper;
        mGson = gson;
    }

    @Override
    public Single<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request) {
        return mApiHelper.doFacebookLoginApiCall(request);
    }

    @Override
    public Single<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request) {
        return mApiHelper.doGoogleLoginApiCall(request);
    }

    @Override
    public Single<LogoutResponse> doLogoutApiCall() {
        return mApiHelper.doLogoutApiCall();
    }

    @Override
    public Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        return mApiHelper.doServerLoginApiCall(request);
    }

    @Override
    public ApiHeader getApiHeader() {
        return null;
    }


    @Override
    public Single<List<BlogResponse>> getBlogApiCall() {
        return mApiHelper.getBlogApiCall();
    }

    @Override
    public Single<List<BlogResponse>> getBlogApiCallpage(String offset) {
        return mApiHelper.getBlogApiCallpage(offset);
    }

    @Override
    public Single<OpenSourceResponse> getOpenSourceApiCall() {
        return null;
    }


}
