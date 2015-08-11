package ShortestRoute;

public class Dijkstra {
    private final int MAX_DISTANCE = 999999;
    private int[][] map = {
            {0,1,12,MAX_DISTANCE,MAX_DISTANCE,MAX_DISTANCE},
            {MAX_DISTANCE,0,9,3,MAX_DISTANCE,MAX_DISTANCE},
            {MAX_DISTANCE,MAX_DISTANCE, 0, MAX_DISTANCE, 5, MAX_DISTANCE},
            {MAX_DISTANCE,MAX_DISTANCE,4,0,13,15},
            {MAX_DISTANCE,MAX_DISTANCE,MAX_DISTANCE,MAX_DISTANCE,0,4},
            {MAX_DISTANCE,MAX_DISTANCE,MAX_DISTANCE,MAX_DISTANCE,MAX_DISTANCE,0}
    };

    private int[] book = new int[6];

    public int[] getShortestDistance(int start) {
        // 先获取起始点到每个点直接距离
        int[] distances = new int[6];
        System.arraycopy(map[start], 0, distances, 0, 6);

        // 将start放入book中
        book[start] = 1;
        // 循环n-1次将剩余的n-1个点距离start最短路径获取
        for(int i = 0; i < 5; i++) {
            int shortest = MAX_DISTANCE;
            int shortestNode = -1;
            for(int j = 0; j < 6; j++) {
                if(book[j] == 0 && distances[j] < shortest) {
                    shortest = distances[j];
                    shortestNode = j;
                }
            }

            if(shortestNode == -1) {
                System.out.println("Cannot find shortest node from " + start);
                return distances;
            }
            // 将此时最短的距离的点设置为已确定点
            book[shortestNode] = 1;
            // 比较其他点与起始点的距离以及起始点->shortestNode->该点的距离大小
            for(int j = 0; j < 6; j++) {
                if(book[j] == 0 && distances[j] > shortest + map[shortestNode][j]) {
                    distances[j] = shortest + map[shortestNode][j];
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        int[] start0 = dijkstra.getShortestDistance(2);
        String result = "";
        for(int i = 0; i < start0.length; i++) {
            result += start0[i] + " ";
        }
        System.out.println(result);
    }

}
