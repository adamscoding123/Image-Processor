package se.kth.mmhaa.demo1.model;

public class HistogramCalc {

    // Metod för att beräkna histogrammet
    public int[][] calculateHistogram(int[][] imageData) {
        int[][] histogram = new int[4][256]; // 4 färger (Alpha(transparens), Röd, Grön, Blå) och 256 intensiteer

        //Gå igenom varje pixel i bilden (loop)
        for (int[] row : imageData) {
            for (int pixel : row) {
                //Extract intensiteter för röd, grön och blå färg från pixeln (enligt Anders används följande maskningar)
                int alphaValue = ((pixel >> 24) & 0xff); // alpha
                int rValue = ((pixel >> 16) & 0xff); // red
                int gValue = ((pixel >> 8) & 0xff); // green
                int bValue = pixel & 0xff; // blue

                //Uppdatera histogrammet för varje färg
                histogram[0][alphaValue]++;    // Alpha kanal
                histogram[1][rValue]++;  // Red kanal
                histogram[2][gValue]++;   // Green kanal
                histogram[3][bValue]++;   // Blue kanal

            }
        }

        return histogram; // Returnera histogramdata
    }

}
