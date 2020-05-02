package com.city.hotel.models;

import android.os.Parcel;
import android.os.Parcelable;

public class City implements Parcelable
{
    private String cityName;
    private String cityImage;

    public City(String cityName, String cityImage)
    {
        this.cityName = cityName;
        this.cityImage = cityImage;
    }

    public City()
    {
    }

    protected City(Parcel in)
    {
        cityName = in.readString();
        cityImage = in.readString();
    }

    public static final Creator<City> CREATOR = new Creator<City>()
    {
        @Override
        public City createFromParcel(Parcel in)
        {
            return new City(in);
        }

        @Override
        public City[] newArray(int size)
        {
            return new City[size];
        }
    };

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public String getCityImage()
    {
        return cityImage;
    }

    public void setCityImage(String cityImage)
    {
        this.cityImage = cityImage;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(cityName);
        dest.writeString(cityImage);
    }
}
