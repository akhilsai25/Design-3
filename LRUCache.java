// This solution uses doubly linked list with a hash map to efficiently store and maintain LRU based on capacity
class LRUCache {

    int capacity;
    Node head;
    Node tail;
    Map<Integer, Node> map;

    // Each node in double linked list
    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            prev=null;
            next=null;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node(0,0);
        this.tail = new Node(0,0);
        head.next = tail;
        head.prev=null;
        tail.prev = head;
        tail.next=null;
        map = new HashMap();
    }

    private void delete(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next=null;
        node.prev=null;
    }

    private void addToHead(Node node) {
        node.prev=head;
        node.next=head.next;
        head.next=node;
        node.next.prev=node;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        int val = node.val;
        delete(node);
        addToHead(node);
        return val;
    }
    
    public void put(int key, int value) {
        if(!map.containsKey(key)){
            if(map.size()==capacity) {
                Node temp = tail.prev;
                delete(tail.prev);
                map.remove(temp.key);
            }
            Node node = new Node(key, value);
            addToHead(node);
            map.put(key, node);
            return;
        }
        get(key);
        map.get(key).val = value;
        return;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
