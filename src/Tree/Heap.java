package Tree;

public class Heap {

    private int[] datas;

    private int count = 0;

    public Heap(int maxLength) {
        datas = new int[maxLength + 1];
    }

    private void swap(int source, int target) {
        int temp = datas[source];
        datas[source] = datas[target];
        datas[target] = temp;
    }

    private void shiftDown(int index) {
        while(index * 2 <= count) {
            int temp = index;
            // 判断左右节点大小
            if(datas[index] < datas[index * 2]) {
                temp = index * 2;
            }
            if(index * 2 + 1 <= count && datas[temp] < datas[index * 2 + 1]) {
                temp = index * 2 + 1;
            }

            if(temp == index) {
                break;
            }
            else {
                swap(index, temp);
                index = temp;
            }
         }
    }

    private void shiftUp(int index) {
        while(index / 2 >= 1) {
            if(datas[index] > datas[index / 2]) {
                swap(index, index / 2);
                index = index / 2;
            }
            else {
                break;
            }
        }
    }

    public void insert(int[] datas) {
        for(int i  = 0; i < datas.length; i++) {
            this.datas[i + 1] = datas[i];
        }
        count = datas.length;

        for(int i = count / 2; i >= 1; i--) {
            shiftDown(i);
        }
    }

    public void add(int data) {
        datas[++count] = data;
        shiftUp(count);
    }

    public int deleteMin() {
        int min = datas[1];
        datas[1] = datas[count];
        shiftDown(1);
        count--;
        return min;
    }

    public void sort() {
        while(count > 1) {
            swap(1, count);
            count--;
            shiftDown(1);
        }

    }


    public static void main(String[] args) {
        Heap heap = new Heap(20);
        int[] inputs = {99,5,36,7,22,17,46,12,2,19,25,28,1,92};
        heap.insert(inputs);
        heap.add(100);
        heap.sort();
        String result = "";
        for(int i = 1; i <= inputs.length + 1; i++) {
            result += heap.datas[i] + " ";
        }
        System.out.println(result);
    }


}
