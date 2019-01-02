package mlb.bawei.com.synthesize.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import mlb.bawei.com.synthesize.R;
import mlb.bawei.com.synthesize.activity.LoginActivity;
import mlb.bawei.com.synthesize.adapter.FragMineRecycleViewAdapter;
import mlb.bawei.com.synthesize.bean.LoginBean;
import mlb.bawei.com.synthesize.bean.MessageBean;
import mlb.bawei.com.synthesize.bean.MineDataBean;

/**
 * @author
 * @date 2018/12/28
 */
public class FragmentMine extends Fragment {

    private LoginActivity loginActivity;
    private SimpleDraweeView simpleDraweeView1;
    private String name;
    private TextView textName;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_mine,null);
        ButterKnife.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //findById
        initView(view);
        //recycyview赋值
        List<MineDataBean> list = new ArrayList<>();
        list.add(new MineDataBean(R.mipmap.my_icon_information_n_xhdpi,"个人资料"));
        list.add(new MineDataBean(R.mipmap.my_icon_circle_n_xhdpi,"我的圈子"));
        list.add(new MineDataBean(R.mipmap.my_icon_foot_n_xhdpi,"我的足迹"));
        list.add(new MineDataBean(R.mipmap.my_icon_wallet_n_xhdpi,"我的钱包"));
        list.add(new MineDataBean(R.mipmap.my_icon_address_n_xhdpi,"我的收货地址"));
        FragMineRecycleViewAdapter adapter = new FragMineRecycleViewAdapter(getActivity(),list);
        //
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initView(View view) {
        simpleDraweeView1 = view.findViewById(R.id.f_mine_mineimage);
        textName = view.findViewById(R.id.f_mine_name);
        recyclerView = view.findViewById(R.id.f_mine_recyc);

    }

    /*private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                textName.setText(name);
            }
        }
    };*/

    //接收数据
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getDataSticky(MessageBean  msg){
        Toast.makeText(getActivity(),"dada",Toast.LENGTH_SHORT).show();
        if(msg!=null){
            if(msg.getLoginBean() instanceof LoginBean){

                LoginBean loginBean = msg.getLoginBean();
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(loginBean.getResult().getHeadPic())
                        .build();
                simpleDraweeView1.setController(controller);

                textName.setText(loginBean.getResult().getNickName());

            }
        }
    }
    //取消注册
    @Override
    public void onResume() {
        super.onResume();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
            Log.i("TAG","EventBus注册");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        //清空粘性所有事件
        EventBus.getDefault().removeAllStickyEvents();
    }

}
