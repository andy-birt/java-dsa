class HashTable<K,V> {

    private Node<K,V>[] nodes;

    private float loadFactor;

    private int DEFAULT_SIZE = 10;

    private float DEFAULT_LOAD_FACTOR = 0.75f;

    @SuppressWarnings("unchecked")
    public HashTable() {
        nodes = (Node<K,V>[]) new Node[DEFAULT_SIZE];
        loadFactor = DEFAULT_LOAD_FACTOR;
    }

    @SuppressWarnings("unchecked")
    public HashTable(int initCapacity) {
        nodes = (Node<K,V>[]) new Node[initCapacity];
        loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public int size() {
        // Iterate the nodes and find everything that isn't null
        int count = 0;
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                count++;
            }
        }
        return count;
    }
    
    public Node<K,V> get(K key) {
        // Get the index based on the key
        // If the node is null or the input key doesn't match the found value then return null
        Node<K,V> node = nodes[this.getIndex(key)];
        if (node != null && key.equals(node.key)) {
            return node;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public void put(K key, Node<K,V> node) {
        // If node array is not big enough to fit new value then increase the size
        // Place the node in the array with the key
        if (isNodesFull()) {
            // System.out.println("nodes full when adding: "+key);
            Node<K,V>[] oldNodes = nodes;
            this.nodes = (Node<K,V>[]) new Node[nodes.length * 2];
            for (Node<K,V> repNode: oldNodes) {
                // System.out.println(repNode);
                if (repNode != null) {
                    int newIndex = getIndex(repNode.key);
                    System.out.println(newIndex);
                    nodes[newIndex] = repNode;
                }
            }
            nodes[getIndex(key)] = node;
        } else {
            nodes[getIndex(key)] = node;
        }
    }

    public void remove(K key) {
        // Remove the value based on key
        nodes[getIndex(key)] = null;
    }

    public void removeAll() {
        // Set all nodes to null, basically an empty HashTable
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = null;
        }
    }

    private int getIndex(K key) {
        // Generate hashcode to find our node array index
        System.out.println(key);
        return key.hashCode() % nodes.length;
    }

    private double getLoadFactor() {
        return (double)size() / nodes.length;
    }

    private boolean isNodesFull() {
        return getLoadFactor() > loadFactor;
    }
    
    public static void main(String[] args) {
        HashTable<String, String> ht = new HashTable<String, String>();
        //------------- Test adding one value then remove then add again with different value
        // ht.put("123", new Node<String, String>("123", "testing"));
        // System.out.println(ht.get("123").value);
        // System.out.println(ht.size());

        // ht.remove("123");
        // System.out.println(ht.get("123"));
        // System.out.println(ht.size());

        // ht.put("123", new Node<String, String>("123","testing again"));
        // System.out.println(ht.get("123").value);
        // System.out.println(ht.size());

        // //------------- Test adding 10 values

        // for (int i = 0; i < 10; i++) {
        //     String key = "key test "+i;
        //     ht.put(key, new Node<String, String>(key, "testing "+i));
        // }

        // for (int i = 0; i < 10; i++) {
        //     // expect values
        //     String key = "key test "+i;
        //     System.out.println(ht.get(key).value);

        //     // expect nulls
        //     System.out.println(ht.get("key "+i));
        // }

        // // Remove all values;
        // ht.removeAll();

        // System.out.println(ht.size());

        //------------- Test adding more than 10 values

        for (int i = 0; i < 20; i++) {
            String key = "key test "+i;
            ht.put(key, new Node<String, String>(key, "testing "+i));
        }

        // for (int i = 0; i < 20; i++) {
        //     if (ht.nodes[i] == null) {
        //         System.out.println("null");
        //     } else {
        //         System.out.println(ht.nodes[i].value);
        //     }
        // }

        // for (int i = 0; i < 20; i++) {
        //     // expect values
        //     String key = "key test "+i;
        //     System.out.println(ht.get(key).value);

        //     // expect nulls
        //     System.out.println(ht.get("key "+i));
        // }

    }

    public static class Node<K, V> {

        public K key;
        public V value;

        public Node(K key, V val) {
            this.key = key;
            this.value = val;
        }
    }
}
