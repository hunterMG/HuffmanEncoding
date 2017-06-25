package Huffman;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import android.R.string;

public class HuffmanCore {
	private String string; // 要编码的字符
	private TreeMap<Node, Character> tree; // Huffman树的副本
	private Node[] node; // 叶子节点数组
	private Node root; // Huffman树树根
	private TreeMap<Character, String> temp; // Huffman编码本
	private TreeMap<Character, Integer> m; // 统计字符数量
	private StringBuffer s = new StringBuffer(); // Huffman编码结果
	private StringBuffer decodingResult = new StringBuffer();//解码结果
	public Queue<Node> nodeTmp; // Huffman树的叶子节点存放(用于显示树的构造过程)
	public StringBuffer getS() {
		return s;
	}

	public TreeMap<Character, Integer> getM() {
		return m;
	}
	
	public HuffmanCore(String str) {
		this.string = str;
		/*
		 * 统计字符出现次数： 1.字符存在，获取字符并将数量增加1，然后加入 字符不存在，加入字符，并将数量设置为1
		 */
		m = new TreeMap<Character, Integer>();

		for (int i = 0; i < str.length(); i++)
			if (m.containsKey(str.charAt(i)))
				m.put(str.charAt(i), new Integer(m.get(str.charAt(i))
						.intValue() + 1));
			else
				m.put(str.charAt(i), new Integer(1));

		/*
		 * 根据统计信息建立Node数组，数组中包含的Node主要包含字符和数量，为建树做准备；
		 */
		int t = 0; // Node数组下标索引
		node = new Node[m.size()];
		for (Map.Entry<Character, Integer> tem : m.entrySet()) {
			node[t] = new Node(tem.getKey(), tem.getValue().intValue(), " ");
			t++;
			/* mark */
//			System.out.print(tem.getKey() + tem.getValue().toString() + "  "); // 统计结果的打印
		}
//		System.out.println();
	}

	/*
	 * 先根据Node数组中的叶子节点构建Huffman树 返回的有两个值：一个是Huffman树的根节点，用于解码当中
	 * 另外一个是Huffman树整体的一份副本，用Map存储，用于编码
	 */
	public void huffmanCore() {
		HuffmanTree huffman = new HuffmanTree(node, node.length);
		nodeTmp = huffman.getNodeTmp();
		root = huffman.bulidtree();
		temp = new TreeMap<Character, String>();
		tree = huffman.tree;
		encoding();
		decoding();
	}

	/*
	 * 编码：自底向上的编码; 1.找到叶子节点 2.叶子节点找到父节点并测试其为左孩子还是右孩子 左孩子node.parent.lchild==node
	 * 在待定编码首位加0 ,右孩子node.parent.rchild==node 在待定编码首位加1 3.保存字符和相应的编码
	 */
	void encoding() {//获得huffman编码本temp
		Node z = tree.lastKey(); // Huffman根节点(副本中)
		for (int j = 0; j < m.size(); j++) {
			Node y = node[j];
			StringBuffer br = new StringBuffer("");
			while (y.weight != z.weight) {
				if (y.parent.lchild == y) {
					br.insert(0, "0");
					y = y.parent;
				} else if (y.parent.rchild == y) {
					br.insert(0, "1");
					y = y.parent;
				} else
					continue;
			}
			temp.put(node[j].data, br.toString());
			/* mark */
			System.out.println("\t" + node[j].data + " " + br.toString());
		}
		System.out.println("\t原文为：" + "\n\t\t" + string);
		for (int x = 0; x < string.length(); x++) {
			s.append(temp.get(string.charAt(x)));
		}
//		System.out.println(" \t编码结果:" + "\n\t\t" + s.toString()); // 编码结果
//		String str = s.toString();
//		return str;
	}
	/*
	 * 解码过程：自上而下递归 哈夫曼树根+编码 序列 1.开始指向根节点，编码为0，转向左孩子，否则转向右孩子
	 * 2打印条件，当前的节点无无孩子，也就是叶节点 3重复循环1和2步骤
	 */
	void decoding() {
		/* mark */
//		System.out.println(" \t解码结果：");
//		System.out.print("\t\t");
		for (int k = 0; k < s.length();) {
			Node tep = root;
			while (tep.lchild != null & k < s.length()) {
				if (s.charAt(k) == '0') {
					tep = tep.lchild;
					k++;
				} else {
					tep = tep.rchild;
					k++;
				}
			}
//			System.out.print(tep.data);
			decodingResult.append(tep.data);
		}
	}

	public StringBuffer getDecodingResult() {
		return decodingResult;
	}
 
	public TreeMap<Character, String> getTemp() {
		return temp;
	}
	public StringBuffer encodingMsg(String msg){//将要发送的消息编码
		StringBuffer result = new StringBuffer("");
		for(int i=0; i<msg.length(); i++){
			result.append(temp.get(msg.charAt(i)));
		}
		return result;
	}
	public StringBuffer decodingMsg(String msg) {//将接收到的消息解码
		s = new StringBuffer(msg);
		StringBuffer result = new StringBuffer("");
		for (int k = 0; k < s.length();) {
			Node tep = root;
			while (tep.lchild != null & k < s.length()) {
				if (s.charAt(k) == '0') {
					tep = tep.lchild;
					k++;
				} else {
					tep = tep.rchild;
					k++;
				}
			}
			result.append(tep.data);
		}
		return result;
	}
}