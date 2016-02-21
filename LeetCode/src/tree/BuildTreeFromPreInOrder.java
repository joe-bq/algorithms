package tree;
import java.util.*;
import static java.util.Arrays.*;

class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	 }
	 
	 
	 
public class BuildTreeFromPreInOrder {
	public static void main(String[] args) { 
		int[] pre = {4, 3, 1, 2, 7, 6};
		int[] in ={1, 2, 3, 4, 6, 7 };
		BuildTreeFromPreInOrder solution = new BuildTreeFromPreInOrder();
		TreeNode root = solution.buildTree(pre, in);
		if (root != null) {
			System.out.println("Success");
		} else { 
			System.out.println("unsuccess");
		}
		
	}
	
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (preorder.length == 0) 
			return null;
		int rootNode = preorder[0];
		int rootNodeIdx = -1;
		
		for (int i = 0; i < inorder.length; i++) { 
			if (inorder[i] == rootNode) {
				rootNodeIdx = i;
				break;
			}
		}
		// left tree
		int[] leftinorder = Arrays.copyOfRange(inorder, 0, rootNodeIdx);
		int[] rightinorder = Arrays.copyOfRange(inorder, rootNodeIdx+1, inorder.length);
		
		int[] leftpreorder = Arrays.copyOfRange(preorder, 1, 1 + leftinorder.length);
		int[] rightpreorder = Arrays.copyOfRange(preorder, 1+leftinorder.length, preorder.length);
		
		TreeNode root = new TreeNode(rootNode);
		root.left = buildTree(leftpreorder, leftinorder);
		root.right = buildTree(rightpreorder, rightinorder);
		
		return root;
    }
	
}
