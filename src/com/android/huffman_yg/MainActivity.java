package com.android.huffman_yg;

import javax.security.auth.PrivateCredentialPermission;
import javax.xml.transform.Source;

import socketmsg.SocketConfig;
import socketmsg.SocketMsgActivity;

import com.example.huffman.R;

import Huffman.Test;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.media.RatingCompat.StarStyle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static String str;
	private Button runBtn;
	private Button checkBtn;
	private EditText source;
	private EditText encoding;
	private EditText decoding;
	private Button chatBtn;
	private Button displayTree;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		runBtn = (Button)findViewById(R.id.button1);
		source = (EditText)findViewById(R.id.editText1);
		encoding = (EditText)findViewById(R.id.editText2);
		decoding = (EditText)findViewById(R.id.editText3);
		checkBtn = (Button)findViewById(R.id.button2);
		chatBtn =  (Button)findViewById(R.id.chatBtn);
		checkBtn.setFocusable(false);
		displayTree = (Button) findViewById(R.id.displayTree);
		runBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				str = source.getText().toString();
				if(str.equals("")){//输入为空
					Toast.makeText(MainActivity.this, "请输入", Toast.LENGTH_SHORT).show();
					source.setFocusable(true);
				}else if(str.length()==1){//一个字母无法建树
					Toast.makeText(MainActivity.this, "Are you kidding me?Just One letter!!!", Toast.LENGTH_SHORT).show();
					source.setFocusable(true);
				}
				else{
					Test.test(str);
					encoding.setText(Test.encodingResult);
					decoding.setText(Test.decodingResult);
					chatBtn.setFocusable(true);
				}
			}
		});
		
		checkBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(Test.string==""||Test.string==null||source.getText().toString().equals("")){
					Toast.makeText(MainActivity.this, "请输入并点击“Run”", Toast.LENGTH_SHORT).show();
					source.setFocusable(true);
				}else {
					Intent intent = new Intent(MainActivity.this, CodeDisplay.class);
					startActivity(intent);
				}
			}
		});
		chatBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					Intent chatIntent = new Intent(MainActivity.this, SocketConfig.class);
					startActivity(chatIntent);
					MainActivity.this.finish();
			}
		});
		displayTree.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, Display.class);
				startActivity(intent);
			}
		});
	}
}
