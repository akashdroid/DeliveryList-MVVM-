

package com.airpay.airpaylendingapp.ui.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.airpay.airpaylendingapp.R;
import com.airpay.airpaylendingapp.data.model.api.BlogResponse;
import com.airpay.airpaylendingapp.databinding.ItemBlogEmptyViewBinding;
import com.airpay.airpaylendingapp.databinding.ItemBlogViewBinding;
import com.airpay.airpaylendingapp.ui.googlemaps.GoogleMapsActivity;
import com.airpay.airpaylendingapp.utils.AppLogger;
import com.airpay.airpaylendingapp.utils.base.BaseViewHolder;


import java.util.List;



public class BlogAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;
    private boolean isFooterEnabled = false;

    Boolean first;

    private List<BlogResponse> mBlogResponseList;

    private BlogAdapterListener mListener;

    public BlogAdapter(List<BlogResponse> blogResponseList) {
        this.mBlogResponseList = blogResponseList;
        this.first=true;
    }

    @Override
    public int getItemCount() {
        if (mBlogResponseList != null && mBlogResponseList.size() > 0) {
            return mBlogResponseList.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mBlogResponseList.get(position) == null){
            return VIEW_TYPE_EMPTY;
        }
        else {
            return VIEW_TYPE_NORMAL;
        }


       // return mBlogResponseList.get(position) != null ? VIEW_TYPE_NORMAL : VIEW_TYPE_EMPTY;
     /*   if (mBlogResponseList != null && !mBlogResponseList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }*/
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(viewType==VIEW_TYPE_NORMAL) {
                ItemBlogViewBinding blogViewBinding = ItemBlogViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new BlogViewHolder(blogViewBinding);
            }else{
                ItemBlogEmptyViewBinding emptyViewBinding = ItemBlogEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new EmptyViewHolder(emptyViewBinding);
            }

        }



    public void addItems(List<BlogResponse> blogList) {
        mBlogResponseList.addAll(blogList);
        this.first=false;
        notifyDataSetChanged();
    }
    public void enableFooter(){
        mBlogResponseList.add(null); //this is Main its for Loader
        notifyItemInserted(mBlogResponseList.size() - 1); //added one more line
    }

    public void disableFooter(List<BlogResponse> blogList){
        mBlogResponseList.remove(mBlogResponseList.size() - 1);
        notifyItemRemoved(mBlogResponseList.size());
        }

    public void clearItems() {
        mBlogResponseList.clear();
    }

    public void setListener(BlogAdapterListener listener) {
        this.mListener = listener;
    }


    public interface BlogAdapterListener {

        void onRetryClick();
    }

    public class BlogViewHolder extends BaseViewHolder implements BlogItemViewModel.BlogItemViewModelListener {

        private ItemBlogViewBinding mBinding;

        private BlogItemViewModel mBlogItemViewModel;

        public BlogViewHolder(ItemBlogViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final BlogResponse blog = mBlogResponseList.get(position);
            mBlogItemViewModel = new BlogItemViewModel(blog, this);
            mBinding.setViewModel(mBlogItemViewModel);

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(String imageUrl, String description, String address, Double lat, Double lng) {
            if (address != null) {
                try {
                    Intent intent = new Intent(itemView.getContext(),GoogleMapsActivity.class);
                    intent.putExtra("imageUrl",imageUrl);
                    intent.putExtra("description",description);
                    intent.putExtra("address",address);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lng",lng);
                    intent.putExtra("position",getAdapterPosition());
                    itemView.getContext().startActivity(intent);
                } catch (Exception e) {
                    AppLogger.d("url error");
                }
            }
        }

        @Override
        public void onLongClick() {
            AskOption(getAdapterPosition(),itemView.getContext());

        }
    }
    private AlertDialog AskOption(int adapterPosition, Context context)
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(context)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setIcon(R.drawable.ic_delete_black_24dp)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        int position = adapterPosition;
                        mBlogResponseList.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }

                })



                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        myQuittingDialogBox.show();
        return myQuittingDialogBox;

    }
    public class EmptyViewHolder extends BaseViewHolder implements BlogEmptyItemViewModel.BlogEmptyItemViewModelListener {

        private ItemBlogEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemBlogEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            BlogEmptyItemViewModel emptyItemViewModel = new BlogEmptyItemViewModel(this,first,itemView.getContext());
            mBinding.setViewModel(emptyItemViewModel);

        }




        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }
}