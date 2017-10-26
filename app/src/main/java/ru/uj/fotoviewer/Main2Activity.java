package ru.uj.fotoviewer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private static final String JPEG_FILE_PREFIX = "fotoviewer";
    private static final String JPEG_FILE_SUFFIX = ".jpg";
    String mCurrentPhotoPath;
    private Uri mOutputFileUri;
    ImageView mImageView;
    private final int CAMERA_RESULT = 1;
    Button button_save;
    Button button_photograph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

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
//                File file = new File(Environment.getExternalStorageDirectory(),
//                        "test.jpg");
                try {
                    mOutputFileUri = Uri.fromFile(createImageFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                mOutputFileUri = Uri.fromFile(file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutputFileUri);
                startActivityForResult(intent, CAMERA_RESULT);
                break;
            default:
                break;
        }
    }


//Системное приложение Камера кодирует фото в возвращаемом намерении, которое поступает в метод onActivityResult() в виде небольшого Bitmap в ключе data.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_RESULT) {
            Bitmap thumbnailBitmap = (Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(thumbnailBitmap);
        } else mImageView.setImageURI(mOutputFileUri); // Не работает
    }


//    //Необходимо позаботиться об уникальности имени файла, чтобы избежать конфликтов:
    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
        File image = File.createTempFile(
                imageFileName,
                JPEG_FILE_SUFFIX,
//                Environment.getExternalStorageDirectory()
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        );
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
//
//    //    Если у нас есть место для сохранения изображения, то сообщите путь приложению камеры через намерение:
//    File f = createImageFile();
//        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(f));
//
//    //    метод вызова системного медиа-сканера, чтобы добавить вашу фотографию в базу данных Media Provider, что сделает её видимой в приложении Галерея и других приложениях.
//    private void galleryAddPic() {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        File f = new File(mCurrentPhotoPath);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        this.sendBroadcast(mediaScanIntent);
//    }

