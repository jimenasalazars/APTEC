/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author samue
 */
public class LinkedList  <T> {
    public class Node<T> {
    //atributos
		
		public T element;
		public Node<T> next;
		
		//Constructores
		public Node() {
			this.element = null;
			this.next = null;
		}
		
		public Node(T element) {
			this.element = element;
			this.next = null;
		}
		
		public Node(T element, Node<T> next) {
			this.element = element;
			this.next = next;
		}
		
		//métodos
		
		public T getElement() {
			return this.element;
		}
		
		public void setElement(T element) {
			this.element = element;
		}
		
		public Node getNext() {
			return this.next;
		}
		
		public void setNext(Node<T> next) {
			this.next = next;	
		}
    
}
    //atributos LinkedList
	private Node<T> head;
	private Node<T> current;
	private Node<T> tail;
	private int position;
	private int size;
	
	//constructores LinkedList
	
	/**
	 * Contructor predeterminado
	 */
	public LinkedList() {
		this.head = new Node<T>();
		this.current = this.head;
		this.tail = this.head;
		this.size = 0;
		this.position = -1;
	}
	
	/**
	 * Construye una lista con base en otra lista
	 * Nota: Hacer deep-copy, no shallow copy
	 * TAREA MORAL: Investigar deep-copy vs. shallow copy, terminar método
	 * @param lista lista de la cual se van a copiar sus elementos
	 */


    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public Node<T> getCurrent() {
        return current;
    }

    public void setCurrent(Node<T> current) {
        this.current = current;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }
        
        public void setPosition(int position) {
            this.position = position;
        }
        
        public int getposition(){
            return this.position;
        }
        public int getsize(){
            return this.size;
        }
        public Object getfarmacia(){
            return this.current.getElement();
        }
	
	/**
	 * Agrega un nuevo elemento a la lista
	 * @param element El elemento a agregar
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
	
	public void append(T element) {
		//siempre se pega al final de la lista
		Node<T> newNode = new Node(element);
		this.tail.setNext(newNode);
		this.tail = newNode;
		this.size++;
	}
	
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
        
	public void clear() {
		this.head = this.tail = this.current = new Node();
		this.position = -1;
		this.size = 0;
	}
	
	public Object getElement(){
		return this.current.getElement();
	}	
	
	public int getSize() {
		return this.size;
	}
	
	public boolean next() {
		if (this.current == this.tail) {
			//System.out.println("End");
		}
		this.current = this.current.getNext();
		this.position++;
		return true;
	}
	
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
	
	public int getPosition() {
		return this.position;	
	}
	
	public void goToStart(){
		this.current = this.head;
		this.position = -1;
	}
	
	public void goToEnd(){
		this.current = this.tail;
		this.position = this.size - 1;
	}
	
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
	
	/**
	 * Devuelve la representaci�n en String de la lista
	 * @return un string representado la lista
	 */
	
    
}
