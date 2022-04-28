import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TgaAdapter {
  public static int height;
  public static int width;

  public static Pixel[][] img;
  private static byte[] file;

  //private int footerSize = 26; // bytes

  private static int headerSize = 18; // bytes
  private static int[] widthBytes = {12, 13};
  private static int[] heightBytes = {14, 15};

  public static void init(String path) throws IOException {
    file = Files.readAllBytes(Paths.get(path));
    //checkFooter(file);

    width = (unsignedByte(file[widthBytes[1]]) << 8) | unsignedByte(file[widthBytes[0]]);
    height = (unsignedByte(file[heightBytes[1]]) << 8) | unsignedByte(file[heightBytes[0]]);

    readImg();
  }

  public static void readImg() {
    img = new Pixel[height][width];
    int fileIt = headerSize;
    byte r, g, b;
    for(int i = 0; i < height; i++)
      for(int j = 0; j < width; j++) {
        r = file[fileIt++];
        g = file[fileIt++];
        b = file[fileIt++];
        img[i][j] = new Pixel(r, g, b);
      }
  }

  public void printHeader(byte[] file) {
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
  }

  private static int unsignedByte(byte b) {
    int w = b;
    if(w < 0) w += 256;
    return w;
  }

  /*private void checkFooter(byte[] file) {
    if(file[file.length-2] != 46 || file[file.length-1] != 0) footerSize = 0;
  }*/

  public static Pixel getPixel(int i, int j) {
    if(i < 0 || j < 0 || i > height || j > width) return new Pixel((byte)0, (byte)0, (byte)0);
    else return img[i][j];
  }

}


