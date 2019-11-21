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
public class TableMap <K, V> implements Serializable{
private class Node<K, V> implements Serializable {
        
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
         *Constructor of the Node class for the dictionary.
         * @param key 
         * @param value 
         */
        
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
        
        /**
         * Constructor of the Node class for the dictionary.
         * @param key 
         * @param value 
         * @param next
         */
        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
        
        /**
         * Gets value
         * @return 
         */
        private V getValue() {
            return this.value;
        }

        /**
         * Sets value
         * @param value 
         */
        private void setValue(V value) {
            this.value = value;
        }

        /**
         * Sets key
         * @param key 
         */
        private void setKey(K key){
            this.key =key;
        }
        
        /**
         * Gets next
         * @return 
         */
        private Node<K, V> getNext() {
            return this.next;
        }

        /**
         * Sets next
         * @param next 
         */
        private void setNext(Node<K, V> next) {
            this.next = next;
        }
    }
    //atributes
    private Node<K, V> head;
    private Node<K, V> tail;
    private Node<K, V> current;
    private int size;
    
    /**
     * Constructor 
     */
    public TableMap() {
        this.head = new Node<>();
        this.tail = this.head;
        this.current=head;
        this.size = 0;
    }

    /**
     * Method to add a key to the dictionary.
     * @param key
     * @param value 
     * @key and value, generics
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
     * Shows the name of the referenced key.
     * @return Nombre del key.
     */
    
    public String getKey() {
        String strKey = ""+this.current.getNext().key;
        return strKey;
    }
    
    /**
     * Modify the name of the key reference.
     * @param nombre
     * @return
     */
    public K renameKey(K nombre) {
        this.current.setKey(nombre);
        return nombre;
    }
    
    /**
     *Shows the value of the referenced key.
     * @param key 
     * @return 
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
     * Modifies key's value
     * @param key 
     * @param list 
     * @return 
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
     * deletes key on the dictionary
     * @param key
     * @return true: can move forward, false: can't move forward
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
