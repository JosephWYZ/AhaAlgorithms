package struct;

public class Queue {
    public int[] datas;
    
    public int head;
    
    public int tail;
    
    public Queue() {
        // TODO Auto-generated constructor stub
        datas = new int[100];
        head = 0;
        tail = 0;
    }
    
    public Queue(int[] input) {
        if(input != null && input.length > 0) {
            datas = new int[100];
            for(int i = 0; i < input.length; i++) {
                datas[i] = input[i];
            }
            head = 0;
            tail = input.length;
        }
        else {
            datas = new int[100];
            head = 0;
            tail = 0;
        }
    }
    
    public void add(int data)
    {
        datas[tail] = data;
        tail++;
    }
    
    public int remove() {
        return datas[head++];
    }
    
    public boolean isEmpty() {
        return head == tail;
    }
    
    public static void main(String[] args) {
        int[] input = {6,3,1,7,5,8,9,2,4};
        Queue queue = new Queue(input);
        
        int index = 0;
        while(queue.head != queue.tail) {
            input[index++] = queue.remove();
            queue.add(queue.remove());
        }
        
        String result = "";
        for(int i = 0; i < input.length; i++) {
            result = result + input[i] + " ";
        }
        System.out.println(result);
    }
}
