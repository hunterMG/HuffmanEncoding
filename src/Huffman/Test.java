package Huffman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

import android.R.string;
import android.support.v4.media.MediaBrowserCompat.MediaItem.Flags;
import android.widget.ListView;
public class Test {
	public static String string=null;
	public static String encodingResult;
	public static String decodingResult;
	public static TreeMap<Character, Integer> m;// 统计字符数量
	private static TreeMap<Character, String> codeBook; // Huffman编码本
	public static Queue<Node> nodeTmp; // Huffman树的叶子节点存放(用于显示树的构造过程)
	public static void test(String str) {
		string= str;
		HuffmanCore huf=new HuffmanCore(string);
		huf.huffmanCore();
		nodeTmp = huf.nodeTmp;
		encodingResult = huf.getS().toString();
		decodingResult = huf.getDecodingResult().toString();
		m = new TreeMap<Character, Integer>();
		m = huf.getM();
		codeBook = huf.getTemp();
		//return encodingResult;
	}
	public static ArrayList<String> getData(){
		//给CodeDisplay返回ListView的数据源
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0; i<(int)string.length(); i++){
			int flag = 0;
			for(int j=0; j<i; j++){
				if(string.charAt(i)==string.charAt(j)){
					flag=1;break;
				}
					
			}
			if(flag==1) continue;
			String temp = string.charAt(i) + " : 次数-"+m.get(string.charAt(i))+"-编码-"+codeBook.get(string.charAt(i));
			list.add(temp);
		}
		return list;
	}
}

/*
public class Test {		//供初始算法测试用

	public static void main(String[] args) throws IOException  {
//		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//		System.out.println("\t输入文本：");
//		 String string=br.readLine();
		String string = "abcc";
//		 System.out.println();
		HuffmanCore huf=new HuffmanCore(string);
		huf.huffmanCore();
		String result = huf.getS().toString();
		System.out.println("\n encodingResult:\n\t\t"+result);
	}
}
*/