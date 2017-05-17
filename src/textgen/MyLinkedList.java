package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	
	/** Create a new empty LinkedList */
	public MyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if (element == null)
			throw new NullPointerException("Value to be added cannot be null.");
		LLNode<E> elementToAdd = new LLNode(element);
		if (this.size == 0)
		{
			this.head = elementToAdd;
			this.tail = elementToAdd;
		}
		else {
			this.tail.next = elementToAdd;
			elementToAdd.prev = this.tail;
			this.tail = elementToAdd;
		}
		this.size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if (index >= this.size || index < 0)
			throw new IndexOutOfBoundsException("Index out of bounds!.");
		int count = 0;
		LLNode<E> temp = this.head; 
		while (count < index)
		{
			temp = temp.next;
			count++;
		}
		return temp.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if (element == null)
			throw new NullPointerException("Value to be added cannot be null.");
		if (index > this.size || index < 0)
			throw new IndexOutOfBoundsException("Index out of bounds!.");
		LLNode<E> elementToAdd = new LLNode(element);
		if (this.size == 0)
		{
			this.head = elementToAdd;
			this.tail = elementToAdd;
		}
		else if (index == this.size){
			this.tail.next = elementToAdd;
			elementToAdd.prev = this.tail;
			this.tail = elementToAdd;
		}
		else if (index == 0){
			elementToAdd.next = this.head;
			this.head.prev = elementToAdd;
			this.head = elementToAdd;
		}
		else {
			int count = 0;
			LLNode<E> temp = this.head; 
			while (count < index)
			{
				temp = temp.next;
				count++;
			}
			elementToAdd.next = temp;
			elementToAdd.prev = temp.prev;
			temp.prev.next = elementToAdd;
			temp.prev = elementToAdd;
		}
		this.size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if (index >= this.size || index < 0)
			throw new IndexOutOfBoundsException("Index out of bounds!.");
		
		E returnValue;
		if (this.size == 1)
		{
			returnValue = this.head.data;
			this.head = null;
			this.tail = this.head;
		}
		else if (index == this.size - 1){
			returnValue = this.tail.data;
			this.tail = this.tail.prev;
			this.tail.next.prev = null;
			this.tail.next = null;
		}
		else if (index == 0){
			returnValue = this.head.data;
			this.head = this.head.next;;
			this.head.prev.next = null;
			this.head.prev = null;
		}
		else {
			int count = 0;
			LLNode<E> temp = this.head; 
			while (count < index)
			{
				temp = temp.next;
				count++;
			}
			returnValue = temp.data;
			temp.prev.next = temp.next;
			temp.next.prev = temp.prev;
		}
		this.size--;
		return returnValue;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (element == null)
			throw new NullPointerException("Value to be added cannot be null.");
		
		if (index >= this.size || index < 0)
			throw new IndexOutOfBoundsException("Index out of bounds!.");
		
		int count = 0;
		LLNode<E> temp = this.head; 
		while (count < index)
		{
			temp = temp.next;
			count++;
		}
		E returnValue = temp.data;
		temp.data = element;
		return returnValue;
	}   
	   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	
	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
