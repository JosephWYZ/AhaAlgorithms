package ShortestRoute;

public class BellmanQueue {
    private final int MAX_DISTANCE = 999999;
    private int[] start;
    private int[] end;
    private int[] weight;
    private int[] first;
    private int[] next;
    private int index = 0;

    private int[] queue;
    private int head = -1;
    private int tail = -1;
    private int[] book;

    public BellmanQueue(int nodeNum, int lineNum) {
        start = new int[lineNum];
        end = new int[lineNum];
        weight = new int[lineNum];

        first = new int[nodeNum];
        for(int i = 0; i < nodeNum; i++) {
            first[i] = -1;
        }

        next = new int[lineNum];
        for(int i = 0; i < lineNum; i++) {
            next[i] = -1;
        }

        queue = new int[nodeNum * nodeNum];
        book = new int[nodeNum];
    }

    public void add(int start, int end, int weight) {
        this.start[index] = start;
        this.end[index] = end;
        this.weight[index] = weight;

        if(first[start] == -1) {
            first[start] = index;
        }
        else {
            next[index] = first[start];
            first[start] = index;
        }
        index++;
    }

    public int[] getShortestDistance(int start) {
        // 初始化一个数组保存start到每个点的距离
        int[] distances = new int[first.length];
        for(int i = 0; i < first.length; i++) {
            distances[i] = MAX_DISTANCE;
        }
        distances[start] = 0;

        // 将start放入队列
        queue[0] = start;
        book[start] = 1;
        head= 0;
        tail = 1;

        while(head != tail) {
            // 获取首节点
            int from = queue[head++];
            // 获取该节点连接的边
            int line = first[from];
            while(line != -1) {
                // 判断是否通过这条边可以缩短距离，是则将end加入队列
                if(distances[end[line]] > distances[this.start[line]] + weight[line]) {
                    distances[end[line]] = distances[this.start[line]] + weight[line];
                    if(book[end[line]] == 0) {
                        queue[tail++] = end[line];
                        book[end[line]] = 1;
                    }
                }
                line = next[line];
            }

            book[from] = 0;
        }

        return distances;
    }

    public static void main(String[] args) {
        BellmanQueue bellmanQueue = new BellmanQueue(5, 7);
        bellmanQueue.add(0,1,2);
        bellmanQueue.add(0,4,10);
        bellmanQueue.add(1,2,3);
        bellmanQueue.add(1,4,7);
        bellmanQueue.add(2,3,4);
        bellmanQueue.add(3,4,5);
        bellmanQueue.add(4,2,6);

        int[] distances = bellmanQueue.getShortestDistance(0);
        String result = "";
        for(int distance : distances) {
            result += distance + " ";
        }
        System.out.println(result);
    }
}
