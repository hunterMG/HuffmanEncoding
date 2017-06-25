package com.android.huffman_yg;


import java.util.PriorityQueue;
import java.util.Queue;

import com.example.huffman.R;

import Huffman.Node;
import Huffman.Test;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Display extends Activity {
	private String str; 
	private Button beginBtn; 
	private MyView view; 
	private MyThread thread;
	private Node root; 
	public Queue<Node> nodeTmp; // Huffman树的叶子节点存放(用于显示树的构造过程)
	public Queue<Node> nodeTmp2; //保存nodeTmp，用于第二次显示，因为每次显示完后nodeTmp会清空
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.displaytree_activity);
		nodeTmp2 = Test.nodeTmp;
		view = (MyView) findViewById(R.id.tree);
		str = MainActivity.str;
		beginBtn = (Button) findViewById(R.id.begin);
		beginBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Test.test(str);
				view.clear();
				nodeTmp = Test.nodeTmp;
//				nodeTmp = copyNodeQueue(nodeTmp2);
				MyThread thread1 = new MyThread();
				thread1.start();
				Log.i("Display", "1");
			}
		});
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		view.surfaceDestroyed(view.getHolder());
		super.onDestroy();
	}
	class MyThread extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.i("Display", "3");
			while (!nodeTmp.isEmpty()) {
				Log.i("Display", "4");
				Node first = nodeTmp.poll();
//				tree.put(first, first.data);
				if (nodeTmp.size() == 0) {
					root = first;
					break;
				} else {
					Node second = nodeTmp.poll();
					String str1,str2;
					if(first.data =='#'){
						str1 = Integer.valueOf(first.weight).toString();
					}
					else {
						char []data1 = new char[1];
						data1[0] = first.data;
						str1 = new String(data1);
					}
					if(second.data =='#'){
						str2 = Integer.valueOf(second.weight).toString();
					}
					else{
						char []data2 = new char[1];
						data2[0] = second.data;
						str2 = new String(data2);
					}
					String str3 = Integer.valueOf(first.weight+second.weight).toString();
					view.clear();
					try {
						sleep(600);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					view.draw(str1, str2, str3);
					Log.i("Display", "2");
					try {
						sleep(600);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					tree.put(second, second.data);
					Node sum = new Node('#', first.weight + second.weight, " ");
					sum.lchild = first;
					sum.rchild = second;
					first.parent = sum;
					second.parent = sum;
					nodeTmp.add(sum);
//					tree.put(sum, sum.data);
				}
			}
			
		}
	}

	public Queue<Node> copyNodeQueue(Queue<Node> nodes) {
		Queue<Node> nodes2 = new PriorityQueue<Node>();
		while (!nodes.isEmpty()) {
			Log.i("Display", "5");
			nodes2.add(nodes.poll());
		}
		return nodes2;
	}
}
