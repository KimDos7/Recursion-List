package structures;

import java.util.Iterator;

class LLNode<T>{

	public T data;
	public LLNode<T> link;
		  
	public LLNode() {
		this(null, null);
	}
		  
	public LLNode(T data, LLNode<T> link) {
		this.data = data;
		this.link = link;
	}
}


public class RecursiveList<T> implements ListInterface<T>{
	
	private int size = 0;
	private LLNode<T> head = null;
	
	
	//Size method
	
	public int size() {
		return size;
	}
	
	//Iterator
	
	public Iterator<T> iterator(){
		return new LLNodeIterator<T>(this.head);
	}
	
	// O(1) Time
	public ListInterface<T> insertFirst(T elem){
		if(elem == null) {
			throw new NullPointerException();
		}
		LLNode<T> newNode = new LLNode<T>(elem, head);
		head = newNode;
		size++;
		return this;
	}
	
	// O(size) Time
	
	public ListInterface<T> insertLast(T elem){
		if(elem == null) {
			throw new NullPointerException();
		}
		if(head == null) {
			return insertFirst(elem);
		}
		LLNode<T> cursor = head;
		insertLast(elem, cursor);
		size++;
		return this;
	}
	
	//InsertLast Supporter Recursion Method
	private LLNode<T> insertLast(T elem, LLNode<T> currNode){
		if(elem == null) {
			throw new NullPointerException();
		}
		if(currNode == null) {
			LLNode<T> newNode = new LLNode<T>(elem, null);
			return newNode;
		} else {
			currNode.link = insertLast(elem, currNode.link);
		}
			return currNode;
	}
	
	//O(index) Time
	
	public ListInterface<T> insertAt(int index, T elem){
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if(elem == null) {
			throw new NullPointerException();
		}
		LLNode<T> cursor = head;
		if(index == 0) {
			return insertFirst(elem);
		}
		if(index == size) {
			return insertLast(elem);
		}
		insertAt(elem, index, cursor, null);
		size++;
		return this;
	}
	
	//InsertAt Recursion Support Method
	private void insertAt(T elem, int index, LLNode<T> currNode, LLNode<T> prevNode){
		if(index == 0) {
			LLNode<T> newNode = new LLNode<T>(elem, null);
			newNode.link = currNode;
			prevNode.link = newNode;
		} else {
			insertAt(elem, index-1, currNode.link, currNode);
		}
		
	}
	
	//O(1) Time
	public T removeFirst() {
		if(head == null) {
			throw new IllegalStateException();
		}
		T temp = head.data;
		head = head.link;
		size--;
		return temp;
	}
	
	//O(size) Time
	public T removeLast() {
		if(head == null) {
			throw new IllegalStateException();
		}
		LLNode<T> cursor = head;
		if(size == 1) {
			return removeFirst();
		}
		size--;
		return removeLast(cursor, null);
	}
	
	//RemoveLast Recursion Support Method
	private T removeLast(LLNode<T> currNode, LLNode<T> prevNode) {
		T removed;
		if(currNode.link == null) {
			prevNode.link = null;
			return currNode.data;
		}
		
		removed = removeLast(currNode.link, currNode);
		return removed;
		
	}
	
	//O(i) Time
	public T removeAt(int i) {
		if(i < 0 || i >= size) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<T> cursor = head;
		if(i == 0) {
			return removeFirst();
		}
		if(i == size) {
			return removeLast();
		}
		
		size--;
		
		return removeAt(cursor, i, null);
		
	}
	
	//RemoveAt Recursion Support Method
	private T removeAt(LLNode<T> currNode, int i, LLNode<T> prevNode) {
		T removed;
		if(i == 0) {
			prevNode.link = currNode.link;
			return currNode.data;
		}
		
		removed = removeAt(currNode.link, i - 1, currNode);
		return removed;
	}
	
	//O(1) Time
	public T getFirst() {
		if(head == null) {
			throw new IllegalStateException();
		}
		return head.data;
	}
	
	//O(size) Time
	public T getLast() {
		if(head == null) {
			throw new IllegalStateException();
		}
		LLNode<T> cursor = head;
		return getLast(cursor);
	}
	
	//GetLast Supporter Recursion Method
	private T getLast(LLNode<T> currNode) {
		T last;
		if(currNode.link == null) {
			return currNode.data;
		}
		
		last = getLast(currNode.link);
		
		return last;
	}
	
	//O(i) Time
	public T get(int i) {
		if(i < 0 || i >= size) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<T> cursor = head;
		if(head == null) {
			return null;
		}
		if(i == 0) {
			return getFirst();
		}
		
		if(i == size - 1) {
			return getLast();
		}
		return get(cursor, i);
	}
	
	//Get Recursion Supporter Method
	private T get(LLNode<T> currNode, int i) {
		T last;
		if(i == 0) {
			return currNode.data;
		}
		
		last = get(currNode.link, i-1);
		
		return last;
	}
	
	//O(size) Time
	public boolean remove(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		if(head == null) {
			return false;
		}
		
		LLNode<T> cursor = head;
		boolean ex = remove(elem, cursor, null);
		if(!ex) {
			return false;
		}
		size--;
		return true;
	}
	
	//Remove Recursion Supporter Method
	private boolean remove(T elem, LLNode<T> currNode, LLNode<T> prevNode) {
		boolean removed;
		if(currNode == null) {
			return false;
		}
		if(currNode.data.equals(elem)) {
			if(prevNode != null) {
				prevNode.link = currNode.link;
			} else {
				head = head.link;
			}
			
			return true;
		} 
		
			removed = remove(elem, currNode.link, currNode);
			
			return removed;
		
	}
	
	//O(size) Time
	public int indexOf(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		if(head == null) {
			return -1;
		}
		LLNode<T> cursor = head;
		
		return indexOf(elem, 0, cursor);
		
	}
	
	//IndexOf Recursion Supporter Method
	private int indexOf(T elem, int currIndex, LLNode<T> currNode) {
		int indexFound;
		if(currNode == null) {
			return -1;
		}
		if(currNode.data.equals(elem)) {
			return currIndex;
		}
		
		indexFound = indexOf(elem, currIndex + 1, currNode.link);
		return indexFound;
	}
	
	//O(1) Time
	public boolean isEmpty() {
		return size == 0;
	}
}
