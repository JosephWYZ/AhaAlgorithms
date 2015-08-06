package struct;

public class Games {
    
    Queue playX;
    
    Queue playY;
    
    IntegerStack desk = new IntegerStack(null);
    
    int[] book = new int[10];
    
    public void addPlayX(int[] cards) {
        playX = new Queue(cards);
    }
    
    public void addPlayY(int[] cards) {
        playY = new Queue(cards);
    }
    
    public void clearDesk() {
        for(int i = 0; i < 10; i++) {
            book[i] = 0;
        }
    }
    
    public boolean checkWin() {
        clearDesk();
        
        while (!playX.isEmpty() && !playY.isEmpty()) {
            // playX shows his card
            int cardX = playX.remove();
            if(book[cardX] == 0) {
                desk.add(cardX);
                book[cardX] = 1;
            }
            else {
                while(desk.top != -1) {
                    int card = desk.remove();
                    playX.add(card);
                    book[card] = 0;
                }
            }
            
            // check playX win?
            if(playX.isEmpty()) {
                return true;
            }
            
            int cardY = playY.remove();
            if(book[cardY] == 0) {
                desk.add(cardY);
                book[cardY] = 1;
            }
            else {
                while(desk.top != -1) {
                    int card = desk.remove();
                    playY.add(card);
                    book[card] = 0;
                }
            }
        }
        
        return playX.isEmpty();
    }

    public static void main(String[] args) {
        Games game = new Games();
        int[] playX = {2,4,1,2,5,6};
        game.addPlayX(playX);
        int[] playY = {3,1,3,5,6,4};
        game.addPlayY(playY);
        
        System.out.println("Is that play X win? " + game.checkWin());
    }
}
