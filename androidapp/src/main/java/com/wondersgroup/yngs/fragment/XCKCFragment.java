package com.wondersgroup.yngs.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;
import com.squareup.picasso.Picasso;
import com.wondersgroup.yngs.R;
import com.wondersgroup.yngs.activity.InputActivity;
import com.wondersgroup.yngs.activity.ListActivity;
import com.wondersgroup.yngs.entity.GeneralResult;
import com.wondersgroup.yngs.entity.MeInfoResult;
import com.wondersgroup.yngs.entity.PicBean;
import com.wondersgroup.yngs.service.ApiManager;
import com.wondersgroup.yngs.service.YunNanApi;
import com.wondersgroup.yngs.widget.LabelInput;
import com.wondersgroup.yngs.widget.LabelNum;
import com.wondersgroup.yngs.widget.LabelSwitch;
import com.wondersgroup.yngs.widget.LabelText;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import butterknife.Bind;
import butterknife.ButterKnife;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link XCKCFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class XCKCFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.xckc_entName)LabelText entName;
    @Bind(R.id.xckc_legRpName)LabelText person;
    @Bind(R.id.xckc_phone)LabelText phone;
    @Bind(R.id.xckc_addr)LabelInput addr;
    @Bind(R.id.xckc_addrType)LabelInput addrType;
    @Bind(R.id.xckc_area)LabelNum area;
    @Bind(R.id.xckc_isRight)LabelSwitch isRight;
    @Bind(R.id.xckc_isNecessary)LabelSwitch isNecessary;
    @Bind(R.id.xckc_assess)LabelInput assess;
    @Bind(R.id.xckc_checkMan)TextView checkTitle;
    @Bind(R.id.xckc_checkManOne)EditText firstBlood;
    @Bind(R.id.xckc_checkManTwo)EditText doubleKill;
    @Bind(R.id.xckc_note)LabelInput note;
    @Bind(R.id.xckc_submit)Button submit;
    @Bind(R.id.pic_1)
    ImageView image1;
    @Bind(R.id.pic_2)
    ImageView image2;
    @Bind(R.id.pic_3)
    ImageView image3;
    @Bind(R.id.pic_4)
    ImageView image4;
    @Bind(R.id.pic_5)
    ImageView image5;

    private static final String ARG_ID = "paramId";
    private static final String ARG_XCKCTYPE ="XCKCTYPE";


    private String mParamId;
    private String xckcType;
    private String runSpaceType=null;
    private String runSpaceTypeOther=null;

    private boolean needFetch=false;
    private boolean submitted=false;

    private Uri uri;
    //存储图片信息
    private String picName;
    private String picPath;
    private List<PicBean> picList = new ArrayList<PicBean>();
    private PicBean [] picArray = new PicBean[5];//最多保存5张图片

    private int picNumber;
    private int picSum;
    private int imgNumber;
    private int imgSum;


    private String baseUrl="";
    private String imgName1="";
    private String imgName2="";
    private String imgName3="";
    private String imgName4="";
    private String imgName5="";

    private List<PicBean> imgList = new ArrayList<PicBean>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==100){

                for(int i=0;i<picArray.length;i++){
                    if(picArray[i]!=null){
                        picList.add(picArray[i]);
                    }
                }

                if(picList.size()!=0){
                    picSum = picList.size();
                    picNumber = 0;
                    PicBean bean = picList.get(picNumber);
                    uploadImage(bean);
                }

            }else if(msg.what==200){
                if (imgList.size()!=0){
                    imgSum = imgList.size();
                    imgNumber = 0;
                    String fileName = imgList.get(imgNumber).getPicName();
                    downLoadImage(fileName);
                }
            }
        }
    };


    public XCKCFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment XCKCFragment.
     */

    public static XCKCFragment newInstance(String param1,String xckcType) {
        XCKCFragment fragment = new XCKCFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ID, param1);
        args.putString(ARG_XCKCTYPE,xckcType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamId = getArguments().getString(ARG_ID);
            xckcType=getArguments().getString(ARG_XCKCTYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_xckc, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        if("2".equals(xckcType))disableInput();
        if(needFetch){
            fetchData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            if(getView()!=null){
                fetchData();
                needFetch=false;
            }else {
                needFetch=true;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==100&&resultCode==getActivity().RESULT_OK){
            addrType.setContent(data.getStringExtra("CDXZ"));
            runSpaceType=data.getStringExtra("typeCode");
            if("9".equals(runSpaceType))runSpaceTypeOther=data.getStringExtra("CDXZ");
        }else if(requestCode==200&&resultCode==getActivity().RESULT_OK){
            assess.setContent(data.getStringExtra("input_string"));
        }else if(requestCode==1&&resultCode==getActivity().RESULT_OK){
            Picasso.with(getActivity())
                    .load(uri)
                    .resize(80,80)
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(image1);
            savePic(requestCode);

        }else if(requestCode==2&&resultCode==getActivity().RESULT_OK){
            Picasso.with(getActivity())
                    .load(uri)
                    .resize(80,80)
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(image2);
            savePic(requestCode);

        }else if(requestCode==3&&resultCode==getActivity().RESULT_OK){
            Picasso.with(getActivity())
                    .load(uri)
                    .resize(80,80)
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(image3);
            savePic(requestCode);

        }else if(requestCode==4&&resultCode==getActivity().RESULT_OK){
            Picasso.with(getActivity())
                    .load(uri)
                    .resize(80,80)
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(image4);
            savePic(requestCode);

        }else if(requestCode==5&&resultCode==getActivity().RESULT_OK){
            Picasso.with(getActivity())
                    .load(uri)
                    .resize(80,80)
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(image5);
            savePic(requestCode);

        }else if(requestCode==11&&resultCode==getActivity().RESULT_OK){
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getActivity().getContentResolver().query(selectedImage,
                    filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);

            picPath = c.getString(columnIndex);
            c.close();
            Picasso.with(getActivity())
                    .load(new File(picPath))
                    .resize(80,80)
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(image1);
            savePic(requestCode-10);

        }else if(requestCode==12&&resultCode==getActivity().RESULT_OK){
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getActivity().getContentResolver().query(selectedImage,
                    filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            picPath = c.getString(columnIndex);
            c.close();
            Picasso.with(getActivity())
                    .load(new File(picPath))
                    .resize(80,80)
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(image2);
            savePic(requestCode-10);

        }
        else if(requestCode==13&&resultCode==getActivity().RESULT_OK){
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getActivity().getContentResolver().query(selectedImage,
                    filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            picPath = c.getString(columnIndex);
            c.close();
            Picasso.with(getActivity())
                    .load(new File(picPath))
                    .resize(80,80)
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(image3);
            savePic(requestCode-10);

        }else if(requestCode==14&&resultCode==getActivity().RESULT_OK){
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getActivity().getContentResolver().query(selectedImage,
                    filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            picPath = c.getString(columnIndex);
            c.close();
            Picasso.with(getActivity())
                    .load(new File(picPath))
                    .resize(80,80)
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(image4);
            savePic(requestCode-10);

        }else if(requestCode==15&&resultCode==getActivity().RESULT_OK){
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getActivity().getContentResolver().query(selectedImage,
                    filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            picPath = c.getString(columnIndex);
            c.close();
            Picasso.with(getActivity())
                    .load(new File(picPath))
                    .resize(80,80)
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(image5);
            savePic(requestCode-10);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(submit)){
//            handler.sendEmptyMessage(100);

            if(isAllFilled()){
                Map<String,String> body=new HashMap<>();
                body.put("wsCodeReq",ApiManager.getWsCodeReq());
                body.put("meId",mParamId);
                body.put("runAddress",addr.getText());
                body.put("runSpaceType",runSpaceType);
                if(runSpaceTypeOther!=null)body.put("runSpaceTypeOther",runSpaceTypeOther);
                body.put("runArea",area.getText());
                body.put("areaEqScale",""+isRight.getSelection());
                body.put("hasEquipment",""+isNecessary.getSelection());
                body.put("explorationOpnn",assess.getText());
                body.put("explorationPerson",firstBlood.getText().toString());
                body.put("explorationPerson2",doubleKill.getText().toString());
                body.put("memo",note.getText());
                Call<GeneralResult> call=ApiManager.yunNanApi.submitMeExploration(body);
                call.enqueue(new Callback<GeneralResult>() {
                    @Override
                    public void onResponse(Response<GeneralResult> response, Retrofit retrofit) {
                        if(response.isSuccess()&&response.body()!=null){
                            GeneralResult result=response.body();
                            if("200".equals(result.getResultCode())){
                                submitted=true;
                                //提交图片
                                handler.sendEmptyMessage(100);

                            }else {
                                new MaterialDialog.Builder(getContext())
                                        .title("提交失败")
                                        .content(result.getMessage())
                                        .positiveText("确定")
                                        .show();
                            }
                        }else {
                            new MaterialDialog.Builder(getContext())
                                    .title("提交失败")
                                    .content("连接服务器失败")
                                    .positiveText("确定")
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        new MaterialDialog.Builder(getContext())
                                .title("提交失败")
                                .content("网络错误")
                                .positiveText("确定")
                                .show();
                    }
                });
            }else {
                new MaterialDialog.Builder(getContext())
                        .content("请填入所有必填项")
                        .show();
            }
        }
        else if(v.equals(image1)){
            //会审下无图不点击
            if ("2".equals(xckcType)){
               if (picArray[0]!=null){
                   getImage(1);
               }
            }else {
                getImage(1);
            }
        }else if(v.equals(image2)){
            if ("2".equals(xckcType)){
                if (picArray[1]!=null){
                    getImage(2);
                }
            }else {
                getImage(2);
            }
        }else if(v.equals(image3)){
            if ("2".equals(xckcType)){
                if (picArray[2]!=null){
                    getImage(3);
                }
            }else {
                getImage(3);
            }
        }else if(v.equals(image4)){
            if ("2".equals(xckcType)){
                if (picArray[3]!=null){
                    getImage(4);
                }
            }else {
                getImage(4);
            }
        }else if(v.equals(image5)){

            if ("2".equals(xckcType)){
                if (picArray[4]!=null){
                    getImage(5);
                }
            }else {
                getImage(5);
            }
        }

    }

    public void fetchData(){
        Map<String,String> body=new HashMap<>();
        body.put("meId",mParamId);
        body.put("wsCodeReq", ApiManager.getWsCodeReq());
        Call<MeInfoResult> call=ApiManager.yunNanApi.getMeInfo(body);
        call.enqueue(new Callback<MeInfoResult>() {
            @Override
            public void onResponse(Response<MeInfoResult> response, Retrofit retrofit) {
                if(response.isSuccess()&&response.body()!=null){
                    MeInfoResult result=response.body();
                    if("200".equals(result.getResultCode())){
                        baseUrl = result.getResult().getBaseUrl();
                        imgName1 = result.getResult().getImgFileName1();
                        imgList.clear();
                        if (!imgName1.equals("")){
                            PicBean bean = new PicBean();
                            bean.setPicNum("1");
                            bean.setPicName(imgName1);
                            imgList.add(bean);
                        }
                        imgName2 = result.getResult().getImgFileName2();
                        if (!imgName2.equals("")){
                            PicBean bean = new PicBean();
                            bean.setPicNum("2");
                            bean.setPicName(imgName2);
                            imgList.add(bean);
                        }
                        imgName3 = result.getResult().getImgFileName3();
                        if (!imgName3.equals("")){
                            PicBean bean = new PicBean();
                            bean.setPicNum("3");
                            bean.setPicName(imgName3);
                            imgList.add(bean);
                        }
                        imgName4 = result.getResult().getImgFileName4();
                        if (!imgName4.equals("")){
                            PicBean bean = new PicBean();
                            bean.setPicNum("4");
                            bean.setPicName(imgName4);
                            imgList.add(bean);
                        }
                        imgName5 = result.getResult().getImgFileName5();
                        if (!imgName5.equals("")){
                            PicBean bean = new PicBean();
                            bean.setPicNum("5");
                            bean.setPicName(imgName5);
                            imgList.add(bean);
                        }
                        entName.setContent(result.getResult().getEntName());
                        person.setContent(result.getResult().getLeRepName());
                        phone.setContent(result.getResult().getPhoneNum());
                        if("2".equals(xckcType)) {
                            addr.setContent(result.getResult().getRunAddress());
                            addrType.setContent(result.getResult().getRunSpaceType());
                            area.setContent(result.getResult().getRunArea());
                            assess.setContent(result.getResult().getExplorationOpnn());
                            if("是".equals(result.getResult().getAreaEqScale())){
                                isRight.check(true);
                            }else {
                                isRight.check(false);
                            }
                            if("是".equals(result.getResult().getHasEquipment())){
                                isNecessary.check(true);
                            }else {
                                isNecessary.check(false);
                            }
                            firstBlood.setText(result.getResult().getExplorationPerson());
                            doubleKill.setText(result.getResult().getExplorationPerson2());
                            note.setContent(result.getResult().getMemo());
                        }
                        handler.sendEmptyMessage(200);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void disableInput(){
        addr.setInputType(InputType.TYPE_NULL);
        addr.setHint("");
        addrType.setInputType(InputType.TYPE_NULL);
        addrType.setHint("");
        area.setInputType(InputType.TYPE_NULL);
        area.setHint("");
        isRight.disable();
        isNecessary.disable();
        assess.setInputType(InputType.TYPE_NULL);
        assess.setHint("");
        checkTitle.setText("场地勘察人");
        firstBlood.setInputType(InputType.TYPE_NULL);
        doubleKill.setInputType(InputType.TYPE_NULL);
        note.setInputType(InputType.TYPE_NULL);
        note.setHint("");
        submit.setVisibility(View.GONE);

        submitted=true;
    }
    public void init(){
        entName.setTitle("企业名称");
        person.setTitle("企业负责人姓名");
        phone.setTitle("手机号码");
        addr.setTitle("经营详细地址");
        if("1".equals(xckcType))addr.isRequired(true);
        addr.setHint("输入场地详细地址");
        addrType.setTitle("经营场地性质");
        if("1".equals(xckcType))addrType.isRequired(true);
        addrType.setHint("请选择经营场地性质");
        if("1".equals(xckcType)) {
            addrType.setContentClick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ListActivity.class);
                    intent.putExtra("title", "请选择场地性质");
                    intent.putExtra("type", "CDXZ");
                    startActivityForResult(intent, 100);
                }
            });
        }
        area.setTitle("经营场地面积");
        if("1".equals(xckcType))area.isRequired(true);
        area.setHint("输入场地面积");
        area.setUnit("(平方米)");
        isRight.setTitle("经营场地是否与企业经营规模相适应");
        if("1".equals(xckcType))isRight.isRequired(true);
        isNecessary.setTitle("企业经营是否具备必要的设备或办公用品");
        if("1".equals(xckcType))isNecessary.isRequired(true);
        assess.setTitle("经营场所勘察意见");
        if("1".equals(xckcType))assess.isRequired(true);
        assess.setHint("输入经营场所勘察意见");
        if("1".equals(xckcType)) {
            assess.setContentClick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), InputActivity.class);
                    intent.putExtra("title", "经营场所勘察意见");
                    startActivityForResult(intent, 200);
                }
            });
        }
        SpannableString sb=new SpannableString("场地勘察人*");
        sb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(),R.color.red)),sb.length()-1,sb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        checkTitle.setText(sb);
        note.setTitle("备注");
        note.setHint("输入备注");
        if("1".equals(xckcType))note.isRequired(true);
        submit.setOnClickListener(this);
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
    }
    public boolean isAllFilled(){
        if(!addr.getText().isEmpty()&&!addrType.getText().isEmpty()&&!area.getText().isEmpty()&&isRight.getSelection()!=-1&&isNecessary.getSelection()!=-1&&!assess.getText().isEmpty()&&!firstBlood.getText().toString().isEmpty()&&!doubleKill.getText().toString().isEmpty())return true;
        return false;
    }

    public boolean confirmQuit(){
        if(submitted) return false;
        else return !addr.getText().isEmpty()||!addrType.getText().isEmpty()||!area.getText().isEmpty()||isRight.getSelection()!=-1||isNecessary.getSelection()!=-1||!assess.getText().isEmpty()||!firstBlood.getText().toString().isEmpty()||!doubleKill.getText().toString().isEmpty();
    }

    public void getImage(final int position){
        final Dialog dialog = new Dialog(getActivity(),R.style.dialog_camera);
        dialog.setContentView(R.layout.dialog_camera);
        dialog.setCancelable(false);
        dialog.show();

        final Button one = (Button) dialog.findViewById(R.id.btn_one);
        one.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                picName = mParamId+"_"+position+".jpg";
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                picPath = Environment.getExternalStorageDirectory()+"/DCIM/Camera/"+dateFormat.format(new Date(System.currentTimeMillis()))+".jpg";
                File out = new File(picPath);
                uri = Uri.fromFile(out);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, position);
                dialog.dismiss();
            }
        });

        Button two = (Button) dialog.findViewById(R.id.btn_two);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                picName = mParamId+"_"+position+".jpg";
                startActivityForResult(intent, position+10);
                dialog.dismiss();
            }
        });

        Button three = (Button) dialog.findViewById(R.id.btn_three);
        three.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button four = (Button) dialog.findViewById(R.id.btn_four);
        four.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PicBean picBean = picArray[position-1];
                if (picBean!=null){
                    String picPath = picBean.getPicPath();

                    final Dialog dialog_dept = new Dialog(getActivity(),
                            R.style.dialog_camera);
                    dialog_dept.setContentView(R.layout.dialog_image);

                    ImageView imageView = (ImageView) dialog_dept.findViewById(R.id.image_view);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPurgeable=true;
                    options.inSampleSize=1;
                    Bitmap bitmap = BitmapFactory.decodeFile(picPath,options);

                    imageView.setImageBitmap(bitmap);

                    dialog_dept.show();
                }
                dialog.dismiss();
            }
        });

        Button five = (Button) dialog.findViewById(R.id.btn_five);
        five.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                PicBean picBean = picArray[position-1];
                if (picBean!=null){
                    picArray[position-1] = null;
                    String pathUrl = null;
                    switch (position){
                        case 1:
                            Picasso.with(getActivity())
                                    .load(pathUrl)
                                    .resize(80,80)
                                    .centerCrop()
//                                    .error(R.mipmap.ic_launcher)
                                    .into(image1);
                            break;
                        case 2:
                            Picasso.with(getActivity())
                                    .load(pathUrl)
                                    .resize(80,80)
                                    .centerCrop()
//                                    .error(R.mipmap.ic_launcher)
                                    .into(image2);
                            break;
                        case 3:
                            Picasso.with(getActivity())
                                    .load(pathUrl)
                                    .resize(80,80)
                                    .centerCrop()
//                                    .error(R.mipmap.ic_launcher)
                                    .into(image3);
                            break;
                        case 4:
                            Picasso.with(getActivity())
                                    .load(pathUrl)
                                    .resize(80,80)
                                    .centerCrop()
//                                    .error(R.mipmap.ic_launcher)
                                    .into(image4);
                            break;
                        case 5:
                            Picasso.with(getActivity())
                                    .load(pathUrl)
                                    .resize(80,80)
                                    .centerCrop()
//                                    .error(R.mipmap.ic_launcher)
                                    .into(image5);
                            break;
                    }
                }
                dialog.dismiss();
            }
        });

        if ("2".equals(xckcType)){
            one.setVisibility(View.GONE);
            two.setVisibility(View.GONE);
            five.setVisibility(View.GONE);
        }
    }

    public void savePic(int position){
        PicBean picBean = new PicBean();
        picBean.setPicName(picName);
        picBean.setPicPath(picPath);
        picBean.setPicNum(position+"");
        picArray[position-1] = picBean;
    }

    public void uploadImage(PicBean bean){
        String name = bean.getPicName();
        String path = bean.getPicPath();
        String num = bean.getPicNum();

        File file = new File(path);
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        RequestBody requestBody = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"picNum\""),
                        RequestBody.create(null, num))
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"meId\""),
                        RequestBody.create(null, mParamId))
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"mFile\"; filename="+name), fileBody)
                .build();
        String baseUrl = "";
        if(ApiManager.getInstance().isReRoot()){
            baseUrl = ApiManager.API_RE_ROOT2;
        }else{
            baseUrl = ApiManager.API_ROOT2;
        }
        Request request=new Request.Builder()
                .url(baseUrl+"mobileUploadFile.do")
                .post(requestBody)
                .build();

        ApiManager.getInstance().httpClient.newCall(request).enqueue(new com.squareup.okhttp.Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {
                if(picNumber<picSum-1){
                    picNumber++;
                    PicBean bean =picList.get(picNumber);
                    uploadImage(bean);
                }
                if(picNumber==picSum-1){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new MaterialDialog.Builder(getContext())
                                    .title("提交成功")
                                    .content("感谢您的反馈")
                                    .positiveText("确定")
                                    .show();
                        }
                    });

                }

            }
        });
    }


    public void downLoadImage(final String fileName){

        Request request=new Request.Builder()
                .url(baseUrl+fileName)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(10000, TimeUnit.MINUTES);
        okHttpClient.setReadTimeout(10000, TimeUnit.MINUTES);
        okHttpClient.setWriteTimeout(10000, TimeUnit.MINUTES);
        okHttpClient.newCall(request).enqueue(new com.squareup.okhttp.Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {
                File file = new File(Environment.getExternalStorageDirectory()+"/DCIM/Camera/"+fileName);
                BufferedSink sink = Okio.buffer(Okio.sink(file));
                BufferedSource source = response.body().source();
                long bytesRead = 0;
                while (source.read(sink.buffer(),2048)!=-1){
                    bytesRead+=2048;
                }
                sink.writeAll(source);
                sink.close();

                if(imgNumber<imgSum-1){
                    imgNumber++;
                    PicBean bean = imgList.get(imgNumber);
                    String fileName = bean.getPicName();
                    downLoadImage(fileName);
                }else {

                }

                if(imgNumber==imgSum-1){

                    for (int i=0;i<imgList.size();i++){
                        PicBean bean = new PicBean();
                        String imgName = imgList.get(i).getPicName();
                        bean.setPicName(imgName);
                        final String number = imgList.get(i).getPicNum();
                        bean.setPicNum(number);
                        String path = Environment.getExternalStorageDirectory()+"/DCIM/Camera/"+imgName;
                        bean.setPicPath(path);
                        picArray[Integer.parseInt(number)-1] = bean;

                        final File file2 = new File(path);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                switch (number){
                                    case "1":
                                        Picasso.with(getActivity())
                                                .load(file2)
                                                .resize(80,80)
                                                .centerCrop()
//                                                .error(R.mipmap.ic_launcher)
                                                .into(image1);
                                        break;
                                    case "2":
                                        Picasso.with(getActivity())
                                                .load(file2)
                                                .resize(80,80)
                                                .centerCrop()
//                                                .error(R.mipmap.ic_launcher)
                                                .into(image2);
                                        break;
                                    case "3":
                                        Picasso.with(getActivity())
                                                .load(file2)
                                                .resize(80,80)
                                                .centerCrop()
//                                                .error(R.mipmap.ic_launcher)
                                                .into(image3);
                                        break;
                                    case "4":
                                        Picasso.with(getActivity())
                                                .load(file2)
                                                .resize(80,80)
                                                .centerCrop()
//                                                .error(R.mipmap.ic_launcher)
                                                .into(image4);
                                        break;
                                    case "5":
                                        Picasso.with(getActivity())
                                                .load(file2)
                                                .resize(80,80)
                                                .centerCrop()
//                                                .error(R.mipmap.ic_launcher)
                                                .into(image5);
                                        break;
                                }
                            }
                        });


                    }
                }
            }
        });

    }
}
