package guopuran.bwie.com.guopuran.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import guopuran.bwie.com.guopuran.Apis;
import guopuran.bwie.com.guopuran.CustomView;
import guopuran.bwie.com.guopuran.R;
import guopuran.bwie.com.guopuran.adapter.ChangeAdapter;
import guopuran.bwie.com.guopuran.adapter.JiuAdapter;
import guopuran.bwie.com.guopuran.bean.ChangeBean;
import guopuran.bwie.com.guopuran.bean.JiuBean;
import guopuran.bwie.com.guopuran.bean.ZiBean;
import guopuran.bwie.com.guopuran.presenter.IpresenterImpl;
import guopuran.bwie.com.guopuran.view.IView;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener,IView {
    private IpresenterImpl mIpresenterImpl;
    private Button button_sao;
    private Button button_change;
    private CustomView customView;
    private RecyclerView jiu_recy;
    private final int JIU_COUNT=5;
    private final int Change_COUNT=2;
    private JiuAdapter jiuAdapter;
    private RecyclerView change_recy;
    private boolean flag=true;
    private ChangeAdapter changeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initPresenter();
        initView();
    }

    private void initView() {
        //获取资源ID
        button_sao = findViewById(R.id.shop_sao);
        button_change = findViewById(R.id.shop_change);
        customView = findViewById(R.id.shop_sou);
        jiu_recy = findViewById(R.id.shop_jiu);
        change_recy = findViewById(R.id.recy_change);
        button_sao.setOnClickListener(this);
        button_change.setOnClickListener(this);
        initJiu();
        initData();
       // mIpresenterImpl.requestter(Apis.URL_CHANGE,new HashMap<String, String>(),ChangeBean.class);
        List<ZiBean> list=new ArrayList<>();
        for (int i=0;i<20;i++){
            ZiBean bean=new ZiBean(R.mipmap.ic_launcher,"数据"+i,"价格"+i);
            list.add(bean);
        }
        changeAdapter.setList(list);
    }

    //九宫格
    private void initJiu() {
        //管理器
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,JIU_COUNT);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        jiu_recy.setLayoutManager(gridLayoutManager);
        mIpresenterImpl.requestter(Apis.URL_JIU,new HashMap<String, String>(),JiuBean.class);
        //设置适配器
        jiuAdapter = new JiuAdapter(this);
        jiu_recy.setAdapter(jiuAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.shop_sao:
                startActivity(new Intent(ShopActivity.this,ZxingActivity.class));
                break;
            case R.id.shop_change:
                List<ZiBean> list = changeAdapter.getList();
                initData();
                changeAdapter.setList(list);
                break;
            default:break;
        }
    }

    private void initData() {
        if (flag){
            initLin();
        }else{
            initGrid();
        }
        changeAdapter = new ChangeAdapter(this,flag);
        change_recy.setAdapter(changeAdapter);
        changeAdapter.setonLongClick(new ChangeAdapter.onLongClick() {
            @Override
            public void onlongclick(int position) {
                Toast.makeText(ShopActivity.this, position+"", Toast.LENGTH_SHORT).show();
            }
        });
        flag=!flag;
    }

    private void initLin() {
        //线性管理器
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        change_recy.setLayoutManager(linearLayoutManager);
    }

    private void initGrid() {
        //管理器
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,Change_COUNT);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        change_recy.setLayoutManager(gridLayoutManager);
    }

    //互绑
    private void initPresenter() {
        mIpresenterImpl=new IpresenterImpl(this);
    }
    //解绑

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIpresenterImpl.Deatch();
    }
    @Override
    public void getdata(Object object) {
        if (object instanceof  JiuBean){
            JiuBean bean= (JiuBean) object;
            jiuAdapter.setList(bean.getData());
        }
        /*if (object instanceof  ChangeBean){
            ChangeBean bean= (ChangeBean) object;
            changeAdapter.setList(bean.getData());
        }*/
    }
}
