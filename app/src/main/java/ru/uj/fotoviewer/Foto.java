package ru.uj.fotoviewer;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by Блохин Евгений on 23.10.2017.
 */

public class Foto implements Serializable {

    private String id;

    private Uri mOutputFileUri;

    private String name;

    private String mCurrentPhotoPath;

    public Foto(String name, Uri mOutputFileUri, String mCurrentPhotoPath) {
        this.name = name;
        this.mOutputFileUri = mOutputFileUri;
        this.mCurrentPhotoPath = mCurrentPhotoPath;
    }

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

}
