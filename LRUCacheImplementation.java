package LinkedList;

import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/* We can use Java inbuilt Deque as a double
   ended queue to store the cache keys, with
   the descending time of reference from front
   to back and a set container to check presence
   of a key. But remove a key from the Deque using
   remove(), it takes O(N) time. This can be
   optimized by storing a reference (iterator) to
   each key in a hash map. */

public class LRUCacheImplementation {
   //store key of cache
    private Deque<Integer> doublyQueue;
    // store refrence of key in cache
    private HashSet<Integer> hashSet;
    //maximum capacity of cache
    private final int Capacity;
    //
     LRUCacheImplementation(int capacity) {
        this.doublyQueue = new LinkedList<>();
        this.hashSet = new HashSet<>();
        Capacity = capacity;
    }

    //refer the page within LRU cache
    public void refer(int pages)
    {
        if(!hashSet.contains(pages))
        {
            if(doublyQueue.size() == Capacity) {
                int last = doublyQueue.removeLast();
                hashSet.remove(last);
            }
            /* The found page may not be always the last element, even if it's an
               intermediate element that needs to be removed and added to the start
               of the Queue */
            else{
                doublyQueue.remove(pages);

            }
            doublyQueue.push(pages);
            hashSet.add(pages);

        }

    }
    // display contents of cache
    public void display()
    {
        Iterator<Integer>  itr = doublyQueue.iterator();
        while(itr.hasNext())
        {
            System.out.print(itr.next() + " ");
        }
    }

    public static void main(String[] args) {
        LRUCacheImplementation cache = new LRUCacheImplementation(5);
        cache.refer(1);
        cache.refer(2);
        cache.refer(3);
        cache.refer(1);
        cache.refer(4);
        cache.refer(5);
        cache.display();

    }
}
