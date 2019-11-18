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
public class TableMap <K, V>{
private class Node<K, V> {
        
        private K key;
        private V value;
        private Node<K, V> next;

        /**
         * Constructor de la clase Nodo para el Diccionario.
         */
        
        public Node () {
            this.key = null;
            this.value = null;
            this.next = null;
        }

        /**
         * Constructor de la clase Nodo para el diccionario.
         * @param key Nombre de la referencia almacenada en diccionario.
         * @param value Valor de la referencia almacenada en diccionario.
         */
        
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
        
        /**
         * Constructor de la clase Nodo para el diccionario.
         * @param key Nombre de la referencia almacenada en diccionario.
         * @param value Valor de la referencia almacenada en diccionario.
         * @param next Nodo de la referencia al siguiente Nodo.
         */

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
        
        private V getValue() {
            return this.value;
        }

        private void setValue(V value) {
            this.value = value;
        }

        private void setKey(K key){
            this.key =key;
        }
        
        private Node<K, V> getNext() {
            return this.next;
        }

        private void setNext(Node<K, V> next) {
            this.next = next;
        }
    }
    private Node<K, V> head;
    private Node<K, V> tail;
    private Node<K, V> current;
    private int size;
    
    /**
     * Constructor de la clase diccionario.
     */
    
    public TableMap() {
        this.head = new Node<>();
        this.tail = this.head;
        this.current=head;
        this.size = 0;
    }

    /**
     * Metodo para agregar un key al diccionario.
     * @param key Nombre de la referencia.
     * @param value Valor que almacena dicha referencia.
     */
    
    public void put (K key, V value) {
        if (this.size==0) {
            Node<K, V> theNode = new Node<>(key, value);
            this.head.setNext(theNode);
            theNode.setNext(this.tail);
        }else {
            Node<K, V> theNode = new Node<>(key, value, this.current.getNext());
            this.current.setNext(theNode);
        }
        //necesario si se está insertando al final de la lista
        if (this.current == this.tail) {
            this.tail = tail.getNext();
        }
        this.size++;
    }
    
    /**
     * Muestra el nombre del key referenciado.
     * @return Nombre del key.
     */
    
    public String getKey() {
        String strKey = ""+this.current.getNext().key;
        return strKey;
    }
    
    /**
     * Modifica el nombre de la referencia del key.
     * @param nombre Nombre con el que se quiere modificar.
     * @return Devuelve el nombre del key referenciado.
     */
    
    public K renameKey(K nombre) {
        this.current.setKey(nombre);
        return nombre;
    }
    
    /**
     * Muestra el valor del key referenciado.
     * @param key El nombre del key del que se quiere obtener el valor.
     * @return Devuelve el valor o no devuelve nada si no lo encuentra.
     */
    
    public V get(K key) {
        if ((this.head == this.tail)){
            System.out.println("Map is empty");
            return null;
        }
        Node<K, V> temp = head;
        for (int i = -1; i< this.size; i++) {
            if (temp.key == key) {
                return temp.getValue();
            }
            temp = temp.getNext();
        }
        return null;
    }
    
    /**
     * Modifica el valor del key.
     * @param key Nombre de la referencia que se quiere cambiar de valor.
     * @param list Lista que se quiere agregar como valor de referencia.
     * @return Devuelve la lista que se agrego como valor, o no devuelve nada si no encuentra el key referenciado.
     */
    
    public V setValue(String key, V list){
        if ((this.head == this.tail)){
            System.out.println("Map is empty");
        }
        Node<K, V> temp = head;
        for (int i = -1; i< this.size; i++) {
            if (temp.key == key) {
                temp.setValue(list);
                return list;
            }
            temp = temp.getNext();
        } return null;
    }
    
    /**
     * Metodo para borrar un key del diccionario.
     * @param key El nombre del key que se quiere eliminar.
     * @return true: si se puede avanzar, false: si no se puede avanzar.
     */
    
    public boolean remove (K key) {
        if (this.head == this.tail) {
            System.out.println("Map is empty");
        }
        Node<K, V> temp = this.head.getNext();
        Node<K, V> ant = this.head;
        for (int i = 0; i<this.size; i++) {
            if (temp.key == key) {
                ant.setNext(temp.getNext());
                return true;
            }
            temp = temp.getNext();
            ant = ant.getNext();
        }
        this.size--;
        return false;
    }
}
