package Enum;

public class Match {
    private int[] matches = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};

    public int match(int count) {
        int numUsedCount = count - 4;
        int upperLimit = upperLimit(count);
        if(upperLimit <= 0) {
            System.out.println("There is not chance");
            return 0;
        }

        int total = 0;
        for(int i = 0; i <= upperLimit; i++) {
            for(int j = 0; j <= upperLimit; j++) {
                if(num2Count(i) + num2Count(j) + num2Count(i + j) == numUsedCount) {
                    System.out.println(i + "+" + j + "=" + (i + j));
                    total++;
                }
            }
        }

        return total;
    }

    private int num2Count(int num) {
        int count = 0;
        while(num / 10 != 0) {
            count += matches[num % 10];
            num = num / 10;
        }
        count += matches[num];

        return count;
    }

    private int upperLimit(int count) {
        // 首先排除加号以及等号使用的四根火柴
        count = count - 4;
        if(count < 0) {
            return count;
        }

        // 由于使用火柴数最少的是数字一使用两根，因此考虑所有数字都是1的情况下每个数字的1的个数
        int temp = (count / 2) / 3;
        int result = 0;
        if(temp == 0) {
            return temp;
        }
        else {
            while(temp-- > 0) {
                result = result * 10 + 1;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Match match = new Match();
        System.out.println("There are " + match.match(18) + " chances");
    }
}
