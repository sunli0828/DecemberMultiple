package mlb.bawei.com.synthesize.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mlb.bawei.com.synthesize.R;

public class RegisterActivity extends AppCompatActivity {
    //注册按钮
    @BindView(R.id.register_register)
    Button btnLogin;
    //手机号
    @BindView(R.id.register_mobile)
    EditText eMobile;
    //密码
    @BindView(R.id.register_pwd)
    EditText ePwd;
    //已有账户注册
    @BindView(R.id.register_gotologin)
    TextView register;
    //验证码输入
    @BindView(R.id.register_code)
    EditText eCode;
    //获取验证码
    @BindView(R.id.register_authcode)
    TextView getCode;
    private Unbinder bind;

    //Handler
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
               i--;
               getCode.setText(i+"s后重试");
               handler.sendEmptyMessageDelayed(0,1000);
               if(i<=0){
                   getCode.setText("获取验证码");
               }
            }
        }
    };
    private int i= 60;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiest);
        //绑定
        bind = ButterKnife.bind(this);
        //

    }
    //验证码
    @OnClick(R.id.register_authcode)
    public void getCode(){
        handler.sendEmptyMessageDelayed(0,1000);
    }

    //已有账户事件
    @OnClick(R.id.register_gotologin)
    public void setRegister_Login(){
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
