import java.io.IOException;
import java.io.InputStreamReader;


/*
 * Various static funtions to add features that are missing from j2me
 * */
class J2MEUtils{
	
	//from stackoverflow: http://stackoverflow.com/questions/8405984/j2me-hexidecimal-string-to-integer
	public static int HexStringToInt(String s) {
	    int base = 10;
	    if (s.toLowerCase().startsWith("0x")) {
	        base = 16;
	        s = s.substring(2);
	    }
	    return Integer.parseInt(s, base);
	}
	
	//from stackoverflow: http://stackoverflow.com/questions/10282519/j2me-alternative-for-java-io-bufferedreader
   public static String readBufferedLine(InputStreamReader reader) throws IOException {
        StringBuffer line = new StringBuffer();
        int c = reader.read();

        while (c != -1 && c != '\n') {
            line.append((char)c);
            c = reader.read();
        }

        if (line.length() == 0) {
            return null;
        }

        return line.toString();
    }
   
   //from Open Source JAVA: http://www.java2s.com/Open-Source/Java/6.0-JDK-Modules/j2me/java/awt/Color.java.htm
   public static int HSBtoRGB(float hue, float saturation, float brightness) {
       int r = 0, g = 0, b = 0;
       if (saturation == 0) {
           r = g = b = (int) (brightness * 255);
       } else {
           double h = (hue - Math.floor(hue)) * 6.0;
           double f = h - java.lang.Math.floor(h);
           double p = brightness * (1.0 - saturation);
           double q = brightness * (1.0 - saturation * f);
           double t = brightness * (1.0 - (saturation * (1.0 - f)));
           switch ((int) h) {
           case 0:
               r = (int) (brightness * 255);
               g = (int) (t * 255);
               b = (int) (p * 255);
               break;

           case 1:
               r = (int) (q * 255);
               g = (int) (brightness * 255);
               b = (int) (p * 255);
               break;

           case 2:
               r = (int) (p * 255);
               g = (int) (brightness * 255);
               b = (int) (t * 255);
               break;

           case 3:
               r = (int) (p * 255);
               g = (int) (q * 255);
               b = (int) (brightness * 255);
               break;

           case 4:
               r = (int) (t * 255);
               g = (int) (p * 255);
               b = (int) (brightness * 255);
               break;

           case 5:
               r = (int) (brightness * 255);
               g = (int) (p * 255);
               b = (int) (q * 255);
               break;
           }
       }
       return 0xff000000 | (r << 16) | (g << 8) | (b << 0);
   }
   
   //from Open Source JAVA: http://www.java2s.com/Open-Source/Java/6.0-JDK-Modules/j2me/java/awt/Color.java.htm
   public static float[] RGBtoHSB(int r, int g, int b, float[] hsbvals) {
       float hue, saturation, brightness;
       if (hsbvals == null) {
           hsbvals = new float[3];
       }
       int cmax = (r > g) ? r : g;
       if (b > cmax) cmax = b;
       int cmin = (r < g) ? r : g;
       if (b < cmin) cmin = b;
       brightness = ((float) cmax) / 255.0f;
       if (cmax != 0)
           saturation = ((float) (cmax - cmin)) / ((float) cmax);
       else
           saturation = 0;
       if (saturation == 0)
           hue = 0;
       else {
           float redc = ((float) (cmax - r)) / ((float) (cmax - cmin));
           float greenc = ((float) (cmax - g)) / ((float) (cmax - cmin));
           float bluec = ((float) (cmax - b)) / ((float) (cmax - cmin));
           if (r == cmax)
               hue = bluec - greenc;
           else if (g == cmax)
               hue = 2.0f + redc - bluec;
           else
               hue = 4.0f + greenc - redc;
           hue = hue / 6.0f;
           if (hue < 0)
               hue = hue + 1.0f;
       }
       hsbvals[0] = hue;
       hsbvals[1] = saturation;
       hsbvals[2] = brightness;
       return hsbvals;
   }
   
   //from javagaming: http://www.java-gaming.org/index.php?topic=4505.0, thanks @Abuse
   public static void fillArray(int [] array, int value)
   {
      int length = Math.min(array.length,16);
      for(int i=0; i< length;i++)
      {
         array[i] = value;
      }
      
      while(length<<1 < array.length)
      {
         System.arraycopy(array,0,array,length,length);
         length<<=1;
      }
      
      System.arraycopy(array,0,array,length,array.length-length);
   }
   
   public static void fillArray(boolean [] array, boolean value)
   {
      int length = Math.min(array.length,16);
      for(int i=0; i< length;i++)
      {
         array[i] = value;
      }
      
      while(length<<1 < array.length)
      {
         System.arraycopy(array,0,array,length,length);
         length<<=1;
      }
      
      System.arraycopy(array,0,array,length,array.length-length);
   }
}