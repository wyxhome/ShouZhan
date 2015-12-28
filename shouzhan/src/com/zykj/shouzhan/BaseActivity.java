package com.zykj.shouzhan;

import java.io.File;

import com.zykj.shouzhan.utils.Tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class BaseActivity extends Activity implements android.view.View.OnClickListener{
	private SharedPreferences defalut_sp;
	private SharedPreferences appoint_sp;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		defalut_sp = getSharedPreferences(BaseApp.config, Context.MODE_PRIVATE);

		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		Tools.M_SCREEN_WIDTH = metrics.widthPixels;
		Tools.M_SCREEN_HEIGHT = metrics.heightPixels;

		BaseApp.getInstance().addActivity(this);
		super.onCreate(savedInstanceState);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Tools.Log("BaseActivity�ر�");
		BaseApp.getInstance().finishActivity(this);
	}

	/**
	 * ��������
	 */
	public void onClick(View view) {
	}

	public void initView(int viewId) {
		setContentView(viewId);
	}

	public void setListener(View... view) {
		for (int i = 0; i < view.length; i++) {
			view[i].setOnClickListener(this);
		}
	}

	public String getSharedPreferenceValue(String key) {
		String value = defalut_sp.getString(key, "");
		return value;
	}

	public void putSharedPreferenceValue(String key, String value) {
		SharedPreferences.Editor editor;
		editor = defalut_sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public String getSharedPreferenceValue(String tag, String key) {
		appoint_sp = getSharedPreferences(tag, Context.MODE_PRIVATE);
		String value = appoint_sp.getString(key, "");
		return value;
	}

	public void putSharedPreferenceValue(String tag, String key, String value) {
		appoint_sp = getSharedPreferences(tag, 0);
		SharedPreferences.Editor editor;
		editor = appoint_sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * �ظ���ǰ����Ĳ���
	 */
	protected void onResume() {
		super.onResume();
		BaseApp.getInstance().resumeActivity(this);
	}

}