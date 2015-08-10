package Image;

public class ShortestDistanceDFS {

    private final int MAX_DISTANCE = 999999;

    private int[][] map = {
            {0,2,MAX_DISTANCE,MAX_DISTANCE,10},
            {MAX_DISTANCE,0,3,MAX_DISTANCE,7},
            {4,MAX_DISTANCE,0,4,MAX_DISTANCE},
            {MAX_DISTANCE,MAX_DISTANCE,MAX_DISTANCE,0,5},
            {MAX_DISTANCE,MAX_DISTANCE,3,MAX_DISTANCE,0}
    };

    private int[] book = new int[5];
    private int[] route = new int[5];
    private int[] shortestRoute = new int[5];
    private int shortest = MAX_DISTANCE;

    private int startPoint = -1;
    private int endPoint = -1;

    public ShortestDistanceDFS(int startPoint, int endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        for(int i = 0; i < 5; i++) {
            route[i] = -1;
            shortestRoute[i] = -1;
        }
    }

    public void setShortestRoute(int step) {
        int i = 0;
        for(; i <= step; i++) {
            shortestRoute[i] = route[i];
        }
        for(; i < 5; i++) {
            shortestRoute[i] = -1;
        }
    }

    public void dfs(int point, int step, int current) {
        // 如果当前行走距离比最短要大则没必要继续执行
        if(current > shortest) {
            return;
        }

        // 首先判断是否已经到达终点
        if(point == endPoint) {
            if(current < shortest) {
                shortest = current;
                setShortestRoute(step);
                return;
            }
        }

        for(int i = 0; i < 5; i++) {
            if(map[point][i] != MAX_DISTANCE && map[point][i] != 0
                    && book[i] == 0) {
                book[i] = 1;
                route[step + 1] = i;
                dfs(i, step + 1, current + map[point][i]);
                book[i] = 0;
            }
        }
    }

    public void dfs() {
        book[startPoint] = 1;
        route[0] = startPoint;
        dfs(startPoint, 0, 0);

        System.out.println("The shortest distance based on DFS is " + shortest);
        String result = "";
        int i = 0;
        while(shortestRoute[i] != -1) {
            result += shortestRoute[i] + "->";
            i++;
        }
        result = result.substring(0, result.length() - 2);
        System.out.println("The shortest distance route is " + result);
    }

    public static void main(String[] args) {
        ShortestDistanceDFS dfs = new ShortestDistanceDFS(0, 4);
        dfs.dfs();
    }
}
