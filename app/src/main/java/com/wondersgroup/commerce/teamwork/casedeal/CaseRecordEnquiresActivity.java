package com.wondersgroup.commerce.teamwork.casedeal;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.AttachResultObject;
import com.wondersgroup.commerce.model.AttachmentDTO;
import com.wondersgroup.commerce.model.BackResultObject;
import com.wondersgroup.commerce.model.CaseInvestigateTitle;
import com.wondersgroup.commerce.model.DataVolume;
import com.wondersgroup.commerce.model.DynamicComponentObject;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.CaseApi;
import com.wondersgroup.commerce.utils.DataShared;
import com.wondersgroup.commerce.utils.DynamicWidgetUtils;
import com.wondersgroup.commerce.utils.FileUtils;
import com.wondersgroup.commerce.utils.TableRowUtils;
import com.wondersgroup.commerce.widget.LoadingDialog;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Lee on 2016/2/19.
 * Update by lzl on 2016/10/10
 * 询问笔录 编辑界面 (舍弃imagePicker)
 */
public class CaseRecordEnquiresActivity extends AppCompatActivity {

    private String TAG = "CaseEnquireActivity";
    @BindView(R.id.mid_toolbar)Toolbar toolbar;
    @BindView(R.id.toolbar_title)TextView title;
    @BindView(R.id.components_LinearLayout)LinearLayout componentsLinearLayout;        //控件显示部分（动态添加控件）
    @BindView(R.id.picLayout) LinearLayout picLayout;                                  //附件显示部分
    @BindView(R.id.pic1) ImageView pic1;
    @BindView(R.id.pic2) ImageView pic2;
    @BindView(R.id.pic3) ImageView pic3;
    @BindView(R.id.commit_record_Button)Button commitButton;                        //提交按钮

    private String clueNo = null;                       //线索号
    private String serialNo = null;                     //序列号

    private List<DataVolume> componentObjectsList;            //动态控件对象列表
    private DataVolume picObject;                             //附件对象
    private ArrayList<EditText> arrayedittext = new ArrayList<EditText>();
    private DynamicWidgetUtils dynamicWidgetUtils;      //动态加载控件对象
    private int resultCode = 1;
    private RootAppcation app;
    private TableRowUtils tableRowUtils;

    //图片部分
    private final static int ONE_IMG = 100;
    private final static int TWO_IMG = 101;
    private final static int THREE_IMG = 102;
    private final static int ONE_CAMERA = 103;
    private final static int TWO_CAMERA = 104;
    private final static int THREE_CAMERA = 105;
    private static String picOneName;
    private static String picTwoName;
    private static String picThreeName;
    private ArrayList<AttachmentDTO> attachList;        //附件列表
    private TotalLoginBean loginBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_inspects_enquire);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        app = (RootAppcation) getApplication();

        initView();
        initData();
    }

    private void initData() {
        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010011");
        if(clueNo != null)
            map.put("clueNo", clueNo);
        else
            map.put("serialNo", serialNo);

        String url = "";
        Call<DynamicComponentObject> call;
        if (ApiManager.caseType == 1){
            url = CaseApi.URL_CASE_1 + CaseApi.CASE_ENQUIRE_EDIT;
            call = ApiManager.caseApi.toEnquireEdit(url,map);
        } else{
            url = CaseApi.CASE_ENQUIRE_EDIT;
            call = ApiManager.shyApi.toEnquireEdit(url,map);
        }
        call.enqueue(new Callback<DynamicComponentObject>() {
            @Override
            public void onResponse(Response<DynamicComponentObject> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    final DynamicComponentObject dynamicComponentObject = response.body();

                    if ((null == dynamicComponentObject) || (null == dynamicComponentObject.getResult())) {
                        Toast.makeText(CaseRecordEnquiresActivity.this, "获得待审查数据错误", Toast.LENGTH_SHORT).show();
                        return;
                    } else
                        componentObjectsList = dynamicComponentObject.getResult();

                    for(DataVolume dataVolume: componentObjectsList){
                        if("4".equals(dataVolume.getType())){
                            picObject = dataVolume;
                            componentObjectsList.remove(dataVolume);
                            picLayout.setVisibility(View.VISIBLE);
                            attachList = new ArrayList<AttachmentDTO>();
                            putInAllPic();
                        }
                    }
                    //添加动态控件
                    tableRowUtils = new TableRowUtils(CaseRecordEnquiresActivity.this, componentsLinearLayout, componentObjectsList);
                    tableRowUtils.build();
                } else {
                    Toast.makeText(CaseRecordEnquiresActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(CaseRecordEnquiresActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        CaseInvestigateTitle caseInvestigateTitle = (CaseInvestigateTitle) bundle.getSerializable("CaseInvestigateTitle");
        if(caseInvestigateTitle != null)
            clueNo = caseInvestigateTitle.getClueNo();
        else
            serialNo = bundle.getString("NoteRecordEnquire");

        title.setText("询问笔录");

        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValue() == true)
                    commitRecord();
            }
        });

    }

    @OnClick({R.id.pic1, R.id.pic2, R.id.pic3, R.id.commit_record_Button})
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.pic1:
                if(attachList.get(0).getAttachFileStr()!=null)
                    showPic(1);
                else
                    pickPic(1);
                break;
            case R.id.pic2:
                if(attachList.get(1).getAttachFileStr()!=null)
                    showPic(2);
                else
                    pickPic(2);
                break;
            case R.id.pic3:
                if(attachList.get(2).getAttachFileStr()!=null)
                    showPic(3);
                else
                    pickPic(3);
                break;
            case R.id.commit_record_Button:
                if (checkValue())
                    commitRecord();
                break;
        }
    }

    //加载全部服务端图片
    public void putInAllPic(){
        List<DataVolume> picList = picObject.getRemark();
        for(int j = 0; j < 3; j++){
            AttachmentDTO attchment = new AttachmentDTO();
            attachList.add(attchment);
        }
        if(picList == null || picList.size() == 0)
            return;
        for(int i = 0; i < 3 && i < picList.size(); i++){
            attachList.get(i).setAttachId(picList.get(i).getKey());
            attachList.get(i).setAttachName(picList.get(i).getValue());
            String picId = picList.get(i).getKey();
            String picName = picList.get(i).getValue();
            putPicinPos(picId, picName, i);
        }
    }

    //在指定位置加载服务端图片
    public void putPicinPos(String picId, String picName, final int pos){
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "03010015");
        map.put("attachId", picId);
        String url = "";
        Call<AttachResultObject> call;
        if (ApiManager.caseType == 1){
            url = CaseApi.URL_CASE_1 + CaseApi.DOWNLAODER_ATTACH;
            call = ApiManager.caseApi.downLoadAttchment(url,map);
        } else{
            url = CaseApi.DOWNLAODER_ATTACH;
            call = ApiManager.shyApi.downLoadAttchment(url,map);
        }
        call = ApiManager.caseApi.downLoadAttchment(url,map);
        call.enqueue(new Callback<AttachResultObject>() {
            @Override
            public void onResponse(Response<AttachResultObject> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    final AttachResultObject attachObject = response.body();

                    if (null == attachObject || null == attachObject.getResult()) {
                        Toast.makeText(CaseRecordEnquiresActivity.this, "下载附件数据错误", Toast.LENGTH_SHORT).show();
                    } else{
                        String fileStr = attachObject.getResult().getAttachFileStr();
                        if(fileStr==null || fileStr=="")
                            return;
                        attachList.get(pos).setAttachFileStr(fileStr);
                        Bitmap pic = FileUtils.stringtoBitmap(fileStr);
                        if (pic != null){
                            File file;
                            switch (pos){
                                case 0:
                                    file = new File(app.getCachePath(), "pic1.jpg");
                                    try {
                                        OutputStream stream = new FileOutputStream(file);
                                        pic.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                                        stream.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    picOneName = app.getCachePath() + "/pic1.jpg";
                                    Picasso.with(CaseRecordEnquiresActivity.this).load(file)
                                            .resize(120, 120).centerCrop().memoryPolicy(MemoryPolicy.NO_CACHE).into(pic1);
                                    break;
                                case 1:
                                    file = new File(app.getCachePath(), "pic2.jpg");
                                    try {
                                        OutputStream stream = new FileOutputStream(file);
                                        pic.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                                        stream.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    picTwoName = app.getCachePath() + "/pic2.jpg";
                                    Picasso.with(CaseRecordEnquiresActivity.this).load(file)
                                            .resize(120, 120).centerCrop().memoryPolicy(MemoryPolicy.NO_CACHE).into(pic2);
                                    break;
                                case 2:
                                    file = new File(app.getCachePath(), "pic3.jpg");
                                    try {
                                        OutputStream stream = new FileOutputStream(file);
                                        pic.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                                        stream.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    picThreeName = app.getCachePath() + "/pic3.jpg";
                                    Picasso.with(CaseRecordEnquiresActivity.this).load(file)
                                            .resize(120, 120).centerCrop().memoryPolicy(MemoryPolicy.NO_CACHE).into(pic3);
                                    break;
                            }
                        }
                    }
                } else {
                    Toast.makeText(CaseRecordEnquiresActivity.this, "服务器信息有误", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(CaseRecordEnquiresActivity.this, "服务器连接错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //查看图片详情
    public void showPic(final int picNo){
        View view = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_picdetail, null);
        ImageView picImg = (ImageView) view.findViewById(R.id.img);
        Button pickBtn = (Button) view.findViewById(R.id.btn_one);
        Button deleteBtn = (Button) view.findViewById(R.id.btn_two);
        Button cancelBtn = (Button) view.findViewById(R.id.btn_three);
        picImg.setImageBitmap(FileUtils.stringtoBitmap(attachList.get(picNo-1).getAttachFileStr()));
        MaterialDialog.Builder builer = new MaterialDialog.Builder(this);
        builer.customView(view, false);
        final MaterialDialog dialog = builer.build();
        dialog.show();
        pickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                pickPic(picNo);
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attachList.get(picNo-1).setAttachFileStr(null);
                attachList.get(picNo-1).setAttachId(null);
                attachList.get(picNo-1).setAttachName(null);
                setBlankPic(picNo);
                dialog.dismiss();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    //放置空白图片
    public void setBlankPic(int picNo){
        switch (picNo){
            case 1:
                pic1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.addf));
                break;
            case 2:
                pic2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.addf));
                break;
            case 3:
                pic3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.addf));
                break;
        }
    }

    //图片选取对话框
    public void pickPic(final int picNo){
        View view = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_camera, null);
        Button photoBtn = (Button) view.findViewById(R.id.btn_one);
        Button picBtn = (Button) view.findViewById(R.id.btn_two);
        Button cancelBtn = (Button) view.findViewById(R.id.btn_three);
        MaterialDialog.Builder builer = new MaterialDialog.Builder(this);
        builer.customView(view, false);
        final MaterialDialog dialog = builer.build();
        dialog.show();
        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (picNo == 1) {
                    picOneName = FileUtils.saveImageName();
                    File out = new File(picOneName);
                    Uri uri = Uri.fromFile(out);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent, ONE_CAMERA);
                } else if (picNo == 2) {
                    picTwoName = FileUtils.saveImageName();
                    File out = new File(picTwoName);
                    Uri uri = Uri.fromFile(out);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent, TWO_CAMERA);
                } else if (picNo == 3) {
                    picThreeName = FileUtils.saveImageName();
                    File out = new File(picThreeName);
                    Uri uri = Uri.fromFile(out);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent, THREE_CAMERA);
                }
                dialog.dismiss();
            }
        });
        picBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (picNo == 1) {
                    startActivityForResult(intent, ONE_IMG);
                } else if (picNo == 2) {
                    startActivityForResult(intent, TWO_IMG);
                } else if (picNo == 3) {
                    startActivityForResult(intent, THREE_IMG);
                }
                dialog.dismiss();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }



    //检查新增、修改内容
    private boolean checkValue() {
        for(int i=0; i<componentObjectsList.size(); i++){
            if(componentObjectsList.get(i).getRequired().equals("1")){
                if(tableRowUtils.getContent(i) == null || "".equals(tableRowUtils.getContent(i))){
                    Toast.makeText(CaseRecordEnquiresActivity.this, componentObjectsList.get(i).getName()+"不能为空！", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }

        return true;
    }

    //新增、修改记录
    private void commitRecord() {

        final LoadingDialog loadingDialog = new LoadingDialog.Builder(CaseRecordEnquiresActivity.this)
                .build();
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String> mapValue = new HashMap<String, String>();
        map.put("wsCodeReq", "03010012");
        if(clueNo != null){//新增记录
            map.put("clueNo", clueNo);
            map.put("serialNo", "");
        }else{//修改记录
            map.put("serialNo", serialNo);
            map.put("clueNo", "");
        }

        map.put("userId", loginBean.getResult().getUserId());
        for(int i=0; i<componentObjectsList.size(); i++){
            if(tableRowUtils.getContent(i)!=null && !"".equals(tableRowUtils.getContent(i))) {
                if("2".equals(componentObjectsList.get(i).getType())){
                    String key = "";
                    for(int k = 0; k<componentObjectsList.get(i).getRemark().size(); k++){
                        if(tableRowUtils.getContent(i).trim()
                                .equals(componentObjectsList.get(i).getRemark().get(k).getValue()))
                            key = componentObjectsList.get(i).getRemark().get(k).getKey();
                    }
                    mapValue.put("\""+componentObjectsList.get(i).getKey()+"\"", "\""+key+"\"");
                }else
                    mapValue.put("\"" + componentObjectsList.get(i).getKey() + "\"", "\"" + tableRowUtils.getContent(i).trim() + "\"");
            }else
                mapValue.put("\""+componentObjectsList.get(i).getKey()+"\"", "\""+""+"\"");
        }
        map.put("enquireStr", mapValue.toString());

        //上传附件
        if(attachList!=null){
            StringBuffer attString = new StringBuffer();
            for(AttachmentDTO image:attachList){
                JSONObject jsonObj = new JSONObject();
                try {
                    if(image.getAttachId()!=null)
                        jsonObj.put("attachId",""+image.getAttachId());
                    else{
                        jsonObj.put("attachName",image.getAttachName());
                        jsonObj.put("attachFileStr", image.getAttachFileStr());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(attString.length()>0)
                    attString.append("*");
                attString.append(jsonObj.toString());
            }
            map.put("attachListStr", attString.toString());
        }

        Log.d(TAG, "map = " + map.toString());
        String url = "";
        Call<BackResultObject> call;
        if (ApiManager.caseType == 1){
            url = CaseApi.URL_CASE_1 + CaseApi.CASE_ENQUIRE_SAVE;
            call = ApiManager.caseApi.saveEnquire(url,map);
        } else{
            url = CaseApi.CASE_ENQUIRE_SAVE;
            call = ApiManager.shyApi.saveEnquire(url,map);
        }
        call.enqueue(new Callback<BackResultObject>() {
            @Override
            public void onResponse(Response<BackResultObject> response, Retrofit retrofit) {
                loadingDialog.dismiss();
                if (response.isSuccess()) {
                    BackResultObject result = response.body();
                    if (result.getCode().equals(ApiManager.RESULT_SUCCESS)){
                        CaseRecordEnquiresActivity.this.setResult(resultCode, null);
                        CaseRecordEnquiresActivity.this.finish();
                    }else{
                        Toast.makeText(CaseRecordEnquiresActivity.this, "提交失败！", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "CaseEnquireActivity--------result.getMessage() = " + result.getMessage());
                    }

                } else {
                    Log.d(TAG, "CaseEnquireActivity --------------- response.is not Success()");
                    Toast.makeText(CaseRecordEnquiresActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                loadingDialog.dismiss();
                Toast.makeText(CaseRecordEnquiresActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult!,requestCode = " + requestCode);
//        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
//            case RESULT_OK:
//                Bundle b=data.getExtras(); //data为B中回传的Intent
//                ArrayList<ImageEntry> imageArrayList =(ArrayList<ImageEntry>)b.getSerializable("characterStream");//str即为回传的bitmap的字符流
//                if(imageArrayList!=null && imageArrayList.size()>0)
//                    dynamicWidgetUtils.refreshGridView(imageArrayList);
//                break;
//            default:
//                break;
//        }

        super.onActivityResult(requestCode, resultCode, data);
        //第一个相机
        if(requestCode == ONE_CAMERA && resultCode == Activity.RESULT_OK) {
            saveCameraPic(picOneName);
            FileUtils.saveImageToGallery(this, new File(picOneName));
            return;
        }
        //第二个相机
        if(requestCode == TWO_CAMERA && resultCode == Activity.RESULT_OK) {
            saveCameraPic(picTwoName);
            FileUtils.saveImageToGallery(this, new File(picOneName));
            return;
        }
        //第三个相机
        if(requestCode == THREE_CAMERA && resultCode == Activity.RESULT_OK) {
            saveCameraPic(picThreeName);
            FileUtils.saveImageToGallery(this, new File(picOneName));
            return;
        }
        //图库
        if (resultCode == Activity.RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage,
                    filePathColumns, null, null, null);
            if(c == null){
                Toast.makeText(this, "图片存在问题！", Toast.LENGTH_SHORT).show();
                return;
            }
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            // 获取图片并显示
            String[] nameStr = picturePath.split("/");
            getStrFromFile(picturePath, requestCode);
            if (requestCode == ONE_IMG) {
                picOneName = picturePath;
                attachList.get(0).setAttachName(nameStr[nameStr.length-1]);
                attachList.get(0).setAttachId(null);
                Picasso.with(this).load(new File(picturePath))
                        .resize(120, 120).centerCrop().memoryPolicy(MemoryPolicy.NO_CACHE).into(pic1);
            } else if (requestCode == TWO_IMG) {
                picTwoName = picturePath;
                attachList.get(1).setAttachName(nameStr[nameStr.length-1]);
                attachList.get(1).setAttachId(null);
                Picasso.with(this).load(new File(picturePath))
                        .resize(120, 120).centerCrop().memoryPolicy(MemoryPolicy.NO_CACHE).into(pic2);
            } else {
                picThreeName = picturePath;
                attachList.get(2).setAttachName(nameStr[nameStr.length-1]);
                attachList.get(2).setAttachId(null);
                Picasso.with(this).load(new File(picturePath))
                        .resize(120, 120).centerCrop().memoryPolicy(MemoryPolicy.NO_CACHE).into(pic3);
            }
        }
    }

    //读取文件获取为字符串
    public void getStrFromFile(final String name, final int code){
        String imageUri = "file://" + name;
        ImageLoader.getInstance().loadImage(imageUri, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                switch (code){
                    case ONE_IMG:
                        attachList.get(0).setAttachFileStr(FileUtils.bitmaptoString(loadedImage, 60));
                        break;
                    case TWO_IMG:
                        attachList.get(1).setAttachFileStr(FileUtils.bitmaptoString(loadedImage, 60));
                        break;
                    case THREE_IMG:
                        attachList.get(2).setAttachFileStr(FileUtils.bitmaptoString(loadedImage, 60));
                        break;
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
            }
        });
    }

    public void saveCameraPic(final String name){
        String imageUri = "file://" + name;
        ImageLoader.getInstance().loadImage(imageUri, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                // 获取当前时间
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());
                String timeString = formatter.format(curDate);

                // 添加水印
                Bitmap resizedBitmap = loadedImage.copy(Bitmap.Config.ARGB_8888, true);
                Canvas cv = new Canvas(resizedBitmap);
                Paint p = new Paint();
                Typeface font = Typeface.create("宋体", Typeface.BOLD);
                p.setColor(Color.BLUE);
                p.setTypeface(font);
                p.setTextSize(36);
                cv.drawBitmap(resizedBitmap, 0, 0, p);
                cv.drawText(timeString, 40, cv.getHeight() - 100, p);
                cv.save(Canvas.ALL_SAVE_FLAG);
                cv.restore();

                try {
                    OutputStream stream = new FileOutputStream(new File(name));
                    resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ImageView pic;
                String[] nameStr = name.split("/");
                if(name.equals(picOneName)){
                    pic = pic1;
                    attachList.get(0).setAttachName(nameStr[nameStr.length-1]);
                    attachList.get(0).setAttachFileStr(FileUtils.bitmaptoString(resizedBitmap, 60));
                    attachList.get(0).setAttachId(null);
                }else if(name.equals(picTwoName)){
                    pic = pic2;
                    attachList.get(1).setAttachName(nameStr[nameStr.length-1]);
                    attachList.get(1).setAttachFileStr(FileUtils.bitmaptoString(resizedBitmap, 60));
                    attachList.get(1).setAttachId(null);
                }else {
                    pic = pic3;
                    attachList.get(2).setAttachName(nameStr[nameStr.length-1]);
                    attachList.get(2).setAttachFileStr(FileUtils.bitmaptoString(resizedBitmap, 60));
                    attachList.get(2).setAttachId(null);
                }

                // 显示图
                Picasso.with(CaseRecordEnquiresActivity.this)
                        .load(new File(name))
                        .memoryPolicy(MemoryPolicy.NO_CACHE).resize(120, 120)
                        .centerCrop().into(pic);
                // 清除缓存
                resizedBitmap.recycle();
                resizedBitmap = null;
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
