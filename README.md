
## Image Similarity System

**Overview**  
The Image Similarity System is a Java-based project that calculates the similarity between a given query image and a set of images stored in a dataset. Using color histograms to represent and compare images, the system identifies and lists the images most similar to the query. This approach is useful for applications involving visual search, content-based image retrieval, and pattern recognition.

## Features
- **Color Histogram Representation**: Each image is represented using a color histogram, a compact summary of color distribution.
- **Histogram Comparison**: Similarity between images is calculated by comparing their histograms using histogram intersection.
- **Multi-Format Support**: Supports `.jpg` and `.ppm` image formats.
- **Top Matches**: Identifies the top 5 images from the dataset that are most similar to the query image.

## Project Structure
- **ColorHistogram.java**: Computes and stores the color histogram for an image. Provides a method to compare histograms to calculate similarity.
- **ColorImage.java**: Loads image data in `.jpg` or `.ppm` format and represents it as pixel values.
- **SimilaritySearch.java**: Manages the search process by comparing the query image's histogram with each image in the dataset, identifying and printing the most similar matches.

## Installation
1. Clone this repository or download the files.
2. Compile the Java files:

```bash
    javac ColorHistogram.java ColorImage.java SimilaritySearch.java
```

3. Ensure the dataset directory contains histogram files (`.txt`) for each image in the format compatible with `ColorHistogram.java`.

## Usage
Run the program with the following command:
```bash
java SimilaritySearch <queryImage> <imageDatasetDirectory>
```

- **`<queryImage>`**: The path to the query image file. Supported formats are `.jpg` and `.ppm`.
- **`<imageDatasetDirectory>`**: The path to the directory containing the dataset of images in `.txt` histogram format.

### Example
```bash
java SimilaritySearch queryimages/sample.jpg imageDataset
```

## How It Works
1. **Image Loading and Histogram Computation**: `ColorImage` loads the query image and converts it into RGB values. `ColorHistogram` then processes these values to compute a color histogram with a depth of 3 bits per channel.
2. **Dataset Histogram Comparison**: `SimilaritySearch` loads histograms from `.txt` files in the dataset directory, comparing each to the query histogram using intersection similarity.
3. **Results Display**: The top 5 most similar images are displayed with their similarity scores.

## File Format Requirements
- **Dataset Histograms**: Each dataset image must have a precomputed histogram saved in a `.txt` file in the dataset directory, formatted with space-separated values on a single line.

## Example Output
Upon running, the system outputs the top 5 most similar images in the dataset:
```
1. image1.txt (Similarity: 0.825345)
2. image2.txt (Similarity: 0.812455)
3. image3.txt (Similarity: 0.800123)
4. image4.txt (Similarity: 0.798765)
5. image5.txt (Similarity: 0.789432)
```

## Future Enhancements
- Expand histogram comparison methods (e.g., Chi-square, Bhattacharyya distance).
- Add support for more image formats (e.g., PNG, BMP).
- Implement automatic histogram generation for dataset images.