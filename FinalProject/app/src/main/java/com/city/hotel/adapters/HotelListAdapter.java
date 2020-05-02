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
import com.city.hotel.databinding.ListViewHotelBinding;
import com.city.hotel.models.Comment;
import com.city.hotel.models.Hotel;
import com.city.hotel.models.HotelList;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class HotelListAdapter
        extends RecyclerView.Adapter<HotelListAdapter.ViewHolder>
{
    private List<Hotel> mHotelList;
    private RequestManager mGlideRequestManager;
    private HotelListClickListener mHotelListClickListener;

    public HotelListAdapter(List<Hotel> hotelList, HotelListClickListener mHotelListClickListener)
    {
        this.mHotelList = hotelList;
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
        return mHotelList
                .size();
    }


    //**********************************************
    @androidx.annotation.NonNull
    @Override
    public ViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType)
    //**********************************************
    {
        ListViewHotelBinding mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.list_view_hotel, parent,
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
        final Hotel item = mHotelList.get(position);
        holder.mBinding.name.setText(item.getName());
        mGlideRequestManager.asBitmap()
                            .load(item.getImage()
                                      .get(0))
                            .centerCrop()
                            .thumbnail(0.1f)
                            .into(new CustomTarget<Bitmap>()
                            {
                                @Override
                                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition)
                                {
                                    holder.mBinding.image.setImageBitmap(resource);
                                }

                                @Override
                                public void onLoadCleared(@Nullable Drawable placeholder)
                                {

                                }
                            });
        holder.mBinding.city.setOnClickListener(view -> {
            if (mHotelListClickListener != null)
                mHotelListClickListener.onListItemClick(item);
        });

        float sumRating=0;
        for(Comment c:item.getComments())
        {
            sumRating+=c.getRating();
        }
        holder.mBinding.rating.setRating((sumRating/item.getComments().size()));

    }


    //**********************************************
    public class ViewHolder
            extends RecyclerView.ViewHolder
            //**********************************************
    {
        ListViewHotelBinding mBinding;

        //**********************************************
        public ViewHolder(@NonNull ListViewHotelBinding itemView)
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