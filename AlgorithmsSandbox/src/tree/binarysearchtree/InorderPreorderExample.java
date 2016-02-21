package tree.binarysearchtree;


public class InorderPreorderExample {
	public static void main(String[] args) { 
//		String inOrder = "ABDCEGF"; // seems there are something wrong with this test data.
//		String preOrder = "DBAEGCF";
		
		String inOrder = "DBEAFC";
        String preOrder = "ABDECF";
		
		InorderPreorderExample example = new InorderPreorderExample();
		TreeNode root = example.solution(preOrder, inOrder);
		
		StringBuilder sb = new StringBuilder();
		example.postorder_visit(root, sb);
		System.out.println(sb.toString());
		//example.solution()
	}

	class TreeNode {
		TreeNode left;
		TreeNode right;
		char key;
	}
	
	public void postorder_visit(TreeNode root, StringBuilder sb) {
		if (root != null) {
			postorder_visit(root.left, sb);
			postorder_visit(root.right, sb);
			sb.append(root.key);
		}
	}
	
	public TreeNode solution(String preOrder, String inOrder) {
		
		if (!preOrder.isEmpty()) {
			String[] subTrees = inOrder.split(Character.toString(preOrder.charAt(0)));
			String left = subTrees.length > 0 ? subTrees[0] : ""; 
			String right = subTrees.length > 1 ? subTrees[1] : "";
			
			TreeNode root = new TreeNode();
			root.key = preOrder.charAt(0);
			root.left = solution(preOrder.substring(1, left.length()+1), left);
			root.right = solution(preOrder.substring(left.length()+1), right);
			
			return root;
		}
		
		return null;
		
	}
}
