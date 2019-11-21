/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.Serializable;

/**
 *
 * @author Samuel and Jimena
 */
public class LinkedList  <T> implements Serializable {
    public class Node<T> implements Serializable{
    //atributes
		
		public T element;
		public Node<T> next;
		
		//Constructors
                /**
                 * Default constructor
                 */
		public Node() {
			this.element = null;
			this.next = null;
		}
		/**
                 * 
                 * @param element 
                 */
		public Node(T element) {
			this.element = element;
			this.next = null;
		}
		/**
                 * 
                 * @param element
                 * @param next 
                 */
		public Node(T element, Node<T> next) {
			this.element = element;
			this.next = next;
		}
		
		//métodos
		/**Retrive the element
                 * 
                 * @return 
                 */
		public T getElement() {
			return this.element;
		}
		/**
                 * 
                 * @param element 
                 */
		public void setElement(T element) {
			this.element = element;
		}
		/**
                 * 
                 * @return 
                 */
		public Node getNext() {
			return this.next;
		}
		/**
                 * 
                 * @param next 
                 */
		public void setNext(Node<T> next) {
			this.next = next;	
		}
    
}
    //LinkedList's atributes 
	private Node<T> head;
	private Node<T> current;
	private Node<T> tail;
	private int position;
	private int size;
	
	//constructores LinkedList
	
	/**
	 * Default constructor
	 */
	public LinkedList() {
		this.head = new Node<T>();
		this.current = this.head;
		this.tail = this.head;
		this.size = 0;
		this.position = -1;
	}

/**
 * 
 * @return 
 */
    public Node<T> getHead() {
        return head;
    }
    
    /**
     * 
     * @param head 
     */
    public void setHead(Node<T> head) {
        this.head = head;
    }

    /**
     * 
     * @return 
     */
    public Node<T> getCurrent() {
        return current;
    }

    /**
     * 
     * @param current 
     */
    public void setCurrent(Node<T> current) {
        this.current = current;
    }

    /**
     * 
     * @return 
     */
    public Node<T> getTail() {
        return tail;
    }

    /**
     * 
     * @param tail 
     */
    public void setTail(Node<T> tail) {
        this.tail = tail;
    }
        
    /**
     * 
     * @param position 
     */
        public void setPosition(int position) {
            this.position = position;
        }
        
        /**
         * 
         * @return 
         */
        public int getposition(){
            return this.position;
        }
        
        /**
         * 
         * @return 
         */
        public int getsize(){
            return this.size;
        }
        
        /**
         * 
         * @return 
         */
        public Object getfarmacia(){
            return this.current.getElement();
        }
	
	/**
	 * Adds a new element to the list
	 * @param element 
         * @restrictions no
	 */
	public void insert(T element) {
           //insertar en cualquier posici�n
            Node<T> newNode = new Node(element, this.current.getNext());
            this.current.setNext(newNode);
            this.current = newNode;
            this.setPosition(position+1);
            
    //necesario si se est� insertando al final de la lista
            if (this.current == this.tail) {
               this.tail = tail.getNext();
            }
            this.size++;

	}
	
        /**
         * 
         * @param element 
         */
	public void append(T element) {
		//siempre se pega al final de la lista
		Node<T> newNode = new Node(element);
		this.tail.setNext(newNode);
		this.tail = newNode;
		this.size++;
	}
	/**
         * Deletes
         */
	public void remove() {
            //verificar que la lista no est� vac�a
        if ((this.head == this.current) && (this.head == this.tail)) {
            return;
        } //tambi�n if (this.size == 0) ...

        //en temp se va a almacenar el nodo ANTERIOR al que se quiere borrar
        Node<T> temp = this.head;
        while (temp.getNext() != this.current) {
            temp = temp.getNext();
        }
        //borrar la referencia al nodo actual
        temp.setNext(this.current.getNext());
        //necesario si se esta borrando el �ltimo nodo
        if (this.current == this.tail) {
            this.current = this.tail = temp;
            this.position--;
        } else //necesario si se est� borrando un nodo diferente al �ltimo
        
            this.current = this.current.getNext();
        

        //disminuir el tama�o
        this.size--;
    }
        
        /**
         * Start again
         */
	public void clear() {
		this.head = this.tail = this.current = new Node();
		this.position = -1;
		this.size = 0;
	}
	
        /**
         * return an element
         * @return 
         */
	public Object getElement(){
		return this.current.getElement();
	}	
	
        /**
         * 
         * @return 
         */
	public int getSize() {
		return this.size;
	}
	
        /**
         * returns next
         * @return 
         */
	public boolean next() {
		if (this.current == this.tail) {
			//System.out.println("End");
		}
		this.current = this.current.getNext();
		this.position++;
		return true;
	}
	
        /**
         * returens previous
         * @return 
         */
	public boolean previous() {
		if (this.current == this.head) {
			System.out.println("Actualmente en primer nodo, no se puede retroceder");
			return false;
		}
		Node temp = head;
		this.position = -1;
		while (temp.getNext() != this.current) {
			temp = temp.getNext();
			this.position++;
		}
		this.current = temp;
		return true;
	}
	
        /**
         * gets the position of the element
         * @return 
         */
	public int getPosition() {
		return this.position;	
	}
	
        /**
         * reubicate
         */
	public void goToStart(){
		this.current = this.head;
		this.position = -1;
	}
	
        /**
         * go to end
         */
	public void goToEnd(){
		this.current = this.tail;
		this.position = this.size - 1;
	}
	
        /**
         * goes to pos
         * @param pos 
         */
	public void goToPos(int pos) {
		if (pos < 0 || pos >= this.size) {
			System.out.println("Posici�n inv�lida");
			return;
		}
		if (pos > this.position) {
			while (pos > this.position) {
				this.next();
			}
		} else if (pos < this.position) {
			while (pos < this.position) {
				this.previous();
			}
		}
	}
	
        /**
         * 
         * @param element
         * @return 
         */
	public int getPositionOfElement(Object element) {
		Node tempNode = this.head;
		int positions = -1;
		while (tempNode != null) {
			if (tempNode.getElement() != null && tempNode.getElement().equals(element)){
				return positions;
			}
			tempNode = tempNode.getNext();
			positions++;
		}
		return -1;
	}
	
	
    
}
