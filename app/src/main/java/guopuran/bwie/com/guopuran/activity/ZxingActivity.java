package guopuran.bwie.com.guopuran.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cn.bingoogolapple.qrcode.zxing.ZXingView;
import guopuran.bwie.com.guopuran.R;

public class ZxingActivity extends AppCompatActivity implements ZXingView.Delegate {

    private ZXingView zXingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
        //获取资源ID
        zXingView = findViewById(R.id.zing);
        zXingView.setDelegate(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //启动相机
        zXingView.startCamera();
        //开始聚焦
        zXingView.startSpotAndShowRect();

    }

    @Override
    protected void onStop() {
        super.onStop();
        //停止相机
        zXingView.stopCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁
        zXingView.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Toast.makeText(this, "识别成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }
}
