package com.city.hotel.models;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment
        implements Serializable, Parcelable
{

    @SerializedName("usern_name")
    @Expose
    private String usernName;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("user_image")
    @Expose
    private String userImage;
    public final static Parcelable.Creator<Comment> CREATOR = new Creator<Comment>()
    {


        @SuppressWarnings({
                                  "unchecked"
                          })
        public Comment createFromParcel(Parcel in)
        {
            return new Comment(in);
        }

        public Comment[] newArray(int size)
        {
            return (new Comment[size]);
        }

    };

    protected Comment(Parcel in)
    {
        this.usernName = ((String)in.readValue((String.class.getClassLoader())));
        this.comment = ((String)in.readValue((String.class.getClassLoader())));
        this.rating = ((Double)in.readValue((Double.class.getClassLoader())));
        this.userImage = ((String)in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Comment()
    {
    }

    /**
     * @param usernName
     * @param userImage
     * @param rating
     * @param comment
     */
    public Comment(String usernName, String comment, Double rating, String userImage)
    {
        super();
        this.usernName = usernName;
        this.comment = comment;
        this.rating = rating;
        this.userImage = userImage;
    }

    public String getUsernName()
    {
        return usernName;
    }

    public void setUsernName(String usernName)
    {
        this.usernName = usernName;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public Double getRating()
    {
        return rating;
    }

    public void setRating(Double rating)
    {
        this.rating = rating;
    }

    public String getUserImage()
    {
        return userImage;
    }

    public void setUserImage(String userImage)
    {
        this.userImage = userImage;
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeValue(usernName);
        dest.writeValue(comment);
        dest.writeValue(rating);
        dest.writeValue(userImage);
    }

    public int describeContents()
    {
        return 0;
    }
}

