
import edu.princeton.cs.algs4.StdRandom;

import java.security.PublicKey;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;//pointer to first item
    private Node last;//pointer to last item
    private int N;// number of items

    private class Node{//declaration of a node
        Item item;
        Node next;
        Node prev;
    }

    public Deque(){
        first = null;
        last = null;
        N = 0;
    }
    public boolean isEmpty(){
        return first ==null;
    } // check if deque is empty
    public int size(){ // return the size of the deque
        return N;
    }
    public void addFirst(Item item){//add item to the front of the deque
        if(item == null) throw  new IllegalArgumentException( "Arguments can't be null");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if(N==0){
            last = first;
        }
        if(N>0) {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        N++;
    }
    public void addLast(Item item){//add item to the end of the deque
        if(item == null) throw new IllegalArgumentException("Arguments can't be null");
        Node oldlast = last;
        last = new Node();
        last.item = item;
        if(N==0){
            first = last;
        }
        if(N > 0) {
            oldlast.next = last;
            last.prev = oldlast;
        }
        N++;
    }
    public void removeFirst(){ // remove item from the front of the list
        if(N==0) throw new NoSuchElementException("The deque is empty");
        first = first.next;
        first.prev = null;//avoid loitering
        N--;
    }
    public void removeLast(){//remove the item from the end of the list
        if(N==0) throw new NoSuchElementException("The deque is empty");
        last = last.prev;
        last.next = null;//avoid loitering
        N--;
    }
    public Iterator<Item> iterator(){//returns an iterator
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current!=null;
        }

        public Item next() {
            if(!hasNext()) throw new NoSuchElementException("No more item in the deque");
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove(){
            throw new UnsupportedOperationException("removal of iterator is not supported");
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        for(int i = 0; i < 9; i++){
//            int random = StdRandom.uniform(1,100);
            deque.addFirst(i);
        }
        for(Integer integer: deque){
            System.out.println(integer);
        }
    }
}
