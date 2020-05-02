package com.city.hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;

import com.city.hotel.adapters.HotelListAdapter;
import com.city.hotel.databinding.ActivityRestaurantListBinding;
import com.city.hotel.models.City;
import com.city.hotel.models.Hotel;
import com.city.hotel.models.HotelList;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//*********************************************************
public class RestaurantListActivity
        extends AppCompatActivity
        implements HotelListAdapter.HotelListClickListener
//*********************************************************
{

    private ActivityRestaurantListBinding mBinding;
    private City mCity;
    public static final String CITY_DETAIL = "CITY_DETAIL";
    private HotelList mHotelList;
    private List<Hotel> mFilteredHotelList;
    private HotelListAdapter mHotelListAdapter;

    //*********************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState)
    //*********************************************************
    {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_list);
        initControls();
    }

    //*********************************************************
    private void initControls()
    //*********************************************************
    {
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getParcelableData();
        mBinding.cityName.setText(mCity.getCityName());
        mHotelList = getHotelList();
        showListOnRecyclerView(mHotelList.getHotel());
        mBinding.searchHotel.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (s.length() == 0)
                {
                    showListOnRecyclerView(mHotelList.getHotel());
                    return;
                }
                filterByName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
    }

    //*********************************************************
    private void filterByName(String toString)
    //*********************************************************
    {
        List<Hotel> tempList = new ArrayList<>();
        for (Hotel hotel : mHotelList.getHotel())
        {
            if (hotel.getName()
                     .toLowerCase()
                     .contains(toString.toLowerCase()))
            {
                tempList.add(hotel);
            }
        }
        showListOnRecyclerView(tempList);
    }

    //*********************************************************
    private void showListOnRecyclerView(List<Hotel> hotelList)
    //*********************************************************
    {
        if (mFilteredHotelList == null)
            mFilteredHotelList = new ArrayList<>();
        mFilteredHotelList.clear();
        mFilteredHotelList.addAll(hotelList);
        if (mFilteredHotelList.size() == 0)
        {
            mBinding.noHotelFound.setVisibility(View.VISIBLE);
        }
        else
        {
            mBinding.noHotelFound.setVisibility(View.GONE);
        }

        if (mHotelListAdapter == null)
        {
            mHotelListAdapter = new HotelListAdapter(mFilteredHotelList, this);
            mBinding.restaurantListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mBinding.restaurantListRecyclerView.setAdapter(mHotelListAdapter);
            return;
        }
        mHotelListAdapter.notifyDataSetChanged();
    }

    //*********************************************************
    private void getParcelableData()
    //*********************************************************
    {
        if (getIntent() == null || getIntent().getExtras() == null)
            return;
        if (getIntent().getExtras()
                       .containsKey(CITY_DETAIL))
        {
            mCity = getIntent().getExtras()
                               .getParcelable(CITY_DETAIL);
        }

    }

    //******************************************************************
    private HotelList getHotelList()
    //******************************************************************
    {
        Gson gson = new Gson();
        HotelList list = gson.fromJson(readFileFroRawFolder(R.raw.hotel_list),
                                       HotelList.class);
        return list;
    }

    //*************************************************
    private String readFileFroRawFolder(int rawResourceID)
    //*************************************************
    {
        InputStream inputStream = getResources()
                .openRawResource(rawResourceID);
        String jsonString = readJsonFile(inputStream);

        return jsonString;

    }

    //*************************************************
    private static String readJsonFile(InputStream inputStream)
    //*************************************************
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte bufferByte[] = new byte[1024];
        int length;
        try
        {
            while ((length = inputStream.read(bufferByte)) != -1)
            {
                outputStream.write(bufferByte, 0, length);
            }
            outputStream.close();
            inputStream.close();
        }
        catch (IOException e)
        {

        }
        return outputStream.toString();
    }

    //*************************************************
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    //*************************************************
    {
        switch (item.getItemId())
        {
        case android.R.id.home:
            finish();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    //*************************************************
    @Override
    public void onListItemClick(Hotel hotel)
    //*************************************************
    {
        gotoHotelDetailActivity(hotel);

    }

    //*************************************************
    private void gotoHotelDetailActivity(Hotel hotel)
    //*************************************************
    {
        Intent hotelDetailIntent = new Intent(this, HotelDetailActivity.class);
        hotelDetailIntent.putExtra(HotelDetailActivity.HOTEL_DETAIL, hotel);
        startActivity(hotelDetailIntent);

    }
}
