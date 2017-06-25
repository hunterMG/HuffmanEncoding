package com.android.huffman_yg;

import java.util.List;

import com.example.huffman.R;

import Huffman.Test;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CodeDisplay extends Activity {

	private ListView codelist;
	private ArrayAdapter<String> adapter;// codelist的适配器
	private List<String> data = null;// codelist的数据源

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.codedisplay);
		
		getData();
		
		adapter = new ArrayAdapter<String>(CodeDisplay.this,
				android.R.layout.simple_list_item_1, data);
		codelist = (ListView) findViewById(R.id.listview1);
		codelist.setAdapter(adapter);

	}
	void getData(){
		data = Test.getData();
	}
}
