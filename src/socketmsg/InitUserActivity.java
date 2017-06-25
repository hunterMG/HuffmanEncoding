package socketmsg;

import java.io.DataOutputStream;
import java.io.IOException;

import com.example.huffman.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InitUserActivity extends Activity {
	private EditText username_et;
	private Button nextButton_et;
	private String chatKey="SLEEKNETGEOCK4stsjeS";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inituser);
		username_et = (EditText)findViewById(R.id.username);
		nextButton_et = (Button)findViewById(R.id.nextBtn1);
		username_et.setFocusable(true);
		
		nextButton_et.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Config.username = username_et.getText().toString().trim();
				if(Config.username==""||Config.username==null||Config.username.equals("")){
					Toast.makeText(InitUserActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
					username_et.setFocusable(true);
				}else {
					new Thread(){
						public void run() {
							try {
								DataOutputStream dos = new DataOutputStream(SocketConfig.socket.getOutputStream());
								dos.writeUTF(chatKey + "online:" + Config.username);
								//dos.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						};
					}.start();
					Intent intent = new Intent(InitUserActivity.this, SocketMsgActivity.class);
					startActivity(intent);
					InitUserActivity.this.finish();
				}
			}
		});
	}
	
}
