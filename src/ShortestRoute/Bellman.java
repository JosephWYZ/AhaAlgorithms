package ShortestRoute;

public class Bellman {
    private final int MAX_DISTANCE = 999999;

    private int[] start;
    private int[] end;
    private int[] weight;
    private int[] head;
    private int[] next;
    private int index = 0;

    public Bellman(int nodeNum, int lineNum) {
        start = new int[lineNum];
        end = new int[lineNum];
        weight = new int[lineNum];

        head = new int[nodeNum];
        for(int i = 0; i < nodeNum; i++) {
            head[i] = -1;
        }

        next = new int[lineNum];
        for(int i = 0; i < lineNum; i++) {
            next[i] = -1;
        }
    }

    public void add(int start, int end, int weight) {
        this.start[index] = start;
        this.end[index] = end;
        this.weight[index] = weight;

        if(head[start] == -1) {
            head[start] = index;
        }
        else {
            next[index] = head[start];
            head[start] = index;
        }

        index++;
    }

    public int[] getShortestDistance(int start) {
        // 初始化一个长度为节点长度的数组，且每项值为MAX_DISTANCE
        int[] distances = new int[head.length];
        for(int i = 0; i < head.length; i++) {
            distances[i] = MAX_DISTANCE;
        }
        distances[start] = 0;

        // 遍历节点个数-1次
        boolean changed = false;
        for(int i = 0; i < head.length - 1; i++) {
            // 遍历所有的边
            for(int j = 0; j < next.length; j++) {
                if(distances[end[j]] > distances[this.start[j]] + weight[j]) {
                    changed = true;
                    distances[end[j]] = distances[this.start[j]] + weight[j];
                }
            }

            // 判断是否已经不再变化，是则不需要继续遍历
            if(!changed) {
                break;
            }
        }

        // 判断是否存在负权回路
        for(int j = 0; j < next.length; j ++) {
            if(distances[end[j]] > distances[this.start[j]] + weight[j]) {
                changed = true;
                break;
            }
        }
        if(changed) {
            System.out.println("There is a negative weight circle");
        }
        return distances;
    }

    public static void main(String[] args) {
        Bellman bellman = new Bellman(5, 5);
        bellman.add(1, 2, 2);
        bellman.add(0, 1, -3);
        bellman.add(0, 4, 5);
        bellman.add(3, 4, 2);
        bellman.add(2, 3, 3);

        int[] distances = bellman.getShortestDistance(0);
        String result = "";
        for (int distance : distances) {
            result += distance + " ";
        }
        System.out.println(result);
    }
}
