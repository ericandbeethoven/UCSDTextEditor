/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.head.next.prev.data);
		assertEquals("Remove: check size is correct ", 2, list1.size());
		// test longer list contents
		
		
		int b = list1.remove(list1.size() - 1);
		assertEquals("Remove: check b is correct ", 42, b);
		assertEquals("Remove: check element last - 1 is correct ", (Integer)21, list1.get(list1.size() - 1));
		assertEquals("Remove: check element last - 1 is correct ", (Integer)21, list1.tail.data);
		assertEquals("Remove: check size is correct ", 1, list1.size());
		
		try {
			emptyList.remove(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			shortList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		try {
			shortList.remove(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		// test off the end of the longer array
		try {
			longerList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		try {
			longerList.remove(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

		int c = longerList.remove(LONG_LIST_LENGTH / 2);
		int index = LONG_LIST_LENGTH / 2;
		assertEquals("Remove: check c is correct ", 5, c);
		assertEquals("Remove: check element ceil(middle - 1) is correct ", (Integer)6, longerList.get(index));
		LLNode<Integer> temp = longerList.head;
		for (int i = 0; i < index; i++)
			temp = temp.next;
		assertEquals("Remove: check element ceil(middle - 1) is correct ", (Integer)6, temp.next.prev.data);
		assertEquals("Remove: check element ceil(middle - 1) is correct ", (Integer)4, temp.prev.data);
		assertEquals("Remove: check size is correct ", 9, longerList.size());
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		try {
			shortList.add(null);
			fail("Null pointer exception");
		}
		catch (NullPointerException e) {
		
		}
		
		boolean a = emptyList.add(67);
		assertEquals("Add: check element added at the end", (Integer)67, emptyList.get(emptyList.size() - 1));
		assertEquals("Add: check size is correct ", 1, emptyList.size());
		try {
			emptyList.get(emptyList.size() - 2);
			fail("Index out of bounds.");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		
		list1.add(86);
		assertEquals("Add: check element added at the end", (Integer)86, list1.get(list1.size() - 1));
		assertEquals("Add: check element before is correct ", (Integer)42, list1.tail.prev.data);
		assertEquals("Add: check size is correct ", 4, list1.size());
		
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		int a = list1.size();
		assertEquals("Remove: check a is correct ", 3, a);
		
		int b = longerList.size();
		assertEquals("Remove: check b is correct ", 10, b);
		
		int c = shortList.size();
		assertEquals("Remove: check c is correct ", 2, c);
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		try {
			longerList.add(null);
			fail("Null pointer exception");
		}
		catch (NullPointerException e) {
		
		}
		
		try {
			shortList.add(-1, "Z");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		try {
			longerList.add(11, 90);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		list1.add(3, 86);
		assertEquals("Add: check element added at the end", (Integer)86, list1.get(list1.size() - 1));
		assertEquals("Add: check element before is correct ", (Integer)42, list1.tail.prev.data);
		assertEquals("Add: check size is correct ", 4, list1.size());
		
		longerList.add(LONG_LIST_LENGTH / 2, 56);
		int index = LONG_LIST_LENGTH / 2;
		assertEquals("Add: check if the element is correct ", (Integer)56, longerList.get(index));
		assertEquals("Add: check element ceil(middle + 1) is correct ", (Integer)5, longerList.get(index + 1));
		LLNode<Integer> temp = longerList.head;
		for (int i = 0; i < index; i++)
			temp = temp.next;
		assertEquals("Add: check if previous element (middle - 1) is correct ", (Integer)4, temp.prev.data);
		assertEquals("Add: check size is correct ", 11, longerList.size());
		
		longerList.add(0, 86);
		assertEquals("Add: check element added at the start", (Integer)86, longerList.get(0));
		assertEquals("Add: check element added at the start", (Integer)86, longerList.head.next.prev.data);
		assertEquals("Add: check element after is correct ", (Integer)0, longerList.head.next.data);
		assertEquals("Add: check size is correct ", 12, longerList.size());
		
		
	}
		

	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		try {
			shortList.set(0, null);
			fail("Null pointer exception");
		}
		catch (NullPointerException e) {
		
		}
		
		try {
			shortList.set(-1, "V");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			longerList.set(11, 90);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
	    
	}
	
	

}
