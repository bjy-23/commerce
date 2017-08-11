package com.wondersgroup.commerce.ynwq.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Callback;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.ynwq.bean.PicBean;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by bjy on 2017/4/18.
 */

public class PicShowAdapter extends PagerAdapter {
    private Context mContext;
    private List<PicBean> data;
    private Picasso picasso;

    public PicShowAdapter(Context mContext, List<PicBean> data) {
        this.mContext = mContext;
        this.data = data;
//        OkHttpClient okHttpClient = new OkHttpClient();
//        okHttpClient.setConnectTimeout(20, TimeUnit.SECONDS);
//        okHttpClient.setReadTimeout(20, TimeUnit.SECONDS);
//        okHttpClient.setWriteTimeout(20, TimeUnit.SECONDS);
//        picasso = new Picasso.Builder(mContext)
//                .downloader(new OkHttpDownloader(okHttpClient))
//                .build();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pic_show,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        final TextView tvRemind = (TextView)view.findViewById(R.id.tv_remind);
        PicBean picBean = data.get(position);
        if (picBean.getType() == 0){
            Picasso.with(mContext)
                    .load(picBean.getPicPath())
                    .centerCrop()
                    .resize(RootAppcation.getInstance().getWidthPixels()-100,RootAppcation.getInstance().getHeightPixels()-100)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            tvRemind.setVisibility(View.GONE);
//                            data.get(position).setLoaded(true);
                        }

                        @Override
                        public void onError() {
                            tvRemind.setText("加载失败！");
                        }
                    });
        }else {
            Uri uri = Uri.fromFile(new File(picBean.getPicPath()));
            Picasso.with(mContext)
                    .load(uri)
                    .centerCrop()
                    .resize(RootAppcation.getInstance().getWidthPixels()-100,RootAppcation.getInstance().getHeightPixels()-100)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            tvRemind.setVisibility(View.GONE);
//                            data.get(position).setLoaded(true);
                        }

                        @Override
                        public void onError() {
                            tvRemind.setText("加载失败！");
                        }
                    });
        }

        container.addView(view);
        return view;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
