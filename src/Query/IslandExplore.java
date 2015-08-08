package Query;

public class IslandExplore {

    class Node {
        int posX;
        int posY;

        public Node(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
        }
    }

    class NodeQueue {
        int head = -1;
        int tail = -1;
        Node[] nodes = new Node[100];

        int add(int posX, int posY) {
            if(tail == -1) {
                nodes[0] = new Node(posX, posY);
                head = 0;
                tail = 1;
            }
            else {
                nodes[tail++] = new Node(posX, posY);
            }
            return  tail - 1;
        }

        Node remove() {
            return nodes[head++];
        }

        int count() {
            return tail;
        }

        boolean isEmpty()
        {
            return head == tail;
        }
    }

    private int[][] map = {
            {1,2,1,0,0,0,0,0,2,3},
            {3,0,2,0,1,2,1,0,1,2},
            {4,0,1,0,1,2,3,2,0,1},
            {3,2,0,0,0,1,2,4,0,0},
            {0,0,0,0,0,0,1,5,3,0},
            {0,1,2,1,0,1,5,4,3,0},
            {0,1,2,3,1,3,6,2,1,0},
            {0,0,3,4,8,9,7,5,0,0},
            {0,0,0,3,7,8,6,0,1,2},
            {0,0,0,0,0,0,0,0,1,0}
    };
    private int[][] direct = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    private int[][] wfsBook = new int[10][10];
    private int[][] dfsBook = new int[10][10];
    private int dfsLandNum = 0;

    private int landX;
    private int landY;

    private NodeQueue nodes = new NodeQueue();

    public IslandExplore(int landX, int landY) {
        this.landX = landX;
        this.landY = landY;
    }

    public void wfs() {
        if(map[landX][landY] == 0) {
            System.out.println("Landing on the ocean...");
            return;
        }

        // 将降落点设置为第一个节点
        nodes.add(landX, landY);
        wfsBook[landX][landY] = 1;

        while (!nodes.isEmpty()) {
            Node node = nodes.remove();
            for(int i = 0; i < 4; i++) {
                int nextX = node.posX + direct[i][0];
                int nextY = node.posY + direct[i][1];

                if(nextX < 0 || nextX >= 10 || nextY < 0 || nextY >= 10) {
                    continue;
                }
                if(map[nextX][nextY] != 0 && wfsBook[nextX][nextY] == 0) {
                    nodes.add(nextX, nextY);
                    wfsBook[nextX][nextY] = 1;
                }
            }
        }

        System.out.println("Landing on position using wfs is X=" + landX + ", Y=" + landY + ", land count=" + nodes.count());
        for(int i = 0; i < 10; i++) {
            String resultX = "";
            for(int j = 0; j < 10; j++) {
                resultX += wfsBook[i][j] + " ";
            }
            System.out.println(resultX);
        }
    }

    public void dfs() {
        if(map[landX][landY] == 0){
            System.out.println("Landing on the ocean...");
            return;
        }

        dfsBook[landX][landY] = 1;
        dfs(landX, landY);

        System.out.println("Landing on position using dfs is X=" + landX + ", Y=" + landY + ", land count=" + dfsLandNum);
        for(int i = 0; i < 10; i++) {
            String resultX = "";
            for(int j = 0; j < 10; j++) {
                resultX += dfsBook[i][j] + " ";
            }
            System.out.println(resultX);
        }
    }

    private void dfs(int posX, int posY) {
        if(map[posX][posY] != 0) {
            dfsLandNum++;
        }

        for(int i = 0; i < 4; i++) {
            int nextX = posX + direct[i][0];
            int nextY = posY + direct[i][1];

            if(nextX < 0 || nextX >= 10 || nextY < 0 || nextY >= 10) {
                continue;
            }
            if(map[nextX][nextY] != 0 && dfsBook[nextX][nextY] == 0) {
                dfsBook[nextX][nextY] = 1;
                dfs(nextX, nextY);
            }
        }
    }

    public static void main(String[] args) {
        IslandExplore island = new IslandExplore(0, 0);
        island.wfs();
        island.dfs();
    }
}
