package Huffman;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class HuffmanTree {
	public Queue<Node> node; // Huffman����Ҷ�ӽڵ���
	public Queue<Node> nodeTmp; // Huffman����Ҷ�ӽڵ���(������ʾ���Ĺ������)
	private Node root; // Huffman�ĸ��ڵ�
	public TreeMap<Node, Character> tree = new TreeMap<Node, Character>(); // Huffman���ĸ���

	/*
	 * ��ʼ��huffmand��Ҷ�ӽڵ����
	 */
	HuffmanTree(Node n[], int m) {
		node = new PriorityQueue<Node>();
		for (int i = 0; i < m; i++) {
			node.add(n[i]);
		}
		nodeTmp = new PriorityQueue<Node>();
		for (int i = 0; i < m; i++) {
			nodeTmp.add(n[i]);
		}
	}
	
	/*
	 * Huffman��������
	 * 1.��HuffmanҶ�ӽڵ㲻Ϊ�յ�ǰ���£�ǰ����Ԫ�ص���������һ��sum�Ľڵ㣬(����һ��Ҫָ��sum�ĺ��ӣ��Լ����������ڵ�ĸ�ĸ)
	 * ��������뵽HuffmanҶ�ӽڵ������ ��1�Ĺ�����ͬʱ����һ�ݸ��� 2.��������:Huffman��Ҷ�ӽڵ����Ĵ�СΪ0ʱ���˳�
	 */
	Node bulidtree() {
		while (!node.isEmpty()) {
			Node first = node.poll();
			tree.put(first, first.data);
			if (node.size() == 0) {
				root = first;
				break;
			} else {
				Node second = node.poll();
				tree.put(second, second.data);
				Node sum = new Node('X', first.weight + second.weight, " ");
				sum.lchild = first;
				sum.rchild = second;
				first.parent = sum;
				second.parent = sum;
				node.add(sum);
				tree.put(sum, sum.data);
			}
		}
		return root;
	}
	public Queue<Node> getNodeTmp() {
		return nodeTmp;
	}

}
