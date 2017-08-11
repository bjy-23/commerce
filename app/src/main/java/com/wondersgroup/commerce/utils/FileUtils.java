package com.wondersgroup.commerce.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wondersgroup.commerce.constant.Constants;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

/**
 * Created by yclli on 2015/12/17.
 */
public class FileUtils {

    public static String saveImageName(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

        String name = year + "_" + month + "_" + date + "_" + hour + "_" + minute + "_" + second + ".jpg";
        name = Environment.getExternalStorageDirectory()+"/DCIM/Camera/"+name;

        return name;
    }

    public static void saveImageToGallery(Context context, File file) {
        /*// 把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        // 通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(file.getPath()))));
    }

    public static String bitmaptoString(Bitmap bitmap, int bitmapQuality) {

        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, bitmapQuality, bStream);
        byte[] bytes = bStream.toByteArray();

        Log.i("size++", bytes.length + "");
        string = Base64.encodeToString(bytes, Base64.DEFAULT);

        bitmap.recycle();
        return string;
    }

    public static Bitmap stringtoBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
                    bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public void decoderBase64File(Context context,  String base64Code, String savePath) throws Exception {
        //byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        byte[] buffer = Base64.decode(base64Code, Base64.DEFAULT);
        FileOutputStream out = new FileOutputStream(savePath);
        out.write(buffer);
        out.close();

        final String savePathFinal = savePath;

        try {
            context.startActivity(
                    OpenFileHelper.openFile(savePathFinal));
        } catch (Exception e) {
            Toast.makeText(context, "不支持打开该类文件",
                    Toast.LENGTH_SHORT).show();
        }

//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setMessage("确定打开文件？");
//        builder.setTitle("提示");
//        builder.setPositiveButton("打开", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//
//                try {
//                    context.startActivity(
//                            OpenFileHelper.openFile(savePathFinal));
//                } catch (Exception e) {
//                    Toast.makeText(getActivity(), "不支持打开该类文件",
//                            Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.create().show();
    }

    public boolean deleteFilesByDirectory(File directory) {
        boolean isDeleted = true;
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                isDeleted = item.delete();
            }
            return isDeleted;
        }else
            return false;
    }

    /*public static File getFileFromServer(String savePath, String urlPath, ProgressDialog pd) throws Exception{
        URL url = new URL(urlPath);
        HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        //获取到文件的大小
        pd.setMax(conn.getContentLength());
        InputStream is = conn.getInputStream();
        File file = new File(savePath, "update.apk");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedInputStream bis = new BufferedInputStream(is);
        byte[] buffer = new byte[1024];
        int len;
        int total=0;
        while((len =bis.read(buffer))!=-1){
            fos.write(buffer, 0, len);
            total+= len;
            //获取当前下载量
            pd.setProgress(total);
        }
        fos.close();
        bis.close();
        is.close();
        return file;
    }*/

    public static File getFileFromServer(String savePath, int size, String urlPath, MaterialDialog pd) throws Exception{
        URL url = new URL(urlPath);
        HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        //获取到文件的大小
        int totalLength = size;
        InputStream is = conn.getInputStream();
        File file = new File(savePath, "update.apk");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedInputStream bis = new BufferedInputStream(is);
        byte[] buffer = new byte[1024];
        int len;
        int total=0;
        while((len =bis.read(buffer))!=-1){
            fos.write(buffer, 0, len);
            total+= len;
            //获取当前下载量
            pd.setProgress(total*100/totalLength);
        }
        pd.setProgress(100);
        fos.close();
        bis.close();
        is.close();
        return file;
    }
}