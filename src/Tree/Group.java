package Tree;

public class Group {

    private int[] members = new int[11];

    public Group() {
        for(int i = 1; i < 11; i++) {
            members[i] = i;
        }
    }

    private int getRoot(int index) {
        while(index != members[index]) {
            index = members[index];
        }

        return index;
    }

    public void merge(int left, int right) {
        int leftRoot = getRoot(left);
        int rightRoot = getRoot(right);
        if(leftRoot != rightRoot) {
            members[rightRoot] = leftRoot;
        }
    }

    public static void main(String[] args) {
        Group group = new Group();
        group.merge(1, 2);
        group.merge(3, 4);
        group.merge(5, 2);
        group.merge(4, 6);
        group.merge(2, 6);
        group.merge(8, 7);
        group.merge(9, 7);
        group.merge(1, 6);
        group.merge(2, 4);

        String result = "";
        int groupCount = 0;
        for(int i = 1; i < 11; i++) {
            result += group.members[i] + " ";
            if(group.members[i] == i) {
                groupCount++;
            }
        }

        System.out.println("Group members : " + result);
        System.out.println("Group count : " + groupCount);
    }
}
