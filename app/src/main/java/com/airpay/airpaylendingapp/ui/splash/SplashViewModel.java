

package com.airpay.airpaylendingapp.ui.splash;


import com.airpay.airpaylendingapp.data.local.db.dao.DataManager;
import com.airpay.airpaylendingapp.utils.base.BaseViewModel;
import com.airpay.airpaylendingapp.utils.rx.SchedulerProvider;



public class SplashViewModel extends BaseViewModel<SplashNavigator> {

    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void startSeeding() {

      decideNextActivity();
    }

    private void decideNextActivity() {
        getNavigator().openMainActivity();

        //getNavigator().openMainActivity();
       /* if (getDataManager().getCurrentUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
            getNavigator().openLoginActivity();
        } else {
            getNavigator().openMainActivity();
        }*/
    }
}
