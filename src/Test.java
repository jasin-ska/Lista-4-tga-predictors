import java.io.IOException;
import java.util.Arrays;

public class Test {


  public static void main(String[] args) throws IOException {
    TgaAdapter.init("./testy4/example0.tga");
    System.out.println("Height: " + TgaAdapter.height);
    System.out.println("Width: " + TgaAdapter.width);

    System.out.println("img length: " + TgaAdapter.img.length);
    System.out.println("h * w * 3 = " + TgaAdapter.height * TgaAdapter.width * 3);

    Pixel[][] predictedImg = new Pixel[TgaAdapter.height][TgaAdapter.width];
    for(int pred = 1; pred <= 8; pred++) {
      for(int i = 0; i < TgaAdapter.height; i++)
        for(int j = 0; j < TgaAdapter.width; j++) {
          predictedImg[i][j] = Predictors.predict(1, i, j);
        }

    }



  }
}
