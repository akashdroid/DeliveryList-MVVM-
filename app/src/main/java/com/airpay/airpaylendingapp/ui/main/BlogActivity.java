package com.airpay.airpaylendingapp.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.airpay.airpaylendingapp.BR;
import com.airpay.airpaylendingapp.R;
import com.airpay.airpaylendingapp.data.model.api.BlogResponse;
import com.airpay.airpaylendingapp.databinding.FragmentBlogBinding;
import com.airpay.airpaylendingapp.utils.PaginationScrollListener;
import com.airpay.airpaylendingapp.utils.base.BaseActivity;


import java.util.List;

import javax.inject.Inject;

import static android.widget.LinearLayout.VERTICAL;


public class BlogActivity extends BaseActivity<FragmentBlogBinding, BlogViewModel>
        implements BlogNavigator, BlogAdapter.BlogAdapterListener {

    @Inject
    BlogAdapter mBlogAdapter;
    FragmentBlogBinding mFragmentBlogBinding;
    @Inject
    LinearLayoutManager mLayoutManager;
    @Inject
    ViewModelProvider.Factory mViewModelFactory;


    private BlogViewModel mBlogViewModel;
    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = PAGE_START;
    private boolean isLoadin = false;


    /* @Override
        public void handleError(String throwable) {
           *//* Intent intent = new Intent("throwabledata");
        intent.putExtra("throwable",throwable);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);*//*
    }*/
    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, BlogActivity.class);
        return intent;
    }
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_blog;
    }

    @Override
    public BlogViewModel getViewModel() {
        mBlogViewModel = ViewModelProviders.of(this, mViewModelFactory).get(BlogViewModel.class);
        return mBlogViewModel;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentBlogBinding = getViewDataBinding();
        mBlogViewModel.setNavigator(this);
        mBlogAdapter.setListener(this);
        setUp();
        subscribeToLiveData();
    }

    @Override
    public void onRetryClick() {
        int itemcount= mFragmentBlogBinding.blogRecyclerView.getLayoutManager().getItemCount();
        mBlogViewModel.fetchBlogspaging(String.valueOf(itemcount));
        //mBlogViewModel.fetchBlogs();
    }


    @Override
    public void handleError(Boolean throwable) {
        Intent intent = new Intent("throwabledata");
        intent.putExtra("throwable",throwable);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void updateBlog(List<BlogResponse> blogList) {
        if(blogList.size()==0){
            mBlogAdapter.disableFooter(blogList);
        }else {
            mBlogAdapter.addItems(blogList);
        }

    }

    @Override
    public void updateflagf(boolean isloading) {
        this.isLoading=isloading;

    }

    @Override
    public void lastpage(boolean lastpage) {
        this.isLastPage=lastpage;
    }


    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentBlogBinding.blogRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentBlogBinding.blogRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, VERTICAL);
        mFragmentBlogBinding.blogRecyclerView.addItemDecoration(itemDecor);
        mFragmentBlogBinding.blogRecyclerView.setAdapter(mBlogAdapter);




        mFragmentBlogBinding.blogRecyclerView.addOnScrollListener(new PaginationScrollListener(mLayoutManager) {
            @Override
            protected void loadMoreItems() {

                    isLoading = true;
                    currentPage += 1;
                    mBlogAdapter.enableFooter();
                    int itemcount= mFragmentBlogBinding.blogRecyclerView.getLayoutManager().getItemCount();
                    mBlogViewModel.fetchBlogspaging(String.valueOf(itemcount));



            }

            @Override
            public int getTotalPageCount() {
                return 0;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }




        });

    }

    private void subscribeToLiveData() {


        mBlogViewModel.getBlogListLiveData().observe(this, blogs -> mBlogViewModel.addBlogItemsToList(blogs));

    }
}
