
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/*
 This is my own work: Michael Mussler
 11/17/2015
 Program working with doubly linked list
 */
/**
 * solution file for DLinkedList Assignment Fall 2014
 *
 * @author lkfritz
 */
public class DLinkedList<T extends Comparable<T>> {

    public static void main(String[] args) throws FileNotFoundException {

        DLinkedList<String> lst1 = new DLinkedList<>();
        DLinkedList<String> lst2 = new DLinkedList<>();

        Scanner darkly = new Scanner(new File("text1.in"));
        String str;
        while (darkly.hasNext()) {
            str = darkly.next();
            str = cleanUp(str);
            lst1.insertOrderUnique(str);
        }
        darkly.close();
        darkly = new Scanner(new File("text2.in"));
        while (darkly.hasNext()) {
            str = darkly.next();
            str = cleanUp(str);
            lst2.insertOrderUnique(str);
        }
        System.out.println("List 1:  " + lst1);
        System.out.println("List 2:  " + lst2);
        DLinkedList combined = lst1.merge(lst2);
        System.out.println("\nAFTER MERGE");
        System.out.println("List 1:  " + lst1);
        System.out.println("List 2:  " + lst2);
        System.out.println("\n" + combined);

    }

    /**
     * ASSIGNED
     *
     * @param str
     * @return str in all lower case with LEADING and TRAILING non-alpha chars
     * removed
     */
    public static String cleanUp(String str) {
        //to make all letters lower case
        str = str.toLowerCase();

        char newNode;
        //to clean up from leading 
        for (int i = 0; i < str.length() - 1;) {
            //set i to a char because we want the int of the character.
            newNode = str.charAt(i);
            //check to see if it is not a character.If it is then stop and move 
            //on to the trailing. if not then goes to the next index and keeps going
            //till it has a character.
            if (!Character.isAlphabetic(newNode)) {
                i++;
                //get the substring after removing the none characters
            } else {
                str = str.substring(i);
                //break so that it sends a substring to check for tailing
                break;
            }
        }
        //to clean up from trailing
        for (int i = str.length() - 1; i > 0;) {
            newNode = str.charAt(i);
            if (!Character.isAlphabetic(newNode)) {
                i--;
            } else {
                str = str.substring(0, i + 1);
                //break so that it doesnt keep going
                break;
            }
        }
        return str;
    }

    //inner DNode class:  PROVIDED
    private class DNode {

        private DNode next, prev;
        private T data;

        private DNode(T val) {
            this.data = val;
            next = prev = this;
        }
    }

    //DLinkedList fields:  PROVIDED
    private DNode header;

    //create an empty list:  PROVIDED
    public DLinkedList() {
        header = new DNode(null);
    }

    /**
     * PROVIDED add
     *
     * @param item return ref to newly inserted node
     */
    public DNode add(T item) {
        //make a new node
        DNode newNode = new DNode(item);
        //update newNode
        newNode.prev = header;
        newNode.next = header.next;
        //update surrounding nodes
        header.next.prev = newNode;
        header.next = newNode;
        return newNode;
    }

    //PROVIDED
    public String toString() {
        String str = "[";
        DNode curr = header.next;
        while (curr != header) {
            str += curr.data + " ";
            curr = curr.next;
        }
        str = str.substring(0, str.length() - 1);
        return str + "]";
    }

    /**
     * ASSIGNED remove val from the list
     *
     * @param val
     * @return true if successful, false otherwise
     */
    public boolean remove(T val) {
        //check to see if val is equal to null and if so return false
        if (val == null) {
            return false;
        }
        // reference current node
        DNode currentNode;
        //loop through list
        for (currentNode = header; currentNode != null; currentNode = currentNode.next) {

            if (currentNode.data == val) {
                currentNode.prev.next = currentNode.next;
                currentNode.next.prev = currentNode.prev;
                return true;
            }

        }

        return true;
    }

    /**
     * ASSIGNED
     *
     * @param item
     */
    public void insertOrder(T item) {
        DNode currentNode = header.next;
        DNode newNode = new DNode(item);
        if (currentNode == null) {
            add(item);

        }
        //find where we want to insert the new node
        while (currentNode != header && currentNode.data.compareTo(item) <= 0) {
            currentNode = currentNode.next;
        }
        //update the next and the prev of the node we just moved
        newNode.next = currentNode;
        newNode.prev = currentNode.prev;
        //update the surrounding nodes
        currentNode.prev.next = newNode;
        currentNode.prev = newNode;

    }

    /**
     * ASSIGNED
     *
     * @param item
     */
    public boolean insertOrderUnique(T item) {
        DNode currentNode = header.next;
        DNode newNode = new DNode(item);

        if (currentNode == null) {
            add(item);
            return true;
        } //find where we want to insert the new node
        else {
            //find the location where the new node needs to be
            while (currentNode != header && !(currentNode.data.compareTo(item) > 0)) {
                if (currentNode.data.compareTo(item) == 0) {
                    return false;
                }
                currentNode = currentNode.next;
            }
            //update the next and the prev of the node we just moved
            newNode.next = currentNode;
            newNode.prev = currentNode.prev;
            //update the surrounding nodes
            currentNode.prev.next = newNode;
            currentNode.prev = newNode;
        }

        return true;
    }

    /**
     * ASSIGNED PRE: this and rhs are sorted lists
     *
     * @param rhs
     * @return list that contains this and rhs merged into a sorted list POST:
     * returned list will not contain duplicates
     */
    public DLinkedList merge(DLinkedList rhs) {
        DLinkedList result = new DLinkedList();
        //reference a and b 
        DNode list1, list2;
        list1= this.header.next;
        list2 = rhs.header.next;
        ///while in bounds
        while (list1 != this.header && list2 != rhs.header) {
            //check to see if list1 is less than list2 and if so use a and discard
            if(list1.data.compareTo(list2.data)<0) {
                //add list1 to list
                list1.prev.next=list1.next;
                list1.next.prev=list1.prev;
                //move
                list1.next=result.header;
                list1.prev=result.header.prev;
               //reference list1 to result
               result.header.prev.next=list1;
               result.header.prev = list1;
               //update list1
               list1=this.header.next;
             
            }
            //check to see if b is less than a and if so use b and discard
            if(list2.data.compareTo(list1.data)<0){
               //add list2 to list
                list2.prev.next=list2.prev;
                list2.next.prev=list2.next;
                //move 
                list2.next=result.header;
                list2.prev=result.header.prev;
                //reference list2 to result 
               result.header.prev.next=list2;
               result.header.prev = list2;
               //update list1
               list2=rhs.header.next;
            }
            //checks to see if a is equal to b and if so use one and discard both
            if(list2.data==list1.data){
                //drop reference list1
                list1.prev.next=list1.next;
                list1.next.prev=list1.prev;
                //drop reference list2
                list2.next.prev=list2.prev;
                list2.prev.next=list2.next;
                
                //move list1
                list1.next =result.header;
                list1.prev=result.header.prev;
              // reference a to result
                result.header.prev.next=list1;
                result.header.prev=list1;
                //update list1 and list2 
              list1=this.header.next;
              list2=rhs.header.next;
            }
           
           
        }
        //if list list1 is longer than list2
        while(list1!=this.header){
          list1.prev.next=list1.next;
          list1.next.prev=list1.prev;
          list1.next=result.header;
          list1.prev=result.header.prev;
          result.header.prev.next=list1;
          result.header.prev=list1;
         list1=this.header.next;
        }
        //if list list2 is longer than list1 
        while(list2!=rhs.header){
            list2.prev.next=list2.next;
            list2.next.prev=list2.prev;
            list2.next=result.header;
            list2.prev=result.header.prev;
             result.header.prev.next=list2;
             result.header.prev=list2;
             list2=rhs.header.next;
        }
        return result;
    }
}
