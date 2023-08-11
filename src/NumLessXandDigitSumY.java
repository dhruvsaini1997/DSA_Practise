public class NumLessXandDigitSumY {
    public static int getCount(int X, int Y) {
        int count = 0;

        for (int num = 1; num <= X; num++) {
            int currentNum = num;
            int digitSum = 0;

            while (currentNum > 0) {
                digitSum += currentNum % 10;
                currentNum /= 10;
            }

            if (digitSum == Y) {
                count++;
            }
        }
if(count==0){
    return -1;
}
        return count;
    }

    public static void main(String[] args) {
        System.out.println(getCount(20, 5));
    }
}
