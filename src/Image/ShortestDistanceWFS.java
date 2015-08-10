package Image;

public class ShortestDistanceWFS {

    class Node {
        int name;
        int index;
        int parentIndex;
        int times;

        public Node(int name, int index, int parentIndex, int times) {
            this.name = name;
            this.index = index;
            this.parentIndex = parentIndex;
            this.times = times;
        }
    }

    class NodeQueue {
        Node[] nodes = new Node[5];
        int head = -1;
        int tail = -1;

        public int add(int name, int parentIndex, int times) {
            if(tail == -1) {
                nodes[0] = new Node(name, 0, parentIndex, times);
                head = 0;
                tail = 1;
            }
            else {
                nodes[tail] = new Node(name, tail, parentIndex, times);
                tail++;
            }

            return tail - 1;
        }

        public Node remove() {
            if(head != tail) {
                return nodes[head++];
            }
            else {
                return null;
            }
        }

        public void getShortestDistance() {
            if(head != tail) {
                int index = tail - 1;
                String result = "";
                while (index > 0) {
                    result = "->" + nodes[index].name + result;
                    index = nodes[index].parentIndex;
                }
                result = nodes[0].name + result;
                System.out.println("The shortest instance using WFS is: " + nodes[tail - 1].times);
                System.out.println("The shortest instance route using WFS is: " + result);
            }
            else {
                System.out.println("Noting found");
            }
        }

        public boolean isEmpty() {
            return head == tail;
        }
    }

    private final int MAX_DISTANCE = 999999;
    private int[][] map = {
            {0,1,1,MAX_DISTANCE,MAX_DISTANCE},
            {1,0,1,1,MAX_DISTANCE},
            {1,1,0,1,1},
            {MAX_DISTANCE,1,1,0,1},
            {MAX_DISTANCE,MAX_DISTANCE,1,1,0}
    };
    private NodeQueue queue = new NodeQueue();
    private int[] book = new int[5];

    private int startNode;
    private int endNode;

    public ShortestDistanceWFS(int startNode, int endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public void wfs() {
        // 插入第一个节点
        queue.add(startNode, -1, 0);
        book[startNode] = 1;

        boolean finish = false;
        while(!queue.isEmpty()) {
            Node node = queue.remove();
            for(int i = 0; i < 5; i++) {
                if(map[node.name][i] != MAX_DISTANCE && map[node.name][i] != 0 &&
                        book[i] == 0) {
                    queue.add(i, node.index, node.times + 1);
                    book[i] = 1;

                    if(i == endNode) {
                        finish = true;
                        break;
                    }
                }
            }

            if(finish) {
                queue.getShortestDistance();
                return;
            }
        }
    }

    public static void main(String[] args) {
        ShortestDistanceWFS wfs = new ShortestDistanceWFS(0, 4);
        wfs.wfs();
    }
}
