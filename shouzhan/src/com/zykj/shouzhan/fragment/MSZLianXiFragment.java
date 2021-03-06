package com.zykj.shouzhan.fragment;

import com.loopj.android.http.RequestParams;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.activity.ManageContactsStyleAcyivity;
import com.zykj.shouzhan.view.XListView;
import com.zykj.shouzhan.view.XListView.IXListViewListener;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MSZLianXiFragment extends Fragment implements IXListViewListener {

	private static int PERPAGE = 5;// perpage默认每页显示10条信息
	private int nowpage = 1;// 当前显示的页面
	private int mType = 1;// 1、首页 2、产品 3、公司 4、联系 5、english
	private XListView mListView;
	private Handler mHandler;
	private RequestParams params;
	@Bind(R.id.tv_mobile)
	TextView tv_mobile;// 手机
	@Bind(R.id.tv_telephone)
	TextView tv_telephone;// 固定电话
	@Bind(R.id.tv_contacts)
	TextView tv_contacts;// 联系人
	@Bind(R.id.tv_qq)
	TextView tv_qq;// qq
	@Bind(R.id.tv_weixin)
	TextView tv_weixin;// 微信
	@Bind(R.id.tv_address)
	TextView tv_address;// 地址
	@Bind(R.id.tv_map_navigation)
	TextView tv_map_navigation;// 地图导航
	@Bind(R.id.btn_manage_lianxi)
	TextView btn_manage_lianxi;// 管理联系

	/**
	 * @param type
	 *            // 1、首页 2、产品 3、公司 4、联系 5、english
	 * @return 实例化收入列表
	 */
	public static MSZLianXiFragment getInstance(int type) {
		MSZLianXiFragment fragment = new MSZLianXiFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("type", type);
		fragment.setArguments(bundle);
		return fragment;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	/**
	 * 初始化页面
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view=inflater.inflate(R.layout.ui_ms_contacts, null);
		
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		mListView = new XListView(getActivity(), null);
		mListView.setDividerHeight(0);
		mListView.setLayoutParams(params);
		mListView.setPullRefreshEnable(true);
		mListView.setPullLoadEnable(true);
		mListView.setXListViewListener(this);
		ButterKnife.bind(this,view);
		return view;

	}

	@OnClick(R.id.btn_manage_lianxi) // 管理联系
	public void manageLianXi() {
		
		startActivity(new Intent(getActivity(), ManageContactsStyleAcyivity.class));
	}

	/**
	 * 配置页面参数
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mType = getArguments().getInt("type");
		mHandler = new Handler();

		requestData();

	}

	/**
	 * 请求服务器数据
	 */
	private void requestData() {

	}

	/**
	 * 下拉刷新
	 */
	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				nowpage = 1;
				requestData();
				onLoad();
			}
		}, 1000);
	}

	/**
	 * 上拉加载
	 */
	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				nowpage += 1;
				requestData();
				onLoad();
			}
		}, 1000);
	}

	/**
	 * 结束加载/刷新
	 */
	public void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}

}
