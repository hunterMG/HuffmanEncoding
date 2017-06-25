package Huffman;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import android.R.string;

public class HuffmanCore {
	private String string; // Ҫ������ַ�
	private TreeMap<Node, Character> tree; // Huffman���ĸ���
	private Node[] node; // Ҷ�ӽڵ�����
	private Node root; // Huffman������
	private TreeMap<Character, String> temp; // Huffman���뱾
	private TreeMap<Character, Integer> m; // ͳ���ַ�����
	private StringBuffer s = new StringBuffer(); // Huffman������
	private StringBuffer decodingResult = new StringBuffer();//������
	public Queue<Node> nodeTmp; // Huffman����Ҷ�ӽڵ���(������ʾ���Ĺ������)
	public StringBuffer getS() {
		return s;
	}

	public TreeMap<Character, Integer> getM() {
		return m;
	}
	
	public HuffmanCore(String str) {
		this.string = str;
		/*
		 * ͳ���ַ����ִ����� 1.�ַ����ڣ���ȡ�ַ�������������1��Ȼ����� �ַ������ڣ������ַ���������������Ϊ1
		 */
		m = new TreeMap<Character, Integer>();

		for (int i = 0; i < str.length(); i++)
			if (m.containsKey(str.charAt(i)))
				m.put(str.charAt(i), new Integer(m.get(str.charAt(i))
						.intValue() + 1));
			else
				m.put(str.charAt(i), new Integer(1));

		/*
		 * ����ͳ����Ϣ����Node���飬�����а�����Node��Ҫ�����ַ���������Ϊ������׼����
		 */
		int t = 0; // Node�����±�����
		node = new Node[m.size()];
		for (Map.Entry<Character, Integer> tem : m.entrySet()) {
			node[t] = new Node(tem.getKey(), tem.getValue().intValue(), " ");
			t++;
			/* mark */
//			System.out.print(tem.getKey() + tem.getValue().toString() + "  "); // ͳ�ƽ���Ĵ�ӡ
		}
//		System.out.println();
	}

	/*
	 * �ȸ���Node�����е�Ҷ�ӽڵ㹹��Huffman�� ���ص�������ֵ��һ����Huffman���ĸ��ڵ㣬���ڽ��뵱��
	 * ����һ����Huffman�������һ�ݸ�������Map�洢�����ڱ���
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
	 * ���룺�Ե����ϵı���; 1.�ҵ�Ҷ�ӽڵ� 2.Ҷ�ӽڵ��ҵ����ڵ㲢������Ϊ���ӻ����Һ��� ����node.parent.lchild==node
	 * �ڴ���������λ��0 ,�Һ���node.parent.rchild==node �ڴ���������λ��1 3.�����ַ�����Ӧ�ı���
	 */
	void encoding() {//���huffman���뱾temp
		Node z = tree.lastKey(); // Huffman���ڵ�(������)
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
		System.out.println("\tԭ��Ϊ��" + "\n\t\t" + string);
		for (int x = 0; x < string.length(); x++) {
			s.append(temp.get(string.charAt(x)));
		}
//		System.out.println(" \t������:" + "\n\t\t" + s.toString()); // ������
//		String str = s.toString();
//		return str;
	}
	/*
	 * ������̣����϶��µݹ� ����������+���� ���� 1.��ʼָ����ڵ㣬����Ϊ0��ת�����ӣ�����ת���Һ���
	 * 2��ӡ��������ǰ�Ľڵ����޺��ӣ�Ҳ����Ҷ�ڵ� 3�ظ�ѭ��1��2����
	 */
	void decoding() {
		/* mark */
//		System.out.println(" \t��������");
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
	public StringBuffer encodingMsg(String msg){//��Ҫ���͵���Ϣ����
		StringBuffer result = new StringBuffer("");
		for(int i=0; i<msg.length(); i++){
			result.append(temp.get(msg.charAt(i)));
		}
		return result;
	}
	public StringBuffer decodingMsg(String msg) {//�����յ�����Ϣ����
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