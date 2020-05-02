package com.city.hotel.adapters;


import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.city.hotel.R;

import androidx.viewpager.widget.PagerAdapter;


//**********************************************
public class SlidingImageAdapter
        extends PagerAdapter
//**********************************************
{


    private String[] urls;
    private LayoutInflater inflater;
    private Context context;
    private SliderTouchListener listener;
    private RequestManager mImageLoader;


    //**********************************************
    public SlidingImageAdapter(Context context, String[] urls, SliderTouchListener listener)
    //**********************************************
    {
        this.context = context;
        this.urls = urls;
        inflater = LayoutInflater.from(context);
        this.listener = listener;
        mImageLoader = Glide.with(context);
    }

    //**********************************************
    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    //**********************************************
    {
        container.removeView((View)object);
    }

    //**********************************************
    @Override
    public int getCount()
    //**********************************************
    {
        return urls.length;
    }


    //**********************************************
    public interface SliderTouchListener
            //**********************************************
    {
        void onPressed();

        void onRelease();

    }


    //**********************************************
    @Override
    public Object instantiateItem(ViewGroup view, int position)
    //**********************************************
    {

        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);
        assert imageLayout != null;
        final ImageView imageView = imageLayout
                .findViewById(R.id.image);
        imageLayout.setOnTouchListener((v, event) -> {
            switch (event.getAction())
            {
            case MotionEvent.ACTION_DOWN:
                if (listener != null)
                    listener.onPressed();
                return true;
            case MotionEvent.ACTION_UP:
                if (listener != null)
                    listener.onRelease();

                return true;
            }
            return false;
        });


        mImageLoader.load(urls[position])
                    .centerCrop()
                    .placeholder(R.drawable.image_load_progress)
                    .into(imageView);
        view.addView(imageLayout, 0);

        return imageLayout;
    }

    //**********************************************
    @Override
    public boolean isViewFromObject(View view, Object object)
    //**********************************************
    {
        return view.equals(object);
    }

    //**********************************************
    @Override
    public void restoreState(Parcelable state, ClassLoader loader)
    //**********************************************
    {
    }

    @Override
    public Parcelable saveState()
    {
        return null;
    }

}