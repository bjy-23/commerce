package com.wondersgroup.commerce.teamwork.dailycheck;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BitMapUtil {
	/**
	 * base64转为bitmap
	 * 
	 * @param base64Data
	 * @return
	 */
	public static Bitmap base64ToBitmap(String base64Data) {
		byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
		// File image = new File(Environment.getExternalStorageDirectory(),
		// "sunhapper.jpg");
		// try {
		// FileOutputStream out = new FileOutputStream(image);
		// out.write(bytes, 0, bytes.length);
		// out.close();
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		Options options = new Options();
		options.inDither = false; /* 不进行图片抖动处理 */
		options.inPreferredConfig = null; /* 设置让解码器以最佳方式解码 */
		// options.inSampleSize = 2;/* 图片长宽方向缩小倍数 */
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
	}

	@SuppressLint("NewApi")
	public static String bitmapToBase64(Bitmap bitmap) {

		String result = null;
		ByteArrayOutputStream baos = null;
		try {
			if (bitmap != null) {
				baos = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 30, baos);

				baos.flush();
				baos.close();

				byte[] bitmapBytes = baos.toByteArray();
				result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.flush();
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
