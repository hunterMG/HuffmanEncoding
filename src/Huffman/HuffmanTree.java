package Huffman;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class HuffmanTree {
	public Queue<Node> node; // Huffman树的叶子节点存放
	public Queue<Node> nodeTmp; // Huffman树的叶子节点存放(用于显示树的构造过程)
	private Node root; // Huffman的根节点
	public TreeMap<Node, Character> tree = new TreeMap<Node, Character>(); // Huffman树的副本

	/*
	 * 初始化huffmand的叶子节点队列
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
	 * Huffman建树过程
	 * 1.在Huffman叶子节点不为空的前提下，前两个元素弹出，生成一个sum的节点，(这里一定要指明sum的孩子，以及弹出两个节点的父母)
	 * 并将其加入到Huffman叶子节点的树中 在1的过程中同时拷贝一份副本 2.结束条件:Huffman的叶子节点树的大小为0时候退出
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
