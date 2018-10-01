
package com.airpay.airpaylendingapp.ui.googlemaps;

import com.airpay.airpaylendingapp.data.local.db.dao.DataManager;
import com.airpay.airpaylendingapp.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;


@Module
public class MapsActivityModule {

    @Provides
    MapsViewModel provideMapsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new MapsViewModel(dataManager, schedulerProvider);
    }
}
