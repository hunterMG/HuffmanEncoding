package socketmsg;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import com.example.huffman.R;

import Huffman.Test;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/*
 * 获得用户输入的服务器ip和端口号
 * */
public class SocketConfig extends Activity {
//	private ProgressDialog progressDialog;
	private EditText hostText;
	private EditText portText;
	private Button nextButton;
	private InetSocketAddress isa;
	public static Socket socket;
	//通过handler更新UI
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				Toast.makeText(SocketConfig.this, "连接成功", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(SocketConfig.this, InitUserActivity.class);
				startActivity(intent);
				SocketConfig.this.finish();
				break;
			case 1:
				Toast.makeText(SocketConfig.this, "连接失败，IP或端口错误", Toast.LENGTH_SHORT).show();
				break;
			case 2:
				Toast.makeText(SocketConfig.this, "连接超时", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.config);
		hostText = (EditText)findViewById(R.id.host);
		portText = (EditText)findViewById(R.id.port);
		nextButton = (Button)findViewById(R.id.nextBtn);
		nextButton.setOnClickListener(new View.OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Config.host = hostText.getText().toString().trim();
				Config.port=  portText.getText().toString().trim();
				//
				if (Config.host ==""||Config.host==null||Config.host.equals("")) {
					Toast.makeText(SocketConfig.this, "请输入IP", Toast.LENGTH_SHORT).show();
					hostText.setFocusable(true);
				}else if( Config.port ==""||Config.port==null||Config.port.equals("")) {
					Toast.makeText(SocketConfig.this, "请输入端口", Toast.LENGTH_SHORT).show();
					portText.setFocusable(true);
				}
				else {
					//progressDialog = ProgressDialog.show(SocketConfig.this, "", "正在连接，请稍候 ···", true, true);
					new Thread(){
						public void run() {
							socket = new Socket();
							isa = new InetSocketAddress(Config.host, Integer.parseInt(Config.port));
							try {
								socket.connect(isa, 5000);
								Log.i("SocketConfig", "连接成功");
								Message message = new Message();
								message.what = 0;
								handler.sendMessage(message);
							}catch(UnknownHostException e){
								Log.e("SocketConfig", "连接失败，IP或端口错误");
								Message message = new Message();
								message.what = 1;
								handler.sendMessage(message);
							}catch (SocketTimeoutException e) {
								// TODO: handle exception
								Log.e("SocketConfig", "连接超时");
								Message message = new Message();
								message.what = 2;
								handler.sendMessage(message);
							}catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								Log.e("SocketConfig", "IOException");
							}catch (Exception e) {
								// TODO: handle exception
								Log.e("SocketConfig", "Uknown Exception!");
							}
							
						}
						
					}.start();
				}
				
			}
		});
	}
}
