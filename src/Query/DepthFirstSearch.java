package Query;

public class DepthFirstSearch {

    private int[] book = new int[10];

    private int[] result = new int[10];

    private int total = 0;

    private int num = 0;

    public DepthFirstSearch(int num) {
        this.num = num;
    }

    public void dfs(int step) {
        if(step == num + 1) {
            String output = "";
            for(int i = 1; i <= num; i++) {
                output = output + result[i];
                //result[i] = 0;
            }
            System.out.println(output);
            total++;
            return;
        }

        for(int i = 1; i <= num; i++) {
            if(book[i] == 0) {
                result[step] = i;
                book[i] = 1;
                dfs(step + 1);
                book[i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        DepthFirstSearch dfs = new DepthFirstSearch(4);
        dfs.dfs(1);
        System.out.println("There are " + dfs.total + " chances");
    }
}
