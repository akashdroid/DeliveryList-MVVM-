
package com.airpay.airpaylendingapp.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;

import com.airpay.airpaylendingapp.data.local.db.dao.DataManager;
import com.airpay.airpaylendingapp.data.model.api.BlogResponse;
import com.airpay.airpaylendingapp.utils.base.BaseViewModel;
import com.airpay.airpaylendingapp.utils.rx.SchedulerProvider;


import java.util.List;


public class BlogViewModel extends BaseViewModel<BlogNavigator>  {



    public final ObservableList<BlogResponse> blogObservableArrayList = new ObservableArrayList<>();

    public final MutableLiveData<List<BlogResponse>> blogListLiveData;

    public ObservableBoolean getIserror() {
        return Iserror;
    }

    public  final  ObservableBoolean Iserror = new ObservableBoolean(false);


    public  final  ObservableBoolean Iserrorpaging = new ObservableBoolean(false);

    public  final ObservableBoolean Isrefresh = new ObservableBoolean(false);



    public ObservableBoolean getIsrefresh() {
        return Isrefresh;
    }




    public BlogViewModel(DataManager dataManager,
                         SchedulerProvider schedulerProvider) {
        super( dataManager,schedulerProvider);
        blogListLiveData = new MutableLiveData<>();
        fetchBlogs();
    }

    public void addBlogItemsToList(List<BlogResponse> blogs) {
        blogObservableArrayList.clear();
        blogObservableArrayList.addAll(blogs);
    }

    public void addBlogItemsToListadd(List<BlogResponse> blogs) {
        blogObservableArrayList.addAll(blogs);
    }

    public void fetchBlogs() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getBlogApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(blogResponse -> {
                    if (blogResponse != null ) {

                        blogListLiveData.setValue((List<BlogResponse>) blogResponse);
                        Iserror.set(false);

                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    Iserror.set(true);

                    //getNavigator().handleError(((ANError) throwable).getErrorCode());
                }));
    }




    public void fetchBlogspaging(String offset) {

       // setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getBlogApiCallpage(offset)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(blogResponse -> {
                    if (blogResponse != null && blogResponse.size() >0 ) {
                      addBlogItemsToListadd((List<BlogResponse>) blogResponse);
                       // blogListLiveData.setValue((List<BlogResponse>) blogResponse);

                          getNavigator().updateflagf(false);

                    }
                    if(blogResponse.size() ==0){
                        getNavigator().lastpage(true);
                        getNavigator().updateflagf(true);
                        getNavigator().updateBlog(blogResponse);

                    }
                   // setIsLoading(false);
                }, throwable -> {
                   // setIsLoading(false);
                    //Iserrorpaging.set(true);
                    getNavigator().handleError(true);
                }));
    }




    public void onRefresh() {
        fetchBlogsrefresh();

        getNavigator().updateflagf(false);

    }

    public void fetchBlogsrefresh() {

            Isrefresh.set(true);
            getCompositeDisposable().add(getDataManager()
                    .getBlogApiCall()
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(blogResponse -> {
                        if (blogResponse != null ) {

                            blogListLiveData.setValue((List<BlogResponse>) blogResponse);
                            Iserror.set(false);

                        }
                        Isrefresh.set(false);
                    }, throwable -> {
                        Isrefresh.set(false);
                        Iserror.set(true);
                        getNavigator().handleError(true);
                        //getNavigator().handleError(((ANError) throwable).getErrorCode());
                    }));
        }



    public MutableLiveData<List<BlogResponse>> getBlogListLiveData() {
        return blogListLiveData;
    }


    public ObservableList<BlogResponse> getBlogObservableArrayList() {
        return blogObservableArrayList;
    }



}
