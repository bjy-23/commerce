package com.wondersgroup.commerce.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.RootActivity;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.model.AttachResultObject;
import com.wondersgroup.commerce.model.AttachmentDTO;
import com.wondersgroup.commerce.model.DataVolume;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.CaseApi;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Lee on 2016/2/22.
 * 用于创建动态控件
 * List<DataVolume> 控件对象列表
 * ArrayList<EditText> 动态加载的文本框
 * Context
 */
public class DynamicWidgetUtils extends RootActivity {

    public static final int DOWNLOAD_RESULT_SUCCESS = 1;
    public static final int DOWNLOAD_RESULT_NET_ERROR = 2;
    public static final int DOWNLOAD_RESULT_DATA_ERROR = 3;
    public static final int DOWNLOAD_RESULT_RESPONSE_ERROR = 4;

    private String TAG = "DynamicWidgetUtils";
    private Context mContext;
    private List<DataVolume> dataVolumeList;            //控件对象列表
    private ArrayList<EditText> editTextArrayList;      //动态加载的控件列表
    private ArrayList<GridViewData> imageArrayList;       //附件图片列表
    private ArrayList<AttachmentDTO> attachList;        //附件列表
    private LinearLayout componentsLinearLayout;        //控件显示部分的LinearLayout
    private int viewTagNo = 0;                          //动态控件tag
    private MyGridView gridView;                        //附件浏览控件
    private Button timeDialogBtnYes;                    //时间对话框Yes按钮
    private Button timeDialogBtnNo;                     //时间对话框No按钮
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams paramsDivider = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,2);
    LinearLayout.LayoutParams paramsTextView = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams paramsEdittext = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT);
    private int requestCode;
    private MyHandler myHandler = new MyHandler();
    private int attachDownloaderIndex = 0;
    private int deleteAttachIndex = 0;
    private View popupWinImgView;
    private ImageView pictureDetailImageView;
    private ImageView deletePictureImageView;
    private TextView pictureName;
    private ImageShowDialog imgDialog;
    private int type = 1;

    public DynamicWidgetUtils(Context context, List<DataVolume> dataList, ArrayList<EditText> widgetList) {

        mContext = context;
        dataVolumeList = dataList;
        editTextArrayList = widgetList;
        paramsTextView.weight   =   2;
        paramsEdittext.weight   =   5;
    }

    public void addComponents(LinearLayout linearLayout) {

        componentsLinearLayout = linearLayout;

        if(CheckData() == false)
            return;

        for (DataVolume voData : dataVolumeList) {
            Log.d(TAG, "=Key = " + voData.getKey() + "---Type =" + voData.getType() + "---Value=" + voData.getValue());
            addComponentView(voData);
        }

        //初始化图片浏览弹出窗口
        LayoutInflater inflater = LayoutInflater.from(mContext);
        popupWinImgView = inflater.inflate(R.layout.view_popup_image_win,null);
        pictureDetailImageView = (ImageView)popupWinImgView.findViewById(R.id.imageview_popup_window);
        deletePictureImageView = (ImageView)popupWinImgView.findViewById(R.id.view_popup_delete_imageview);
        pictureName = (TextView)popupWinImgView.findViewById(R.id.image_name_popup_window);
        imgDialog = new ImageShowDialog(mContext);
        imgDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        imgDialog.setContentView(popupWinImgView, new ViewGroup.LayoutParams(600, 700));

        deletePictureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageArrayList.remove(deleteAttachIndex);
                imgDialog.dismiss();
                gridView.setAdapter(new ImageAdapter(mContext));
            }
        });

    }

    private void addGridView() {

        gridView = new MyGridView(mContext);
        gridView.setColumnWidth(20);
        gridView.setNumColumns(3);
        gridView.setVerticalSpacing(20);
        gridView.setHorizontalSpacing(20);
        gridView.setGravity(Gravity.CENTER);
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setAdapter(new ImageAdapter(mContext));
        componentsLinearLayout.addView(gridView, params);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (imgDialog != null) {
                    pictureDetailImageView.setImageBitmap(imageArrayList.get(position).getBitmap());
                    Log.d(TAG, "imageArrayList.get(position).getAttachName() = "+imageArrayList.get(position).getAttachName());
                    pictureName.setText(imageArrayList.get(position).getAttachName());
                    imgDialog.show();
                    deleteAttachIndex = position;
                }
            }
        });
    }

    private boolean CheckData() {

        if(componentsLinearLayout == null){
            Log.d(TAG,"动态加载组件类传入的linearLayout为空！！！");
            return false;
        }else if(mContext == null){
            Log.d(TAG,"动态加载组件类传入的Context为空！！！");
            return false;
        }else if(dataVolumeList == null){
            Log.d(TAG,"动态加载组件类传入的dataVolumeList为空！！！");
            return false;
        }else if(editTextArrayList == null){
            Log.d(TAG,"动态加载组件类传入的editTextArrayList为空！！！");
            return false;
        }else
           return true;
    }


    //加载单个控件
    private void addComponentView(final DataVolume dataVolume){

        viewTagNo++;
        Log.d(TAG,"addComponentView()--------------dataVolume.getName() = "+dataVolume.getName());
        TextView textViewtemp = new TextView(mContext);
        textViewtemp.setText(dataVolume.getName());//字段名称
        textViewtemp.setTextColor(mContext.getResources().getColor(R.color.gray));
        textViewtemp.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14); //SP
        EditText editTexttemp = new EditText(mContext);
        editTexttemp.setTextColor(mContext.getResources().getColor(R.color.deep_gray));
        if(dataVolume.getValue()!=null && !dataVolume.getValue().equals(""))//字段值
            editTexttemp.setText(dataVolume.getValue());//字段值
        else{
            editTexttemp.setText("");
            editTexttemp.setHint("请输入" + dataVolume.getName());
            editTexttemp.setHintTextColor(mContext.getResources().getColor(R.color.hint_color));
        }
        editTexttemp.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14); //SP
        editTexttemp.setTag(viewTagNo);
        editTextArrayList.add(editTexttemp);
        editTexttemp.setFocusable(false);
        editTexttemp.setFocusableInTouchMode(false);
        editTexttemp.setBackgroundColor(mContext.getResources().getColor(android.R.color.transparent));

        LinearLayout linearLayouttemp = new LinearLayout(mContext);
        linearLayouttemp.setOrientation(LinearLayout.HORIZONTAL);
        linearLayouttemp.addView(textViewtemp, paramsTextView);
        linearLayouttemp.addView(editTexttemp, paramsEdittext);
        componentsLinearLayout.addView(linearLayouttemp, params);

        ImageView imageViewLine = new ImageView(mContext);
        imageViewLine.setLayoutParams(new LinearLayout.LayoutParams(GridLayout.LayoutParams.FILL_PARENT, 2));
        imageViewLine.setBackgroundColor(mContext.getResources().getColor(R.color.linecolor));
        componentsLinearLayout.addView(imageViewLine, paramsDivider);

        if(dataVolume.getType().equals("4"))
            getAttchments(dataVolume.getRemark());

        final int finalI = viewTagNo;
        editTexttemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (Integer.parseInt(dataVolume.getType())) {
                    case 1: //输入框
                        for (int i=0;i<editTextArrayList.size();i++){
                            if(((Integer)editTextArrayList.get(i).getTag())==finalI){
                                editTextArrayList.get(i).setFocusable(true);
                                editTextArrayList.get(i).setFocusableInTouchMode(true);
                                editTextArrayList.get(i).setSingleLine();
                            }
                        }

                    case 7: //文本框
                        Log.d(TAG,"这是一个输入框");
                        for (int i=0;i<editTextArrayList.size();i++){
                            /*if(editTextArrayList.get(i).getTag()==finalI){
                                editTextArrayList.get(i).setFocusable(true);
                                editTextArrayList.get(i).setFocusableInTouchMode(true);
                                editTextArrayList.get(i).setSingleLine();
                            };*/
                        }
                        break;
                    case 2:
                        //单选框
//                        Toast.makeText(mContext, "这是一个单选框", Toast.LENGTH_SHORT).show();
                        if(dataVolume.getRemark()!=null && dataVolume.getRemark().size()>0){
                            String []array1 = new String[dataVolume.getRemark().size()];
                            for (int k=0;k<array1.length;k++){
                                array1[k] = dataVolume.getRemark().get(k).getValue();
                            }
                            createRadioDialog(array1, finalI);
                        }
                        break;
//                    case 3:
//                        Toast.makeText(MainActivity.this, "这是一个复选框", Toast.LENGTH_SHORT).show();
//                        ArrayList<String> arrayList3 = new ArrayList<String>();
//                        try {
//                            JSONArray remark = new JSONArray(jsonObject.getString("remark"));
//                            for (int j = 0; j < remark.length(); j++) {
//                                JSONObject jsonObject1 = (JSONObject) remark.get(j);
//                                arrayList3.add(jsonObject1.getString("value"));
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        String []array3 = new String[arrayList3.size()];
//                        for (int k=0;k<array3.length;k++){
//                            array3[k] = arrayList3.get(k);
//                        }
//                        arrayList3.clear();
//                        createMultiChoiceDialog(array3, finalI);
//                        break;
                    case 4://附件选择
                        Log.d(TAG, "这是一个附件");
                        //Intent intent = new Intent(mContext, PicCropActivity.class);
                        //requestCode = 100;
                        //((Activity)mContext).startActivityForResult(intent, requestCode);
//                        new Picker.Builder(mContext, new MyPickerListener(), R.style.MIP_theme)
//                                .build()
//                                .startActivity();
                        break;
                    case 5://时间选择
                        Log.d(TAG,"这是一个时间");
                        createTimeDialog(finalI);
                        break;
                    case 6:
                        Log.d(TAG, "这是一个是非");
                        break;
                }
            }

        });

    }

//    private class MyPickerListener implements Picker.PickListener{
//        @Override
//        public void onPickedSuccessfully(ArrayList<ImageEntry> arrayList) {
//            for(ImageEntry image:arrayList)
//                Log.d(TAG,"selected image.path = "+image.path + "----image.isPicked = "+image.isPicked);
//            imageList = arrayList;
//            if(imageList!=null && imageList.size()>0)
//                refreshGridView(arrayList);
//        }
//        @Override
//        public void onCancel() {
//        }
//    }

    private void getAttchments(List<DataVolume> remarkList) {

        if(remarkList == null || remarkList.size() == 0)
            return;
        else
            attachList = new ArrayList<AttachmentDTO>();

        for(DataVolume data : remarkList){
            AttachmentDTO attchment = new AttachmentDTO();
            attchment.setAttachId(data.getKey());
            attchment.setAttachName(data.getValue());
            attachList.add(attchment);
        }
        downLoaderAttachment(attachList.get(attachDownloaderIndex).getAttachId());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult!,requestCode = " + requestCode);
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                Bundle b=data.getExtras(); //data为B中回传的Intent
                String str=b.getString("characterStream");//str即为回传的bitmap的字符流
                Log.d(TAG,"str = "+str);
                break;
            default:
                break;
        }
    }



    //时间选择对话框
    public void createTimeDialog(final int finalI){
        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.dialog_time);
        dialog.show();

        //点击取消按钮
        timeDialogBtnNo = (Button) dialog.findViewById(R.id.btn_no);
        timeDialogBtnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        timeDialogBtnYes = (Button) dialog.findViewById(R.id.btn_yes);
        timeDialogBtnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker dp = (DatePicker) dialog.findViewById(R.id.datepicker);
                int year = dp.getYear();
                int month = dp.getMonth() + 1;
                int day = dp.getDayOfMonth();

                TimePicker tp = (TimePicker) dialog.findViewById(R.id.timepicker);
                int hour = tp.getCurrentHour();
                int hour1 = 0;
                if (hour > 12) {
                    hour1 = hour - 12;
                } else {
                    hour1 = hour;
                }

                String minute = "";
                if (tp.getCurrentMinute() < 10) {
                    minute = "0" + tp.getCurrentMinute();
                } else {
                    minute = tp.getCurrentMinute() + "";
                }
                String time = (year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day) + " " + (hour < 10 ? "0" + hour : hour) + ":" + minute + ":00");
                for (int i = 0; i < editTextArrayList.size(); i++) {
                    if (((Integer)editTextArrayList.get(i).getTag()) == finalI) {
                        editTextArrayList.get(i).setText(time);
                    }
                }
                dialog.dismiss();
            }
        });
    }

    //单选对话框
    public void createRadioDialog(String[] a,  final int finalI){

        new MaterialDialog.Builder(mContext)
                .title("请选择")
                .items(a)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        /**
                         * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                         * returning false here won't allow the newly selected radio button to actually be selected.
                         **/
                        for (int i = 0; i < editTextArrayList.size(); i++) {
                            if (((Integer)editTextArrayList.get(i).getTag()) == finalI) {
                                editTextArrayList.get(i).setText(text);
                            }
                        }
//                        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
                        return true;
                    }
                })
                .positiveText("确定")
                .show();

    }

//    public void refreshGridView(ArrayList<ImageEntry> imageList) {
//        if(imageArrayList == null)
//            imageArrayList = new ArrayList<GridViewData>();
//        for(ImageEntry img:imageList){
//            int index = img.path.lastIndexOf("/");
//            Bitmap bitmap = BitmapFactory.decodeFile(img.path);
//            GridViewData gridviewdata = new GridViewData(img.path.substring(index+1,img.path.length()), bitmap);
//            imageArrayList.add(gridviewdata);
//        }
//        if(null == gridView)
//            addGridView();
//        else
//            gridView.setAdapter(new ImageAdapter(mContext));
//    }

    public class MyGridView extends GridView{
        public MyGridView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public MyGridView(Context context) {
            super(context);
        }

        public MyGridView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        }
    }

    public class ImageAdapter extends BaseAdapter {
        private Context adapterContext;

        public ImageAdapter(Context c)
        {
            adapterContext=c;
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return imageArrayList.size();
        }
        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }
        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ImageView imageview;
            if(convertView==null)
            {
                imageview=new ImageView(adapterContext);
                imageview.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageview.setPadding(8,8,8,8);
            }
            else
            {
                imageview=(ImageView) convertView;
            }
            imageview.setImageBitmap(imageArrayList.get(position).getBitmap());
            return imageview;
        }
    }

    private Bitmap stringtoBitmap(String string) {
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

    private String bitmaptoString(Bitmap bitmap, int bitmapQuality) {

        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, bitmapQuality, bStream);
        byte[] bytes = bStream.toByteArray();

        Log.i("size++", bytes.length + "");
        string = Base64.encodeToString(bytes, Base64.DEFAULT);

        bitmap.recycle();
        return string;
    }

    public class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            attachDownloaderIndex++;
            if(attachDownloaderIndex == attachList.size()){
                Log.d(TAG,"MyHandler-----attachDownloaderIndex = "+attachDownloaderIndex);
                showDownLoadAttachments();
                return;
            }
            switch (msg.what){
                case DOWNLOAD_RESULT_SUCCESS:
                case DOWNLOAD_RESULT_DATA_ERROR:
                case DOWNLOAD_RESULT_RESPONSE_ERROR:
                    Log.d(TAG,"MyHandler-----msg.what = "+msg.what+"attachList.get(attachDownloaderIndex).getAttachId() = "+attachList.get(attachDownloaderIndex).getAttachId());
                    downLoaderAttachment(attachList.get(attachDownloaderIndex).getAttachId());
                    break;
                case DOWNLOAD_RESULT_NET_ERROR:
                    Log.d(TAG,"MyHandler-----msg.what = "+msg.what);
                    break;
                default:
                    break;
            }
        }

    }

    private void showDownLoadAttachments() {
        if(imageArrayList == null){
            imageArrayList = new ArrayList<GridViewData>();
            for(AttachmentDTO attach:attachList){
                GridViewData gridViewData = new GridViewData(attach.getAttachId(), attach.getAttachName(), attach.getAttachFileStr());
                gridViewData.initBitmap();
                imageArrayList.add(gridViewData);
            }
        }
        addGridView();
    }

    private void downLoaderAttachment(String attachId){

        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010015");
        map.put("attachId", attachId);
        String url = "";
        if (type == 1)
            url = CaseApi.URL_CASE_1 + CaseApi.DOWNLAODER_ATTACH;
        else
            url = CaseApi.URL_CASE_2 + CaseApi.DOWNLAODER_ATTACH;
        Call<AttachResultObject> call = ApiManager.caseApi.downLoadAttchment(url,map);
        call.enqueue(new Callback<AttachResultObject>() {
            @Override
            public void onResponse(Response<AttachResultObject> response, Retrofit retrofit) {
                Log.d(TAG,"onResponse()---------------1");
                Message message = new Message();
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    final AttachResultObject attachObject = response.body();

                    if (null == attachObject) {
                        Log.d(TAG,"onResponse()---------------2");
                        Toast.makeText(mContext, "下载附件数据错误1", Toast.LENGTH_SHORT).show();
                        message.what = DOWNLOAD_RESULT_DATA_ERROR;
                    }else if(null == attachObject.getResult()){
                        Log.d(TAG,"onResponse()---------------3");
                        Toast.makeText(mContext, "下载附件数据错误2", Toast.LENGTH_SHORT).show();
                        message.what = DOWNLOAD_RESULT_DATA_ERROR;
                    } else{
                        attachList.get(attachDownloaderIndex).setAttachFileStr(attachObject.getResult().getAttachFileStr());
                        message.what = DOWNLOAD_RESULT_SUCCESS;
                    }

//                    if ((null == attachObject) || (null == attachObject.getCaseInfo())) {
//                        Toast.makeText(mContext, "下载附件数据错误", Toast.LENGTH_SHORT).show();
//                        message.what = DOWNLOAD_RESULT_DATA_ERROR;
//                    } else{
//                        attachList.get(attachDownloaderIndex).setAttachFileStr(attachObject.getCaseInfo());
//                        message.what = DOWNLOAD_RESULT_SUCCESS;
//                    }
                } else {
                    Log.d(TAG, "CaseInspectActivity --------------- response.is not Success()");
                    message.what = DOWNLOAD_RESULT_RESPONSE_ERROR;
                    Toast.makeText(mContext, "服务器信息有误", Toast.LENGTH_SHORT).show();
                }
                myHandler.sendMessage(message);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(mContext, "服务器连接错误", Toast.LENGTH_SHORT).show();
                Log.d(TAG,"onFailure()-------------t.getMessage() = "+t.getMessage());
                Message message = new Message();
                message.what = DOWNLOAD_RESULT_NET_ERROR;
                myHandler.sendMessage(message);
            }
        });

    }

    private class GridViewData {
        private String attachId = null;
        private String attachName = null;
        private String attachFileStr = null;
        private Bitmap bitmap = null;

        public GridViewData(String id, String name, String fileStr){
            attachId = id;
            attachName = name;
            attachFileStr = fileStr;
        }

        public GridViewData(String name, Bitmap pic){
            attachName = name;
            bitmap = pic;
        }

        public void initBitmap(){
            if(attachFileStr!=null)
                bitmap = stringtoBitmap(attachFileStr);
        }

        public String getAttachId() {
            return attachId;
        }

        public void setAttachId(String attachId) {
            this.attachId = attachId;
        }

        public String getAttachName() {
            return attachName;
        }

        public void setAttachName(String attachName) {
            this.attachName = attachName;
        }

        public String getAttachFileStr() {
            return attachFileStr;
        }

        public void setAttachFileStr(String attachFileStr) {
            this.attachFileStr = attachFileStr;
        }

        public Bitmap getBitmap() {
            return bitmap;
        }

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }
    }

    public ArrayList<AttachmentDTO> getAttachList() {
        if(imageArrayList == null || imageArrayList.size() == 0)
            return null;
        else{
            final ArrayList<AttachmentDTO> mAttachList = new ArrayList<AttachmentDTO>();
            for(GridViewData data:imageArrayList){
                AttachmentDTO mAttach = new AttachmentDTO();
                if(data.getAttachId()!=null){//旧的附件，仍要保存的，则id不为空
                    mAttach.setAttachId(data.getAttachId());
                }else{//Id为空，表示是新的附件
                    mAttach.setAttachName(data.getAttachName());
                    if(data.getAttachFileStr() == null)
                        mAttach.setAttachFileStr(bitmaptoString(data.getBitmap(),1));
                    else
                        mAttach.setAttachFileStr(data.getAttachFileStr());
                }
                mAttachList.add(mAttach);
            }
            return mAttachList;
        }
    }

    public class ImageShowDialog extends Dialog {
        public ImageShowDialog(Context context) {
            super(context);
        }

        public ImageShowDialog(Context context, int theme) {
            super(context, theme);
        }

        @Override
        public void show(){
            super.show();
        }
    }
}
