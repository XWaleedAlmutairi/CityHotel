package com.city.hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.city.hotel.adapters.CommentsAdapters;
import com.city.hotel.adapters.SlidingImageAdapter;
import com.city.hotel.databinding.ActivityCommentsBinding;
import com.city.hotel.models.Hotel;

//**********************************************
public class CommentsActivity
        extends AppCompatActivity
//**********************************************
{

    private ActivityCommentsBinding mBinding;
    public static final String HOTEL_DETAIL = "HOTEL_DETAIL";
    private Hotel mHotel;


    //**********************************************
    @Override
    protected void onCreate(Bundle savedInstanceState)
    //**********************************************
    {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_comments);
        initControls();

    }

    //**********************************************
    private void initControls()
    //**********************************************
    {
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getParcelableData();
        mBinding.restrauntName.setText(mHotel.getName());
        if (mHotel.getImage() == null || mHotel.getImage()
                                               .size() == 0)
            return;
        startImageSlider(mHotel.getImage()
                               .toArray(new String[0]));
        showCommentsOnRecyclerView();
    }

    //**********************************************
    private void showCommentsOnRecyclerView()
    //**********************************************
    {
        CommentsAdapters commentsAdapters = new CommentsAdapters(mHotel.getComments(), null);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(commentsAdapters);
    }

    //******************************************************************
    private void startImageSlider(String[] urls)
    //******************************************************************
    {
        mBinding.pager.setAdapter(new SlidingImageAdapter(CommentsActivity.this, urls,
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
