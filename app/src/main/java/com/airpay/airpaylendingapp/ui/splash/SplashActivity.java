

package com.airpay.airpaylendingapp.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import com.airpay.airpaylendingapp.BR;
import com.airpay.airpaylendingapp.R;
import com.airpay.airpaylendingapp.databinding.ActivitySplashBinding;

import com.airpay.airpaylendingapp.ui.main.BlogActivity;
import com.airpay.airpaylendingapp.utils.base.BaseActivity;


import javax.inject.Inject;



public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {

    @Inject
    SplashViewModel mSplashViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, SplashActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        return mSplashViewModel;
    }

    @Override
    public void openLoginActivity() {
       /* Intent intent = LoginActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();*/
    }

    @Override
    public void openMainActivity() {
        Intent intent = BlogActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSplashViewModel.setNavigator(this);
        mSplashViewModel.startSeeding();
    }
}
