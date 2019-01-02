package mlb.bawei.com.synthesize.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mlb.bawei.com.synthesize.R;
import mlb.bawei.com.synthesize.adapter.FraMainRecycviewAdapter;

/**
 * @author
 * @date 2018/12/29
 */
public class FragmentMainHome extends Fragment {

    private boolean flag;
    private Unbinder bind;
    private RecyclerView recyclerViewTop;
    private RecyclerView recyclerViewButtom;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_main,null);
        bind = ButterKnife.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewTop = view.findViewById(R.id.f_main_recyctop);
        recyclerViewButtom = view.findViewById(R.id.f_main_recycbtm);
        imageView = view.findViewById(R.id.f_main_menu);
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add("你的我的");
        }
        FraMainRecycviewAdapter adapter = new FraMainRecycviewAdapter(getActivity(),list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewTop.setLayoutManager(layoutManager);
        recyclerViewTop.setAdapter(adapter);
        flag = false;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    recyclerViewTop.setVisibility(View.GONE);
                    recyclerViewButtom.setVisibility(View.GONE);
                }else{
                    recyclerViewTop.setVisibility(View.VISIBLE);
                    recyclerViewButtom.setVisibility(View.VISIBLE);
                }
                flag=!flag;
            }
        });
        //
        RelativeLayout relativeLayout = view.findViewById(R.id.f_main_parent);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewTop.setVisibility(View.GONE);
                recyclerViewButtom.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        bind.unbind();
    }
}
