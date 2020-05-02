package com.city.hotel.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class HotelList
        implements Serializable, Parcelable
{

    @SerializedName("hotel")
    @Expose
    private List<Hotel> hotel = null;
    public final static Parcelable.Creator<HotelList> CREATOR = new Creator<HotelList>()
    {


        @SuppressWarnings({
                                  "unchecked"
                          })
        public HotelList createFromParcel(Parcel in)
        {
            return new HotelList(in);
        }

        public HotelList[] newArray(int size)
        {
            return (new HotelList[size]);
        }

    };

    protected HotelList(Parcel in)
    {
        in.readList(this.hotel, (Hotel.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public HotelList()
    {
    }

    /**
     * @param hotel
     */
    public HotelList(List<Hotel> hotel)
    {
        super();
        this.hotel = hotel;
    }

    public List<Hotel> getHotel()
    {
        return hotel;
    }

    public void setHotel(List<Hotel> hotel)
    {
        this.hotel = hotel;
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeList(hotel);
    }

    public int describeContents()
    {
        return 0;
    }

}

