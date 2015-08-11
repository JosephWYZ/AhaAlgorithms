package ShortestRoute;

public class Floyd {

    private final int MAX_DISTANCE = 99999;

    private int[][] map = {
            {0,2,6,4},
            {MAX_DISTANCE,0,3,MAX_DISTANCE},
            {7,MAX_DISTANCE,0,1},
            {5,MAX_DISTANCE,12,0}
    };

    private void printMap() {
        for(int i = 0; i < 4; i++) {
            String line = "| ";
            for(int j = 0; j < 4; j++) {
                line += map[i][j] + " | ";
            }
            System.out.println(line);
        }
    }

    private void getShortestRoute() {
        for(int k = 0; k < 4; k++) {
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                    if(map[i][k] + map[k][j] < map[i][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
            System.out.println("when k=" + k + ", map is :");
            printMap();
        }
    }

    public static void main(String[] args) {
        Floyd floyd = new Floyd();
        floyd.getShortestRoute();
    }
}
