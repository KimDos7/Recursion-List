package structures;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class PublicListInterfaceTest {

  private ListInterface<String> list;
  private ListInterface<String> list2;
  private ListInterface<String> list3;
  private ListInterface<Integer> list4;
  
  @Before
  public void setup() {
    list = new RecursiveList<String>();
    list2 = new RecursiveList<String>();
    list3 = new RecursiveList<String>();
    list4 = new RecursiveList<Integer>();
    
    list2.insertFirst("1");
    list2.insertFirst("2");
    list2.insertLast("3");
	list2.insertAt(2, "4");
	
	list3.insertLast("1");
	list3.insertLast("2");
	list3.insertLast("3");
	list3.insertLast("4");
	list3.insertLast("5");
	list3.insertLast("6");
	list3.insertLast("7");
	
  }
  
  @Test 
  public void testAll() {
	for(String e : list3) {
		System.out.println(e);
	}
	list3.remove("7");
	for(String e : list3) {
		System.out.println(e);
	} 
	  
  }
 
  @Test (timeout = 500)
  public void testIteratorandFirstInsert(){
	  assertFalse(list2.isEmpty());
	  int count = 0;
	  for(String e : list2) {
		  count += 1;
	  }
	  assertEquals(4, list2.size());
	  assertEquals(4, count);
	  
  }
  
  @Test 
  public void testRemoves() {
	  
	  assertEquals("3", list2.removeAt(3));

  }
  
  @Test (timeout = 500)
  public void testGets() {
	  assertEquals("3", list2.getLast());
	  assertEquals("3", list2.get(3));
  }

  @Test (timeout = 500)
  public void testRemoveAt() {
	  assertEquals(true, list2.remove("2"));
	  assertEquals(3, list2.size());
	  assertEquals("1", list2.get(0));
  }
  
  @Test (timeout = 500)
  public void testfindElem() {
	  assertEquals(3, list2.indexOf("3"));
  }
  
  @Test (timeout = 500)
  public void testInsertFirstIsEmptySizeAndGetFirst1() {
    assertTrue("Newly constructed list should be empty.", list.isEmpty());
    assertEquals("Newly constructed list should be size 0.", 0, list.size());
    assertEquals("Insert First should return instance of self", list, list.insertFirst("hello"));
    assertFalse("List should now have elements.", list.isEmpty());
    assertEquals("List should now have 1 element.", 1, list.size());
    assertEquals("First element should .equals \"hello\".", "hello", list.getFirst());
    list.insertFirst("world");
    assertEquals(2, list.size());
    list.insertFirst("foo");
    assertEquals(3, list.size());
    assertEquals("First element should .equals \"foo\".", "foo", list.getFirst());
  }
  
  
}
