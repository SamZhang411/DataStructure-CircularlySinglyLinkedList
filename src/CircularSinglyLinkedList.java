import java.util.NoSuchElementException;

/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
 *
 * @author Qingyuan Zhang
 * @version 1.0
 * @userid qzhang417
 * @GTID 903497782
 * <p>
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 * <p>
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class CircularSinglyLinkedList<T> {


    // Do not add new instance variables or modify existing ones.
    private CircularSinglyLinkedListNode<T> head;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the data to the specified index.
     * <p>
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Cannot insert data at indexes outside of the data structure");
        }
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data into data structure");
        }
        if (head == null) {
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<>(data);
            head = newNode;
            head.setNext(head);
        } else if (index == 0) {
            addToFront(data);
        } else {
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<>(data);
            CircularSinglyLinkedListNode<T> curr = head;
            int count = 0;
            while (count < index - 1) {
                curr = curr.getNext();
                count++;
            }
            newNode.setNext(curr.getNext());
            curr.setNext(newNode);
        }
        size++;
    }

    /**
     * Adds the data to the front of the list.
     * <p>
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data into data structure");
        }
        if (head != null) {
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<>(head.getData());
            newNode.setNext(head.getNext());
            head.setNext(newNode);
            head.setData(data);
        } else {
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<>(data);
            head = newNode;
            head.setNext(head);
        }
        size++;
    }

    /**
     * Adds the data to the back of the list.
     * <p>
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data into data structure");
        }
        if (head == null) {
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<>(data);
            head = newNode;
            head.setNext(head);
        } else {
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<>(head.getData());
            newNode.setNext(head.getNext());
            head.setNext(newNode);
            head.setData(data);
            head = head.getNext();
        }
        size++;
    }

    /**
     * Removes and returns the data at the specified index.
     * <p>
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Cannot remove data outside of the existing data structure");
        }
        if (head == null) {
            return null;
        }
        if (index == 0) {
            T tempData = head.getData();
            head = head.getNext();
            size--;
            return tempData;
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            int count = 0;
            while (count++ != index - 1) {
                curr = curr.getNext();
            }
            T tempData = curr.getNext().getData();
            curr.setNext(curr.getNext().getNext());
            size--;
            return tempData;
        }
    }

    /**
     * Removes and returns the first data of the list.
     * <p>
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (head == null) {
            return null;
        } else {
            T tempData = head.getData();
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
            size--;
            return tempData;
        }
    }

    /**
     * Removes and returns the last data of the list.
     * <p>
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (head == null) {
            throw new NoSuchElementException("Cannot remove from an empty list");
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            while (curr.getNext().getNext() != head) {
                curr = curr.getNext();
            }
            T returnData = curr.getNext().getData();
            curr.setNext(head);
            size--;
            return returnData;
        }
    }

    /**
     * Returns the data at the specified index.
     * <p>
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Cannot insert data at indexes outside of the data structure");
        }
        if (head == null) {
            return null;
        }
        if (index == 0) {
            return head.getData();
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            int count = 0;
            while (count++ != index) {
                curr = curr.getNext();
            }
            return curr.getData();
        }
    }

    /**
     * Returns whether or not the list is empty.
     * <p>
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Clears the list.
     * <p>
     * Clears all data and resets the size.
     * <p>
     * Must be O(1).
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     * <p>
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     * <p>
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot search for null data in the given data structure");
        }
        if (head == null) {
            return null;
        }
        return null;
    }

    /**
     * Returns an array representation of the linked list.
     * <p>
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        T[] returnData;
        returnData = (T[]) new Object[size];
        CircularSinglyLinkedListNode<T> curr = head;
        for (int i = 0; i < size; i++) {
            returnData[i] = curr.getData();
            curr = curr.getNext();
        }
        return returnData;
    }

    /**
     * Returns the head node of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public CircularSinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the size of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
