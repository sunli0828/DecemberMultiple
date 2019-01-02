package mlb.bawei.com.synthesize.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mlb.bawei.com.synthesize.R;
import mlb.bawei.com.synthesize.adapter.MainActivityAdapter;
import mlb.bawei.com.synthesize.bean.LoginBean;
import mlb.bawei.com.synthesize.bean.MessageBean;
import mlb.bawei.com.synthesize.fragment.FragmentCircle;
import mlb.bawei.com.synthesize.fragment.FragmentIndent;
import mlb.bawei.com.synthesize.fragment.FragmentMainHome;
import mlb.bawei.com.synthesize.fragment.FragmentMine;
import mlb.bawei.com.synthesize.fragment.FragmentShopsCar;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_pager)
    ViewPager viewPager;
    @BindView(R.id.main_group)
    RadioGroup radioGroup;
    private Unbinder bind;
    //多选框
    @BindView(R.id.main_chome)
    RadioButton cHome;
    @BindView(R.id.main_ccircle)
    RadioButton cCircle;
    @BindView(R.id.main_cindent)
    RadioButton cIndent;
    @BindView(R.id.main_cmine)
    RadioButton cMine;
    @BindView(R.id.main_shopcar)
    RadioButton imageShopsCar;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);
        Log.i("TAG","001");
        bind = ButterKnife.bind(this);
        final List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(new FragmentMainHome());
        fragmentList.add(new FragmentCircle());
        fragmentList.add(new FragmentShopsCar());
        fragmentList.add(new FragmentIndent());
        fragmentList.add(new FragmentMine());

        //滑动
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return fragmentList.get(i);
                    case 1:
                        return fragmentList.get(i);
                    case 2:
                        return fragmentList.get(i);
                    case 3:
                        return fragmentList.get(i);
                    case 4:
                        return fragmentList.get(i);
                }
                return new Fragment();
            }
            @Override
            public int getCount() {
                return fragmentList.size();
            }

        });
        //
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        cHome.setChecked(true);
                        cCircle.setChecked(false);
                        cIndent.setChecked(false);
                        cMine.setChecked(false);
                        break;
                    case 1:
                        cHome.setChecked(false);
                        cCircle.setChecked(true);
                        cIndent.setChecked(false);
                        cMine.setChecked(false);
                        break;
                    case 2:
                        cHome.setChecked(false);
                        cCircle.setChecked(false);
                        cIndent.setChecked(false);
                        cMine.setChecked(false);
                        break;
                    case 3:
                        cHome.setChecked(false);
                        cCircle.setChecked(false);
                        cIndent.setChecked(true);
                        cMine.setChecked(false);
                        break;
                    case 4:
                        cHome.setChecked(false);
                        cCircle.setChecked(false);
                        cIndent.setChecked(false);
                        cMine.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //切换
        switchHover();

    }

    private void switchHover() {
        //首页
        cHome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    viewPager.setCurrentItem(0);
                    cHome.setChecked(isChecked);
                    cCircle.setChecked(false);
                    cIndent.setChecked(false);
                    cMine.setChecked(false);
                }
            }
        });
        //圈子
        cCircle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    viewPager.setCurrentItem(1);
                    cHome.setChecked(false);
                    cCircle.setChecked(true);
                    cIndent.setChecked(false);
                    cMine.setChecked(false);
                }
            }
        });
        //购物车
        imageShopsCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
                cHome.setChecked(false);
                cCircle.setChecked(false);
                cIndent.setChecked(false);
                cMine.setChecked(false);
            }
        });
        //订单
        cIndent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    viewPager.setCurrentItem(3);
                    cHome.setChecked(false);
                    cCircle.setChecked(false);
                    cIndent.setChecked(isChecked);
                    cMine.setChecked(false);
                }
            }
        });
        //我的
        cMine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    viewPager.setCurrentItem(4);
                    cHome.setChecked(false);
                    cCircle.setChecked(false);
                    cIndent.setChecked(false);
                    cMine.setChecked(isChecked);
                    return;
                }
            }
        });
    }

    //
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(MessageBean messageBean){
        LoginBean loginBean = messageBean.getLoginBean();
        String status = loginBean.getStatus();
        Log.i("TAG",status+"");
        Toast.makeText(this,status+"dadad",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
