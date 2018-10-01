

package com.airpay.airpaylendingapp.ui.main;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.airpay.airpaylendingapp.data.model.api.BlogResponse;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;


public class BlogItemViewModel {

    public final ObservableField<String> author;

    public final ObservableField<String> imageUrl;
    public final BlogItemViewModelListener mListener;

    public final ObservableField<String> title;

    private final BlogResponse mBlog;

    public BlogItemViewModel(BlogResponse blog, BlogItemViewModelListener listener) {
        this.mBlog = blog;
        this.mListener = listener;
        imageUrl = new ObservableField<>(mBlog.getImageUrl());
        title = new ObservableField<>(mBlog.getDescription());
        author = new ObservableField<>(mBlog.getLocation().getAddress());

    }

    public void onItemClick() {
        mListener.onItemClick(mBlog.getImageUrl(),mBlog.getDescription(),mBlog.getLocation().getAddress(),mBlog.getLocation().getLat(),mBlog.getLocation().getLng());
    }

    public boolean onLongClick() {
        mListener.onLongClick();
        return true;
    }

    public interface BlogItemViewModelListener {

        void onItemClick(String imageUrl, String description, String blogUrl, Double lat, Double lng);
        void onLongClick();
    }

    @BindingAdapter("load_image")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        });    }

}
