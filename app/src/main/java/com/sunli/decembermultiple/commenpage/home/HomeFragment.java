package com.sunli.decembermultiple.commenpage.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;
import com.sunli.decembermultiple.R;
import com.sunli.decembermultiple.commenpage.home.classify_menu.adapter.FirstCategoryAdapter;
import com.sunli.decembermultiple.commenpage.home.classify_menu.adapter.SecondCategoryAdapter;
import com.sunli.decembermultiple.commenpage.home.classify_menu.adapter.SecondCategoryItemAdapter;
import com.sunli.decembermultiple.commenpage.home.classify_menu.bean.FirstCategoryBean;
import com.sunli.decembermultiple.commenpage.home.classify_menu.bean.SecondCategoryItemBean;
import com.sunli.decembermultiple.commenpage.home.hot_sale.adapter.HotSaleAdapter;
import com.sunli.decembermultiple.commenpage.home.hot_sale.adapter.HotSaleMoreAdapter;
import com.sunli.decembermultiple.commenpage.home.hot_sale.adapter.MagicFashionAdapter;
import com.sunli.decembermultiple.commenpage.home.hot_sale.adapter.QualityLifeAdapter;
import com.sunli.decembermultiple.commenpage.home.hot_sale.bean.HotSaleBean;
import com.sunli.decembermultiple.commenpage.home.hot_sale.bean.HotSaleMoreBean;
import com.sunli.decembermultiple.commenpage.home.nav_search.adapter.NavSearchAdapter;
import com.sunli.decembermultiple.commenpage.home.nav_search.bean.NavSearchBean;
import com.sunli.decembermultiple.commenpage.home.xbanner_icon.XBannerImageBean;
import com.sunli.decembermultiple.shell_frame.mvp.presenter.IPresenterImpl;
import com.sunli.decembermultiple.shell_frame.mvp.view.IView;
import com.sunli.decembermultiple.shell_frame.network.ApiUtils;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment implements IView {

    @BindView(R.id.relative_search_none)
    RelativeLayout relativeSearchNone;

    private int TYPE_SECOND_CATEGORY_ITEM_RECYCLERVIEW = 1;
    private int TYPE_NAV_SEARCH_THINGS_RECYCLERVIEW = 2;
    private int TYPE_HOTSALE_MORE_RECYCLERVIEW = 3;

    private RecyclerView recyclerView, recyclerView_item, recyclerView_hotsale, recyclerView_magicfashion,
                    recyclerView_qualitylife, recyclerview_search, recyclerview_second_category_item;
    private XRecyclerView recyclerView_hotsale_more, recyclerView_magicfashion_more, recyclerView_qualitylife_more;
    private RelativeLayout hotRelative, magicRelative, qualityRelative;

    private ImageView hot_image, magic_image, quality_image;
    private ImageView hot_icon_back, magic_icon_back, quality_icon_back, search_icon_back, search_icon_go, nav_classify;

    private ScrollView scrollView;

    private EditText edit_search;

    private Unbinder bind;
    private IPresenterImpl iPresenter;

    private FirstCategoryAdapter firstCategoryAdapter;
    private List<FirstCategoryBean.ResultBean> list;

    private String name;
    private String id;
    private boolean flag;
    private boolean isFirst;

    private SecondCategoryAdapter secondCategoryAdapter;
    private HotSaleAdapter hotSaleAdapter;
    private List<HotSaleBean.ResultBean.RxxpBean> rxxpBeanList;
    private MagicFashionAdapter magicFashionAdapter;
    private List<HotSaleBean.ResultBean.MlssBean> mlssBeanList;
    private final int qlSpanCount = 2;
    private QualityLifeAdapter qualityLifeAdapter;
    private List<HotSaleBean.ResultBean.PzshBean> pzshBeanList;
    private List<HotSaleMoreBean.ResultBean> hotSaleMoreList;

    private int hotId, magicId, qualityId, mPage, i = 0;
    private HotSaleMoreAdapter hotSaleMoreAdapter;
    private NavSearchAdapter navSearchAdapter;
    private List<NavSearchBean.ResultBean> resultBeanList;
    private List<String> mImgUrl;
    private XBanner xBanner;
    private String secondCategoryId;
    private SecondCategoryItemAdapter secondCategoryItemAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_home, null);

        bind = ButterKnife.bind(this, view);
        iPresenter = new IPresenterImpl(this);

        mPage = 1;

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //加载视图
        initView(view);

        //首页轮播图
        mImgUrl = new ArrayList<>();
        iPresenter.startRequestGet(ApiUtils.GET_URL_HOME_XBANNER_IMAGE, null, XBannerImageBean.class);

        //根据关键词进行查询
        searchByWord();

        //首页菜单按钮分类
        classifyMenuClick();

        //控制显示隐藏
        isMenuHide(view);

        //热销新品
        hotSale();

        //魔力时尚
        magicFashion();

        //品质生活
        qualityLife();
    }

    private void classifyMenuClick() {
        //第一层 分类
        list = new ArrayList<>();
        firstCategoryAdapter = new FirstCategoryAdapter(getContext());
        LinearLayoutManager firstLayoutManager = new LinearLayoutManager(getActivity());
        firstLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(firstLayoutManager);
        //第一层数据条目点击获取ID请求第二层数据
        firstCategoryAdapter.setListener(new FirstCategoryAdapter.ParentCallbackListener() {
            @Override
            public void parentCallbackListener(final String i) {
                iPresenter.startRequestGet(String.format(ApiUtils.GET_URL_HOME_SECOND_CATEGORY,i),null,FirstCategoryBean.class);
                isFirst = false;

            }
        });

        iPresenter.startRequestGet(ApiUtils.GET_URL_HOME_FIRST_CATEGORY, null, FirstCategoryBean.class);
        isFirst = true;

        recyclerView.setAdapter(firstCategoryAdapter);

        //第二层 分类
        secondCategoryAdapter = new SecondCategoryAdapter(getContext());
        LinearLayoutManager secondLayoutManager = new LinearLayoutManager(getActivity());
        secondLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_item.setLayoutManager(secondLayoutManager);
        recyclerView_item.setAdapter(secondCategoryAdapter);
        //第二层数据条目点击查询商品信息
        secondCategoryAdapter.setListener(new SecondCategoryAdapter.CarCallBackListener() {
            @Override
            public void callBack(final int i) {
                iPresenter.startRequestGet(String.format(ApiUtils.GET_URL_HOME_SECOND_CATEGORY_ITEM, secondCategoryId), null, SecondCategoryItemBean.class);
                scrollView.setVisibility(View.GONE);
                recyclerview_second_category_item.setVisibility(View.VISIBLE);
            }
        });
        secondCategoryItemAdapter = new SecondCategoryItemAdapter(getContext());
        GridLayoutManager secondCategoryItemGridLayoutManager = new GridLayoutManager(getActivity(), qlSpanCount);
        secondCategoryItemGridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerview_second_category_item.setLayoutManager(secondCategoryItemGridLayoutManager);
        recyclerview_second_category_item.setAdapter(secondCategoryItemAdapter);
    }

    private void qualityLife() {
        qualityLifeAdapter = new QualityLifeAdapter(getContext());
        pzshBeanList = new ArrayList<>();
        GridLayoutManager qualitylifeLayoutManager = new GridLayoutManager(getActivity(), qlSpanCount);
        qualitylifeLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView_qualitylife.setLayoutManager(qualitylifeLayoutManager);
        recyclerView_qualitylife.setAdapter(qualityLifeAdapter);
        //点击更多
        quality_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    scrollView.setVisibility(View.GONE);
                    qualityRelative.setVisibility(View.VISIBLE);

                    hotSaleMoreAdapter = new HotSaleMoreAdapter(getContext());
                    hotSaleMoreList = new ArrayList<>();

                    GridLayoutManager hotSaleMoreLayoutManager = new GridLayoutManager(getActivity(), qlSpanCount);
                    hotSaleMoreLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                    recyclerView_qualitylife_more.setLayoutManager(hotSaleMoreLayoutManager);

                    recyclerView_qualitylife_more.setPullRefreshEnabled(true);
                    recyclerView_qualitylife_more.setLoadingMoreEnabled(true);
                    recyclerView_qualitylife_more.setLoadingListener(new XRecyclerView.LoadingListener() {
                        @Override
                        public void onRefresh() {
                            i = 0;
                            mPage = 1;
                            iPresenter.startRequestGet(String.format(ApiUtils.GET_URL_HOME_HOT_SALE_MORE, qualityId, mPage), null, HotSaleMoreBean.class);
                        }

                        @Override
                        public void onLoadMore() {
                            i = 1;
                            iPresenter.startRequestGet(String.format(ApiUtils.GET_URL_HOME_HOT_SALE_MORE, qualityId, mPage), null, HotSaleMoreBean.class);
                        }
                    });

                    recyclerView_qualitylife_more.setAdapter(hotSaleMoreAdapter);

                    iPresenter.startRequestGet(String.format(ApiUtils.GET_URL_HOME_HOT_SALE_MORE, qualityId, mPage), null, HotSaleMoreBean.class);

                    quality_icon_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scrollView.setVisibility(View.VISIBLE);
                        qualityRelative.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    private void magicFashion() {
        magicFashionAdapter = new MagicFashionAdapter(getContext());
        mlssBeanList = new ArrayList<>();
        LinearLayoutManager magicfashionLayoutManager = new LinearLayoutManager(getActivity());
        magicfashionLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView_magicfashion.setLayoutManager(magicfashionLayoutManager);
        recyclerView_magicfashion.setAdapter(magicFashionAdapter);
        //点击更多
        magic_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    scrollView.setVisibility(View.GONE);
                    magicRelative.setVisibility(View.VISIBLE);

                    hotSaleMoreAdapter = new HotSaleMoreAdapter(getContext());
                    hotSaleMoreList = new ArrayList<>();

                    GridLayoutManager hotSaleMoreLayoutManager = new GridLayoutManager(getActivity(), qlSpanCount);
                    hotSaleMoreLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                    recyclerView_magicfashion_more.setLayoutManager(hotSaleMoreLayoutManager);

                    recyclerView_magicfashion_more.setPullRefreshEnabled(true);
                    recyclerView_magicfashion_more.setLoadingMoreEnabled(true);
                    recyclerView_magicfashion_more.setLoadingListener(new XRecyclerView.LoadingListener() {
                        @Override
                        public void onRefresh() {
                            i = 0;
                            mPage = 1;
                            iPresenter.startRequestGet(String.format(ApiUtils.GET_URL_HOME_HOT_SALE_MORE, magicId, mPage), null, HotSaleMoreBean.class);
                        }

                        @Override
                        public void onLoadMore() {
                            i = 1;
                            iPresenter.startRequestGet(String.format(ApiUtils.GET_URL_HOME_HOT_SALE_MORE, magicId, mPage), null, HotSaleMoreBean.class);
                        }
                    });

                    recyclerView_magicfashion_more.setAdapter(hotSaleMoreAdapter);

                    iPresenter.startRequestGet(String.format(ApiUtils.GET_URL_HOME_HOT_SALE_MORE, magicId, mPage), null, HotSaleMoreBean.class);

                    magic_icon_back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            scrollView.setVisibility(View.VISIBLE);
                            magicRelative.setVisibility(View.GONE);
                        }
                    });


            }
        });
    }

    private void hotSale() {
        hotSaleAdapter = new HotSaleAdapter(getContext());
        rxxpBeanList = new ArrayList<>();
        LinearLayoutManager hotsaleLayoutManager = new LinearLayoutManager(getActivity());
        hotsaleLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_hotsale.setLayoutManager(hotsaleLayoutManager);
        recyclerView_hotsale.setAdapter(hotSaleAdapter);
        //点击更多
        hot_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    scrollView.setVisibility(View.GONE);
                    hotRelative.setVisibility(View.VISIBLE);

                    hotSaleMoreAdapter = new HotSaleMoreAdapter(getContext());
                    hotSaleMoreList = new ArrayList<>();

                    GridLayoutManager hotSaleMoreLayoutManager = new GridLayoutManager(getActivity(), qlSpanCount);
                    hotSaleMoreLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                    recyclerView_hotsale_more.setLayoutManager(hotSaleMoreLayoutManager);

                    recyclerView_hotsale_more.setPullRefreshEnabled(true);
                    recyclerView_hotsale_more.setLoadingMoreEnabled(true);
                    recyclerView_hotsale_more.setLoadingListener(new XRecyclerView.LoadingListener() {
                        @Override
                        public void onRefresh() {
                            i = 0;
                            mPage = 1;
                            iPresenter.startRequestGet(String.format(ApiUtils.GET_URL_HOME_HOT_SALE_MORE, hotId, mPage), null, HotSaleMoreBean.class);
                        }

                        @Override
                        public void onLoadMore() {
                            i = 1;
                            iPresenter.startRequestGet(String.format(ApiUtils.GET_URL_HOME_HOT_SALE_MORE, hotId, mPage), null, HotSaleMoreBean.class);
                        }
                    });

                    recyclerView_hotsale_more.setAdapter(hotSaleMoreAdapter);

                    iPresenter.startRequestGet(String.format(ApiUtils.GET_URL_HOME_HOT_SALE_MORE, hotId, mPage), null, HotSaleMoreBean.class);

                    hot_icon_back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            scrollView.setVisibility(View.VISIBLE);
                            hotRelative.setVisibility(View.GONE);
                        }
                    });
            }
        });
    }

    private void isMenuHide(@NonNull View view) {
        flag = false;
        view.findViewById(R.id.fragment_home_common_btn_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag) {
                    recyclerView.setVisibility(View.GONE);
                    recyclerView_item.setVisibility(View.GONE);
                    relativeSearchNone.setVisibility(View.GONE);
                }else {
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView_item.setVisibility(View.VISIBLE);
                    relativeSearchNone.setVisibility(View.GONE);
                }
                flag = !flag;
            }
        });
    }

    private void searchByWord() {
        navSearchAdapter = new NavSearchAdapter(getContext());
        resultBeanList = new ArrayList<>();

        GridLayoutManager hnavSearchLayoutManager = new GridLayoutManager(getActivity(), qlSpanCount);
        hnavSearchLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerview_search.setLayoutManager(hnavSearchLayoutManager);
        recyclerview_search.setAdapter(navSearchAdapter);

        search_icon_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String things = edit_search.getText().toString();
                if(things.equals("")) {
                    Toast.makeText(getContext(), "请输入搜索内容", Toast.LENGTH_SHORT).show();
                } else {
                    iPresenter.startRequestGet(String.format(ApiUtils.GET_URL_HOME_SEARCH_THINGS, things), null, NavSearchBean.class);
                    recyclerview_search.setVisibility(View.VISIBLE);
                    scrollView.setVisibility(View.GONE);
                    recyclerview_second_category_item.setVisibility(View.GONE);
                    qualityRelative.setVisibility(View.GONE);
                    hotRelative.setVisibility(View.GONE);
                    magicRelative.setVisibility(View.GONE);
                }
            }
        });

    }

    private void initView(@NonNull View view) {
        recyclerView = view.findViewById(R.id.fragment_home_classify_recyclerview);
        recyclerView_item = view.findViewById(R.id.fragment_home_classify_recyclerview_item);
        recyclerView_hotsale = view.findViewById(R.id.fragment_home_hotsale_recyclerview);
        recyclerView_magicfashion = view.findViewById(R.id.fragment_home_magicfashion_recyclerview);
        recyclerView_qualitylife = view.findViewById(R.id.fragment_home_qualitylife_recyclerview);
        recyclerview_search = view.findViewById(R.id.fragment_home_search_recyclerview);
        recyclerview_second_category_item = view.findViewById(R.id.fragment_home_second_catrgory_item_recyclerview);

        recyclerView_hotsale_more = view.findViewById(R.id.fragment_home_hotsale_more_recyclerview);
        recyclerView_magicfashion_more = view.findViewById(R.id.fragment_home_magicfashion_more_recyclerview);
        recyclerView_qualitylife_more = view.findViewById(R.id.fragment_home_qualitylife_more_recyclerview);

        hotRelative = view.findViewById(R.id.rl6);
        magicRelative = view.findViewById(R.id.rl7);
        qualityRelative = view.findViewById(R.id.rl8);


        hot_image = view.findViewById(R.id.fragment_home_image_hotsale_more);
        magic_image = view.findViewById(R.id.fragment_home_image_magicfashion_more);
        quality_image = view.findViewById(R.id.fragment_home_image_qualitylife_more);


        hot_icon_back = view.findViewById(R.id.fragment_home_hotsale_more_btn_back);
        magic_icon_back = view.findViewById(R.id.fragment_home_magicfashion_more_btn_back);
        quality_icon_back = view.findViewById(R.id.fragment_home_qualitylife_more_btn_back);
        search_icon_go = view.findViewById(R.id.fragment_home_search_icon_go);
        nav_classify = view.findViewById(R.id.fragment_home_common_btn_menu);

        scrollView = view.findViewById(R.id.fragment_home_scrollview);

        edit_search = view.findViewById(R.id.fragment_home_search_edit_keyword);

        xBanner = view.findViewById(R.id.fragment_home_xbanner_image);

    }

    @Override
    public void showResponseData(Object data) {
        if (data instanceof FirstCategoryBean) {
            FirstCategoryBean bean = (FirstCategoryBean) data;
            List<FirstCategoryBean.ResultBean> result = ((FirstCategoryBean) data).getResult();
            if (result != null && isFirst) {
                firstCategoryAdapter.setList(result);
                iPresenter.startRequestGet(String.format(ApiUtils.GET_URL_HOME_SECOND_CATEGORY, "1001002"), null, FirstCategoryBean.class);
                isFirst = false;
            }
            if (result != null && !isFirst) {
                iPresenter.startRequestGet(ApiUtils.GET_URL_HOME_HOT_SALE, null, HotSaleBean.class);
                secondCategoryAdapter.setResultBeanList(result);
                secondCategoryId = result.get(i).getId();
            }
        } else if(data instanceof HotSaleBean){
            HotSaleBean bean = (HotSaleBean) data;

            List<HotSaleBean.ResultBean.RxxpBean> rxxp = bean.getResult().getRxxp();

            if (rxxp != null) {
                if (rxxp.size() <= 0) {
                    return;
                }
                for (int i = 0; i < rxxp.size(); i++){
                    hotId = rxxp.get(i).getId();
                    List<HotSaleBean.ResultBean.RxxpBean.CommodityListBean> commodityList = rxxp.get(i).getCommodityList();
                    hotSaleAdapter.setCommodityListBeanList(commodityList);
                }
            }

            List<HotSaleBean.ResultBean.MlssBean> mlss = bean.getResult().getMlss();
            if (mlss != null) {
                if (mlss.size() <= 0) {
                    return;
                }
                for (int i = 0; i < mlss.size(); i++) {
                    magicId = mlss.get(i).getId();
                    List<HotSaleBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList = mlss.get(i).getCommodityList();
                    magicFashionAdapter.setMlssBeanXXList(commodityList);
                }
            }

            List<HotSaleBean.ResultBean.PzshBean> pzsh = bean.getResult().getPzsh();
            if (pzsh != null) {
                if (pzsh.size() <= 0) {
                    return;
                }
                for (int i = 0; i < pzsh.size(); i++) {
                    qualityId = pzsh.get(i).getId();
                    List<HotSaleBean.ResultBean.PzshBean.CommodityListBeanX> commodityList = pzsh.get(i).getCommodityList();
                    qualityLifeAdapter.setPzshBeanList(commodityList);
                }
            }
        } else if(data instanceof HotSaleMoreBean) {
            HotSaleMoreBean hotSaleMoreBean = (HotSaleMoreBean) data;
            List<HotSaleMoreBean.ResultBean> result = hotSaleMoreBean.getResult();
            if (i == 0){
                hotSaleMoreAdapter.setData(result);
                recyclerView_hotsale_more.refreshComplete();
            } else {
                hotSaleMoreAdapter.addData(result);
                mPage ++;
                recyclerView_hotsale_more.loadMoreComplete();
            }
        } else if (data instanceof NavSearchBean) {
            NavSearchBean navSearchBean = (NavSearchBean) data;
            List<NavSearchBean.ResultBean> result = navSearchBean.getResult();
            if (result != null) {
                navSearchAdapter.setResultBeanList(result);

                recyclerview_search.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.GONE);
                relativeSearchNone.setVisibility(View.GONE);
                if (result.size() == 0){
                    recyclerview_search.setVisibility(View.GONE);
                    scrollView.setVisibility(View.GONE);
                    relativeSearchNone.setVisibility(View.VISIBLE);
                }
            }
        } else if (data instanceof XBannerImageBean) {
            XBannerImageBean xBannerImageBean = (XBannerImageBean) data;
            if (xBannerImageBean == null || !xBannerImageBean.isSuccess()) {
                Toast.makeText(getActivity(), xBannerImageBean.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                for (int i = 0; i < xBannerImageBean.getResult().size(); i++) {
                    mImgUrl.add(xBannerImageBean.getResult().get(i).getImageUrl());
                    //加载图片
                    initImageData();
                }
            }
        } else if (data instanceof SecondCategoryItemBean) {
            SecondCategoryItemBean secondCategoryItemBean = (SecondCategoryItemBean) data;
            List<SecondCategoryItemBean.ResultBean> result = secondCategoryItemBean.getResult();
            if (result != null) {
                secondCategoryItemAdapter.setResultBeanList(result);
            }
        }


    }

    //首页轮播图
    private void initImageData() {
        xBanner.setData(mImgUrl, null);
        xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(mImgUrl.get(position)).into((ImageView) view);
            }
        });
        xBanner.setPageTransformer(com.stx.xhb.xbanner.transformers.Transformer.Default);
        xBanner.setPageTransformer(com.stx.xhb.xbanner.transformers.Transformer.Alpha);
        xBanner.setPageTransformer(com.stx.xhb.xbanner.transformers.Transformer.ZoomFade);
        xBanner.setPageTransformer(com.stx.xhb.xbanner.transformers.Transformer.ZoomCenter);
        xBanner.setPageTransformer(com.stx.xhb.xbanner.transformers.Transformer.ZoomStack);
        xBanner.setPageTransformer(com.stx.xhb.xbanner.transformers.Transformer.Stack);
        xBanner.setPageTransformer(com.stx.xhb.xbanner.transformers.Transformer.Depth);
        xBanner.setPageTransformer(com.stx.xhb.xbanner.transformers.Transformer.Zoom);
        xBanner.setPageChangeDuration(0);

    }

    @Override
    public void showResponseFail(String e) {
        Toast.makeText(getActivity(), "fail"+e, Toast.LENGTH_SHORT).show();
    }

    //监听返回键
    public void getBackData(boolean back){
        Log.i("TAG",back+"");
        if(back){
            recyclerview_search.setVisibility(View.GONE);
            recyclerview_second_category_item.setVisibility(View.GONE);
            recyclerView_hotsale_more.setVisibility(View.GONE);
            recyclerView_qualitylife_more.setVisibility(View.GONE);
            recyclerView_magicfashion_more.setVisibility(View.GONE);
            hotRelative.setVisibility(View.GONE);
            magicRelative.setVisibility(View.GONE);
            qualityRelative.setVisibility(View.GONE);
            relativeSearchNone.setVisibility(View.GONE);

            scrollView.setVisibility(View.VISIBLE);
        } else {
            recyclerview_search.setVisibility(View.VISIBLE);
            recyclerview_second_category_item.setVisibility(View.VISIBLE);
            recyclerView_hotsale_more.setVisibility(View.VISIBLE);
            recyclerView_qualitylife_more.setVisibility(View.VISIBLE);
            recyclerView_magicfashion_more.setVisibility(View.VISIBLE);
            hotRelative.setVisibility(View.VISIBLE);
            magicRelative.setVisibility(View.VISIBLE);
            qualityRelative.setVisibility(View.VISIBLE);
            relativeSearchNone.setVisibility(View.VISIBLE);

            scrollView.setVisibility(View.GONE);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
        iPresenter.onDetach();
    }
}