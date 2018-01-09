package ru.uj.fotoviewer;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Блохин Евгений on 23.10.2017.
 */

public class Foto implements Parcelable {

    private String id;

    private Uri mOutputFileUri;

    private String name;

    private String mCurrentPhotoPath;

    public Foto(String name, Uri mOutputFileUri, String mCurrentPhotoPath) {
        this.name = name;
        this.mOutputFileUri = mOutputFileUri;
        this.mCurrentPhotoPath = mCurrentPhotoPath;
    }

    protected Foto(Parcel in) {
        id = in.readString();
        mOutputFileUri = in.readParcelable(Uri.class.getClassLoader());
        name = in.readString();
        mCurrentPhotoPath = in.readString();
    }

    public static final Creator<Foto> CREATOR = new Creator<Foto>() {
        @Override
        public Foto createFromParcel(Parcel in) {
            return new Foto(in);
        }

        @Override
        public Foto[] newArray(int size) {
            return new Foto[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Uri getmOutputFileUri() {
        return mOutputFileUri;
    }

    public void setmOutputFileUri(Uri mOutputFileUri) {
        this.mOutputFileUri = mOutputFileUri;
    }

    public String getmCurrentPhotoPath() {
        return mCurrentPhotoPath;
    }

    public void setmCurrentPhotoPath(String mCurrentPhotoPath) {
        this.mCurrentPhotoPath = mCurrentPhotoPath;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(mOutputFileUri, flags);
        dest.writeString(name);
        dest.writeString(mCurrentPhotoPath);
    }
}
