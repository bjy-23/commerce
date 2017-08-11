package com.wondersgroup.commerce.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.ImageEntry;
import com.wondersgroup.commerce.model.ImageEntry;import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by yclli on 2016/2/23.
 */
public class PicCropActivity extends AppCompatActivity {

    private final String TAG = "PicCropActivity";
    String SAMPLE_CROPPED_IMAGE_NAME = "SampleCropImage.jpeg";
    ImageView resultView;
    String picOfString;
    Button okButton;
    //private ArrayList<ImageEntry> imageArrayList;
    private Uri mDestinationUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piccrop);
        resultView = (ImageView)findViewById(R.id.resultImage);
        okButton = (Button)findViewById(R.id.ok_button);
        mDestinationUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), SAMPLE_CROPPED_IMAGE_NAME));

//        okButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("characterStream", imageArrayList);
//                intent.putExtras(bundle);
//                setResult(RESULT_OK, intent);
//                finish();
//            }
//        });
            }

//    public void pickImage(View v){
//        new Picker.Builder(this,new MyPickerListener(),R.style.MIP_theme)
//                .build()
//                .startActivity();
//    }
//
//    private class MyPickerListener implements Picker.PickListener{
//        @Override
//        public void onPickedSuccessfully(ArrayList<ImageEntry> arrayList) {
//            ImageEntry entry=arrayList.get(0);
//            for(ImageEntry image:arrayList)
//                Log.d(TAG,"selected image.path = "+image.path + "----image.isPicked = "+image.isPicked);
//            imageArrayList = arrayList;
//            if(imageArrayList!=null && imageArrayList.size()>0)
//                okButton.setVisibility(View.VISIBLE);
////            UCrop.of(Uri.parse("file://" + entry.path), mDestinationUri)
////                    .withOptions(new UCrop.Options())
////                    .start(PicCropActivity.this);
//        }
//        @Override
//        public void onCancel() {
//        }
//    }

    protected void onActivityResult(int requestCode, int resultCode, Intent result) {

        Log.d(TAG,"onActivityResult------------requestCode = "+requestCode);
//        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
//            Uri resultUri = UCrop.getOutput(result);
//            try {
//                resultView.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri));
////                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
////                picOfString = bitmaptoString(bitmap, 100);
//                Log.d(TAG, "resultUri = " + resultUri.toString());
//                picOfString = resultUri.toString();
//                okButton.setVisibility(View.VISIBLE);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        } else if (resultCode == UCrop.RESULT_ERROR) {
//            final Throwable cropError = UCrop.getError(result);
//        }
        super.onActivityResult(requestCode, resultCode, result);
    }

}
