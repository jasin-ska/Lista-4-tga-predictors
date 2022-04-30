public class Pixel {
  public int R;
  public int G;
  public int B;

  public Pixel(int r, int g, int b) {
    R = r;
    G = g;
    B = b;
  }

  public static void add(Pixel p1, Pixel p2) {
    p1.R = (p1.R + p2.R) % 256;
    p1.G = (p1.G + p2.G) % 256;
    p1.B = (p1.B + p2.B) % 256;
  }
  public static void sub(Pixel p1, Pixel p2) {
    p1.R = (p1.R - p2.R) % 256;
    p1.G = (p1.G - p2.G) % 256;
    p1.B = (p1.B - p2.B) % 256;
  }
  public static void div2(Pixel p) {
    p.R /= 2;
    p.G /= 2;
    p.B /= 2;
  }

}
