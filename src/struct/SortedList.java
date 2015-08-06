package struct;

public class SortedList {

    int[] datas = new int[100];
    
    int[] nexts = new int[100];
    
    int index = 0;
    
    int start = 0;
    
    public void add(int data) {
        // 首次插入的时候
        if(index == 0) {
            index++;
            start++;
            datas[index] = data;
        }
        else
        {
            // 判断是否比第一位数字小 是的话则需要将新的数字放在首位
            if(datas[start] > data) {
                index++;
                datas[index] = data;
                nexts[index] = start;
                start = index;
            }
            else{
                int i = start;
                boolean flag = false;
                while(i != 0 && nexts[i] != 0){
                    if(datas[nexts[i]] > data) {
                        index++;
                        datas[index] = data;
                        nexts[index] = nexts[i];
                        nexts[i] = index;
                        flag = true;
                        break;
                    }
                    i = nexts[i];
                }
                
                if(!flag) {
                    index++;
                    datas[index] = data;
                    nexts[i] = index;
                }
            }
            
        }
    }
    
    public void print() {
        if(start == 0) {
            System.out.println("Empty list");
        }
        else{
            int i = start;
            String result = "";
            while(i != 0) {
                result = result + datas[i] + " ";
                i = nexts[i];
            }
            System.out.println(result);
        }
        
    }
    
    public static void main(String[] args) {
        SortedList list = new SortedList();
        list.add(10);
        list.add(7);
        list.add(100);
        list.add(34);
        list.print();
    }
}
