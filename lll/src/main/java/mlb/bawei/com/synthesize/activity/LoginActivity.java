package mlb.bawei.com.synthesize.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.Unbinder;
import mlb.bawei.com.synthesize.R;
import mlb.bawei.com.synthesize.apis.APIs;
import mlb.bawei.com.synthesize.bean.LoginBean;
import mlb.bawei.com.synthesize.bean.MessageBean;
import mlb.bawei.com.synthesize.mvpdome.presneter.Presenterlmpl;
import mlb.bawei.com.synthesize.mvpdome.view.IView;

public class LoginActivity extends AppCompatActivity implements IView {
    //登录按钮
    @BindView(R.id.login_login)
    Button btnLogin;
    //手机号
    @BindView(R.id.login_mobile)
    EditText eMobile;
    //密码
    @BindView(R.id.login_password)
    EditText ePwd;
    //注册
    @BindView(R.id.login_register)
    TextView register;
    //记住密码
    @BindView(R.id.login_remeberpwd)
    CheckBox remeberPwd;
    //显示密码
    @BindView(R.id.login_showpwd)
    ImageView showPwd;
    private Unbinder bind;
    private Presenterlmpl presenterlmpl;
    private boolean flag;
    private String phone;
    private String pwd;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private boolean isc;
    private boolean isv;
    private LoginBean loginBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //绑定ButterKnifo
        bind = ButterKnife.bind(this);
        //
        presenterlmpl = new Presenterlmpl(this);
        //EventBus.getDefault().postSticky(loginBean);
        //
        flag = true;
        //存储
        sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
        edit = sharedPreferences.edit();
        isc = true;
        boolean isc = sharedPreferences.getBoolean("isc", false);
        if(this.isc){
            eMobile.setText(sharedPreferences.getString("phone",null));
            ePwd.setText(sharedPreferences.getString("pwd",null));
            boolean isc1 = sharedPreferences.getBoolean("isc", false);
            remeberPwd.setChecked(isc1);
        }

    }
    //登录
    @OnClick(R.id.login_login)
    public void loginToLogin(){
        Map<String,String> map =new HashMap<>();
        phone = eMobile.getText().toString();
        pwd = ePwd.getText().toString();
        /*String phone="15101004301";
        String pwd="123456";*/
        map.put("phone", phone);
        map.put("pwd", pwd);
        presenterlmpl.goToRequest(APIs.loginUrl,LoginBean.class,map);

    }
    //记住密码
    @OnCheckedChanged(R.id.login_remeberpwd)
    public void remeberPwd(){
        if(isc){
            isv = isc;
            edit.putBoolean("isc",isc);
            edit.commit();
        }else{
            edit.putBoolean("isc",isc);
            edit.clear();
            edit.commit();
            isv=isc;
        }
        isc=!isc;

    }
    //显示或隐藏密码
    @OnClick(R.id.login_showpwd)
    public void setShowPwd(){
        if(flag){
            ePwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else{
            ePwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        flag=!flag;

    }
    //注册
    @OnClick(R.id.login_register)
    public void loginToRegister(){
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }
    //销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        EventBus.getDefault().unregister(this);
    }

    //成功
    @Override
    public void setData(Object data) {
        if(data instanceof LoginBean){
            loginBean = (LoginBean) data;
            String message = loginBean.getMessage();
            if(loginBean.getStatus().equals("0000")){
                boolean isc = sharedPreferences.getBoolean("isc", false);
                if(isc){
                    edit.putString("phone",phone);
                    edit.putString("pwd",pwd);
                    edit.putBoolean("isc",isc);
                    edit.commit();
                }
                EventBus.getDefault().postSticky(new MessageBean(loginBean));
                //EventBus.getDefault().post(new MessageBean(loginBean));
                /*if(loginCallBack!=null){
                    loginCallBack.setData(new MessageBean(loginBean));
                }*/
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        }

    }
    //失败
    @Override
    public void setFail(String msg) {
        //Toast.makeText(this,"登录失败请检查手机号或密码",Toast.LENGTH_SHORT).show();
    }

    public interface loginCallBack{
        void setData(Object data);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getDataSticky(String msg) {
        if (msg.equals("okok")) {
            Toast.makeText(this, "dada", Toast.LENGTH_SHORT).show();
        }
    }
    private  loginCallBack loginCallBack;
    public void setLoginCallBack(loginCallBack loginCallBack){
        this.loginCallBack=loginCallBack;
    }
}
