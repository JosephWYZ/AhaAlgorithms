package Query;

public class LostWFS {
    class Step {
        int posX;
        int posY;
        int index;
        int parentIndex = -1;
        int step;

        public Step(int posX, int posY, int index, int parentIndex, int step) {
            this.posX = posX;
            this.posY = posY;
            this.index = index;
            this.parentIndex = parentIndex;
            this.step = step;
        }
    }

    class StepsQueue {
        int tail = -1;
        int head = -1;
        Step[] steps = new Step[25];

        int add(int posX, int posY, int parentIndex) {
            if(tail == -1) {
                steps[0] = new Step(posX, posY, 0, -1, 0);
                head++;
                tail = 1;
            }
            else {
                steps[tail] = new Step(posX, posY, tail, parentIndex, steps[parentIndex].step + 1);
                tail++;
            }
            return tail - 1;
        }

        Step remove() {
            if(head != tail) {
                return steps[head++];
            }
            else {
                System.out.println("Nothing to remove from an empty queue");
                return null;
            }
        }

        boolean isEmpty() {
            return head == tail;
        }

        void printRouteBasedOnTail() {
            int index = tail - 1;
            System.out.println("The min route step is " + steps[index].step);
            String result = "";
            while(index != 0) {
                result = " -> " + steps[index].posX + ":" + steps[index].posY + result;
                index = steps[index].parentIndex;
            }
            result = steps[0].posX + ":" + steps[0].posY + result;
            System.out.println("The min route is " + result);
        }
    }

    private int[][] book = new int[5][5];
    private int[][] map = {{0,0,1,0}, {0,0,0,0}, {0,0,1,0}, {0,1,0,0}, {0,0,0,1}};
    private int[][] direct = {{1,0}, {0,1}, {-1,0}, {0,-1}};

    private int targetX;
    private int targetY;

    // 记录步数的队列
    private StepsQueue steps = new StepsQueue();

    public LostWFS(int targetX, int targetY) {
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public void wfs(int startX, int startY) {
        // 首先插入第一个起始位置
        int startIndex = steps.add(startX, startY, -1);
        book[startX][startY] = 1;

        boolean found = false;
        while(!steps.isEmpty()) {
            // 获取队列中的头部元素
            Step step = steps.remove();
            for(int i = 0; i < 4; i++) {
                int nextX = step.posX + direct[i][0];
                int nextY = step.posY + direct[i][1];
                // 判断是否越界
                if(nextX < 0 || nextX >= map[0].length || nextY < 0 || nextY >= map.length) {
                    continue;
                }

                // 判断是否已经找到
                if(nextX == targetX && nextY == targetY) {
                    book[nextX][nextY] = 1;
                    steps.add(nextX, nextY, step.index);
                    found = true;
                    break;
                }

                if(map[nextY][nextX] == 0 && book[nextY][nextX] == 0) {
                    book[nextY][nextX] = 1;
                    steps.add(nextX, nextY, step.index);
                }
            }

            if(found) {
                // 已经找到最短路径因此不需要继续操作
                steps.printRouteBasedOnTail();
                break;
            }
        }
    }

    public static void main(String[] args) {
        LostWFS lost = new LostWFS(2, 3);
        lost.wfs(0, 0);
    }
}
