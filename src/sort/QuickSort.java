package sort;

public class QuickSort {

    /**
     * MAX O(N*N) AVERAGE O(N*logN)
     * @param numbers
     * @param start
     * @param end
     */
    public void sort(int[] numbers, int start, int end) {
        if(start > end) {
            return;
        }
        
        int i = start;
        int j = end;
        int key = numbers[start];
        while(i != j) {
            while(numbers[j] >= key && i < j) {
                j--; 
            }
            
            while(numbers[i] <= key && i < j) {
                i++;
            }
            
            if(i < j) {
                int temp = numbers[j];
                numbers[j] = numbers[i];
                numbers[i] = temp;
            }
        }
        
        numbers[start] = numbers[i];
        numbers[i] = key;
        
        sort(numbers, start, i - 1);
        sort(numbers, i + 1, end);
        return;
    }
    
    public static void main(String[] args) {
        int[] numbers = {15, 23, 2, 44, 51};
        QuickSort sort = new QuickSort();
        sort.sort(numbers, 0, numbers.length - 1);
        
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
    }
}
