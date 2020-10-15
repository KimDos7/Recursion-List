package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class LLNodeIterator<T> implements Iterator<T>{
	private LLNode<T> current;

	

	  // Constructors
	  public LLNodeIterator(LLNode<T> head) {
		  this.current = head;
		   
	  }

	  @Override
	  public boolean hasNext() {
		if(current == null) {
			return false;
		}
	    return true;
	  }

	  @Override
	  public T next() {
		  if(!hasNext()) {
			 throw new NoSuchElementException("No next element!");
		  }
		  T curData = current.data;
		  current = current.link;
		  return curData;
		 
	  }

	  @Override
	  public void remove() {
	    // Nothing to change for this method
	    throw new UnsupportedOperationException();
	  }
}
