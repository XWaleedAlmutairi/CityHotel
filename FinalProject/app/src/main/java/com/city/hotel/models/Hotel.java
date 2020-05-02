package com.city.hotel.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hotel
        implements Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("hotel_details")
    @Expose
    private String hotelDetails;
    @SerializedName("image")
    @Expose
    private List<String> image = null;
    @SerializedName("comments")
    @Expose
    private List<Comment> comments = null;
    @SerializedName("hotel_detail")
    @Expose
    private String hotelDetail;
    public final static Parcelable.Creator<Hotel> CREATOR = new Creator<Hotel>()
    {


        @SuppressWarnings({
                                  "unchecked"
                          })
        public Hotel createFromParcel(Parcel in)
        {
            return new Hotel(in);
        }

        public Hotel[] newArray(int size)
        {
            return (new Hotel[size]);
        }

    };

    protected Hotel(Parcel in)
    {
        this.name = ((String)in.readValue((String.class.getClassLoader())));
        this.phone = ((String)in.readValue((String.class.getClassLoader())));
        this.website = ((String)in.readValue((String.class.getClassLoader())));
        this.address = ((String)in.readValue((String.class.getClassLoader())));
        this.longitude = ((Double)in.readValue((Double.class.getClassLoader())));
        this.latitude = ((Double)in.readValue((Double.class.getClassLoader())));
        this.hotelDetails = ((String)in.readValue((String.class.getClassLoader())));
        this.image=new ArrayList<>();
        in.readList(this.image, (java.lang.String.class.getClassLoader()));
        this.comments=new ArrayList<>();

        in.readList(this.comments, (Comment.class.getClassLoader()));
        this.hotelDetail = ((String)in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Hotel()
    {
    }

    /**
     * @param image
     * @param website
     * @param hotelDetails
     * @param address
     * @param comments
     * @param phone
     * @param latitude
     * @param name
     * @param hotelDetail
     * @param longitude
     */
    public Hotel(String name, String phone, String website, String address, Double longitude, Double latitude, String hotelDetails, List<String> image, List<Comment> comments, String hotelDetail)
    {
        super();
        this.name = name;
        this.phone = phone;
        this.website = website;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.hotelDetails = hotelDetails;
        this.image = image;
        this.comments = comments;
        this.hotelDetail = hotelDetail;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public Double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(Double longitude)
    {
        this.longitude = longitude;
    }

    public Double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(Double latitude)
    {
        this.latitude = latitude;
    }

    public String getHotelDetails()
    {
        return hotelDetails;
    }

    public void setHotelDetails(String hotelDetails)
    {
        this.hotelDetails = hotelDetails;
    }

    public List<String> getImage()
    {
        return image;
    }

    public void setImage(List<String> image)
    {
        this.image = image;
    }

    public List<Comment> getComments()
    {
        return comments;
    }

    public void setComments(List<Comment> comments)
    {
        this.comments = comments;
    }

    public String getHotelDetail()
    {
        return hotelDetail;
    }

    public void setHotelDetail(String hotelDetail)
    {
        this.hotelDetail = hotelDetail;
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeValue(name);
        dest.writeValue(phone);
        dest.writeValue(website);
        dest.writeValue(address);
        dest.writeValue(longitude);
        dest.writeValue(latitude);
        dest.writeValue(hotelDetails);
        dest.writeList(image);
        dest.writeList(comments);
        dest.writeValue(hotelDetail);
    }

    public int describeContents()
    {
        return 0;
    }
}
