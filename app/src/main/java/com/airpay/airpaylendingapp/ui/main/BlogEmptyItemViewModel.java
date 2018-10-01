
package com.airpay.airpaylendingapp.ui.main;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.ObservableBoolean;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.airpay.airpaylendingapp.data.model.api.BlogResponse;
import com.airpay.airpaylendingapp.utils.NetworkUtils;
import com.airpay.airpaylendingapp.utils.base.BaseViewModel;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class BlogEmptyItemViewModel     {

    private BlogEmptyItemViewModelListener mListener;
    boolean first;
    NetworkUtils networkUtils;
    Context context;
    private BlogViewModel mBlogItemViewModel;

    public  final  ObservableBoolean Iserrorpaging = new ObservableBoolean(false);


    public ObservableBoolean getIserrorpaging() {
        return Iserrorpaging;
    }

    public int getSaleVisibility(){
        return first ? GONE : VISIBLE;
        }




    public BlogEmptyItemViewModel(BlogEmptyItemViewModelListener listener,Boolean first,Context context) {
        this.mListener = listener;
        this.first =first;
        this.context=context;
        LocalBroadcastManager.getInstance(context).registerReceiver(mMessageReceiver, new IntentFilter("throwabledata"));

    }

    public boolean getisconnected(){
        return networkUtils.isNetworkConnected(context);
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }


   /* @Override
    public void onReceive(Context context, Intent intent) {
        String throwa=intent.getStringExtra("throwable");
        this.throwable=throwa;

    }*/

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            boolean message = intent.getBooleanExtra("throwable", false);
            Iserrorpaging.set(message);
        }
    };


   /* @Override
    public void handleError(String throwable) {
       this.throwable=throwable;
    }
*/
   /*public  int geterror(){
       Iserrorpaging.set(mBlogItemViewModel.getIserrorpaging());
   }*/









    public interface BlogEmptyItemViewModelListener {

        void onRetryClick();
    }
}
