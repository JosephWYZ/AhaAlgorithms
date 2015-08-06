package sort;

public class BubbleSort {

    /**
     * O(N*N)
     * @param numbers
     * @return
     */
    public int[] sort(int[] numbers) {
        for(int i = 0; i < numbers.length; i++) {
            for(int j = 0; j < numbers.length - i - 1; j++){
                if(numbers[j] < numbers[j + 1])
                {
                    int temp = numbers[j + 1];
                    numbers[j + 1] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
        
        return numbers;
    }
    
    public static void main(String[] args) {
        int[] numbers = {15, 23, 2, 44, 51};
        BubbleSort sort = new BubbleSort();
        int[] result = sort.sort(numbers);
        
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
