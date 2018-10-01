
package com.airpay.airpaylendingapp.ui.main;



import com.airpay.airpaylendingapp.data.model.api.BlogResponse;

import java.util.List;



public interface BlogNavigator {

    void handleError(Boolean throwable);

    void updateBlog(List<BlogResponse> blogList);

    void  updateflagf(boolean isloading);

    void lastpage(boolean lastpage);



}
