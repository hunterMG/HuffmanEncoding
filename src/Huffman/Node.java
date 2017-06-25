package Huffman;

import java.util.PriorityQueue;
import java.util.Queue;

public class Node implements Comparable<Node>{
	    public char data;
//	    public  String path;
	    public int  weight;
	    public Node parent;
	    public Node lchild;
	    public Node rchild;
	  

	    public Node(char d, int w,String str) {
	        data = d;
	        weight = w;
	        parent=null;
	        lchild = null;
	        rchild = null;
//	        path=new String(str);
	    }
   void setWeight()
   {
	   this.weight++;
   }
	
   char getChar()
   {
	   return this.data;
   }
   
  
   public int compareTo(Node Nobj) {
	    if(this.weight>Nobj.weight)
	    	return 1;
	    else if(this.weight<Nobj.weight)
	    	return -1;
	    else if(this.weight==Nobj.weight&Nobj.data=='X'&this.data=='X')
		   return 1;
	    else
	    	 return 0;
   }
}
