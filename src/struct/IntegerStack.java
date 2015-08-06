package struct;

public class IntegerStack {

    public int[] datas;
    
    public int top = -1;
    
    public IntegerStack(int[] input)
    {
        datas = new int[100];
        if(input != null && input.length > 0) {
            for(int i = 0; i < input.length; i++) {
                datas[i] = input[i];
            }
            top = input.length - 1;
        }
    }
    
    public void add(int data) {
        datas[++top] = data;
    }
    
    public int remove() {
        return datas[top--];
    }
}
