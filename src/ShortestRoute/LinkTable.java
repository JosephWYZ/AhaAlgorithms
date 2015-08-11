package ShortestRoute;

public class LinkTable {
    private final int MAX_DISTANCE = 999999;

    private int[] start;
    private int[] end;
    private int[] weight;
    private int[] head;
    private int[] next;
    private int index = 0;

    public LinkTable(int nodeNum, int lineNum) {
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

        // 首次加入这个起始点的边
        if(head[start] == -1) {
            head[start] = index;
        }
        else {
            next[index] = head[start];
            head[start] = index;
        }

        index++;
    }

    public int[][] getMap() {
        int[][] map = new int[head.length][head.length];
        for(int i = 0; i < head.length; i++) {
            for(int j = 0; j < head.length; j++) {
                map[i][j] = MAX_DISTANCE;
            }
        }

        for(int i = 0; i < head.length; i++) {
            map[i][i] = 0;
            int line = head[i];
            while(line != -1) {
                map[start[line]][end[line]] = weight[line];
                line = next[line];
            }
        }

        return map;
    }

    public void printMap(int[][] map) {
        for(int i = 0; i < map.length; i++) {
            String result = "| ";
            for(int j = 0; j < map[i].length; j++) {
                result += map[i][j] + " | ";
            }
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        LinkTable table = new LinkTable(4, 5);
        table.add(0, 3, 9);
        table.add(1, 3, 6);
        table.add(0, 1, 5);
        table.add(3, 2, 8);
        table.add(0, 2, 7);

        int[][] map = table.getMap();
        table.printMap(map);
    }
}
