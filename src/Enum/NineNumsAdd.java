package Enum;

public class NineNumsAdd {
    int[] nums = new int[10];
    int[] books = new int[10];

    public void clearBooks() {
        for (int i = 0; i < 10; i++) {
            books[i] = 0;
        }
    }

    public int find() {
        int total = 0;
        for (nums[1] = 1; nums[1] < 10; nums[1]++) {
            for (nums[2] = 1; nums[2] < 10; nums[2]++) {
                for (nums[3] = 1; nums[3] < 10; nums[3]++) {
                    for (nums[4] = 1; nums[4] < 10; nums[4]++) {
                        for (nums[5] = 1; nums[5] < 10; nums[5]++) {
                            for (nums[6] = 1; nums[6] < 10; nums[6]++) {
                                for (nums[7] = 1; nums[7] < 10; nums[7] ++) {
                                    for (nums[8] = 1; nums[8] < 10; nums[8] ++) {
                                        for(nums[9] = 1; nums[9] < 10; nums[9]++) {
                                            clearBooks();

                                            for (int i = 1; i < 10; i++) {
                                                books[nums[i]] = 1;
                                            }

                                            int sum = 0;
                                            // 判断是否数字都不存在重复
                                            for (int i = 1; i < 10 ; i++) {
                                                sum += books[i];
                                            }

                                            if (sum == 9) {
                                                int a = nums[1] * 100 + nums[2] * 10 + nums[3];
                                                int b = nums[4] * 100 + nums[5] * 10 + nums[6];
                                                int c = nums[7] * 100 + nums[8] * 10 + nums[9];

                                                if (a + b == c) {
                                                    total++;
                                                    System.out.println(a + "+" + b + "=" + c);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return total / 2;
    }

    public static void main(String[] args) {
        NineNumsAdd add = new NineNumsAdd();
        System.out.println("There are " + add.find() + " chances");
    }
}
