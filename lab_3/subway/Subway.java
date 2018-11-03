/**
 * Name		:Zhou Litian
 * Matric. No	:A0174913B
 * PLab Acct.	:xxoo
 */

import java.util.*;

public class Subway {
	private DoublyLinkedList<String> stations;

	public Subway() {
		//constructor
		stations = new DoublyLinkedList<String>();
	}

	public void run() {
		//implement your "main" method here
		Scanner sc = new Scanner(System.in);
		int station_num = sc.nextInt();
		for(int i=0;i<station_num;i++)
			stations.addFirst(sc.next());
		stations.getHead().setPrev(stations.getTail());
		stations.getTail().setNext(stations.getHead());

		System.out.println("checkPoint26");		
		int query_num = sc.nextInt();
		for(int q=0;q<query_num;q++){
			String query = sc.next();	

			if(query.equals("TIME")){
				String sta = sc.next();
				String end = sc.next();
				if(clock(stations, sta, end) >= aclock(stations, sta, end))
					System.out.println(clock(stations, sta, end));
				else
					System.out.println(aclock(stations, sta, end));
			}

			if(query.equals("BUILD")){
				String proSta = sc.next();
				String newSta = sc.next();
				ListNode<String> curr = stations.getHead();
				ListNode<String> sta = new ListNode<String>(newSta);
				for(int i=0;i<stations.size();i++){
					if(curr.getElement().equals(proSta)){
						ListNode<String> nextSta = curr.getNext();
						stations.setHead(nextSta);
						System.out.println(stations.getHead().getElement());
						stations.addFirst(sta.getElement());
						sta.setPrev(curr);
						sta.setNext(nextSta);
						curr.setNext(sta);
						nextSta.setPrev(sta);
						break;
					}
				}
			}

			if(query.equals("PATH")){
				String start = sc.next();
				String ending = sc.next();
				if(clock(stations, start, ending)>aclock(stations, start, ending)){
					aprint(start, ending);			
				}
				else print(start, ending);
			}
		}
	}
	public void aprint(String start, String ending){
		ListNode<String> curr = stations.getHead();
		String space = "";
		for(int i=0;i<stations.size();i++){
			if(curr.getElement().equals(start)){
				break;
			}
			else{
				curr = curr.getNext();
			}
		}
		for(int i=0; i<stations.size();i++){
			while(!curr.getElement().equals(ending)){
				System.out.print(space+curr.getElement());
				space = " ";
				curr = curr.getPrev();
			}
		}
	}

	public void print(String start, String ending){
		ListNode<String> curr = stations.getHead();
		String space = "";
		for(int i=0;i<stations.size();i++){
			if(curr.getElement().equals(start)){
				break;
			}
			else{
				curr = curr.getNext();
			}
		}
		for(int i=0; i<stations.size();i++){
			while(!curr.getElement().equals(ending)){
				System.out.print(space+curr.getElement());
				space = " ";
				curr = curr.getNext();
			}
		}
	}

	public void print(String start){
		ListNode<String> curr = stations.getHead();
		String space = "";
		for(int i=0;i<stations.size();i++){
			if(curr.getElement().equals(start)){
				break;
			}
			else{
				curr = curr.getNext();
			}
		}
		for(int i=0; i<stations.size();i++){
			while(!curr.getElement().equals(start)){
				System.out.print(space+curr.getElement());
				space = " ";
				curr = curr.getNext();
			}
		}
	}

	public int clock(DoublyLinkedList<String> stations, String start, String ending){	
		ListNode<String> curr = stations.getHead();
		ListNode<String> sta = null;
		for(int i=0;i<stations.size();i++){
			if(curr.getElement().equals(start)){
				sta = curr;
				break;
			}
			curr = curr.getNext();
		}
		int count = 0;
		if(sta.getElement().equals(ending))
			return 0;
		else{
			while(!sta.getElement().equals(ending)){
				sta = sta.getNext();
				count++;
			}
			return (count*2+(count-1));
		}
	}


	public int aclock(DoublyLinkedList<String> stations, String start, String ending){	
		ListNode<String> curr = stations.getHead();
		ListNode<String> sta = null;
		for(int i=0;i<stations.size();i++){
			if(curr.getElement().equals(start)){
				sta = curr;
				break;
			}
			curr = curr.getNext();
		}
		int count = 0;
		if(sta.getElement().equals(ending))
			return 0;
		else{
			while(!sta.getElement().equals(ending)){
				sta = sta.getPrev();
				count++;
			}
			return (count*2+(count-1));
		}
	}
	public static void main(String[] args) {
		Subway newSubwayNetwork = new Subway();
		newSubwayNetwork.run();
	}
}

class DoublyLinkedList<E> {

	//Data attributes
	private ListNode<E> head;
	private ListNode<E> tail;
	private int size;

	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public void setHead(ListNode<E> newHead){
		head = newHead;
		tail = newHead.getPrev();
	}

	// returns the size of the linked list
	public int size() {
		return this.size;
	}

	// returns true if the list is empty, false otherwise
	public boolean isEmpty() {
		return this.size == 0;
	}

	// adds the specified element to the beginning of the list
	public void addFirst(E element) {
		ListNode<E> newNode = new ListNode<E>(element);

		if (size == 0) {
			this.head = newNode;
			this.tail = this.head;
		} else {
			ListNode<E> oldHead = this.head;
			this.head = newNode;
			newNode.setNext(oldHead);
			oldHead.setPrev(newNode);
		}

		this.size++;
	}

	// retrieves the first element of the list
	public E getFirst() throws NoSuchElementException {
		if (head == null) {
			throw new NoSuchElementException("Cannot get from an empty list");
		} else {
			return head.getElement();
		}
	}

	// returns true if the list contains the element, false otherwise
	public boolean contains(E element) {
		for (ListNode<E> current = head; current != null; current = current.getNext()) {
			if (current.getElement().equals(element)) {
				return true;
			}
		}

		return false;
	}

	// removes the first element in the list
	public E removeFirst() throws NoSuchElementException {
		if (head == null) {
			throw new NoSuchElementException("Cannot remove from an empty list");
		} else {
			ListNode<E> currentHead = head;
			head = head.getNext();
			if (head == null) {
				tail = null;
			} else {
				head.setPrev(null);
			}
			this.size--;
			return currentHead.getElement();
		}
	}

	// Returns reference to first node.
	public ListNode<E> getHead() {
		return this.head;
	}

	// Returns reference to last node of list.
	public ListNode<E> getTail() {
		return this.tail;
	}

}

class ListNode<E> {
	private E element;
	private ListNode<E> next;
	private ListNode<E> prev;

	public ListNode(E newElement) {
		this.element = newElement;
		this.next = null;
		this.prev = null;
	}

	public void setElement(E newElement) {
		this.element = newElement;
	}

	public E getElement() {
		return this.element;
	}

	public void setPrev(ListNode<E> previous) {
		this.prev = previous;
	}

	public void setNext(ListNode<E> next) {
		this.next = next;
	}

	public ListNode<E> getNext() {
		return next;
	}

	public ListNode<E> getPrev() {
		return prev;
	}
}
