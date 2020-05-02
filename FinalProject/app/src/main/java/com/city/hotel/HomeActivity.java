package com.city.hotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.city.hotel.adapters.CitiesListAdapter;
import com.city.hotel.databinding.ActivityHomeBinding;
import com.city.hotel.models.City;

import java.util.ArrayList;
import java.util.List;

//*************************************************
public class HomeActivity
        extends AppCompatActivity
        implements CitiesListAdapter.CityListClickListener
//*************************************************
{

    private ActivityHomeBinding mBinding;
    private List<City> mCityList;

    //*************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState)
    //*************************************************
    {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        initControls();
    }

    //*************************************************
    private void initControls()
    //*************************************************
    {

        mCityList = new ArrayList<>(3);
        mCityList.add(new City(getResources().getString(R.string.new_york),
                               "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRDbvEXC5YbVa7NNVgLUlfBxGVTOGJkyhyNKJjTn3vRmQHVvZp4&usqp=CAU"));
        showDataOnRecyclerView();

    }

    //*************************************************
    private void showDataOnRecyclerView()
    //*************************************************
    {
        CitiesListAdapter citiesListAdapter = new CitiesListAdapter(mCityList, this);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(citiesListAdapter);
    }

    //*************************************************
    @Override
    public void onListItemClick(City city)
    //*************************************************
    {
        gotoHotelListScreen(city);
    }

    //*************************************************
    private void gotoHotelListScreen(City city)
    //*************************************************
    {
        Intent restaurantListIntent = new Intent(this, RestaurantListActivity.class);
        restaurantListIntent.putExtra(RestaurantListActivity.CITY_DETAIL,city);
        startActivity(restaurantListIntent);

    }
}
