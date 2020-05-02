package com.city.hotel.adapters;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.city.hotel.R;
import com.city.hotel.databinding.ListviewCommentsBinding;
import com.city.hotel.models.Comment;
import com.city.hotel.models.Hotel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.MergedDataBinderMapper;
import androidx.recyclerview.widget.RecyclerView;

public class CommentsAdapters
        extends RecyclerView.Adapter<CommentsAdapters.ViewHolder>
{
    private List<Comment> mCommentsList;
    private RequestManager mGlideRequestManager;
    private HotelListAdapter.HotelListClickListener mHotelListClickListener;

    public CommentsAdapters(List<Comment> commentsList, HotelListAdapter.HotelListClickListener mHotelListClickListener)
    {
        this.mCommentsList = commentsList;
        this.mHotelListClickListener = mHotelListClickListener;
    }

    //**********************************************
    @Override
    public int getItemViewType(int position)
    //**********************************************
    {
        return position;
    }


    //**********************************************
    @Override
    public long getItemId(int position)
    //**********************************************
    {
        return super.getItemId(position);
    }

    //**********************************************
    @Override
    public int getItemCount()
    //**********************************************

    {
        return mCommentsList
                .size();
    }


    //**********************************************
    @androidx.annotation.NonNull
    @Override
    public ViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType)
    //**********************************************
    {
        ListviewCommentsBinding mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.listview_comments, parent,
                false);
        ViewHolder holder = new ViewHolder(mBinding);
        mGlideRequestManager = Glide.with(parent.getContext());
        return holder;
    }


    //**********************************************
    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull ViewHolder holder, int position)
    //**********************************************
    {
        final Comment item = mCommentsList
                .get(position);
        holder.mBinding.userName.setText(item.getUsernName());
        mGlideRequestManager.asBitmap()
                            .load(item.getUserImage())
                            .centerCrop()
                            .thumbnail(0.1f)
                            .into(new CustomTarget<Bitmap>()
                            {
                                @Override
                                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition)
                                {
                                    holder.mBinding.userImage.setImageBitmap(resource);
                                }

                                @Override
                                public void onLoadCleared(@Nullable Drawable placeholder)
                                {

                                }
                            });


        holder.mBinding.comment.setText(item.getComment());
        holder.mBinding.rating.setRating(Float.parseFloat(item.getRating().toString()));

    }


    //**********************************************
    public class ViewHolder
            extends RecyclerView.ViewHolder
            //**********************************************
    {
        ListviewCommentsBinding mBinding;

        //**********************************************
        public ViewHolder(@NonNull ListviewCommentsBinding itemView)
        //**********************************************
        {
            super(itemView.getRoot());
            mBinding = itemView;
        }

    }


    //**********************************************
    public interface HotelListClickListener
            //**********************************************
    {
        void onListItemClick(Hotel hotel);
    }
}