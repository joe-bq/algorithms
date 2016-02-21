package tree.binarysearchtree;

/** the problem is based on two list given by the traversal methods, one is pre-order and the other is in-order, given the two tree, 
 * find out the orginal tree is by printing out the post-order visit result
 *
 */
public class InorderPreorderTree {

	/* there is a link to an existing soluiton */
	public static void main(String[] args) {
		String inorder = "DBEAFC";
		String preorder = "ABDECF";
		
		InorderPreorderTree tree = new InorderPreorderTree();
		TreeNode root = tree.solution(preorder, inorder);
		
		StringBuilder sb = new StringBuilder();
		tree.postorder(root, sb);
		
		System.out.println("post order " + sb.toString());
		
	}
	
	class TreeNode
	{
		TreeNode left;
		TreeNode right;
		char key;
	}
	
	
	public TreeNode solution(String preorder, String inorder) {
		
		if (!"".equals(preorder)) {
			char root = preorder.charAt(0);
			TreeNode p = new TreeNode();
			p.key = root;
			int index = inorder.indexOf(root); // you can change to use the .split method.
			String left = inorder.substring(0, index);
			String right = inorder.substring(index + 1);
			p.left = solution(preorder.substring(1, left.length()+1), left);
			p.right = solution(preorder.substring(1+left.length()), right);
			
			return p;
		}
		
		return null;
	}
	
	public void postorder(TreeNode tree, StringBuilder sb) {
		if (tree != null) {
			postorder(tree.left, sb);
			postorder(tree.right, sb);
			sb.append(tree.key);
		}
	}
}
