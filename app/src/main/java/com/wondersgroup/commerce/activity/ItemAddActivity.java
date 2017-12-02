package com.wondersgroup.commerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.DataVolume;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.model.TreeBean;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.utils.TableRowUtils;
import com.wondersgroup.commerce.widget.TableRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAddActivity extends BaseActivity {
    @BindView(R.id.layout_add)
    LinearLayout layoutAdd;
    @BindView(R.id.btn_group)
    LinearLayout btnGroup;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    private LinearLayout childLayout;
    private String type;
    private TotalLoginBean loginBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_item_add);

        ButterKnife.bind(this);
        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        type = getIntent().getStringExtra(Constants.TYPE);
        if (type != null){
            switch (type){
                default:
                    tvTitle.setText("详情");
                    break;
                case "dataVolume":
                    tvTitle.setText("当事人信息");
                    addDataVolume((List<DataVolume>)getIntent().getSerializableExtra("list"));
                    break;
                case Constants.XSDJ_NAME_SC:
                    tvTitle.setText(Constants.XSDJ_NAME_SC);
                    btnGroup.setVisibility(View.VISIBLE);
                    initScrollView();
                    initXsdj();
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 101:
                if (data != null){
                    TreeBean treeBean = data.getParcelableExtra(Constants.TREE_BEAN);
                    if (treeBean != null){
                        switch (treeBean.getId()){
                            case "1":
                                initLayout_1();
                                break;
                            case "2":
                                initLayout_2();
                                break;
                            case "3":
                            case "4":
                                initLayout_3();
                                break;
                        }
                    }
                }
                break;
        }
    }

    public void addDataVolume(List<DataVolume> list){
        TableRowUtils utils = new TableRowUtils(this, layoutAdd, list);
        utils.build();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        super.onWindowFocusChanged(hasFocus);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, RootAppcation.getInstance().getHeightPixels() - DWZH.dp(50));
        scrollView.setLayoutParams(lp);

        scrollView.requestLayout();
    }

    public void initScrollView(){

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, RootAppcation.getInstance().getHeightPixels() - DWZH.dp(50));
        scrollView.setLayoutParams(lp);

        scrollView.requestLayout();
    }

    public void initXsdj(){
        final ArrayList<TreeBean> array_xsly = new ArrayList<>(Arrays.asList(
                new TreeBean("1", "监督检查", null),
                new TreeBean("2", "投诉、申诉、举报", null),
                new TreeBean("3", "其他机关移送", null),
                new TreeBean("4", "上级机关交办", null)));

        TableRow xsly = new TableRow.Builder(ItemAddActivity.this)
                .title("线索来源")
                .select("请选择")
                .onSelect(new TableRow.SelectCallBack() {
                    @Override
                    public void onSelect(TableRow row, int which) {
                        Intent intent = new Intent(ItemAddActivity.this, SingleChoiceActivity.class);
                        intent.putParcelableArrayListExtra(Constants.ARRAY, array_xsly);
                        startActivityForResult(intent, 101);
                    }
                })
                .required()
                .build();
        layoutAdd.addView(xsly);

        //可变子布局
        childLayout = new LinearLayout(ItemAddActivity.this);
        childLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        childLayout.setLayoutParams(lp);
        layoutAdd.addView(childLayout);

        //案源登记
        initAydj();

        //附件
        initFj();

        //操作信息
        initOperation();

        //添加底部信息
        initBottom();
    }

    public void initAydj(){
        TableRow title = new TableRow.Builder(ItemAddActivity.this)
                .asTitle("案源登记")
                .build();
        layoutAdd.addView(title);

        TableRow aymc = new TableRow.Builder(ItemAddActivity.this)
                .title("案源名称")
                .required()
                .input("关于XXX涉嫌XXX案")
                .build();
        layoutAdd.addView(aymc);

        TableRow aydjnr = new TableRow.Builder(ItemAddActivity.this)
                .title("案源登记内容")
                .required()
                .select("点击输入")
                .build();
        layoutAdd.addView(aydjnr);

        TableRow djr = new TableRow.Builder(ItemAddActivity.this)
                .title("登记人")
                .content(loginBean.getResult().getUserName())
                .build();
        layoutAdd.addView(djr);

        TableRow djsj = new TableRow.Builder(ItemAddActivity.this)
                .title("登记时间")
                .required()
                .time("请选择")
                .build();
        layoutAdd.addView(djsj);

        TableRow bz = new TableRow.Builder(ItemAddActivity.this)
                .title("备注")
                .select("点击输入")
                .build();
        layoutAdd.addView(bz);
    }

    public void initFj(){
        TableRow title = new TableRow.Builder(ItemAddActivity.this)
                .asTitle("附件")
                .build();
        layoutAdd.addView(title);
    }

    public void initOperation(){
        TableRow title = new TableRow.Builder(ItemAddActivity.this)
                .asTitle("操作信息")
                .build();
        layoutAdd.addView(title);

        TableRow czcl = new TableRow.Builder(ItemAddActivity.this)
                .title("操作处理")
                .select("")
                .content("报审核")
                .build();
        layoutAdd.addView(czcl);

        TableRow nextOrgan = new TableRow.Builder(ItemAddActivity.this)
                .title("下一步办理机关")
                .select("")
                .content(loginBean.getResult().getOrganName())
                .build();
        layoutAdd.addView(nextOrgan);

        TableRow nextDept = new TableRow.Builder(ItemAddActivity.this)
                .title("下一步办理部门")
                .select("")
                .content(loginBean.getResult().getDeptName())
                .build();
        layoutAdd.addView(nextDept);

        TableRow blr = new TableRow.Builder(ItemAddActivity.this)
                .title("下一步办理人")
                .select("")
                .content(loginBean.getResult().getUserName())
                .build();
        layoutAdd.addView(blr);
    }

    public void initBottom(){
        TextView notice = new TextView(ItemAddActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(30, 30, 30 ,30);
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        notice.setLayoutParams(lp);
        notice.setText("已经到底啦~~");
        notice.setTextSize(12);
        notice.setTextColor(getResources().getColor(R.color.blue));
        layoutAdd.addView(notice);
    }

    public void initLayout_1(){
        childLayout.removeAllViews();
        TableRow aytgr = new TableRow.Builder(ItemAddActivity.this)
                .asTitle("案源提供人")
                .build();
        childLayout.addView(aytgr);

        TableRow name_1 = new TableRow.Builder(ItemAddActivity.this)
                .title("姓名")
                .input("请输入")
                .required()
                .content(loginBean.getResult().getUserName())
                .build();
        childLayout.addView(name_1);

        TableRow dept_1 = new TableRow.Builder(ItemAddActivity.this)
                .title("执法单位")
                .input("请输入")
                .required()
                .content(loginBean.getResult().getDeptName())
                .build();
        childLayout.addView(dept_1);

        TableRow name_2 = new TableRow.Builder(ItemAddActivity.this)
                .title("姓名")
                .input("请输入")
                .required()
                .build();
        childLayout.addView(name_2);

        TableRow dept_2 = new TableRow.Builder(ItemAddActivity.this)
                .title("执法单位")
                .input("请输入")
                .required()
                .content(loginBean.getResult().getDeptName())
                .build();
        childLayout.addView(dept_2);
    }

    public void initLayout_2(){
        childLayout.removeAllViews();
        TableRow aytgr = new TableRow.Builder(ItemAddActivity.this)
                .asTitle("案源提供人")
                .build();
        childLayout.addView(aytgr);

        TableRow sfbm = new TableRow.Builder(ItemAddActivity.this)
                .title("是否保密")
                .select("")
                .build();
        childLayout.addView(sfbm);

        TableRow djrlxdh = new TableRow.Builder(ItemAddActivity.this)
                .title("登记人联系电话")
                .input("请输入")
                .build();
        childLayout.addView(djrlxdh);

        TableRow sflm = new TableRow.Builder(ItemAddActivity.this)
                .title("是否匿名")
                .select("否")
                .build();
        childLayout.addView(sflm);

        TableRow name = new TableRow.Builder(ItemAddActivity.this)
                .title("个人姓名")
                .input("")
                .build();
        childLayout.addView(name);

        TableRow sfz = new TableRow.Builder(ItemAddActivity.this)
                .title("身份证（其他有效证件）号码")
                .input("请输入")
                .build();
        childLayout.addView(sfz);

        TableRow dwmc = new TableRow.Builder(ItemAddActivity.this)
                .title("单位名称")
                .input("请输入")
                .build();
        childLayout.addView(dwmc);

        TableRow fddbr = new TableRow.Builder(ItemAddActivity.this)
                .title("法定代表人/负责人/联系人")
                .input("请输入")
                .build();
        childLayout.addView(fddbr);

        TableRow lxdh = new TableRow.Builder(ItemAddActivity.this)
                .title("联系电话")
                .input("请输入")
                .build();
        childLayout.addView(lxdh);

        TableRow email = new TableRow.Builder(ItemAddActivity.this)
                .title("邮政编码")
                .input("请输入")
                .build();
        childLayout.addView(email);

        TableRow address = new TableRow.Builder(ItemAddActivity.this)
                .title("联系地址")
                .input("请输入")
                .build();
        childLayout.addView(address);
    }

    public void initLayout_3(){
        childLayout.removeAllViews();
        TableRow aytgr = new TableRow.Builder(ItemAddActivity.this)
                .asTitle("案源提供人")
                .build();
        childLayout.addView(aytgr);

        TableRow name = new TableRow.Builder(ItemAddActivity.this)
                .title("名称")
                .required()
                .input("请输入")
                .build();
        childLayout.addView(name);

        TableRow contacts = new TableRow.Builder(ItemAddActivity.this)
                .title("联系人")
                .input("请输入")
                .build();
        childLayout.addView(contacts);

        TableRow email = new TableRow.Builder(ItemAddActivity.this)
                .title("邮政编码")
                .input("请输入")
                .build();
        childLayout.addView(email);

        TableRow tel = new TableRow.Builder(ItemAddActivity.this)
                .title("联系电话")
                .input("请输入")
                .build();
        childLayout.addView(tel);

        TableRow address = new TableRow.Builder(ItemAddActivity.this)
                .title("联系地址")
                .input("请输入")
                .build();
        childLayout.addView(address);
    }
}
