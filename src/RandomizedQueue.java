import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**Performance requirement: Operations:constant
 iterator construction: linear
 **/

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int N;
    private Item[] queue;

    public RandomizedQueue()    {
        N = 0;
        queue =(Item[]) new Object[1];
    }             // construct an empty randomized queue
    public boolean isEmpty()           {
        return N==0;
    }      // is the randomized queue empty?
    public int size()     {
        return N;
    }                   // return the number of items on the randomized queue
    public void enqueue(Item item){
        if(item  == null) throw new IllegalArgumentException();
        resizeQueue();
        queue[N++] = item;
    }           // add the item

    private void resizeQueue() {
        if(N+1>queue.length){
            Item[] temp =(Item[]) new Object[queue.length*2];
            for(int i = 0; i < N; i++){
                temp[i] = queue[i];
            }
            queue = temp;
            return;
        }

    }
    private void resizeDequeue(){
        if(N-1<(queue.length)/4){
            Item[] temp =(Item[]) new Object[queue.length/2];
            for(int i = 0; i < N; i++){
                temp[i] = queue[i];
            }
            queue = temp;
            return;
        }
    }
    public Item dequeue(){
        if(!isEmpty()){
            resizeDequeue();
            int random = StdRandom.uniform(N);
            Item temp = queue[random];
            queue[random] = queue[N-1];
            queue[N-1] = null;
            N--;
            return temp;
        }
        else throw new NoSuchElementException();
    }                    // remove and return a random item
    public Item sample(){
        if(isEmpty()) throw new NoSuchElementException();
        return queue[StdRandom.uniform(N)];
    }                     // return a random item (but do not remove it)
    public Iterator<Item> iterator(){
        return new iterator();
    }         // return an independent iterator over items in random order
    private class iterator implements Iterator<Item>{
        private int i = 0;
        private Item[] randomOrder;
        public iterator(){
            randomOrder =(Item[]) new Object[N];
            for(int j = 0; j<N;j++ ){
                randomOrder[j] = queue[j];
            }
            StdRandom.shuffle(randomOrder);
        }
        @Override
        public boolean hasNext() {
            return i != N;
        }

        @Override
        public Item next() {
            if(i==N) throw new NoSuchElementException();
            return randomOrder[i++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    public static void main(String[] args){
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for(int i = 0; i < 10; i++){
            queue.enqueue(i);
        }
//        for (int i = 0; i <=4; i++)
//        queue.dequeue();
        for(Integer s : queue){
            System.out.println(s);
        }
    }   // unit testing (optional)
}
