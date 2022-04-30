import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TgaAdapter {
  public static int height;
  public static int width;

  public static Pixel[][] img;
  private static byte[] file;

  // TGA format
  private static final  int headerSize = 18; // bytes
  private static final int[] widthBytes = {12, 13};
  private static final int[] heightBytes = {14, 15};

  public static void init(String path) throws IOException {
    file = Files.readAllBytes(Paths.get(path));
    width = (unsignedByte(file[widthBytes[1]]) << 8) | unsignedByte(file[widthBytes[0]]);
    height = (unsignedByte(file[heightBytes[1]]) << 8) | unsignedByte(file[heightBytes[0]]);
    readImg();

    //printHeader(file);
  }

  public static void readImg() {
    img = new Pixel[height][width];
    int fileIt = headerSize;
    int r, g, b;
    for(int i = 0; i < height; i++)
      for(int j = 0; j < width; j++) {
        b = unsignedByte(file[fileIt++]);
        g = unsignedByte(file[fileIt++]);
        r = unsignedByte(file[fileIt++]);
        img[height - i - 1][j] = new Pixel(r, g, b); // images from tests start at bottom-left (can be checked in header)
      }
  }

  // just for debugging
  public static void printHeader(byte[] file) {
    System.out.println("header:");
    System.out.println(file[0]);
    System.out.println(file[1]);
    System.out.println(file[2]);
    System.out.println(((file[3] << 8) | (file[4]))+", "+((file[5] << 8) | (file[6]))+", "+file[7]);
    System.out.print(((file[8] << 8) | (file[9]))+", ");
    System.out.print(((file[10] << 8) | (file[11]))+", ");
    System.out.print(((file[13] << 8 & 0xff00) | (file[12]& 0x00ff))+", ");
    System.out.print(((file[15] << 8 & 0xff00) | (file[14]& 0x00ff))+", ");
    System.out.println(file[16] +", "+file[17]);

    System.out.println("pierwszy pixel:" + img[0][0].R + " " + img[0][0].G + " " + img[0][0].B);
    System.out.println("drugi pixel:" + img[0][1].R + " " + img[0][1].G + " " + img[0][1].B);

    System.out.println("ostatni w 2 rz pixel:" + img[0][width-1].R + " " + img[0][width-1].G + " " + img[0][width-1].B);

    System.out.println("ostatni pixel:" + img[height-1][width-1].R + " " + img[height-1][width-1].G + " " + img[height-1][width-1].B);
    System.out.println("przedostatni pixel:" + img[height-1][width-2].R + " " + img[height-1][width-2].G + " " + img[height-1][width-2].B);
  }

  private static int unsignedByte(byte b) {
    return b & 0xFF;
  }

  public static Pixel getPixel(int i, int j) {
    if(i < 0 || j < 0 || i > height || j > width) return new Pixel(0, 0, 0);
    else return img[i][j];
  }

}


