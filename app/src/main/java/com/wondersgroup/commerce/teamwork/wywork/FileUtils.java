package com.wondersgroup.commerce.teamwork.wywork;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Base64;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.utils.OpenFileHelper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yclli on 2015/12/17.
 */
public class FileUtils {

    public void openFile(Context context,String fileName){
        final String savePathFinal = fileName;

        try {
            context.startActivity(
                    OpenFileHelper.openFile(savePathFinal));
        } catch (Exception e) {
            Toast.makeText(context, "不支持打开该类文件",
                    Toast.LENGTH_SHORT).show();
        }
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

    public static File getFileFromServer(String savePath, String urlPath, MaterialDialog pd) throws Exception{
        URL url = new URL(urlPath);
        HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        //获取到文件的大小
        int totalLength = conn.getContentLength();
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
        fos.close();
        bis.close();
        is.close();
        return file;
    }
}