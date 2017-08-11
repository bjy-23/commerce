package com.wondersgroup.commerce.widget;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.utils.DWZH;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by 薛定猫 on 2016/3/8.
 */
public class ImageTableView extends LinearLayout {

    TextView title;
    RecyclerView images;

    private ImageGridAdapter adapter;
    private GridLayoutManager manager;

    private float textSize;
    private float titleMarginL;
    private float titleMarginT;
    private float titleMarginB;
    private float imageMarginLR;
    private float marginB;
    private float imagePaddingV;
    private List<Uri> imgSrcs;
    private boolean canAdd;
    Uri addImageUri;

    private int spanCount;
    private int textColor;

    private String titleString=null;

    private imagePickerListener imagePickerListener;

    public ImageTableView(Context context) {
        this(context,null);
    }

    public ImageTableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        textSize =14;
        textColor=ContextCompat.getColor(getContext(), R.color.deep_gray);
        titleMarginL= DWZH.dp2pt(getContext(), 22);
        titleMarginT=DWZH.dp2pt(getContext(),15);
        titleMarginB =DWZH.dp2pt(getContext(),12);
        imageMarginLR=DWZH.dp2pt(getContext(),40);
        imagePaddingV=DWZH.dp2pt(getContext(),15);
        spanCount=3;
        imgSrcs=new ArrayList<>();
        canAdd=false;
        addImageUri=Uri.parse("android.resource://com.wondersgroup.commerce/" + R.mipmap.add);
        LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(params);
        this.setOrientation(VERTICAL);
        this.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        initTitle(getContext());
        this.addView(title);
        initGrid(getContext());
        this.addView(images);
    }

    public void addImage(Uri uri){
        if(canAdd){
            imgSrcs.add(imgSrcs.size() - 1, uri);
        }else {
            imgSrcs.add(uri);
        }
        if(adapter!=null){
            if(canAdd){
                adapter.notifyItemInserted(imgSrcs.size() - 2);
            }else {
                adapter.notifyItemInserted(imgSrcs.size());
            }
            manager.requestLayout();
        }
    }

    public void addImages(List<Uri> uris){
        int curSize=imgSrcs.size();
        if (canAdd){
            imgSrcs.addAll(curSize-1,uris);
        }else {
            imgSrcs.addAll(uris);
        }
        if(adapter!=null){
            if(canAdd){
                adapter.notifyItemRangeInserted(curSize-1,uris.size());
            }else {
                adapter.notifyItemRangeInserted(curSize, uris.size());
            }
            manager.requestLayout();
        }
    }

    public void setTitle(String title){
        titleString=title;
        if(this.title!=null){
            this.title.setText(title);
        }
    }

    private void initTitle(Context context){
        title=new TextView(context);
        LayoutParams params=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins((int) titleMarginL, (int) titleMarginT, (int) titleMarginL, (int) titleMarginB);
        title.setLayoutParams(params);
        title.setTextSize(textSize);
        title.setTextColor(textColor);
        if(titleString!=null)title.setText(titleString);
    }

    private void initGrid(Context context){
        images = new RecyclerView(context);
        LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins((int) imageMarginLR, 0, (int) imageMarginLR, (int) titleMarginT);
        images.setLayoutParams(params);
        images.setHasFixedSize(true);
        manager=new GridLayoutManager(context,spanCount);
        images.setLayoutManager(manager);
        images.addItemDecoration(new GridSpacingItemDecoration(spanCount, 0, (int) imagePaddingV, false));
        adapter=new ImageGridAdapter(imgSrcs);
        adapter.setOnItemClickListener(new ImageGridAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {

                if (canAdd && position == imgSrcs.size() - 1) {
                   imagePickerListener.imagePicker();

                }

            }
        });
        images.setAdapter(adapter);
        images.setItemAnimator(new DefaultItemAnimator());
    }

//    public class MyPickerListener implements Picker.PickListener{
//        @Override
//        public void onPickedSuccessfully(ArrayList<ImageEntry> arrayList) {
//            List<Uri> tmp=new ArrayList<>();
//            for (ImageEntry e :
//                    arrayList) {
//                tmp.add(Uri.parse("file://" + e.path));
//            }
//            addImages(tmp);
//        }
//        @Override
//        public void onCancel() {
//        }
//    }
    public void setCanAdd(boolean canAdd) {
        this.canAdd = canAdd;
        if(canAdd){
            imgSrcs.add(addImageUri);
            if(adapter!=null){
                adapter.notifyItemInserted(0);
            }
        }
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public void setTitleMarginL(float titleMarginL) {
        this.titleMarginL = titleMarginL;
    }

    public void setTitleMarginT(float titleMarginT) {
        this.titleMarginT = titleMarginT;
    }

    public void setTitleMarginB(float titleMarginB) {
        this.titleMarginB = titleMarginB;
    }

    public void setImageMarginLR(float imageMarginLR) {
        this.imageMarginLR = imageMarginLR;
    }

    public void setMarginB(float marginB) {
        this.marginB = marginB;
    }

    public void setImagePaddingV(float imagePaddingV) {
        this.imagePaddingV = imagePaddingV;
    }

    public void setImgSrcs(List<Uri> imgSrcs) {
        int curSize=this.imgSrcs.size();
        this.imgSrcs.clear();
        if(adapter!=null){
            adapter.notifyItemRangeRemoved(0,curSize);
        }
        this.imgSrcs.addAll(imgSrcs);
        if(canAdd){
            this.imgSrcs.add(addImageUri);
        }
        if(adapter!=null){
            adapter.notifyItemRangeInserted(0, imgSrcs.size());
        }
        if(manager!=null){
            manager.requestLayout();
        }
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    private static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacingH;
        private int spacingV;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacingH,int spacingV, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacingH = spacingH;
            this.spacingV=spacingV;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacingH - column * spacingH / spanCount; // spacingH - column * ((1f / spanCount) * spacingH)
                outRect.right = (column + 1) * spacingH / spanCount; // (column + 1) * ((1f / spanCount) * spacingH)

                if (position < spanCount) { // top edge
                    outRect.top = spacingV;
                }
                outRect.bottom = spacingV; // item bottom
            } else {
                if(column==0){
                    outRect.left=0;
                    outRect.right=spacingH;
                }else if((column+1)%spanCount==0){
                    outRect.left=spacingH;
                    outRect.right=0;
                }
                if (position >= spanCount) {
                    outRect.top = spacingV; // item top
                }
            }
        }
    }

    private static class ImageGridAdapter extends RecyclerView.Adapter<ImageGridAdapter.ViewHolder>{
        private List<Uri> imageSources;

        public ImageGridAdapter(List<Uri> imageSources) {
            this.imageSources = imageSources;
        }

        public interface OnItemClickListener{
            void OnItemClick(View view, int position);
        }

        private static OnItemClickListener listener;

        public void setOnItemClickListener(OnItemClickListener listener){
            this.listener=listener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ImageView imageView=new ImageView(parent.getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new ViewGroup.LayoutParams((int)DWZH.dp2pt(parent.getContext(),60),(int)DWZH.dp2pt(parent.getContext(),60)));
            ViewHolder holder=new ViewHolder(imageView);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Picasso.with(holder.image.getContext()).load(imageSources.get(position)).resize(360,360).centerInside().into(holder.image);
        }

        @Override
        public int getItemCount() {
            return imageSources.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            ImageView image;
            public ViewHolder(ImageView itemView) {
                super(itemView);
                image=itemView;
                image.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.OnItemClick(v,getLayoutPosition());
                }
            }
        }
    }



    public interface imagePickerListener{

        void imagePicker();

    }

    public void setImagePickerListener(ImageTableView.imagePickerListener imagePickerListener) {
        this.imagePickerListener = imagePickerListener;
    }

    public List<Uri> getImgSrcs() {
        return imgSrcs;
    }
}
