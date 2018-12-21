package guopuran.bwie.com.guopuran.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import guopuran.bwie.com.guopuran.Apis;
import guopuran.bwie.com.guopuran.R;
import guopuran.bwie.com.guopuran.bean.ViewPagerBean;
import guopuran.bwie.com.guopuran.presenter.IpresenterImpl;
import guopuran.bwie.com.guopuran.view.IView;

public class MainActivity extends AppCompatActivity implements IView {
    private IpresenterImpl mIpresenterImpl;
    private Banner banner;
    private List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();
        initView();
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

    private void initView() {
        //获取资源ID
        banner = findViewById(R.id.main_banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.isAutoPlay(false);
        banner.setImageLoader(new ImageLoaderInterface<ImageView>() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }

            @Override
            public ImageView createImageView(Context context) {
                ImageView imageView=new ImageView(context);
                //imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }
        });
        initUrl();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (position==3){
                    Intent intent=new Intent(MainActivity.this,ShopActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void initUrl() {
        mIpresenterImpl.requestter(Apis.URL_SHOU,new HashMap<String, String>(),ViewPagerBean.class);
    }

    @Override
    public void getdata(Object object) {
        if (object instanceof ViewPagerBean){
            ViewPagerBean bean= (ViewPagerBean) object;
            for (int i=0;i<bean.getData().size();i++){
                list.add(bean.getData().get(i).getIcon());
            }
            banner.setImages(list);
            banner.start();
        }
    }
}
