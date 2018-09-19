package com.airpay.airpaylendingapp.di.builder;



import com.airpay.airpaylendingapp.ui.login.LoginActivity;
import com.airpay.airpaylendingapp.ui.login.LoginActivityModule;
import com.airpay.airpaylendingapp.ui.splash.SplashActivity;
import com.airpay.airpaylendingapp.ui.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {
    /*@ContributesAndroidInjector(modules = {
            FeedActivityModule.class,
            BlogFragmentProvider.class,
            OpenSourceFragmentProvider.class})
    abstract FeedActivity bindFeedActivity();*/

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity bindLoginActivity();



    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();
}
