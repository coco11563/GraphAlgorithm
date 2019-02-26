package graph.ds.Huffman;

import scala.Char;

import java.util.*;

public class TreeNode {
    private char nodeChar;
    private int num;
    private boolean isTrunk;
    private TreeNode left;
    private TreeNode right;
    private String code;
    public TreeNode (char nodeChar, int num) {
        this.nodeChar = nodeChar;
        this.num = num;
        this.isTrunk = false;
    }

    public TreeNode (TreeNode left, TreeNode right) {
        this.isTrunk = true;
        this.left = left;
        this.right = right;
        this.num = left.num + right.num;
    }

    public char getNodeChar() {
        return nodeChar;
    }

    public void setNodeChar(char nodeChar) {
        this.nodeChar = nodeChar;
    }

    public int getNum() {
        return num;
    }
    public boolean isTrunk() {return isTrunk;}
    public static Map<Character, String> makeMap(TreeNode e) {
        Map<Character, String> ret = new HashMap<>();
        LinkedList<TreeNode> qt = new LinkedList<>();
        e.code = "";
        qt.addFirst(e);
        while (!qt.isEmpty()) {
            TreeNode t = qt.pop();
            if (t.isTrunk) {
                t.right.code = t.code + "1";
                qt.addFirst(t.right);
                t.left.code = t.code + "0";
                qt.addFirst(t.left);
            } else {
                ret.put(t.nodeChar, t.code);
            }
        }
        return ret;
    }

    public TreeNode getLeft() {
        return left;
    }
    public TreeNode getRight() {
        return right;
    }
}
