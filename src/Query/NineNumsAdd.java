package Query;

public class NineNumsAdd {

    private int total = 0;
    private int[] result = new int[10];
    private int[] book = new int[10];

    public void dfs(int step) {
        if(step == 10)
        {
            int a = result[1] * 100 + result[2] * 10 + result[3];
            int b = result[4] * 100 + result[5] * 10 + result[6];
            int c = result[7] * 100 + result[8] * 10 + result[9];
            if(a + b == c) {
                System.out.println(a + "+" + b + "=" + c);
                total++;
            }
            return;
        }

        for(int i = 1; i < 10; i++) {
            if(book[i] == 0) {
                result[step] = i;
                book[i] = 1;
                dfs(step + 1);
                book[i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        NineNumsAdd add = new NineNumsAdd();
        add.dfs(1);
        System.out.println("There are " + (add.total / 2) + " chances");
    }
}
