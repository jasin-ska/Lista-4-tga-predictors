public class Pixel {
  public byte R;
  public byte G;
  public byte B;

  public Pixel(byte r, byte g, byte b) {
    R = r;
    G = g;
    B = b;
  }

  public static void add(Pixel p1, Pixel p2) {
    p1.R += p2.R;
    p1.G += p2.G;
    p1.B += p2.B;
  }
  public static void sub(Pixel p1, Pixel p2) {
    p1.R -= p2.R;
    p1.G -= p2.G;
    p1.B -= p2.B;
  }
  public static void div2(Pixel p) {
    p.R /= 2;
    p.G /= 2;
    p.B /= 2;
  }

}
