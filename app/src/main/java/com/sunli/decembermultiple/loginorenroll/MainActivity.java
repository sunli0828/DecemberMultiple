package com.sunli.decembermultiple.loginorenroll;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.sunli.decembermultiple.commenpage.HomePageActivity;
import com.sunli.decembermultiple.R;
import com.sunli.decembermultiple.shell_frame.bean.LoginBean;
import com.sunli.decembermultiple.shell_frame.mvp.presenter.IPresenterImpl;
import com.sunli.decembermultiple.shell_frame.mvp.view.IView;
import com.sunli.decembermultiple.shell_frame.network.ApiUtils;
import com.sunli.decembermultiple.shell_frame.network.Constants;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements IView {

    @BindView(R.id.activity_main_edit_phone_number)
    EditText edit_number;
    @BindView(R.id.activity_main_edit_login_pwd)
    EditText edit_pwd;
    @BindView(R.id.activity_main_ckbox_remember_pwd)
    CheckBox checkBox;

    private IPresenterImpl iPresenter;
    private Unbinder bind;
    private boolean flag = true;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private String number;
    private String pwd;
    private boolean ck_remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iPresenter = new IPresenterImpl(this);

        bind = ButterKnife.bind(this);

        number = edit_number.getText().toString();
        pwd = edit_pwd.getText().toString();
        sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
        edit = sharedPreferences.edit();
        ck_remember = sharedPreferences.getBoolean("ck_remember", false);

    }

    //注册
    @OnClick(R.id.activity_main_text_enroll)
    public void textEnrollClick() {
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }

    //密码的显示隐藏
    @OnClick(R.id.activity_main_image_pwd_hide)
    public void hideOrShowPwdClick() {
        if (flag) {
            edit_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            edit_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        flag = !flag;
    }

    //登录
    @OnClick(R.id.activity_main_btn_login)
    public void btnLoginClick() {
        Map<String, String> params = new HashMap<>();
        params.put(Constants.POST_BODY_KEY_LOGIN_PHONE, edit_number.getText().toString());
        params.put(Constants.POST_BODY_KEY_LOGIN_PASSWORD, edit_pwd.getText().toString());
        iPresenter.startRequestPost(ApiUtils.POST_URL_USER_LOGIN, params, LoginBean.class);
    }

    //游客模式
    @OnClick(R.id.activity_main_text_visitor)
    public void textVisitorClick() {
        startActivity(new Intent(MainActivity.this, HomePageActivity.class));
    }

    //记住密码勾选
    @OnCheckedChanged(R.id.activity_main_ckbox_remember_pwd)
    public void ckboxRememberPwdChecked() {
        if(ck_remember){
            String num = sharedPreferences.getString("name", null);
            String pwds = sharedPreferences.getString("pwd", null);
            edit_pwd.setText(pwds);
            edit_number.setText(num);
        }else{
            edit.remove("ck_remember");
        }
    }

    @Override
    public void showResponseData(Object data) {
        LoginBean bean = (LoginBean) data;
        Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, HomePageActivity.class));
    }

    @Override
    public void showResponseFail(String e) {
        Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.onDetach();
        bind.unbind();
    }
}
