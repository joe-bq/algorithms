package lrucache;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class LRUCache2 {

	
    /*===============================================
     * Private Class
     *===============================================*/
	
	private class Entry {
		private int _key;
		private int _timestamp;
		
		public void setKey(int key) {
			this._key = key;
		}
		
		public void setTimestampe(int timestamp) {
			this._timestamp = timestamp;
		}
		
		public boolean greaterThan(Entry another) { 
			return this._timestamp > another._timestamp;
		}
		
		@Override 
		public boolean equals(Object another) {
			if (another instanceof Entry ) {
				return this._timestamp == ((Entry)another)._timestamp;	
			}
			
			return false;
		}
		
		public boolean lessThan(Entry another) { 
			return this._timestamp < another._timestamp;
		}
	}
	
	private class CircularRing 
	{
//		private int _tail;
//		private final Entry[] _bucks;
//		private final Hashtable _lookup;
		
		/* Constructor 
		 * 
		 */
//		public CircularRing() {
//			_bucks = new Entry[LRUCache2.this._capacity];
//			_lookup = new Hashtable(_capacity);
//		}
		
		
		/* Public Methods 
		 * 
		 */
		/*
		public void heapify(int i) { // push down
			heapify(0, i);
		}
		
		
		private void heapify(int start, int i) { // push down
			int largest = -1;
			int l = left(start, i);
			int r = right(start, i);
//			if (l < _tail && _bucks[i] < _bucks[l]) {
			if (l < _tail && _bucks[i].lessThan(_bucks[l])) {
				largest = l;
			} else { 
				largest = i;
			}
			
//			if (r < _tail && _bucks[i] < _bucks[largest]) {
			if (r < _tail && _bucks[i].lessThan(_bucks[largest])) {
				largest = r;
			}
			
			if (largest != i) { 
				Entry temp = _bucks[i];
				_bucks[i] = _bucks[largest];
				_bucks[largest] = temp;
//				_lookup.put(_bucks[i], i);
//				_lookup.put(_bucks[largest], largest);
				_lookup.put(_bucks[i]._key, i);
				_lookup.put(_bucks[largest]._key, largest);
				heapify(start, largest);
			}
		}
		
		
		public void pushDown(int i) { 
			heapify(i);
		}
		
		public void pushUp(int i) {
			pushUp(0, i);
		}
		
		private void pushUp(int start, int i) { 
			int parent = parent(start, i);
			int l = left(start, parent);
			int r = right(start, parent);
			
			int largest = -1;
//			if ( l < _tail && _bucks[parent] < _bucks[l]) {
			if (l < _tail && _bucks[parent].lessThan(_bucks[l])) {
				largest = l;
			} else { 
				largest = parent;
			}
			
//			if (r < _tail && _bucks[largest] < _bucks[r]) {
			if (r < _tail && _bucks[largest].lessThan(_bucks[r])) {
				largest = r;
			}
			
			if (largest != parent) {
				Entry temp = _bucks[parent];
				_bucks[parent] = _bucks[largest];
				_bucks[largest] = temp;
//				_lookup.put(_bucks[parent], parent);
//				_lookup.put(_bucks[largest], largest);
				_lookup.put(_bucks[parent]._key, parent);
				_lookup.put(_bucks[largest]._key, largest);
				pushUp(start, largest);
			}
		}
		
		public void buildHeap() { 
			for (int i = _tail / 2; i >= 0; i--) { 
				heapify(i);
			}
		}
		
		public void evict(int key) {
			if (exists(key)) { 
				int index = (int) _lookup.get(key);
//				_bucks[index] = -1;
				_bucks[index] = new Entry();
				heapify(index, index);
				_tail--;
			}
		}
		
		public boolean exists(int key) { 
			return _lookup.containsKey(key);
		}
		
		public void update(int key) {
			if (exists(key)) { 
				throw new UnsupportedOperationException();
			}
		}
		
		*/
		
		/* Private Methods 
		 * 
		 */
		private int left(int i) { 
			return i << 1;
		}
		
		private int right(int i) { 
			return (i << 1) + 1;
		}
		
		private int parent(int i) {
			return i / 2;
		}
		
		private int left(int start, int i) { 
			return ((i - start) << 1) + start;
		}
		
		private int right(int start, int i) { 
			return (((i - start) << 1) + 1) + start; 
		}
		
		private int parent(int start, int i) {
			return ((i - start) / 2) + start;
		}
	}
	
    /*===============================================
     * Private Fields
     *===============================================*/
	
	/*
	private final Hashtable _map;
	private final CircularRing _usage;
	private final int _capacity;
	
	public LRUCache(int capacity) {
        _map = new Hashtable(capacity);
        _usage = new Hashtable(capacity);
        _capacity = capacity;
    }
    
    
    public int get(int key) {
        if (_map.contains(key)) {
        	update(key);
        	return (int)_map.get(key);
        }
        
        return -1;
    }
    
    public void set(int key, int value) {
        if (!exists(key)) {
        	if (_map.size() == _capacity) {
        		Object lruKey = null;
        		Calendar calendar = null;
        		
        		for (Map.Entry entry : (Set<Map.Entry>)_map.entrySet()) {
        			Object k = entry.getKey();
        			Calendar v = (Calendar)_usage.get(k);
        			if (calendar == null || v.before(calendar)) {
        				calendar = v;
        				lruKey = k;
        			}
        		}
        		
        		evict((int)lruKey);
        	}
        }
        
        _map.put(key, value);
        update(key);
    }
    */
    
    /*===============================================
     * Private Regions
     *===============================================*/
//    private boolean exists(int key) { 
//    	return _map.containsKey(key);
//    }
//    
//    private void evict(int key) {
//    	System.out.println("Evicting " + key);
//    	_map.remove(key);
//    	_usage.remove(key);
//    }
//    
//    private void update(int key) {
//    	_usage.put(key, new GregorianCalendar());
//    }
        
    /* ===============================================
     * Main
     * ===============================================*/
    public static void main(String[] args) throws InterruptedException {
    	LRUCache cache = new LRUCache(3);
    	cache.set(1, 1);
    	Thread.sleep(100);
    	cache.set(2, 2);
    	Thread.sleep(100);
    	cache.set(3, 3);
    	Thread.sleep(100);
    	cache.set(4, 4);
    }
    
}
