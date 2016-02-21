package tree.binarysearchtree;


import static java.lang.System.out;
/** this class will demonstrate some of the use of the BinarySearchTree for */

/* for our discussion we are discussing tree with three links, parent, left and right
 * 
 *  any node in the left subtree has key that is less or equal to the parent node
 *  any node in the right subtree has key that is great or equal to the parent node 
 *  */
public class BinarySearchTreeExample {
	public static void main(String[] args) { 
		BinarySearchTreeExample example = new BinarySearchTreeExample();
		example.insert(6);
		example.insert(4);
		example.insert(3);
		example.insert(5);
		example.insert(8);
		example.insert(7);
		example.insert(9);
		
		Tree minimum = example.minimum();
		Tree maximum = example.maximum();
		
		out.println("maximum " + maximum.key);
		out.println("minmum " + minimum.key);
		
		Tree found4 = example.search(example.root, 4);
		if (found4 != null) {
			out.println("found 4 :" + found4.key);
		}
		
		Tree predecessor4 = example.predecessor(found4);
		if (predecessor4 != null) {
			out.println("found 4 predecessor " + predecessor4.key);
		}
		
		Tree successor4 = example.successor(found4);
		if (successor4 != null) { 
			out.println("found 4 successor " + successor4.key);
		}
	}
	
	class Tree { 
		Tree left;
		Tree right;
		Tree parent;
		int key;
	}
	
	Tree root;
	
	public void insert( int key) {
		Tree p = root;
		Tree x = root;
		while (p != null) {
			x = p;
			if (key < p.key) { 
				p = p.left;
			} else { 
				p = p.right;
			}
		}
		
		if (x != null) { 
			if (key < x.key) {
				x.left = new Tree();
				x.left.key = key;
			} else {
				x.right = new Tree();
				x.right.key = key;
			}
		} else { 
			root = x = new Tree();
			root.key = key;
		}
	}
	
	/** a much better implementation that does search via iteration */
	public Tree search(Tree node, int key) {
		while (node != null && node.key != key) { 
			if (node.key < key) {
				node = node.right;
			} else node = node.left;
		}
		
		return node;
	}
	
	/* we will do a recursive iterative */
	public Tree search1(Tree node, int key) {
		Tree p = node;
		while (p != null) { 
			if (p.key == key) return p;
			else if (p.key < key) p = p.right;
			else p = p.left;
		}
		
		return p;
	}
	
	public Tree search_recursive1(Tree node, int key) {
		if (node != null) { 
			if (node.key == key) return node;
			else if (node.key < key) return search_recursive1(node.right, key);
			else return search_recursive1(node.left, key);
		}
		
		return node;
	}
	
	/** a much better implementation that does search via recursion */
	public Tree search_recursive(Tree node, int key) {
		if (node == null || node.key == key) return node;
		
		if (node.key < key) { 
			return search_recursive(node.right, key);
		} else { 
			return search_recursive(node.left, key);
		}
		
	}
	

	/* search iteratively */
	public Tree search_iterative(int key) { 
		return null;
	}
	
	public Tree predecessor(Tree node) { 
		Tree y = node;
		if (node.left != null) {
			return maximum(node.left);
		}
		else { 
			Tree p = node;
			y = p.parent;
			while (y != null && p != y.right){ 
				p = y;
				y = y.parent;
			}
		}
		
		return y;
	}
	
	
	public Tree successor(Tree node) {
		Tree y = node;
		
		if (node.right != null) { 
			return minimum(node.right);
		} else { 
			Tree p = node;
			y = p.parent;
			while (y != null && p != y.left) { 
				p = y;
				y = y.parent;
			}
		}
		
		return y;
	}
	
	public Tree minimum() { 
		Tree p = root;
		while (p != null && p.left != null) {
			p = p.left;
		}
		
		return p;
	}
	
	/** since maximum used in successor, better maximum to take an parameter */
	public Tree minimum(Tree node) {
		Tree p = node;
		while (p != null && p.left != null) {
			p = p.left;
		}
		
		return p;
	}
	
	/** since maximum used in predecessor, better maximum to take an parameter */
	public Tree maximum(Tree node) {
		Tree p = node;
		while (p != null && p.right != null) { 
			p = p.right;
		}
		
		return p;
	}
	
	
	public Tree maximum() { 
		Tree p = root;
		while (p != null && p.right != null) { 
			p = p.right;
		}
		
		return p;
	}
	
	/** transplant is a function that used by delete to maintain the binary tree properties
	 * 
	 *  
	 *  @param u the node to be transplanted. cannot be null
	 *  @param v new node. can be null;
	 *   
	 *   */
	public void transplant(Tree u, Tree v) {
		if (u.parent == null) {
			root = v;
		} else if (u.parent.left == u) { // left child 
			u.parent.left = v;
		} else if (u.parent.right == u) { // right child
			u.parent.right = v;
		}
		
		if (v != null) { 
			v.parent = u.parent;
		}
	}
	
	/** delete a node from the tree */
	public void delete(Tree x) { 
		if (x.left == null) { // promote right 
			transplant(x, x.right);
		} else if (x.right == null)  { // promote
			transplant(x, x.left);
		} else { 
			Tree y = minimum(x.right);
			if (y.parent != x.right) {
				// transform
				transplant(y, y.right); // y is sure to have a right child 
				y.right = x.right; // reparenting 
				y.right.parent = y;
			}
			
			// case 4) becomes case 3)  
			// now we move y on to x
			transplant(x, y); // why x.left? --:)
			y.left = x.left;
			y.left.parent = y; 
		}
		
		
	}
}
