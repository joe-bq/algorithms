package list;

// the class ListNode has been defined somewhere

/*
class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}
*/

public class SortListInsertion {
	public static void main(String[] args) {
		// here we tries to use insertion sort to sort a List.
		
		
		ListNode head = new ListNode(4);
		ListNode p = head;
		p.next = new ListNode(7);
		p = p.next;
		p.next = new ListNode(8);
		p = p.next;
		p.next = new ListNode(2);
		p = p.next;
		p.next = new ListNode(5);
		p = p.next;
		p.next = new ListNode(1);
		p = p.next;
		p.next = new ListNode(10);
		p = p.next;
		p.next = new ListNode(9);
		p = p.next;
		
		
		SortListInsertion solution = new SortListInsertion();
//		head = solution.sortList(head);
		head = solution.sortList2(head);
		
		
		for (p  = head; p != null; p = p.next) {
			System.out.print(p.val + " ");
		}
		
		System.out.println();
		
		
	}
	
	
	public ListNode sortList(ListNode head) {
		// here we will use the insertion sort..
		
		ListNode p = head;
		ListNode q = null;
		
		ListNode h = new ListNode(0);
		h.next = head;
		ListNode last = h; // last is the prev to p, while p is the position that we are inspecting.
		ListNode prev = h; // prev is the prev to q , while q is the position where we should insert p into in the pre-sorted list
		
		// skip first node, as first node by far is a sorted list
		if (p != null) {
			last = last.next;
			p = p.next;
		}
		
		while (p != null) {
			// walk through q from head to p, find position
			prev = h;
			boolean hasInsertion = false;
			for (prev = h, q = prev.next; q != p; prev = prev.next, q = prev.next) { 
				if (p.val < q.val) {
					prev.next = p;
					last.next = p.next;
					p.next = q;
					hasInsertion = true;
					break;
				}
			}
			
			if (!hasInsertion) { 
				last = last.next;
			}
			
			p = last.next;
			
		}
		
		return h.next;
	}
	
	
	public ListNode sortList2(ListNode head) {
		ListNode h = new ListNode(0);
		h.next = head;
		
		ListNode p = h.next;
		ListNode last = h;
		while (p != null) {
			ListNode l = last;
			last = l.next;
			
			for (ListNode prev = h, q = prev.next; q != p; prev = prev.next, q = prev.next) {
				if (p.val < q.val) {
					l.next = p.next;
					p.next = q;
					prev.next = p;
					last = l;
					break;
				}			
			}
			
			p = last.next;

		}
		return h.next;
	}
	
}
