

package com.airpay.airpaylendingapp.data.remote;

import com.airpay.airpaylendingapp.data.model.api.BlogResponse;
import com.airpay.airpaylendingapp.data.model.api.LoginRequest;
import com.airpay.airpaylendingapp.data.model.api.LoginResponse;
import com.airpay.airpaylendingapp.data.model.api.LogoutResponse;
import com.airpay.airpaylendingapp.data.model.api.OpenSourceResponse;


import java.util.List;

import io.reactivex.Single;

/**
 */

public interface ApiHelper {

    Single<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request);

    Single<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request);

    Single<LogoutResponse> doLogoutApiCall();

    Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    ApiHeader getApiHeader();

    Single<List<BlogResponse>> getBlogApiCall();

    Single<List<BlogResponse>> getBlogApiCallpage(String offset);

    Single<OpenSourceResponse> getOpenSourceApiCall();
}
