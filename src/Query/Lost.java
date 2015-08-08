package Query;

public class Lost {
    private static final int NO_WAY = 999999;
    private int minStep = NO_WAY;
    private int[][] route = new int[50][2];
    private int[][] minRoute = new int[50][2];
    private int[][] book = new int[5][5];
    private int[][] map = {{0,0,1,0}, {0,0,0,0}, {0,0,1,0}, {0,1,0,0}, {0,0,0,1}};
    private int[][] direct = {{1,0}, {0,1}, {-1,0}, {0,-1}};

    private int targetX;
    private int targetY;

    public Lost(int targetX, int targetY) {
        this.targetX = targetX;
        this.targetY = targetY;
    }

    private boolean finish(int posX, int posY, int step) {
        if(posX == targetX && posY == targetY) {
            String result = "";
            if(minStep > step)
            {
                minStep = step;
                for(int i = 0; i < step; i++) {
                    minRoute[i][0] = route[i][0];
                    minRoute[i][1] = route[i][1];
                    result += route[i][0] + ":" + route[i][1] + " -> ";
                }
            }
            else
            {
                for(int i = 0; i < step; i++) {
                    result += route[i][0] + ":" + route[i][1] + " -> ";
                }
            }
            result += posX + ":" + posY;
            System.out.println(result);

            return true;
        }

        return false;
    }

    public void dfs(int posX, int posY, int step) {
        route[step][0] = posX;
        route[step][1] = posY;

        if(finish(posX, posY, step))
        {
            return;
        }

        for(int i = 0; i < 4; i++) {
            int nextX = posX + direct[i][0];
            int nextY = posY + direct[i][1];
            // 判断是否越界
            if(nextX < 0 || nextX >= map[0].length || nextY < 0 || nextY >= map.length) {
                continue;
            }
            if(map[nextY][nextX] == 0 && book[nextY][nextX] == 0) {
                book[nextY][nextX] = 1;
                dfs(nextX, nextY, step + 1);
                book[nextY][nextX] = 0;
            }
        }

    }

    public static void main(String[] args) {
        Lost lost = new Lost(2, 3);
        lost.book[0][0] = 1;
        lost.dfs(0, 0, 0);
        System.out.println("The min route step is " + lost.minStep);
        if(lost.minStep != NO_WAY)
        {
            String result = "";
            for(int i = 0; i < lost.minStep; i++) {
                result += lost.minRoute[i][0] + ":" + lost.minRoute[i][1] + " -> ";
            }
            result += lost.minRoute[lost.minStep][0] + ":" + lost.minRoute[lost.minStep][1];
            System.out.println("The min route is " + result);
        }
    }
}
