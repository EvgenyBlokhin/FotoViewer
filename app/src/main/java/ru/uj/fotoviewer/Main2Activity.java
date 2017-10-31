package ru.uj.fotoviewer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private Foto foto;
    private ImageView mImageView;
    private final int CAMERA_RESULT = 1;
    private Button button_save;
    private Button button_photograph;
    private File directory;
    final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        createDirectory();
        button_photograph = (Button) findViewById(R.id.button_photograph);
        button_save = (Button) findViewById(R.id.button_save);
        mImageView = (ImageView) findViewById(R.id.imageView);
        button_photograph.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_photograph:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri());
                startActivityForResult(intent, CAMERA_RESULT);
                break;
            case R.id.button_save:

                break;
                default:
                    break;
        }
    }

    //Системное приложение Камера кодирует фото в возвращаемом намерении, которое поступает в метод onActivityResult() в виде небольшого Bitmap в ключе data.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

//        Основной код
//        if (requestCode == CAMERA_RESULT) {
//            Bitmap thumbnailBitmap = (Bitmap) data.getExtras().get("data");
//            mImageView.setImageBitmap(thumbnailBitmap);
//        } else mImageView.setImageURI(mOutputFileUri); // Не работает

// Для теста
        if (resultCode == RESULT_OK) {
            if (data == null) {
                Log.d(TAG, "Intent is null");
            } else {
                Log.d(TAG, "Photo uri: " + data.getData());
                Bundle bndl = data.getExtras();
                if (bndl != null) {
                    Object obj = data.getExtras().get("data");
                    if (obj instanceof Bitmap) {
                        Bitmap bitmap = (Bitmap) obj;
                        Log.d(TAG, "bitmap " + bitmap.getWidth() + " x "
                                + bitmap.getHeight());
                        mImageView.setImageBitmap(bitmap);
                    }
                }
            }
        } else if (resultCode == RESULT_CANCELED) {
            Log.d(TAG, "Canceled");
        }
//        Второй вариант
//        Picasso.with(this).load(foto.getmCurrentPhotoPath()).fit().into(mImageView);
    }

    private Uri generateFileUri() {
        File image = new File(directory.getPath() + "/" + "photo_"
                + System.currentTimeMillis() + ".jpg");
        Log.d(TAG, "fileName = " + image);
        foto = new Foto(image, Uri.fromFile(image), image.getAbsolutePath());
        return foto.getmOutputFileUri();
    }

    private void createDirectory() {
        directory = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MyFolder");
        if (!directory.exists())
            directory.mkdirs();
    }
}
//    //    метод вызова системного медиа-сканера, чтобы добавить вашу фотографию в базу данных Media Provider, что сделает её видимой в приложении Галерея и других приложениях.
//    private void galleryAddPic() {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        File f = new File(mCurrentPhotoPath);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        this.sendBroadcast(mediaScanIntent);
//    }

