package com.city.hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.city.hotel.adapters.SlidingImageAdapter;
import com.city.hotel.databinding.ActivityHotelDetailBinding;
import com.city.hotel.models.Comment;
import com.city.hotel.models.Hotel;


//***************************************************
public class HotelDetailActivity
        extends AppCompatActivity
//***************************************************
{

    private ActivityHotelDetailBinding mBinding;
    public static final String HOTEL_DETAIL = "HOTEL_DETAIL";
    private RequestManager mGlideRequestManager;
    private Hotel mHotel;


    //***************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState)
    //***************************************************
    {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_hotel_detail);
        initControls();
    }

    //***************************************************
    private void initControls()
    //***************************************************
    {
        getParcelableData();
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mGlideRequestManager = Glide.with(this);
        mBinding.restrauntName.setText(mHotel.getName());
        mBinding.phoneNumber.setText(mHotel.getPhone());
        mBinding.address.setText(mHotel.getAddress());
        mBinding.webUrl.setText(mHotel.getWebsite());
        mBinding.aboutDetails.setText(mHotel.getHotelDetail());
        mBinding.navgiate.setOnClickListener(
                view -> showDirection(mHotel.getLatitude() + "," + mHotel.getLongitude()));
        if (mHotel.getImage() == null || mHotel.getImage()
                                               .size() == 0)
            return;
        startImageSlider(mHotel.getImage()
                               .toArray(new String[0]));
        mBinding.viewComments.setOnClickListener(view -> gotoCommentScreen());

        float sumRating = 0;
        for (Comment c : mHotel.getComments())
        {
            sumRating += c.getRating();
        }
        mBinding.rating.setRating((sumRating / mHotel.getComments()
                                                     .size()));
        mBinding.webUrl.setOnClickListener(view -> openWebsite(mHotel.getWebsite()));
        mBinding.phoneNumber.setOnClickListener(view->openDialer(mHotel.getPhone()));

    }

    //******************************************************************
    void openDialer(String number)
    //******************************************************************
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+number));
        startActivity(intent);

    }

    //******************************************************************
    void openWebsite(String webUrl)
    //******************************************************************
    {
        if (!webUrl.startsWith("http://") && !webUrl.startsWith("https://"))
            webUrl = "http://" + webUrl;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
        startActivity(browserIntent);
    }

    //******************************************************************
    private void gotoCommentScreen()
    //******************************************************************
    {
        Intent commentIntent = new Intent(this, CommentsActivity.class);
        commentIntent.putExtra(CommentsActivity.HOTEL_DETAIL, mHotel);
        startActivity(commentIntent);
    }

    //******************************************************************
    private void startImageSlider(String[] urls)
    //******************************************************************
    {
        mBinding.pager.setAdapter(new SlidingImageAdapter(HotelDetailActivity.this, urls,
                                                          new SlidingImageAdapter.SliderTouchListener()
                                                          {
                                                              @Override
                                                              public void onPressed()
                                                              {
                                                              }

                                                              @Override
                                                              public void onRelease()
                                                              {
                                                              }
                                                          }));

    }

    //***************************************************
    private void getParcelableData()
    //***************************************************
    {
        if (getIntent() == null || getIntent().getExtras() == null)
            return;
        if (getIntent().getExtras()
                       .containsKey(HOTEL_DETAIL))
        {
            mHotel = getIntent().getParcelableExtra(HOTEL_DETAIL);
        }

    }

    //**************************************************************
    private void showDirection(@NonNull String longLat)
    //**************************************************************
    {
        Intent intent =
                new Intent(Intent.ACTION_VIEW,
                           Uri.parse("http://maps.google.com/maps?" +
                                             "&daddr=" + longLat));
        startActivity(intent);
    }

    //***************************************************
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    //***************************************************
    {
        switch (item.getItemId())
        {
        case android.R.id.home:
            finish();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

}
