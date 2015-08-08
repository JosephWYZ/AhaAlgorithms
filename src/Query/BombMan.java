package Query;

public class BombMan {
    class Node {
        int posX;
        int posY;
        int killNum;

        public Node(int posX, int posY, int killNum) {
            this.posX = posX;
            this.posY = posY;
            this.killNum = killNum;
        }

        public String toString() {
            return "X=" + posX + ", Y=" + posY + ", kill number=" + killNum;
        }
    }

    class NodeQueue {
        int head = -1;
        int tail = -1;
        int maxIndex= -1;
        Node[] nodes = new Node[121];

        int add(int posX, int posY, int killNum) {
            if(tail == -1) {
                nodes[0] = new Node(posX, posY, killNum);
                head = 0;
                tail = 1;
                maxIndex = 0;
            }
            else {
                nodes[tail] = new Node(posX, posY, killNum);
                if(nodes[maxIndex].killNum < killNum) {
                    maxIndex = tail;
                }
                tail++;
            }
            return tail - 1;
        }

        Node remove() {
            return nodes[head++];
        }

        boolean isEmpty() {
            return head == tail;
        }

        Node max() {
            if(maxIndex < 0)
                return null;
            return nodes[maxIndex];
        }
    }

    private char[][] map = {
            {'G','G','.','G','G','G','#','G','G','G','.'},
            {'#','#','.','#','G','#','G','#','G','#','G'},
            {'.','.','.','.','.','.','.','#','.','.','G'},
            {'G','#','.','#','#','#','.','#','G','#','G'},
            {'G','G','.','G','G','G','.','#','.','G','G'},
            {'G','#','.','#','G','#','.','#','.','#','.'},
            {'#','G','.','.','.','G','.','.','.','.','.'},
            {'G','#','.','#','G','#','#','#','.','#','G'},
            {'.','.','.','G','#','G','G','G','.','G','G'},
            {'G','#','.','#','G','#','G','#','.','#','G'},
            {'G','G','.','G','G','G','#','G','.','G','G'}
    };
    private int[][] direct = {{1,0}, {0,1}, {-1,0}, {0,-1}};
    private int[][] wfsBook = new int[11][11];

    private int maxDfsX = -1;
    private int maxDfsY = -1;
    private int maxDfsKillNum = 0;
    private int[][] dfsBook = new int[11][11];

    private int startX;
    private int startY;
    private NodeQueue nodes = new NodeQueue();

    public BombMan(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }

    private int getKillNum(int posX, int posY) {
        int killNum = 0;

        // 右侧的敌人数目
        int tempX = posX;
        while(tempX < map[0].length && map[posY][tempX] != '#') {
            if(map[posY][tempX] == 'G') {
                killNum++;
            }
            tempX++;
        }
        // 下方的敌人数目
        int tempY = posY;
        while(tempY < map.length && map[tempY][posX] != '#') {
            if(map[tempY][posX] == 'G') {
                killNum++;
            }
            tempY++;
        }
        // 左侧的敌人数目
        tempX = posX;
        while(tempX >= 0 && map[posY][tempX] != '#') {
            if (map[posY][tempX] == 'G') {
                killNum++;
            }
            tempX--;
        }
        // 上方的敌人数目
        tempY = posY;
        while(tempY >= 0 && map[tempY][posX] != '#') {
            if(map[tempY][posX] == 'G') {
                killNum++;
            }
            tempY--;
        }

        return killNum;
    }

    public void wfs() {
        // 将起始位置放入队列
        int killNum = getKillNum(startX, startY);
        nodes.add(startX, startY, killNum);
        wfsBook[startX][startY] = 1;

        while(!nodes.isEmpty()) {
            Node node = nodes.remove();
            for(int i = 0; i < 4; i++) {
                int nextX = node.posX + direct[i][0];
                int nextY = node.posY + direct[i][1];

                // 判断是否已经到达边缘地带
                if(nextX < 0 || nextX >= map[0].length || nextY < 0 || nextY >= map.length) {
                    continue;
                }

                if(map[nextY][nextX] == '.' && wfsBook[nextX][nextY] == 0) {
                    killNum = getKillNum(nextX, nextY);
                    nodes.add(nextX, nextY, killNum);
                    wfsBook[nextX][nextY] = 1;
                }
            }
        }

        Node max = nodes.max();
        if(max != null) {
            System.out.println("The max kill num position using WFS is " + max.toString());
        }
        else {
            System.out.println("Nothing found");
        }
    }

    public void dfs(){
        dfsBook[startX][startY] = 1;
        dfs(startX, startY);

        if(maxDfsKillNum > 0) {
            System.out.println("The max kill num position using DFS is X=" + maxDfsX + ", Y=" + maxDfsY + ", kill number=" + maxDfsKillNum);
        }
        else {
            System.out.println("Nothing found");
        }
    }

    private void dfs(int posX, int posY) {
        int killNum = getKillNum(posX, posY);
        if(killNum > maxDfsKillNum) {
            maxDfsX = posX;
            maxDfsY = posY;
            maxDfsKillNum = killNum;
        }

        for(int i = 0; i < 4; i++) {
            int nextX = posX + direct[i][0];
            int nextY = posY + direct[i][1];

            // 判断是否已经到达边缘地带
            if(nextX < 0 || nextX >= map[0].length || nextY < 0 || nextY >= map.length) {
                continue;
            }
            if(map[nextY][nextX] == '.' && dfsBook[nextX][nextY] == 0) {
                dfsBook[nextX][nextY] = 1;
                dfs(nextX, nextY);
            }
        }
    }

    public static void main(String[] args) {
        BombMan man = new BombMan(2, 2);
        man.wfs();
        man.dfs();
    }
}
