public class FileUploadChunks {

    public static int[] getChunksDetails(int[][] chunks) {
        int[] result = new int[chunks.length];
        int min = chunks[0][0];
        int max = chunks[0][1];
        result[0] = max - min + 1;

        for (int i = 1; i < chunks.length; i++) {
            int chunkStart = chunks[i][0];
            int chunkEnd = chunks[i][1];

            if (chunkStart >= min && chunkEnd <= max) {
                result[i] = result[i - 1];
            } else {
                int overlap = Math.max(0, Math.min(max, chunkEnd) - Math.max(min, chunkStart) + 1);
                result[i] = result[i - 1] + (chunkEnd - chunkStart + 1) - overlap;

                min = Math.min(min, chunkStart);
                max = Math.max(max, chunkEnd);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] chunks = {{1, 1}, {2, 2}, {3, 3}, {1, 15}, {11, 12}};
        int[] res = getChunksDetails(chunks);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
