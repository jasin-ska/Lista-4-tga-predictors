public class Predictors {

  private static Pixel pred1(int i, int j) {
    Pixel px = new Pixel((byte)0, (byte)0, (byte)0);
    Pixel.add(px, TgaAdapter.getPixel(i - 1, j));
    return px;
  }

  private static Pixel pred2(int i, int j) {
    Pixel px = new Pixel((byte)0, (byte)0, (byte)0);
    Pixel.add(px, TgaAdapter.getPixel(i, j - 1));
    return px;
  }

  private static Pixel pred3(int i, int j) {
    Pixel px = new Pixel((byte)0, (byte)0, (byte)0);
    Pixel.add(px, TgaAdapter.getPixel(i - 1, j - 1));
    return px;
  }

  private static Pixel pred4(int i, int j) {
    Pixel px = new Pixel((byte)0, (byte)0, (byte)0);
    Pixel.add(px, TgaAdapter.getPixel(i, j - 1));
    Pixel.add(px, TgaAdapter.getPixel(i - 1, j));
    Pixel.sub(px, TgaAdapter.getPixel(i - 1, j - 1));
    return px;
  }

  private static Pixel pred5(int i, int j) {
    Pixel px = new Pixel((byte)0, (byte)0, (byte)0);
    Pixel.add(px, TgaAdapter.getPixel(i - 1, j));
    Pixel.sub(px, TgaAdapter.getPixel(i - 1, j - 1));
    Pixel.div2(px);
    Pixel.add(px, TgaAdapter.getPixel(i, j - 1));
    return px;
  }

  private static Pixel pred6(int i, int j) {
    Pixel px = new Pixel((byte)0, (byte)0, (byte)0);
    Pixel.add(px, TgaAdapter.getPixel(i, j - 1));
    Pixel.sub(px, TgaAdapter.getPixel(i - 1, j - 1));
    Pixel.div2(px);
    Pixel.add(px, TgaAdapter.getPixel(i - 1, j));
    return px;
  }

  private static Pixel pred7(int i, int j) {
    Pixel px = new Pixel((byte)0, (byte)0, (byte)0);
    Pixel.add(px, TgaAdapter.getPixel(i - 1, j));
    Pixel.add(px, TgaAdapter.getPixel(i, j - 1));
    Pixel.div2(px);
    return px;
  }

  private static Pixel pred8(int i, int j) {
    Pixel px = new Pixel((byte)0, (byte)0, (byte)0);
    Pixel N = TgaAdapter.getPixel(i - 1, j);
    Pixel W = TgaAdapter.getPixel(i, j - 1);
    Pixel NW = TgaAdapter.getPixel(i - 1, j - 1);

    px.R = pred8_initial(N.R, W.R, NW.R);
    px.G = pred8_initial(N.G, W.G, NW.G);
    px.B = pred8_initial(N.B, W.B, NW.B);



    return px;
  }

  private static byte pred8_initial(byte N, byte W, byte NW) {
    byte X;
    if(NW >= Math.max(W, N))
      X = (byte)Math.max(W, N);
    else {
      if(NW <= Math.min(W, N))
        X = (byte)Math.min(W, N);
      else
        X = (byte)(W + N - NW);
    }
    return X;
  }

  public static Pixel predict(int mode, int i, int j) {
    switch(mode) {
      case 1 -> {
        return pred1(i, j);
      }
      case 2 -> {
        return pred2(i, j);
      }
      case 3 -> {
        return pred3(i, j);
      }
      case 4 -> {
        return pred4(i, j);
      }
      case 5 -> {
        return pred5(i, j);
      }
      case 6 -> {
        return pred6(i, j);
      }
      case 7 -> {
        return pred7(i, j);
      }
      case 8 -> {
        return pred8(i, j);
      }
    }
    return null;
  }
}
