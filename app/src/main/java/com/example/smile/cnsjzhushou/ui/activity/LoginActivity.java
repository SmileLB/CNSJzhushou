package com.example.smile.cnsjzhushou.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.example.smile.cnsjzhushou.R;
import com.example.smile.cnsjzhushou.di.component.AppComponent;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LiBing
 * on 2017/7/11 0011
 * describe:
 */

public class LoginActivity extends BaseActivity {

    private static final int READ_PHONE_STATE_CODE = 1000;
    @BindView(R.id.btn)
    Button mBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {

    }

    @OnClick(R.id.btn)
    public void onClick() {
//        RxPermissions rxPermissions = new RxPermissions(this);
//
//        rxPermissions.request(Manifest.permission.CAMERA)
//                .subscribe(new Action1<Boolean>() {
//                    @Override
//                    public void call(Boolean aBoolean) {
//                        if(aBoolean){
//
//
//                        }else {
//
//                        }
//
//                    }
//                });




//        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED) {
//            //没有授权，请求授权
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},READ_PHONE_STATE_CODE);
//
//        }else{
//            //已经授权
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if(requestCode==requestCode){
//            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
//
//            }else{
//
//            }
//        }
    }
}
