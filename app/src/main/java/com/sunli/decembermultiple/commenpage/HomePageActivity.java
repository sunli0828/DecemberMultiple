package com.sunli.decembermultiple.commenpage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioGroup;

import com.sunli.decembermultiple.R;
import com.sunli.decembermultiple.commenpage.circle.CircleFragment;
import com.sunli.decembermultiple.commenpage.home.HomeFragment;
import com.sunli.decembermultiple.commenpage.list.ListFragment;
import com.sunli.decembermultiple.commenpage.mine.MyFragment;
import com.sunli.decembermultiple.commenpage.shopcar.ShopCarFragment;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        final List<Fragment> list = new ArrayList<>();
        homeFragment = new HomeFragment();
        list.add(homeFragment);
        list.add(new CircleFragment());
        list.add(new ListFragment());
        list.add(new MyFragment());
        list.add(new ShopCarFragment());

        radioGroup = findViewById(R.id.activity_homepage_radiogroup);
        viewPager = findViewById(R.id.activity_home_page_viewpager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        radioGroup.check(R.id.activity_homepage_radiobtn_home);
                        break;
                    case 1:
                        radioGroup.check(R.id.activity_homepage_radiobtn_circle);
                        break;
                    case 2:
                        radioGroup.check(R.id.activity_homepage_radiobtn_shopcar);
                        break;
                    case 3:
                        radioGroup.check(R.id.activity_homepage_radiobtn_list);
                        break;
                    case 4:
                        radioGroup.check(R.id.activity_homepage_radiobtn_my);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.activity_homepage_radiobtn_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.activity_homepage_radiobtn_circle:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.activity_homepage_radiobtn_shopcar:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.activity_homepage_radiobtn_list:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.activity_homepage_radiobtn_my:
                        viewPager.setCurrentItem(4);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ){
            homeFragment.getBackData(true);
        }
        return false;
    }

}
