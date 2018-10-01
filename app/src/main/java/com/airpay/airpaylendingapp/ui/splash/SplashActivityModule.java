
package com.airpay.airpaylendingapp.ui.splash;

import com.airpay.airpaylendingapp.data.local.db.dao.DataManager;
import com.airpay.airpaylendingapp.utils.rx.SchedulerProvider;


import dagger.Module;
import dagger.Provides;


@Module
public class SplashActivityModule {

    @Provides
    SplashViewModel provideSplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new SplashViewModel(dataManager, schedulerProvider);
    }
}
