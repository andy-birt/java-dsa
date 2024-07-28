import java.util.ArrayList;

class FirstHashTable<K,V> {

    ArrayList<Node<V>> al;

    public FirstHashTable() {
        al = new ArrayList<Node<V>>();
    }

    public Integer hashCode(K key) {
        return key.hashCode()%10;
    }

    
    // public static void main(String[] args) {
    //     // Create 2 hashtables
    //     HashTable<Integer, String> ht = new HashTable<Integer, String>();
    //     HashTable<String, Integer> ht2 = new HashTable<String, Integer>();

    //     // Initialize the capacity
    //     for (int i = 0; i < 10; i++) {
    //         ht.al.add(null);
    //         ht2.al.add(null);
    //     }
        
    //     // Hashtable 1 key/value
    //     Integer key = 1234;
    //     String val = "hey";

    //     // Hashtable 2 key/value
    //     String key2 = "1234";
    //     Integer val2 = 999;

    //     // Generate hashcode for each key in each table
    //     Integer hashCode = ht.hashCode(key);
    //     Integer hashCode2 = ht2.hashCode(key2);

    //     // Set the value for the key in hashtable 1
    //     ht.al.set(hashCode, new Node<String>(val));
    //     System.out.println(ht.al.get(hashCode).value);
    //     System.out.println(hashCode);

    //     // Set the value for the key in hashtable 2
    //     ht2.al.set(hashCode2, new Node<Integer>(val2));
    //     System.out.println(ht2.al.get(hashCode2).value);
    //     System.out.println(hashCode2);
    // }

    public static class Node<E> {

        public E value;

        public Node(E v) {
            this.value = v;
        }
    }
}