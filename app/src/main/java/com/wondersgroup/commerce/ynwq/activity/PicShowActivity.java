package com.wondersgroup.commerce.ynwq.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.ynwq.adapter.PicShowAdapter;
import com.wondersgroup.commerce.ynwq.bean.PicBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bjy on 2017/4/18.
 */

public class PicShowActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    @Bind(R.id.layout_tab_img_show)
    ViewGroup layoutTab;
    @Bind(R.id.tv_index)
    TextView tvIndex;
    @Bind(R.id.tv_sum)
    TextView tvSum;
    @Bind(R.id.layout_back)
    ViewGroup layoutBack;
    @Bind(R.id.img_delete)
    ImageView imgDelete;

    private ArrayList<PicBean> images;
    private PicShowAdapter mAdapter;
    private boolean firstShow;
    private DeleteListener deleteListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pic_show);
        ButterKnife.bind(this);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!firstShow){
            firstShow = true;
            hideTab();
        }
    }

    private void init(){
        int index = getIntent().getIntExtra("position",0);
        images = getIntent().getParcelableArrayListExtra("images");

        tvIndex.setText(String.valueOf(index+1));
        tvSum.setText("/"+images.size());

        imgDelete.setOnClickListener(this);
        layoutBack.setOnClickListener(this);
        mAdapter = new PicShowAdapter(this,images);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(index);
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            int flag = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        flag = 1;
                        break;
                    case MotionEvent.ACTION_UP:
                        if (flag == 0){
                            if(layoutTab.getTranslationY()<0)
                                showTab();
                            else
                                hideTab();
                        }
                        flag = 0;
                        break;
                }
                return false;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (layoutTab.getTranslationY() == 0){
                    hideTab();
                }
            }

            @Override
            public void onPageSelected(int position) {
                tvIndex.setText(String.valueOf(mViewPager.getCurrentItem()+1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void hideTab(){
        ObjectAnimator
                .ofFloat(layoutTab,"translationY",0,-200)
                .setDuration(300)
                .start();

//        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,-200);
//        translateAnimation.setInterpolator(new AccelerateInterpolator());
//        translateAnimation.setDuration(300);
//        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                layoutTab.setVisibility(View.INVISIBLE);
//                layoutTab.clearAnimation();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        layoutTab.setAnimation(translateAnimation);
    }

    private void showTab(){
        ObjectAnimator
                .ofFloat(layoutTab,"translationY",-200,0)
                .setDuration(300)
                .start();

//        int positon = mViewPager.getCurrentItem();
//        if (images.get(positon).isLoaded())
//            imgDelete.setVisibility(View.VISIBLE);
//        else
//            imgDelete.setVisibility(View.GONE);
//        layoutTab.setVisibility(View.VISIBLE);
//        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,-200,0);
//        translateAnimation.setInterpolator(new AccelerateInterpolator());
//        translateAnimation.setDuration(300);
//        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                layoutTab.clearAnimation();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        layoutTab.setAnimation(translateAnimation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:
                break;
            case R.id.layout_back:
                back();
                break;
            case R.id.img_delete:
                if (deleteListener != null){
                    deleteListener.onDelete(mViewPager.getCurrentItem());
                }
                delete(mViewPager.getCurrentItem());
                break;
        }
    }

    public void delete(int position){
        int sumOld = images.size();
        images.remove(position);
        mAdapter.notifyDataSetChanged();
        if (sumOld != 1){
            if (position == sumOld -1){
                mViewPager.setCurrentItem(position-1);
            }else {
                mViewPager.setCurrentItem(position);
            }
            tvIndex.setText(String.valueOf(mViewPager.getCurrentItem()+1));
            tvSum.setText("/"+images.size());
        }else {
            back();
        }
    }

    public void back(){
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("images",images);
        setResult(-1,intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            back();
        }
        return super.onKeyDown(keyCode, event);
    }

    public interface DeleteListener{
        void onDelete(int position);
    }

    public void setDeleteListener(DeleteListener deleteListener) {
        this.deleteListener = deleteListener;
    }
}
