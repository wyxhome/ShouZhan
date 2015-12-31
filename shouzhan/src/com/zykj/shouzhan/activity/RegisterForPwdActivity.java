package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.utils.TextUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

public class RegisterForPwdActivity extends BaseActivity {
	
	private boolean isUsed = false;

	@Bind(R.id.rl_register_pwd_click) 	RelativeLayout    rl_register_pwd_click;		//密码设置-确定按钮
	@Bind(R.id.et_register_pwd_first)	EditText	      et_register_pwd_first;		//第一次输入密码
	@Bind(R.id.et_register_pwd_two)		EditText		  et_register_pwd_two;			//第二次输入密码
	@Bind(R.id.aci_title_textview) 		TextView 	   	  aci_title_textview;			//顶部标题栏显示
	@Bind(R.id.ll_edit_pwd_t_clear)		LinearLayout	  ll_edit_pwd_t_clear;			//第二个输入框叉号
	@Bind(R.id.ll_edit_pwd_o_clear)		LinearLayout	  ll_edit_pwd_o_clear;			//第一个输入框叉号
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_register_pwd);
		ButterKnife.bind(this);// 绑定第三方
		aci_title_textview.setText(R.string.register_pwd);
	}
	
	/**
	 * 密码设置-确定按钮点击事件
	 */
	@OnClick(R.id.rl_register_pwd_click)
	public void pwdOnClick(){
		if(!isUsed)
			return;
		if(et_register_pwd_first.getText().toString().equals(et_register_pwd_two.getText().toString())){
			Toast.makeText(RegisterForPwdActivity.this, "密码设置成功", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(RegisterForPwdActivity.this,RegisterForPayActivity.class));
			overridePendingTransition(R.anim.default_fromright_in, R.anim.default_toleft_out);
		}
		else
			Toast.makeText(RegisterForPwdActivity.this, "两次密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();
	}

	/**
	 * 当第一个输入框失去焦点时，判断密码是否合法
	 * @param b false为失去焦点，true为获得焦点
	 */
	@OnFocusChange(R.id.et_register_pwd_first)
	public void registerPwdFFocusChange(boolean b){
		if(!b)
			Toast.makeText(RegisterForPwdActivity.this, TextUtil.isPasswordLengthLegal(et_register_pwd_first.getText().toString())?"密码合法":"密码不合法，请重新输入", Toast.LENGTH_SHORT).show();
		
	}
	
	/**
	 * 当第二个输入框输入密码时，启用确定按钮
	 */
	@OnTextChanged(R.id.et_register_pwd_two)
	public void registerPwdTChanged(){
		if(et_register_pwd_two.getText().toString().equals("")){
			isUsed = false;
			rl_register_pwd_click.setBackgroundResource(R.drawable.button_register_circle_gray);
			return;
		}
			
		isUsed = true;
		rl_register_pwd_click.setBackgroundResource(R.drawable.button_register_circle_red);
	}
	

	/**
	 *手机文本清除按钮点击事件：第一个输入框
	 */
	@OnClick(R.id.ll_edit_pwd_o_clear)
	public void editPwdOClear() {
		et_register_pwd_first.setText("");
	}
	
	/**
	 *手机文本清除按钮点击事件：第二个输入框
	 */
	@OnClick(R.id.ll_edit_pwd_t_clear)
	public void editPwdTClear() {
		et_register_pwd_two.setText("");
	}

}
