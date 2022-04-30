import java.io.IOException;

public class Test {

    private static final float[] minAll = {10f, 0};
    private static final float[] minR = {10f, 0};
    private static final float[] minG = {10f, 0};
    private static final float[] minB = {10f, 0};

    public static void main(String[] args) throws IOException {
        TgaAdapter.init("./testy4/example0.tga");

        System.out.print("\nBEFORE:\t");
        printEntropy(TgaAdapter.img, 0);
        System.out.println();

        Pixel[][] predictedImg = new Pixel[TgaAdapter.height][TgaAdapter.width];
        for (int pred = 1; pred <= 8; pred++) {
            for (int i = 0; i < TgaAdapter.height; i++) // predicting loop
                for (int j = 0; j < TgaAdapter.width; j++) {
                    predictedImg[i][j] = new Pixel(0, 0, 0);
                    Pixel.add(predictedImg[i][j], TgaAdapter.img[i][j]);
                    Pixel.sub(predictedImg[i][j], Predictors.predict(pred, i, j));
                }

            System.out.print("PRED" + pred + ":\t");
            printEntropy(predictedImg, pred);
        }

        System.out.println("\nMinAll = " + minAll[0] + " (pred " + (int) minAll[1] + ")");
        System.out.println("MinR = " + minR[0] + " (pred " + (int) minR[1] + ")");
        System.out.println("MinG = " + minG[0] + " (pred " + (int) minG[1] + ")");
        System.out.println("MinB = " + minB[0] + " (pred " + (int) minB[1] + ")");
    }

    private static void printEntropy(Pixel[][] img, int pred) {
        System.out.printf("all = %.6f", entropy(img, "all", pred));
        System.out.printf("\t| red = %.6f", entropy(img, "red", pred));
        System.out.printf("\t| green = %.6f", entropy(img, "green", pred));
        System.out.printf("\t| blue = %.6f\n", entropy(img, "blue", pred));
    }

    private static float entropy(Pixel[][] image, String mode, int pred) {
        float entropy = Entropy.calculateEntropy(image, mode);
        if (entropy < min(mode)[0]) {
            min(mode)[0] = entropy;
            min(mode)[1] = pred;
        }
        return entropy;
    }

    private static float[] min(String mode) {
        if (mode.equals("all")) return minAll;
        if (mode.equals("red")) return minR;
        if (mode.equals("green")) return minG;
        return minB;
    }
}
