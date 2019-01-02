package mlb.bawei.com.synthesize.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mlb.bawei.com.synthesize.fragment.FragmentMainHome;
import mlb.bawei.com.synthesize.fragment.FragmentMine;

/**
 * @author
 * @date 2018/12/28
 */
public class MainActivityAdapter extends FragmentPagerAdapter {
    String[] strings = new String[]{
      "我的"
    };
    public MainActivityAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:return new FragmentMine();
            case 1:return new FragmentMainHome();
            default:return new Fragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings[position];
    }

    @Override
    public int getCount() {
        return strings.length;
    }
}
