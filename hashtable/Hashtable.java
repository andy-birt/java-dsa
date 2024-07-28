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
        int index = getIndex(key);
        int stepSize = secondaryHash(key);
        while (nodes[index] != null && !key.equals(nodes[index].key)) {
            index = (index + stepSize) % nodes.length;
        }
        if (nodes[index] != null && key.equals(nodes[index].key)) {
            return nodes[index];
        }
        return null;
    }

    public void put(K key, Node<K,V> node) {
        // If node array is not big enough to fit new value then increase the size
        // Place the node in the array with the key
        if (isTimeToResize()) {
            resize();
        } 
        int index = getIndex(key);
        int stepSize = secondaryHash(key);
        int probes = 0;
        while (nodes[index] != null) {
            if (++probes > nodes.length) {
                resize();
                put(key, node);
                return;
            }
            index = (index + stepSize) % nodes.length;
        }
        nodes[index] = node;    
    }

    public void remove(K key) {
        // Remove the value based on key
        int index = getIndex(key);
        int stepSize = secondaryHash(key);
        while (nodes[index] != null && !key.equals(nodes[index].key)) {
            index = (index + stepSize) % nodes.length;
        }
        if (nodes[index] != null && key.equals(nodes[index].key)) {
            nodes[index] = null;
        }
    }

    public void removeAll() {
        // Set all nodes to null, basically an empty HashTable
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = null;
        }
    }

    private int getIndex(K key) {
        // Generate hashcode to find our node array index
        return Math.abs(key.hashCode() % nodes.length);
    }

    private int secondaryHash(K key) {
        return 1 + Math.abs(key.hashCode() % (nodes.length - 1));
    }

    private double getLoadFactor() {
        return (double)size() / nodes.length;
    }

    private boolean isTimeToResize() {
        return getLoadFactor() > loadFactor;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K,V>[] oldNodes = nodes;
        this.nodes = (Node<K,V>[]) new Node[nodes.length * 2];
        for (Node<K,V> repNode: oldNodes) {
            if (repNode != null) {
                int newIndex = getIndex(repNode.key);
                int stepSize = secondaryHash(repNode.key);
                if (nodes[newIndex] != null) {
                    while (nodes[newIndex] != null) {
                        newIndex = (newIndex + stepSize) % nodes.length;
                    }
                }
                nodes[newIndex] = repNode;
            }
        }
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

        for (int i = 0; i < 20; i++) {
            if (ht.nodes[i] == null) {
                System.out.println("null");
            } else {
                System.out.println(ht.nodes[i].value);
            }
        }

        for (int i = 0; i < 20; i++) {
            // expect values
            String key = "key test "+i;
            Node<String, String> node = ht.get(key);
            System.out.println(node.value);

            // expect nulls
            System.out.println(ht.get("key "+i));
        }

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
