package PriorityQueue;

import java.util.Iterator;
import data_structures.*;

public class Test {
    public static void main(String [] args) {
        PriorityQueue<Integer> pq = new UnorderedArrayPriorityQueue<Integer>(10);
        System.out.println("Grading the UnorderedArray implementation");        
        try {
            for(int i=10; i > 0; i--)
                if(!pq.insert(i))
                    throw new RuntimeException("ERROR in ADD.");
                    
            for(int i=1; i <= 10; i++)
                if(pq.remove() != i)
                    throw new RuntimeException("ERROR, out of order removal");            
            
            for(int i=0; i < 1000; i++) {
                pq.insert(i);
                pq.remove();
                }
                
            if(pq.size() != 0)
                throw new RuntimeException("ERROR, wrong size()");
            }
        catch(Exception e) {
            System.out.println(e);
            }
            
            
        int [] array = new int[100];
        for(int i=0; i < 100; i++)
            array[i] = i;
        for(int i=0; i < 100; i++) {
            int newIndex = (int) (100*Math.random());
            int tmp = array[i];
            array[i] = array[newIndex];
            array[newIndex] = tmp;
            }
            
        pq.clear();
        pq = new UnorderedArrayPriorityQueue<Integer>(100);            
        try {
            for(int i=0; i < 100; i++)
                if(!pq.insert(array[i]))
                    throw new RuntimeException("ERROR in insertion"); 
                    
            if(pq.peek() != 0)
                throw new RuntimeException("ERROR, peek() returned wrong value");   
            for(int i=0; i < 100; i++) {
                if(pq.remove() != i)
                    throw new RuntimeException("ERROR, out of order removal");
                }
            
            for(int i=0; i < 150; i++)
                pq.insert(i);
                
            if(pq.size() != 100)
                throw new RuntimeException("ERROR in size()");
                
            if(!pq.isFull())
                throw new RuntimeException("ERROR in isFull()");
            for(int i=0; i < 150; i++)
                pq.remove();                
            }
        catch(Exception e) {
            System.out.println(e);
            }
            
        try {
            pq.clear();
            for(Integer x : pq)
                System.out.println("ERROR, value returned from empty iterator");
            
            for(int i=10; i >= 0; i--)
                pq.insert(i);
                
            System.out.println("Iterator should print 0 .. 10 in any order");
            for(Integer x : pq)
                System.out.println(x);
            }
        catch(Exception e) {
            System.out.println(e);
            } 
            
        try {
            pq = new UnorderedArrayPriorityQueue<Integer>(500);
            for(int i=0; i < 5; i++)
                for(int j=0; j < 100; j++)
                    if(!pq.insert(j))
                        throw new RuntimeException("ERROR in insert");
                        
            for(int i=0; i < 100; i++)
                for(int j=0; j < 5; j++)
                    if(pq.remove() != i)
                        throw new RuntimeException("ERROR, out of order removal");
             }
        catch(Exception e) {
            System.out.println(e);
            }                     
        try {
            PriorityQueue<Item> pq2 = new UnorderedArrayPriorityQueue<Item>(6);
            pq2.insert(new Item(5,0)); 
            pq2.insert(new Item(5,1));
            pq2.insert(new Item(5,2));
            pq2.insert(new Item(3,3));
            pq2.insert(new Item(3,4));
            pq2.insert(new Item(3,5));
            
            for(int i=0; i < 6; i++)
                System.out.println(pq2.remove());
            }
        catch(Exception e) {
            System.out.println(e);
            }             
            
         try {
            PriorityQueue<Item> pq2 = new UnorderedArrayPriorityQueue<Item>(10);
            int sequence = 0;
            for(int i=0; i < 10; i++) {
                pq2.insert(new Item(2,sequence++));
                pq2.insert(new Item(1,sequence++));  
                }
                
            System.out.println("\nPrinting newly inserted elements, all 2 and 1");
            for(Item x : pq2) System.out.println(x);  
            System.out.println("\nNow deleting all the 1s");                         

            pq2.delete(new Item(1,0));
            if(pq2.size() != 5) System.out.println("Size error, should be 5 but is " + pq2.size());
            for(Item x : pq2) System.out.println(x);
            System.out.println("\n"); 
                      
            for(int i=0; i < 5; i++)
                System.out.println(pq2.remove());
            System.out.println("\n"); 
            if(pq2.size() != 0) System.out.println("Size error, should be 0 but is " + pq2.size()); 
            System.out.println("Empty iterator follows, should print nothing.");                           
            for(Item x : pq2) System.out.println(x);                
            }
        catch(Exception e) {
            System.out.println(e);
            }                        
 
                      
            
        }
        
        static class Item implements Comparable<Item>  {
            private int priority;
            private int sequence;
            
            public Item(int p, int s) {
                priority = p;
                sequence = s;
                }
                
            public int compareTo(Item i) {
                return priority - i.priority;
                }
                
            public String toString() {
                return "Priority: " + priority + "   Sequence: " + sequence;
                }
            }                         
    }