package com.sunli.decembermultiple.loginorenroll;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sunli.decembermultiple.R;
import com.sunli.decembermultiple.loginorenroll.bean.EnRollBean;
import com.sunli.decembermultiple.shell_frame.mvp.presenter.IPresenterImpl;
import com.sunli.decembermultiple.shell_frame.mvp.view.IView;
import com.sunli.decembermultiple.shell_frame.network.ApiUtils;
import com.sunli.decembermultiple.shell_frame.network.Constants;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegisterActivity extends AppCompatActivity implements IView {

    @BindView(R.id.activity_register_edit_phone_number)
    EditText edit_number;
    @BindView(R.id.activity_register_edit_pwd)
    EditText edit_pwd;
    @BindView(R.id.activity_register_edit_validatecode)
    EditText edit_validatecode;
    @BindView(R.id.activity_register_text_send_validatecode)
    TextView text_send_validate_code;

    private Unbinder bind;
    private IPresenterImpl iPresenter;
    private int i = 60;
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what == 0){
                if(i == 0){
                    text_send_validate_code.setText("发送验证码");
                    text_send_validate_code.setTextColor(Color.WHITE);
                }else{
                    i--;
                    text_send_validate_code.setText(i+"s秒后重试");
                    handler.sendEmptyMessageDelayed(0, 1000);
                }
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        iPresenter = new IPresenterImpl(this);
        bind = ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_register_text_login)
    public void textLoginClick(){
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
    }

    @OnClick(R.id.activity_register_btn_enroll)
    public void btnEnrollClick(){
        Map<String, String> params = new HashMap<>();
        params.put(Constants.POST_BODY_KEY_REGISTER_PHONE, edit_number.getText().toString());
        params.put(Constants.POST_BODY_KEY_REGISTER_PASSWORD, edit_pwd.getText().toString());
        iPresenter.startRequestPost(ApiUtils.POST_URL_USER_REGISTER, params, EnRollBean.class);
    }

    @OnClick(R.id.activity_register_text_send_validatecode)
    public void textSendValidateCode(){
        handler.sendEmptyMessageDelayed(0, 1000);
        if (i == 0) {
            handler.sendEmptyMessageDelayed(0, 1000);
        }
    }

    @Override
    public void showResponseData(Object data) {
        EnRollBean bean = (EnRollBean) data;
        Toast.makeText(RegisterActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
    }

    @Override
    public void showResponseFail(String e) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
