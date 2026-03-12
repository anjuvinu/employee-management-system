import java.util.HashMap;
import java.util.Map;

class LRUCache {

    /*
      Node class for Doubly Linked List
      Each node stores:
      - key
      - value
      - pointer to previous node
      - pointer to next node
    */
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // Maximum number of elements cache can store
    private int capacity;

    // Current number of elements in cache
    private int count;

    // HashMap for O(1) lookup of nodes
    // key -> node in linked list
    private Map<Integer, Node> map;

    // Dummy head and tail nodes
    // head.next = Most Recently Used
    // tail.prev = Least Recently Used
    private Node head;
    private Node tail;

    /*
      Constructor
      Initializes cache capacity and creates dummy nodes
    */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;

        map = new HashMap<>();

        // Create dummy head and tail
        head = new Node(0,0);
        tail = new Node(0,0);

        // Connect head and tail
        head.next = tail;
        tail.prev = head;
    }

    /*
      GET operation Returns value if key exists Otherwise returns -1
    */
    public int get(int key) {

        Node node = map.get(key);

        // Key not found in cache
        if(node == null)
            return -1;

        // Move accessed node to head
        // because it becomes most recently used
        updateNode(node);

        return node.value;
    }

    /*
      PUT operation Insert or update key-value pair
    */
    public void put(int key, int value) {

        Node node = map.get(key);

        // Key not present → create new node
        if(node == null){

            Node newNode = new Node(key,value);

            // Add node to hashmap
            map.put(key,newNode);

            // Add node to linked list (front)
            addNode(newNode);

            count++;

            // If cache exceeds capacity
            // remove least recently used node
            if(count > capacity){

                Node toDelete = tail.prev;

                removeNode(toDelete);

                map.remove(toDelete.key);

                count--;
            }

        }
        else {
            // Key already present
            // update value
            node.value = value;

            // Move node to front
            updateNode(node);
        }
    }

    /*
      Add node right after head
      Means node becomes Most Recently Used
    */
    private void addNode(Node node){

        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    /*
      Remove node from linked list
    */
    private void removeNode(Node node){

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /*
      Move node to front
      Used when node is accessed or updated
    */
    private void updateNode(Node node){

        removeNode(node);
        addNode(node);
    }
}