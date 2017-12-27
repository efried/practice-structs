package org.friedenberg.structures;

public class SinglyLinkedList<E> implements Cloneable{
	
	private static class Node<E> {
		private E element;
		private Node<E> next;
		public Node(E e, Node<E> next){
			this.element = e;
			this.next = next;
		}
		public E getElement() {
			return element;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
	}
	
	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;
	
	public SinglyLinkedList(){};
	
	public int getSize() {
		return size;
	}
	public boolean isEmpty() {
		return size==0;
	}
	public E first() {
		if(isEmpty()){
			return null;
		}
		return head.getElement();
	}
	public E last() {
		if(isEmpty()){
			return null;
		}
		return tail.getElement();
	}
	public void addFirst(E e) {
		head = new Node<>(e, head);
		if(isEmpty()){
			tail = head;
		}
		size++;
	}
	public void addLast(E e) {
		Node<E> newest = new Node<>(e, null);
		if(isEmpty()){
			head = newest;
		}
		else {
			tail.setNext(newest);
		}
		tail = newest;
		size++;
	}
	public E removeFirst() {
		if(isEmpty()){
			return null;
		}
		E newFirst = head.getElement();
		head = head.getNext();
		size--;
		if(isEmpty()){
			tail = null;
		}
		return newFirst;
	}
	
	public boolean equals(Object o){
		if (o == null)
			return false;
		if (o.getClass() != this.getClass())
			return false;
		SinglyLinkedList other = (SinglyLinkedList) o;
		if (this.size != other.size)
			return false;
		Node walkA = this.head;
		Node walkB = other.head;
		while(walkA != null){
			if(!walkA.getElement().equals(walkB.getElement()))
				return false;
			walkA = walkA.getNext();
			walkB = walkB.getNext();
		}
		return true;
	}
	
	public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
		SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone();
		if (size > 0) {
			other.head = new Node<>(head.getElement(), null);
			Node<E> walk = head.getNext();
			Node<E> otherTail = other.head;
			while (walk != null) {
				Node<E> newest = new Node<>(walk.getElement(), null);
				otherTail.setNext(newest);
				otherTail = newest;
				walk = walk.getNext();
			}
		}
		return other;
	}
}
