package lrucache;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/*
 
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

*/

/*

What is left to be done?

 1. lock 
 2. more effecient algorithms...
 

 */
public class LRUCache {
    /*===============================================
     * Private Fields
     *===============================================*/
	
	private final Hashtable _map;
	private final Hashtable _usage;
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
    
    /*===============================================
     * Private Regions
     *===============================================*/
    private boolean exists(int key) { 
    	return _map.containsKey(key);
    }
    
    private void evict(int key) {
    	System.out.println("Evicting " + key);
    	_map.remove(key);
    	_usage.remove(key);
    }
    
    private void update(int key) {
    	_usage.put(key, new GregorianCalendar());
    }
        
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
