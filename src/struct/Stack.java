package struct;

public class Stack {

    private char[] items;
    
    public int top;
    
    public Stack(String content) {
        // TODO Auto-generated constructor stub
        if(content != null && content.length() > 0) {
            items = new char[100];
            for(int i = 0; i < content.length(); i++) {
                items[i] = content.charAt(i);
            }
            top = content.length() - 1;
        }
        else {
            items = new char[100];
            top = -1;
        }
    }
    
    public void add(char item) {
        items[++top] = item;
    }
    
    public char remove() {
        return items[top--];
    }
    
    public boolean check() {
        if(top == -1) {
            return false;
        }
        
        int parentheses = 0;
        int bracket = 0;
        int brace = 0;
        while(top != -1) {
            if(parentheses < 0 || bracket < 0 || brace < 0) {
                return false;
            }
            
            char item = this.remove();
            if(item == ')') {
                parentheses++;
            }
            else if (item == '(') {
                parentheses--;
            }
            else if(item == ']') {
                bracket++;
            }
            else if (item == '[') {
                bracket--;
            }
            else if (item == '}') {
                brace++;
            }
            else if (item == '{') {
                brace--;
            }
            else {
                continue;
            }
        }
        if(parentheses != 0 || bracket != 0 || brace != 0) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public static void main(String[] args) {
//        String text = "ahaha0";
//        int index = 0;
//        Stack front;
//        if(text.length() % 2 == 0) {
//            index = text.length() / 2;
//            front = new Stack(text.substring(0, index));
//        }
//        else {
//            index = text.length() / 2 + 1;
//            front = new Stack(text.substring(0, index - 1));
//        }
//        
//        for(; index < text.length(); index++) {
//            char item = front.remove();
//            if(text.charAt(index) != item) {
//                System.out.println("This is not a palindrome string! " + text.charAt(index) + " front=" + item);
//                return;
//            }
//        }
//        
//        System.out.println("This is a palindrome string");
        
        String content = "{[()]}";
        Stack stack = new Stack(content);
        System.out.println(stack.check());
    }
}
