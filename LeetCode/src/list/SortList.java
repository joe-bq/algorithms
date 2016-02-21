package list;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}
public class SortList {

	public static void main(String[] args) throws Exception {
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
		
		SortList solution = new SortList();
		/*
		head = solution.sortList(head);
		
		for (p = head; p != null; p = p.next)	{
			System.out.print(p.val + " ");
		}
		System.out.println();
		
		head = null;
		head = solution.sortList(head);
		for (p = head; p != null; p = p.next)	{
			System.out.print(p.val + " ");
		}
		System.out.println();
		head = new ListNode(1);
		head = solution.sortList(head);
		for (p = head; p != null; p = p.next)	{
			System.out.print(p.val + " ");
		}
		*/
		
		/*
		head = new ListNode(3);
		p = head;
		p.next = new ListNode(2);
		p = p.next;
		p.next = new ListNode(4);
		p = p.next;
		
		
		head = solution.sortList(head);
		for (p = head; p != null; p = p.next)	{
			System.out.print(p.val + " ");
		}
		*/
		
		/*
		head = new ListNode(3);
		p = head;
		p.next = new ListNode(2);
		p = p.next;
		p.next = new ListNode(4);
		p = p.next;
		*/
		ListNode tail = null;
		p = head;
		while (p != null) {
			tail = p;
			p = p.next;
		}
		
		solution.sortList_quicksort(head, tail);
		
		for (p = head; p != null; p = p.next)	{
			System.out.print(p.val + " ");
		}
	}
	
	
	class HeadTailPair { 
		HeadTailPair(ListNode head, ListNode tail) {
			this.head = head;
			this.tail = tail;
		}
		
		ListNode head;
		ListNode tail;
	}
	
	/* Merge sort two List, p and q; the h head to the connect to the new merged list, the n is the node that afte the merged list */
	public HeadTailPair merge(ListNode p, ListNode q, ListNode h, ListNode n) {
		if (p == null) {
			return new HeadTailPair(q, n);
		}
		
		if (q == null) { 
			return new HeadTailPair(p, n);
		}
		
		if (p.val < q.val) {  
			h.next = p;
		} else  {
			h.next = q;
		}
		
		ListNode prev = h;
		
		while (p != null && q != null) {
			while (p != null && p.val < q.val) { 
				prev = p;
				p = p.next;
			}
			
			if (q != null) {
				prev.next = q;
				ListNode r = q.next;
				if (p != null) { // think of why we need to check p == null? think of the case where p: [1, 5] but q: [9, 10]
					q.next = p;
				}				
				q = r;
				
				prev = prev.next;
			}
		}
		
		while (prev.next != null) { 
			prev = prev.next;
		}
		
		prev.next = n;
		return this.new HeadTailPair(h.next, prev);
		
	}
	
	public ListNode sortList(ListNode head) { 
		int length = 0;
		ListNode p = head;
		while (p != null) {
			p = p.next;
			length++;
		}
		
		for (int segsize = 1; segsize < length; segsize <<= 1) {
			ListNode h = new ListNode(0);
			h.next = head;
			head = null;
			p = h.next;
			
			while (p != null && h != null)   {
				p = h.next;
				// find q
				ListNode q = p;
				
				ListNode prev = p;
				for (int i = 0; i < segsize && q != null; i++) {
					prev = q;
					q = q.next;
				}
				
				if (prev != null) {
					prev.next = null; // break p list... 
				}
				
				ListNode n = q;
				prev = n;
				
				for (int i = 0; i < segsize && n != null; i++) { 
					prev = n;
					n = n.next;
				}
				if (prev != null) {
					prev.next = null;	// break q list
				}
				
				HeadTailPair ht = merge(p, q, h, n);
				h = ht.tail;
				if (head == null) { 
					head = ht.head;
				}
			}

		}
		
		return head;
	}
	
	
	/* this is get inspired by the post here  https://leetcode.com/discuss/66553/java-5ms-ac-solution-using-quick-sort 
	 
	  Also, it points to some static nested class in java as in StackOverflow http://stackoverflow.com/questions/253492/static-nested-class-in-java-why
	 
	 * */
	public void sortList_quicksort(ListNode head, ListNode tail) throws Exception { 
		// ListNode r = partition_quicksort(head, tail);
		SortedList r = partition_quicksort(head, tail);
		if (r != null) { 
			sortList_quicksort(head, r.head);
			sortList_quicksort(r.tail, tail);
		}
	}
	
	/*
	public SortedList partition_quicksort(ListNode head, ListNode tail) { 
		if (head == null || tail == null) {
			return null;
		}
		
		if (head == tail) { 
			return null;
		}
		
		ListNode p = new ListNode(0), pend = new ListNode(0);
		ListNode q = new ListNode(0), qend = new ListNode(0);
		ListNode r = new ListNode(0);
		
		p.next = pend.next = head;
		q.next = qend.next = head;
		r.next = head.next;
		
		int temp = head.val;
		
		for (; r.next != tail; r.next = r.next.next) {
			if (r.next.val < temp) { // swap
				pend.next = pend.next.next;
				q.next = q.next.next;
				
				// swap values pend and r
				int t = q.next.val;
				q.next.val = r.next.val;
				r.next.val = t;
			}  
			
			qend.next = r.next;
		}
		
		// swap r to pivot 
		if (pend.next.next != null) {
			int t = pend.next.next.val;
			head.val = t;
			pend.next.next.val = temp;
		}
		
		
//		return pend.next;
		
		return new SortedList(pend.next, q);
		
	}
	*/
	
	public SortedList partition_quicksort(ListNode head, ListNode tail) throws Exception { 
		if (head == null || tail == null) {
			return null;
		}
		
		if (head == tail) { 
			return null;
		}
		
		ListNode p = head, pend = new ListNode(0);
		ListNode q = head, qend = head;
		ListNode r = head.next;
		pend.next = head;
		
		
		//ListNode wp = pend.next;
		

			int temp = head.val;
			for (;r != tail.next; r = r.next) { 
				if (r.val < temp) { // swap r and pend
					pend = pend.next;
					q = q.next;
					
					/*wp = pend.next;
					
						int t = r.val;
						r.val = wp.val;
						wp.val = t;
					*/
					if (pend == head) { 
						// a bit more complicated swap here
						ListNode a = pend.next;
						int t = r.val;
						r.val = a.val;
						a.val = t;
					} else {
						int t = r.val;
						r.val = pend.val;
						pend.val = t;
					}
					
				}
						
				qend = r;
			}
				
			q = q.next; // find the pivot places.
			
			if (pend.next != head) {
				// swap and return
				int t= pend.val;
				pend.val = temp;
				head.val = t;
				
			}
			
//			if (q == null) { 
//				throw new Exception("exception");
//			}
			return new SortedList(pend.next == head ? head : pend, q);

	}
	
	static class SortedList { 
		ListNode head;
		ListNode tail;
		public SortedList(ListNode h, ListNode t) {
			head = h;
			tail = t;
		}
	}
	
	
	/** the following parts are code snippet that is produced as a middle result */
	
	
	/* Merge only return head */
	public ListNode merge2(ListNode p, ListNode q, ListNode h, ListNode n) {
		if (p == null) {
			return q;
		}
		
		if (q == null) { 
			return p;
		}
		
		if (p.val < q.val) {  
			n.next = p;
		} else  {
			n.next = q;
		}
		
		ListNode prev = n;
		
		while (p != null && q != null) {
			while (p.val < q.val && p != null) { 
				prev = p;
				p = p.next;
			}
			
			if (q != null) {
				prev.next = q;
				ListNode r = q.next;
				if (p != null) {
					q.next = p;
				}
				q.next = p;
				q = r;
				
				prev = prev.next;
			}
		}
		
		prev.next = n;
		return n.next;
	}
	
	
	/* check the right implementation on how to sort two list wiht quick merge sort - https://leetcode.com/discuss/60133/simple-java-merge-sort-commented */
	public ListNode merge3(ListNode p, ListNode q) { 
		ListNode h = new ListNode(0);
		ListNode n = h;
		
		while (p != null && q != null) { 
			if (p.val < q.val) { 
				n.next = p;
				n = n.next;
				p = p.next;
			} else {
				n.next = q;
				n = n.next;
				q = q.next;
			}
		}
		
		if (p != null || q != null) {
			n.next = (p != null) ? p : q;
		}
		
		return h.next;
	}
	
	/* well, I was hoping that the shell sort can help, but it turns out not to be the solution */
	/*
	public ListNode sortList(ListNode head) { 
		
		ListNode p = head;
		int length = 0;
		ListNode q = head;
		while (p != null && q != null) {
			length++;
			p = p.next;
			q = q.next;
			if (q != null) {
				q = q.next;
				length++;
			}
		}
		
		int segsize = length >> 1;
		
		int amoved = 0;
		while (segsize > 0) { 
			
			ListNode a = head;
			ListNode b = a;
			int i = 0;
			
			// find b 
			while (b != null && i < segsize) {
				i++;
				b = b.next;
			}
			
						
			// we walk from head to tail, so the comparison is to push data (bigger to end)
			while (b != null) {
				int temp = a.val;
				
				// until b, we sort each column
				p = a;
				q = p;// previous pos
				while (p != b) { 
					i = 0;
					while (q != null && i < segsize) {
						q = q.next; // next pos
						i++;
					}
					
					if (q != null)
					{
						if (q.val < temp) {
							p.val = q.val; // like an insertion
						} else { 
							break;
						}
					}
					
					if (q != null) 
						p = q;
				}
				
				if (p != null)
				{
					p.val = temp; // push temp to far end
				}
				
				// advance the b and p both once...
				b = b.next;
				a = a.next;
				amoved++;
				if (amoved >= segsize) 
				{
					a = head;
					amoved = 0;
				}
			}
			
			
			
			for (p = head; p != null; p = p.next) {
				System.out.print(p.val  + " ");
			}
			System.out.println();
			
			segsize >>= 1;
		}
		
		return head;
	}
	*/
}


/*
  There is no need to do the log2 thing

 		// int segsize = (int)Math.ceil(Math.log(length) / Math.log(2));
		int segsize = 1;
		int i = length;
		while (i > 1) {
			segsize <<= 1;
			i >>= 1;
		}
		
		if (segsize == length) {
			segsize >>= 1;
		}
		
		
Draft code:

		ListNode sa = head; // sa: segment sentry a
		ListNode sb = head; // sb: Segment sentry b
		
		while (segsize > 0) 
		{
			i = 0;
			while (sb != null && i < segsize) {
				sb = sb.next;
				i++;
			}
			
			
			ListNode a = sa; // two cursors, a, moves between sa and sb
			ListNode b = sb; // b, moves after sb, pin to a (difference).

			while (a != sb && b != null) {
				if (a.val > b.val) {
					// swap
					int temp = a.val;
					a.val = b.val;
					b.val = temp;
				}
				a = a.next;
				b = b.next;
			}
			
			sa = sb;
			
			segsize >>= 1;
		}
		
		
		
		
well, the following is not a very solid shell implementation

		while (segsize > 0) { 
			ListNode a = head;
			ListNode b = a;
			int i = 0;
			while (b != null && i < segsize) {
				i++;
				b = b.next;
			}
			
			// we walk from head to tail, so the comparison is to push data (bigger to end)
			while (b != null) {
				if (a.val > b.val) { 
					int temp = a.val;
					a.val = b.val;
					b.val = temp;
				}
				a = a.next;
				b = b.next;
			}
			
			segsize >>= 1; // seg size cut half -- shell sorting algorithms..
		}
		
		
we are near the solution

		
public ListNode sortList(ListNode head) { 
		
		ListNode p = head;
		int length = 0;
		ListNode q = head;
		while (p != null && q != null) {
			length++;
			p = p.next;
			q = q.next;
			if (q != null) {
				q = q.next;
				length++;
			}
		}
		
		int segsize = length >> 1;
		
		int amoved = 0;
		while (segsize > 0) { 
			
			ListNode a = head;
			ListNode b = a;
			int i = 0;
			
			// find b 
			while (b != null && i < segsize) {
				i++;
				b = b.next;
			}
			
						
			// we walk from head to tail, so the comparison is to push data (bigger to end)
			while (b != null) {
				int temp = a.val;
				
				// until b, we sort each column
				p = a;
				ListNode n = p;// previous pos
				while (n != b) { 
					i = 0;
					while (n != null && i < segsize) {
						n = n.next; // next pos
						i++;
					}
					if (n.val < temp) {
						p.val = n.val; // like an insertion 
					} else { 
						break;
					}
					
					p = n; // moves p...
				}
				
				n.val = temp; // push temp to far end
				
				// advance the b and p both once...
				b = b.next;
				if (amoved < segsize) {
					a = a.next;
					amoved++;
				} else {
					amoved = 0;
					a = head;
				}
			}
			
			segsize >>= 1;
		}
		
		return head;
	}
}
*/		
