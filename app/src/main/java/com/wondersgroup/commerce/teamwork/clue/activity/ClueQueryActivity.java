package com.wondersgroup.commerce.teamwork.clue.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.BaseActivity;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.widget.TableRow;

import butterknife.BindView;
import butterknife.ButterKnife;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;

/*
* 线索查询页面
* */
public class ClueQueryActivity extends BaseActivity {
    @BindView(R.id.layout_add)
    LinearLayout layoutAdd;
    @BindView(R.id.btn_query)
    Button btnQuery;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    private TableRow djjg, djbm, djsj, djsj_start, djsj_end, djr, xsbh, xsmc, xsly, xsbljg;

    private TotalLoginBean loginBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_clue_query);

        ButterKnife.bind(this);
        tvTitle.setText("线索查询");
        loginBean = Hawk.get(Constants.LOGIN_BEAN);

        //初始化查询界面
        initView();
    }

    public void initView(){
        //重新设置scrollView的高度
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                RootAppcation.getInstance().getHeightPixels() - DWZH.dp(128));
        scrollView.setLayoutParams(lp);
        scrollView.requestLayout();

        djjg = new TableRow.Builder(ClueQueryActivity.this)
                .title("登记机关")
                .select()
                .content(loginBean.getResult().getOrganName())
                .build();
        layoutAdd.addView(djjg);

        djbm = new TableRow.Builder(ClueQueryActivity.this)
                .title("登记部门")
                .select()
                .content(loginBean.getResult().getDeptName())
                .build();
        layoutAdd.addView(djbm);

        djsj_start = new TableRow.Builder(ClueQueryActivity.this)
                .noTitle()
                .time("开始时间")
                .build();

        djsj_end = new TableRow.Builder(ClueQueryActivity.this)
                .noTitle()
                .time("结束时间")
                .hideBtmLine()
                .build();

        djsj = new TableRow.Builder(ClueQueryActivity.this)
                .title("登记时间")
                .addChild(djsj_start, djsj_end)
                .build();
        layoutAdd.addView(djsj);

        djr = new TableRow.Builder(ClueQueryActivity.this)
                .title("登记人")
                .input("请输入")
                .build();
        layoutAdd.addView(djr);

        xsbh = new TableRow.Builder(ClueQueryActivity.this)
                .title("线索编号")
                .input("请输入")
                .build();
        layoutAdd.addView(xsbh);

        xsmc = new TableRow.Builder(ClueQueryActivity.this)
                .title("线索名称")
                .input("请输入")
                .build();
        layoutAdd.addView(xsmc);

        xsly = new TableRow.Builder(ClueQueryActivity.this)
                .title("线索来源")
                .select("请选择")
                .build();
        layoutAdd.addView(xsly);

        xsbljg = new TableRow.Builder(ClueQueryActivity.this)
                .title("线索办理结果")
                .select("请选择")
                .build();
        layoutAdd.addView(xsbljg);

        ClassLoader c = ClassLoader.getSystemClassLoader();
        BaseDexClassLoader baseDexClassLoader = new BaseDexClassLoader("", null, "", null);
        DexClassLoader dexClassLoader = new DexClassLoader("", null, "", null);
    }
}
