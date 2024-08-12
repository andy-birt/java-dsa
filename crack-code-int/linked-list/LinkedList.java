import java.util.HashSet;

class LinkedList {

    Node head;
    int length;

    public LinkedList() {
        this.head = null;
        this.length = 0;
    }

    public LinkedList(int d) {
        this.head = new Node(d);
        this.length = 1;
    }

    public LinkedList(Node head) {
        this.head = head;
        this.length = getLength();
    }

    void appendNode(int d) {
        if (head == null) {
            head = new Node(d);
        } else {
            head.append(d);
        }
    }

    int getLength() {
        int length = 0;
        Node n = head;
        while (n != null) {
            n = n.next;
            length++;
        }
        return length;
    }

    LinkedList reverseList() {
        Node h = null;
        Node c = head;
        while (c != null) {
            Node n = new Node(c.data);
            n.next = h;
            h = n;
            c = c.next;
        }
        return new LinkedList(h);
    }

    

    // remove duplicates from an unsorted linked list
    // FOLLOW UP
    // How would you solve this problem if a temporary buffer is not allowed?
    void removeDupes() {
        Node n = head;
        Node prev = null;
        HashSet<Integer> set = new HashSet<Integer>();
        while (n != null) {
            if (set.contains(n.data)) {
                prev.next = n.next;
            } else {
                set.add(n.data);
                prev = n;
            }
            n = n.next;
        }
    }

    void removeDupesRunner() {
        Node n = head;
        while (n != null) {
            Node runner = n;
            while (runner.next != null) {
                if (runner.next.data == n.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            n = n.next;
        }
    }

    // not the exact middle but not the first or last
    // EXAMPLE
    // Input: the node c from the linked list a -> b -> c -> d -> e -> f
    // Output: nothing is returned but the new linked list looks like a-> b -> d -> e -> f
    void deleteMiddleNode() {
        int length = getLength();
        if (length == 0) return;
        int middle = (length / 2)-1;
        int count = 0;
        Node n = head;
        while (middle != count) {
            n = n.next;
            count++;
        }
        n.next = n.next.next;
    }

    // find the kth to last element in singly linked list
    Node getKthToLast(int k) {
        int length = getLength();

        if (k == length) return head;

        int count = 0;
        int target = length - k;
        Node targetNode = head;
        while (target != count) {
            targetNode = targetNode.next;
            count++;
        }

        return targetNode;
    }

    // partition a linked list around a value v such that all nodes
    // less than v come before all nodes greater than or equal to v
    // (IMPORTANT: The partition element v can appear anywhere in the "right partition";
    // it does not need to appear between the left and right partitions.
    // The additional spacing in the example below indicates the partition. Yes, the output below is one of many valid outputs!)
    // EXAMPLE
    // Input:   3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition=5]
    // Output:  3 -> 1 -> 2      ->      10 -> 5 -> 5 -> 8
    // I'll need to revisit this
    LinkedList partition(int v) {
        LinkedList lessThanValue = new LinkedList();
        LinkedList greaterThanOrEqualToValue = new LinkedList();

        // Populate distinguishing linked lists
        Node n = head;
        while (n.next != null) {
            if (n.data < v) {
                lessThanValue.appendNode(n.data);
            } else {
                greaterThanOrEqualToValue.appendNode(n.data);
            }
            n = n.next;
        }

        // Once the separate lists are populated put them together
        Node lessTail = lessThanValue.getTail();
        lessTail.next = greaterThanOrEqualToValue.head;
        return lessThanValue;
    }

    // EXAMPLE
    // Input: (7 -> 1 -> 6) + (5 -> 9 -> 2). That is, 617 + 295.
    // Output:  2 -> 1 -> 9. That is, 912.
    // FOLLOW UP
    // Suppose the digits are stored in forward order. Repeat the above problem.
    // EXAMPLE
    // Input: (6 -> 1 -> 7) + (2 -> 9 -> 5). That is, 617 + 295.
    // Output: 9 -> 1 -> 2. That is, 912.
    // Not allowed to convert linked list to integer
    Node sumLists(Node a, Node b) {
        return sumLists(a, b, 0);
    }

    Node sumLists(Node a, Node b, int carry) {
        if (a == null && b == null && carry == 0) {
            return null;
        }

        Node result = new Node();

        int value = carry;
        if (a != null) {
            value += a.data;
        } 
        if (b != null) {
            value += b.data;
        }

        result.data = value % 10;

        if (a != null || b != null) {
            Node more = sumLists(a == null ? null : a.next, b == null ? null : b.next, value >= 10 ? 1 : 0);
            result.next = more;
        }
        return result;
    }

    boolean isPalindrome() {
        return this.toString().equals(this.reverseList().toString());
    }

    Node intersection(int n) {
        return new Node(n);
    }

    Node findLoop() {
        return new Node(0);
    }

    Node getTail() {
        Node n = head;
        while (n.next != null) {
            n = n.next;
        }
        return n;
    }

    public String toString() {
        String s = "";
        Node n = head;
        while (n != null) {
            if (n.next != null) {
                s += n.data + " -> ";
            } else {
                s += n.data;
            }
            n = n.next;
        }
        return s;
    }

    public static void main(String[] args) {
        // Generate Linked List with values 1 - 10 and perform operations
        int val = 1;
        LinkedList list = new LinkedList(val);
        for (int i = val+1; i < 11; i++) {
            list.appendNode(i);
        }

        System.out.println(list.toString());

        list.deleteMiddleNode();

        System.out.println(list.toString());

        list.deleteMiddleNode();

        System.out.println(list.toString());

        System.out.println(list.getKthToLast(3).toString());

        // Generate Linked List with duplicated values
        LinkedList dupeList = new LinkedList(1);
        dupeList.appendNode(2);
        dupeList.appendNode(3);
        dupeList.appendNode(4);
        dupeList.appendNode(4);
        dupeList.appendNode(3);
        dupeList.appendNode(6);
        dupeList.appendNode(1);
        dupeList.appendNode(10);
        System.out.println("List with dupes");
        System.out.println(dupeList.toString());
        System.out.println("Remove dupes");
        dupeList.removeDupes();
        System.out.println(dupeList.toString());

        // Let's try the partition functionality
        LinkedList listToPartition = dupeList;
        listToPartition.appendNode(5);
        listToPartition.appendNode(6);
        listToPartition.appendNode(7);
        listToPartition.appendNode(8);
        listToPartition.appendNode(9);

        // Here's our list so far
        System.out.println(listToPartition.toString());

        // Let's try partitioning
        System.out.println(listToPartition.partition(6).toString());

        // addsums
        LinkedList sums = new LinkedList();
        LinkedList one = new LinkedList(7);
        one.appendNode(1);
        one.appendNode(6);

        LinkedList two = new LinkedList(5);
        two.appendNode(9);
        two.appendNode(2);
        Node summedNode = sums.sumLists(one.head, two.head);
        LinkedList summedList = new LinkedList(summedNode);
        System.out.println(summedList.toString());

        System.out.println(summedList.reverseList().toString());

        System.out.println(summedList.isPalindrome());

        LinkedList lp = new LinkedList(1);
        lp.appendNode(2);
        lp.appendNode(3);
        lp.appendNode(2);
        lp.appendNode(1);

        System.out.println(lp.isPalindrome());
    }

    class Node {
        Node next = null;
        int data;

        public Node(Node n, int d) {
            this.next = n.next;
            this.data = d;
        }

        public Node(int d) {
            this.data = d;
        }

        public Node() {}

        void append(int d) {
            Node end = new Node(d);
            Node n = this;
            while (n.next != null) {
                n = n.next;
            }
            n.next = end;
        }

        Node deleteNode(Node head, int d) {
            if (head == null) return null;
            Node n = head;
            if (n.data == d) {
                return head.next;
            }

            while (n.next != null) {
                if (n.next.data == d) {
                    n.next = n.next.next;
                    return head;
                }
                n = n.next;
            }
            return head;
        }

        public String toString() {
            return ""+data;
        }
    }
}