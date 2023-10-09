public class CheckTileCircle {
    public static int getSubArrayTiles(int[] tileColors, int size) {
        int count = 0;
        for (int i = 0; i < tileColors.length; i++) {
            boolean hasDuplicate = false;

            for (int j = 1; j < size; j++) {
                int index = (i + j) % tileColors.length;
                int prevIndex = (index - 1 + tileColors.length) % tileColors.length;

                if (tileColors[index] == tileColors[prevIndex]) {
                    hasDuplicate = true;
                    break;
                }
            }

            if (!hasDuplicate) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(getSubArrayTiles(new int[]{0, 1, 0, 1}, 4));
    }
}
