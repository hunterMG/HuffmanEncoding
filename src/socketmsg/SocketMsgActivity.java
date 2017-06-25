package socketmsg;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.example.huffman.R;

import Huffman.HuffmanCore;
import Huffman.Test;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import Huffman.*;
public class SocketMsgActivity extends Activity {
	private EditText chatBox;
	private EditText sendText;
	private Button sendBtn;
	private DataInputStream dis = null;
	private DataOutputStream dos=null;
	private Socket socket=null;
	private String reMsg=null;
	private String sendMsg=null;
	private String chatKey="SLEEKNETGEOCK4stsjeS";
	HuffmanCore huf;
	private Handler handler= new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				chatBox.setText(chatBox.getText().toString()+reMsg+'\n');
				chatBox.setSelection(chatBox.length());
				break;
			default:
				break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_socketmsg);
		chatBox = (EditText)findViewById(R.id.chatbox);
		chatBox.setGravity(2);
		sendText = (EditText)findViewById(R.id.chattext);
		sendBtn =  (Button)findViewById(R.id.okbtn);
		socket = SocketConfig.socket;
		try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("SocketMsgActivity", "dis/dos");
		}
//		new Thread(){
//			public void run() {
				huf = new HuffmanCore(" qwertyuiopasdfghjklzxcvbnm,!！\\./?，。、？[]()-+");
				huf.huffmanCore();
				Log.i("MM", "编码表已生成");
//			}
//		}.start();
		new Thread(){
			public void run() {
				ReceiveMsg();
			}
		}.start();
		sendBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sendMsg = sendText.getText().toString().trim();
				if(sendMsg==null||sendMsg==""||sendMsg.equals("")){
					Toast.makeText(SocketMsgActivity.this, "发送文本不能为空", Toast.LENGTH_SHORT).show();
				}
				else {
					sendMsg = huf.encodingMsg(sendMsg).toString();
					try {
						dos.writeUTF(chatKey+"name:"+Config.username+"end;"+sendMsg);
						sendText.setText("");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		disConnect();
	}
	private void ReceiveMsg() {
		// TODO Auto-generated method stub
		while (socket.isConnected() && (!socket.isClosed())) {
			try {
				while((reMsg=dis.readUTF())!=null){
					int n = reMsg.indexOf("\n");
					if(n!=-1){
						String chatTextString = reMsg.substring(n+1);
						chatTextString = huf.decodingMsg(chatTextString).toString();
						reMsg = reMsg.substring(0, n+1) + chatTextString;
					}
					Message message = new Message();
					message.what = 0;
					handler.sendMessage(message);
					Log.i("ReceiveMsg", "ReceiveMsg");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("ReceiveMsg", "IOException");
			}
			
		}
	};
	private void disConnect() {
		if(dos!=null){
			 try {
				dos.writeUTF(chatKey+"offline:"+Config.username);
				Log.i("SocketMsgActivity", "offline:");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("SocketMsgActivity", "disConnect error");
			}
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("SocketMsgActivity", "disConnect error2");
			}
		}
		
	}
	
}
