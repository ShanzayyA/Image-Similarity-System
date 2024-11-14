import java.io.File;
import java.io.IOException;
import java.util.PriorityQueue;

public class SimilaritySearch {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java SimilaritySearch <queryImage> <imageDatasetDirectory>");
            System.exit(1);
        }

        try {
            String queryImagePath = args[0];
            String datasetDirectoryPath = args[1];
            searchSimilarImages(queryImagePath, datasetDirectoryPath);
        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void searchSimilarImages(String queryImagePath, String datasetDirectoryPath) throws IOException {
        ColorImage queryImage = new ColorImage(queryImagePath);
        ColorHistogram queryHistogram = new ColorHistogram(queryImage, 3); // 3 bits depth

        File datasetDir = new File(datasetDirectoryPath);
        File[] files = datasetDir.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null) {
            throw new IOException("Dataset directory is empty or does not exist.");
        }

        PriorityQueue<ImageSimilarity> queue = new PriorityQueue<>((a, b) -> Double.compare(b.similarity, a.similarity));

        for (File file : files) {
            ColorHistogram datasetHistogram = new ColorHistogram(file.getAbsolutePath());
            double similarity = queryHistogram.compare(datasetHistogram);
            queue.add(new ImageSimilarity(file.getName(), similarity));
        }

        // Make sure to poll the top 5 most similar images after comparing all
        int count = Math.min(5, queue.size());
        for (int i = 0; i < count; i++) {
            ImageSimilarity sim = queue.poll();
            System.out.printf("%d. %s (Similarity: %.12f)%n", i + 1, sim.fileName, sim.similarity);
        }
    }

    private static class ImageSimilarity {
        String fileName;
        double similarity;

        ImageSimilarity(String fileName, double similarity) {
            this.fileName = fileName;
            this.similarity = similarity;
        }
    }
}