/*
 This is my own work Michael Mussler
 10/27/15
 program 4 working with an interface.
 */
package SET_OPS;

 

/**
 *
 * @author LKFRITZ
 */
public interface SetInterface<T> {
    public boolean addItem(T item);
    public boolean contains(T item);
    public int getSize();
    public boolean remove(T item);
    public T remove();
    public T[] toArray();
    public String toString();
    public SetInterface<T> union(SetInterface<T> rhs);
    public SetInterface<T> intersection(SetInterface<T> rhs);
    public SetInterface<T> difference(SetInterface<T> rhs);
    public SetInterface<T> symmDifference(SetInterface<T> rhs);
}

class ArraySet<T> implements SetInterface<T> {

    private T[] arr;
    private int numItems;

    private void ensureCap() {

        if (arr == null) {
            arr = (T[]) new Object[1];
        }
        T[] temp = (T[]) new Object[arr.length * 2];
        if (arr.length == numItems) {
            for (int i = 0; i < numItems; i++) {
                temp[i] = arr[i];

            }
        }

    }

    @Override
    public boolean addItem(T item) {
        //calls conatins to make sure there are no duplicates 
        if (!contains(item)) {
            ensureCap();
            arr[numItems] = item;
            numItems++;
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T item) {
        //go through set
        for (int i = 0; i < this.numItems; i++) {
            if (arr[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getSize() {
        return this.numItems;
    }
//remove specific

    @Override
    public boolean remove(T item) {
        for (int i = 0; i < numItems; i++) {
            //checks to see if the element is there
            if (arr[i].equals(item)) {
                arr[i] = arr[numItems];
                //decraments
                numItems -= 1;
                return true;
            }
        }
        return false;
    }

    @Override
    public T remove() {
        T item;
        item = arr[numItems];
        numItems -= 1;
        return item;
    }

    @Override
    public T[] toArray() {
        return arr;
    }
    public String toString(){
        String result="";
        for (int i = 0; i < numItems; i++) {
            result+=arr[i]+ " ";
        }
        return result;
    }

    @Override
    public SetInterface<T> union(SetInterface<T> rhs) {
        SetInterface<T> answer = new ArraySet<T>();
        T[] temp = (T[]) new Object[rhs.getSize()];
        temp = rhs.toArray();
        for (int i = 0; i < numItems; i++) {

            answer.addItem(arr[i]);

        }
        for (int i = 0; i < rhs.getSize(); i++) {
            answer.addItem(temp[i]);

        }
        return answer;
    }

    @Override
    public SetInterface<T> intersection(SetInterface<T> rhs) {
        SetInterface<T> answer = new ArraySet<T>();
        for (int i = 0; i < numItems; i++) {
            if (rhs.contains(arr[i])) {
                answer.addItem(arr[i]);
            }
        }
        return answer;
    }

    @Override
    public SetInterface<T> difference(SetInterface<T> rhs) {
        SetInterface<T> answer = new ArraySet<T>();
        for (int i = 0; i < numItems; i++) {
            if (!rhs.contains(arr[i])) {
                answer.addItem(arr[i]);
            }
        }
        return answer;
    }

    @Override
    public SetInterface<T> symmDifference(SetInterface<T> rhs) {
        SetInterface<T> answer = new ArraySet<T>();
        T[] temp = (T[]) new Object[rhs.getSize()];
        temp = rhs.toArray();
        for (int i = 0; i < numItems; i++) {
            if (!rhs.contains(arr[i])) {
                answer.addItem(arr[i]);
            }
        }
        for (int i = 0; i < rhs.getSize(); i++) {
            if (!contains(temp[i])) {
                answer.addItem(temp[i]);
            }
        }
        return answer;
    }

    class LinkedSet<T> implements SetInterface<T> {

        private class Node {

            private Node next;
            private T val;

            private Node(T val) {
                this.val = val;
                this.next = null;
            }

            private Node(T val, Node n) {
                this.val = val;
                this.next = n;
            }
        }

        private Node frontNode;
        private T val;

        private Node getRefTo(T item) {
            Node n = frontNode;
            while (n != null && !n.val.equals(item)) {
                n = n.next;
                return n;
            }
            return null;
        }

        @Override
        public boolean addItem(T item) {

            if (!contains(item)) {

                Node n = new Node(item);
                n.next = frontNode;
                frontNode = n;
                return true;
            }
            return false;
        }

        @Override
        public boolean contains(T item) {
            boolean answer = false;
            Node currentNode = frontNode;
            while (!answer && (currentNode != null)) {
                if (item.equals(currentNode.val)) {
                    answer = true;
                } else {
                    currentNode = currentNode.next;
                }
            }
            return answer;
        }

        @Override
        public int getSize() {
            int size = 0;
            for (Node n = frontNode; n != null; n = n.next) {
                size++;
            }
            return size;
        }

        @Override
        public boolean remove(T item) {
            boolean result = false;
            Node n = getRefTo(item);
            if (n != null) {
                n.val = frontNode.val;
                frontNode = frontNode.next;

                result = true;
            }
            return result;
        }

        @Override
        public T remove() {
            T result = null;
            if (frontNode != null) {
                result = frontNode.val;
                frontNode = frontNode.next;

            }
            return result;
        }

        @Override
        public T[] toArray() {
            T[] arr = (T[]) new Object[getSize()];
            int index = 0;
            Node currentNode = frontNode;
            while ((index < getSize()) && (currentNode != null)) {
                arr[index] = currentNode.val;
                index++;
                currentNode = currentNode.next;

            }
            return arr;

        }
        public String toString(){
            String str="";
            Node n=frontNode;
            while(n!=null){
                str+=n.val+"";
                n=n.next;
            }
            return str;
        }

        @Override
        public SetInterface<T> union(SetInterface<T> rhs) {
            SetInterface<T> answer = new LinkedSet<T>();
            T[] temp = (T[]) new Object[rhs.getSize()];
            T[] arr = (T[]) new Object[getSize()];
            arr = toArray();
            temp = rhs.toArray();
            for (int i = 0; i < getSize(); i++) {

                answer.addItem(arr[i]);

            }
            for (int i = 0; i < rhs.getSize(); i++) {
                answer.addItem(temp[i]);

            }
            return answer;
        }

        @Override
        public SetInterface<T> intersection(SetInterface<T> rhs) {
            SetInterface<T> answer = new LinkedSet<T>();
            T[] temp = (T[]) new Object[rhs.getSize()];
            T[] arr = (T[]) new Object[getSize()];
            arr = toArray();
            temp = rhs.toArray();
            for (int i = 0; i < getSize(); i++) {
                if (rhs.contains(arr[i])) {
                    answer.addItem(arr[i]);
                }
            }
            return answer;
        }

        @Override
        public SetInterface<T> difference(SetInterface<T> rhs) {
            SetInterface<T> answer = new LinkedSet<T>();
            T[] temp = (T[]) new Object[rhs.getSize()];
            T[] arr = (T[]) new Object[getSize()];
            arr = toArray();
            temp = rhs.toArray();
            for (int i = 0; i < numItems; i++) {
                if (!rhs.contains(arr[i])) {
                    answer.addItem(arr[i]);
                }
            }
            return answer;
        }

        @Override
        public SetInterface<T> symmDifference(SetInterface<T> rhs) {
            SetInterface<T> answer = new LinkedSet<T>();
            T[] temp = (T[]) new Object[rhs.getSize()];
            temp = rhs.toArray();
            T[] arr = (T[]) new Object[getSize()];
            arr = toArray();
            for (int i = 0; i < numItems; i++) {
                if (!rhs.contains(arr[i])) {
                    answer.addItem(arr[i]);
                }
            }
            for (int i = 0; i < rhs.getSize(); i++) {
                if (!contains(temp[i])) {
                    answer.addItem(temp[i]);
                }
            }
            return answer;
        }
    }
}
    //ADD YOUR LINKED SET CODE HERE
    
