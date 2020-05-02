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
import com.city.hotel.databinding.ListViewCitiesBinding;
import com.city.hotel.databinding.ListViewHotelBinding;
import com.city.hotel.models.City;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class CitiesListAdapter
        extends RecyclerView.Adapter<CitiesListAdapter.ViewHolder>
{
    private List<City> mSelectedActivities;
    private RequestManager mGlideRequestManager;
    private CityListClickListener mCityListClickListener;

    public CitiesListAdapter(List<City> mSelectedActivities, CityListClickListener mCityListClickListener)
    {
        this.mSelectedActivities = mSelectedActivities;
        this.mCityListClickListener = mCityListClickListener;
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
        return mSelectedActivities.size();
    }


    //**********************************************
    @androidx.annotation.NonNull
    @Override
    public ViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType)
    //**********************************************
    {
        ListViewCitiesBinding mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.list_view_cities, parent,
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
        final City item = mSelectedActivities
                .get(position);
        holder.mBinding.name.setText(item.getCityName());
        mGlideRequestManager.asBitmap()
                            .load(item.getCityImage())
                            .centerCrop()
                            .thumbnail(0.1f)
                            .placeholder(R.drawable.image_load_progress)
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
            if (mCityListClickListener != null)
                mCityListClickListener.onListItemClick(item);
        });

    }


    //**********************************************
    public class ViewHolder
            extends RecyclerView.ViewHolder
            //**********************************************
    {
        ListViewCitiesBinding mBinding;

        //**********************************************
        public ViewHolder(@NonNull ListViewCitiesBinding itemView)
        //**********************************************
        {
            super(itemView.getRoot());
            mBinding = itemView;
        }

    }


    //**********************************************
    public interface CityListClickListener
            //**********************************************
    {
        void onListItemClick(City city);
    }
}