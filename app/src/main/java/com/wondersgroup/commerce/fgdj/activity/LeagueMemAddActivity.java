package com.wondersgroup.commerce.fgdj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.SingleItemSelectActivity;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.fgdj.bean.DicBean;
import com.wondersgroup.commerce.fgdj.bean.LeagueMem;
import com.wondersgroup.commerce.fgdj.util.CheckUtil;
import com.wondersgroup.commerce.fgdj.widget.InputUnit;
import com.wondersgroup.commerce.fgdj.widget.SelectUnit;
import com.wondersgroup.commerce.fgdj.widget.TimeUnit;
import com.wondersgroup.commerce.interface_.TextChanger;
import com.wondersgroup.commerce.model.KeyValue;
import com.wondersgroup.commerce.utils.DateUtil;

import java.util.LinkedHashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LeagueMemAddActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_save)
    TextView tvSave;
    @Bind(R.id.input_name)
    InputUnit inputName;
    @Bind(R.id.select_gender)
    SelectUnit selectGender;
    @Bind(R.id.select_cert_type)
    SelectUnit selectCertType;
    @Bind(R.id.input_cert_num)
    InputUnit inputCertNum;
    @Bind(R.id.select_nation)
    SelectUnit selectNation;
    @Bind(R.id.time_birth)
    TimeUnit timeBirth;
    @Bind(R.id.select_league_mem_type)
    SelectUnit selectLeagueMemType;
    @Bind(R.id.select_league_position)
    SelectUnit selectLeaguePosition;
    @Bind(R.id.time_join)
    TimeUnit timeJoin;
    @Bind(R.id.input_job)
    InputUnit inputJob;
    @Bind(R.id.select_edu)
    SelectUnit selectEdu;
    @Bind(R.id.input_tel)
    InputUnit inputTel;
    @Bind(R.id.input_tel_2)
    InputUnit inputTel2;
    @Bind(R.id.et_detail)
    EditText etDetail;
    @Bind(R.id.input_league_name)
    InputUnit inputLeagueName;

    private LeagueMem leagueMem;
    private int clickId;
    private DicBean dicBean;
    private String type;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_mem_add);
        ButterKnife.bind(this);

        tvTitle.setText("团员信息");
        tvSave.setVisibility(View.VISIBLE);
        tvSave.setOnClickListener(this);
        imgBack.setOnClickListener(this);

        leagueMem = getIntent().getParcelableExtra(Constants.DATA);
        if (leagueMem == null)
            leagueMem = new LeagueMem();
        dicBean = Hawk.get(Constants.DIC);
        type = getIntent().getStringExtra(Constants.TYPE);
        position = getIntent().getIntExtra(Constants.POSITION,0);

        timeBirth.setTime(leagueMem.getBirthday());
        timeBirth.tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateUtil.createDatePicker(LeagueMemAddActivity.this,timeBirth.tvDate);
            }
        });

        timeJoin.setTime(leagueMem.getJoinDate());
        timeJoin.tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateUtil.createDatePicker(LeagueMemAddActivity.this,timeJoin.tvDate);
            }
        });

        inputName.setInput(leagueMem.getName());
        inputName.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueMem.setName(String.valueOf(s));
            }
        });

        inputCertNum.setInput(leagueMem.getCerno());
        inputCertNum.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueMem.setCerno(String.valueOf(s));
            }
        });

        inputJob.setInput(leagueMem.getJobPosition());
        inputJob.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueMem.setJobPosition(String.valueOf(s));
            }
        });

        inputLeagueName.setInput(leagueMem.getLeagueOrgName());
        inputLeagueName.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueMem.setLeagueOrgName(String.valueOf(s));
            }
        });

        if (Constants.LOOK.equals(type))
            inputTel.setHint("");
        inputTel.setInput(leagueMem.getMobile());
        inputTel.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueMem.setMobile(String.valueOf(s));
            }
        });

        if (Constants.LOOK.equals(type))
            inputTel2.setHint("");
        inputTel2.setInput(leagueMem.getFixtel());
        inputTel2.etInput.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueMem.setFixtel(String.valueOf(s));
            }
        });

        if (Constants.LOOK.equals(type))
            etDetail.setHint("");
        etDetail.setText(leagueMem.getMembershipInfo());
        etDetail.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                leagueMem.setMembershipInfo(String.valueOf(s));
            }
        });

        if (leagueMem.getCertype() != null){
            LinkedHashMap hashMap = dicBean.getCertType();
            selectCertType.tvSelected.setText(hashMap.get(leagueMem.getCertype()).toString());
        }
        selectCertType.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeagueMemAddActivity.this,SingleItemSelectActivity.class);
                intent.putExtra(Constants.TYPE, Constants.CERT_TYPE);
                intent.putExtra(Constants.TITLE,"证件类型");
                startActivityForResult(intent, Constants.REQUEST_CERT_TYPE);
            }
        });

        if (leagueMem.getNation() != null){
            LinkedHashMap hashMap = dicBean.getNation();
            selectNation.tvSelected.setText(hashMap.get(leagueMem.getNation()).toString());
        }
        selectNation.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeagueMemAddActivity.this,SingleItemSelectActivity.class);
                intent.putExtra(Constants.TYPE, Constants.NATION);
                intent.putExtra(Constants.TITLE,"民族");
                startActivityForResult(intent, Constants.REQUEST_NATION);
            }
        });

        if (leagueMem.getType() != null){
            LinkedHashMap hashMap = dicBean.getLeagueMemType();
            selectLeagueMemType.tvSelected.setText(hashMap.get(leagueMem.getType()).toString());
        }
        selectLeagueMemType.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeagueMemAddActivity.this,SingleItemSelectActivity.class);
                intent.putExtra(Constants.TYPE, Constants.LEAGUE_MEM_TYPE);
                intent.putExtra(Constants.TITLE,"类别");
                startActivityForResult(intent, Constants.REQUEST_PARTY_MEM_TYPE);
            }
        });

        if (leagueMem.getLeaguePositon() != null){
            LinkedHashMap hashMap = dicBean.getLeaguePosition();
            selectLeaguePosition.tvSelected.setText(hashMap.get(leagueMem.getLeaguePositon()).toString());
        }
        selectLeaguePosition.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeagueMemAddActivity.this,SingleItemSelectActivity.class);
                intent.putExtra(Constants.TYPE, Constants.LEAGUE_POSITION);
                intent.putExtra(Constants.TITLE,"团内职务");
                startActivityForResult(intent, Constants.REQUEST_PARTY_POSITION);
            }
        });

        if (leagueMem.getEducation() != null){
            LinkedHashMap hashMap = dicBean.getEducation();
            selectEdu.tvSelected.setText(hashMap.get(leagueMem.getEducation()).toString());
        }
        selectEdu.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeagueMemAddActivity.this,SingleItemSelectActivity.class);
                intent.putExtra(Constants.TYPE, Constants.EDUCATION);
                intent.putExtra(Constants.TITLE,"学历");
                startActivityForResult(intent, Constants.REQUEST_EDUCATION);
            }
        });

        if (leagueMem.getSex() != null){
            LinkedHashMap hashMap = dicBean.getGender();
            selectGender.tvSelected.setText(hashMap.get(leagueMem.getSex()).toString());
        }
        selectGender.tvSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeagueMemAddActivity.this,SingleItemSelectActivity.class);
                intent.putExtra(Constants.TYPE, Constants.GENDER);
                intent.putExtra(Constants.TITLE,"性别");
                startActivityForResult(intent, Constants.REQUEST_GENDER);
            }
        });


        if(Constants.LOOK.equals(type))
            justShow();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Constants.RESPONSE_KEY_VALUE){
            KeyValue keyValue = data.getParcelableExtra(Constants.KEY_VALUE);
            switch (requestCode){
                case Constants.REQUEST_GENDER:
                    selectGender.setSelected(true);
                    selectGender.tvSelected.setText(keyValue.getValue());
                    leagueMem.setSex(keyValue.getKey());
                    break;
                case Constants.REQUEST_CERT_TYPE:
                    selectCertType.setSelected(true);
                    selectCertType.tvSelected.setText(keyValue.getValue());
                    leagueMem.setCertype(keyValue.getKey());
                    break;
                case Constants.REQUEST_NATION:
                    selectNation.setSelected(true);
                    selectNation.tvSelected.setText(keyValue.getValue());
                    leagueMem.setNation(keyValue.getKey());
                    break;
                case Constants.REQUEST_PARTY_MEM_TYPE:
                    selectLeagueMemType.setSelected(true);
                    selectLeagueMemType.tvSelected.setText(keyValue.getValue());
                    leagueMem.setType(keyValue.getKey());
                    break;
                case Constants.REQUEST_PARTY_POSITION:
                    selectLeaguePosition.setSelected(true);
                    selectLeaguePosition.tvSelected.setText(keyValue.getValue());
                    leagueMem.setLeaguePositon(keyValue.getKey());
                    break;
                case Constants.REQUEST_EDUCATION:
                    selectEdu.setSelected(true);
                    selectEdu.tvSelected.setText(keyValue.getValue());
                    leagueMem.setEducation(keyValue.getKey());
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_save:
                save();
                break;
        }
    }

    private void save(){
        if (!checkSave())
            return;

        leagueMem.setBirthday(timeBirth.getTime());
        leagueMem.setJoinDate(timeJoin.getTime());

        Intent intent = new Intent();
        intent.putExtra(Constants.LEAGUE_MEM,leagueMem);
        if (Constants.ADD.equals(type)){
            setResult(Constants.RESPONSE_ADD,intent);
        }else {
            intent.putExtra(Constants.POSITION,position);
            setResult(Constants.RESPONSE_EDIT,intent);
        }

        finish();
    }

    public boolean checkSave(){
        CheckUtil checkUtil = new CheckUtil();
        return checkUtil.checkInput(inputName) &&
                checkUtil.checkSelect(selectGender) &&
                checkUtil.checkSelect(selectCertType) &&
                checkUtil.checkInput(inputCertNum) &&
                ("10".equals(leagueMem.getCertype()) ? (checkUtil.checkIdentity(inputCertNum.getInput())) : true) && //只对证件类型为身份证的证件号码做校验
                checkUtil.checkSelect(selectNation) &&
                checkUtil.checkTime(timeBirth) &&
                checkUtil.checkTime(timeJoin) &&
                checkUtil.checkSelect(selectLeagueMemType) &&
                checkUtil.checkInput(inputLeagueName) &&
                checkUtil.checkSelect(selectLeaguePosition) &&
                checkUtil.checkInput(inputJob) &&
                checkUtil.checkSelect(selectEdu) &&
                (TextUtils.isEmpty(inputTel.getInput()) ? true : checkUtil.checkTel(inputTel)) &&
                (TextUtils.isEmpty(inputTel2.getInput()) ? true : checkUtil.checkTel(inputTel2));
    }

    public void justShow(){
        tvSave.setVisibility(View.GONE);

        //取消必填项标记/索引图标、点击事件
        inputName.hideMark();
        inputName.cancel();
        selectGender.hideMark();
        selectGender.hideIndex();
        selectGender.cancel();
        selectCertType.hideMark();
        selectCertType.hideIndex();
        selectCertType.cancel();
        inputCertNum.hideMark();
        inputCertNum.cancel();
        selectNation.hideMark();
        selectNation.hideIndex();
        selectNation.cancel();
        timeBirth.hideMark();
        timeBirth.cancel();
        timeJoin.hideMark();
        timeJoin.cancel();
        selectLeagueMemType.hideMark();
        selectLeagueMemType.hideIndex();
        selectLeagueMemType.cancel();
        selectLeaguePosition.hideMark();
        selectLeaguePosition.hideIndex();
        selectLeaguePosition.cancel();
        inputJob.hideMark();
        inputJob.cancel();
        inputLeagueName.hideMark();
        inputLeagueName.cancel();
        selectEdu.hideMark();
        selectEdu.hideIndex();
        selectEdu.cancel();
        inputTel.cancel();
        inputTel2.cancel();
        etDetail.setEnabled(false);
    }
}
