

package com.airpay.airpaylendingapp.ui.googlemaps;


import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.airpay.airpaylendingapp.data.local.db.dao.DataManager;
import com.airpay.airpaylendingapp.utils.base.BaseViewModel;
import com.airpay.airpaylendingapp.utils.rx.SchedulerProvider;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;



public class MapsViewModel extends BaseViewModel<MapsNavigator> {

    public  ObservableField<String> author = new ObservableField<>();
    public  ObservableField<String> imageUrl = new ObservableField<>();
    public  ObservableField<String> title = new ObservableField<>();
    public  ObservableField<Integer> position = new ObservableField<>();

    public ObservableField<String> getAuthor() {
        return author;
    }

   /* public void setAuthor(ObservableField<String> author) {
        this.author = author;
    }*/

    public ObservableField<String> getImageUrl() {
        return imageUrl;
    }

   /* public void setImageUrl(ObservableField<String> imageUrl) {
        this.imageUrl = imageUrl;
    }*/

    public ObservableField<String> getTitle() {
        return title;
    }

   /* public void setTitle(ObservableField<String> title) {
        this.title = title;
    }*/

    public ObservableField<Integer> getPosition() {
        return position;
    }

   /* public void setPosition(ObservableField<Integer> position) {
        this.position = position;
    }*/

    public MapsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

    }

   /* public void startSeeding(String imageurl, String desc, String addr, Integer position1) {
        title.set(desc);
        imageUrl.set(imageurl);
        author.set(addr);
        position.set(position1);

       *//* imageUrl = new ObservableField<>(imageurl);
        title = new ObservableField<>(desc);
        author = new ObservableField<>(addr);
        position=(position1);*//*

    }*/

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
        });
    }

    public int getSaleVisibility(int position){
        return position==(13) ? VISIBLE : GONE;
    }

}
