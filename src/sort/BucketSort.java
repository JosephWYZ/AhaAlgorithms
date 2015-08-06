package sort;

public class BucketSort {

    /**
     * O(M+N) M=最大数，N=数组长度
     * @param numbers
     * @param max
     * @return
     */
    public int[] sort(int[] numbers, int max) {
        int[] temp = new int[max];
        for (int i = 0; i < max; i++) {
            temp[i] = 0;
        }

        for (int i = 0; i < numbers.length; i++) {
            temp[numbers[i]] += 1;
        }

        int index = 0;
        int[] result = new int[numbers.length];
        for (int i = 0; i < max; i++) {
            if (temp[i] == 0) {
                continue;
            } else {
                for (int j = 0; j < temp[i]; j++) {
                    result[index++] = i;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] test = { 5, 3, 5, 2, 8 };
        BucketSort sort = new BucketSort();
        int[] result = sort.sort(test, 10);

        for (int i = 0; i < test.length; i++) {
            System.out.println(result[i]);
        }
    }
}
