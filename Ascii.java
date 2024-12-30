package src;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Ascii {

    static String luminanceString = " `.-':_,^=;><+!rc*/z?sLTv)J7(|Fi{C}fI31tlu[neoZ5Yxjya]2ESwqkP6h9d4VpOGbUAKXHm8RD#$Bg0MNWQ%&@";
    static double[] luminanceValues = {0, 0.0751, 0.0829, 0.0848, 0.1227, 0.1403, 0.1559, 0.185, 0.2183, 0.2417, 0.2571, 0.2852, 0.2902, 0.2919, 0.3099, 0.3192, 0.3232, 0.3294, 0.3384, 0.3609, 0.3619, 0.3667, 0.3737, 0.3747, 0.3838, 0.3921, 0.396, 0.3984, 0.3993, 0.4075, 0.4091, 0.4101, 0.42, 0.423, 0.4247, 0.4274, 0.4293, 0.4328, 0.4382, 0.4385, 0.442, 0.4473, 0.4477, 0.4503, 0.4562, 0.458, 0.461, 0.4638, 0.4667, 0.4686, 0.4693, 0.4703, 0.4833, 0.4881, 0.4944, 0.4953, 0.4992, 0.5509, 0.5567, 0.5569, 0.5591, 0.5602, 0.5602, 0.565, 0.5776, 0.5777, 0.5818, 0.587, 0.5972, 0.5999, 0.6043, 0.6049, 0.6093, 0.6099, 0.6465, 0.6561, 0.6595, 0.6631, 0.6714, 0.6759, 0.6809, 0.6816, 0.6925, 0.7039, 0.7086, 0.7235, 0.7302, 0.7332, 0.7602, 0.7834, 0.8037, 0.9999};

    public static void main(String[] args) throws Exception {
        if(args.length < 2)
            System.out.println("Some values are missing... Try: java AsciiGen demo.jpg 0");
        else{
            int[] characterDimension = {2, 4};
            System.out.println("Printing...");

            BufferedImage image = ImageIO.read(new File(args[0]));

            for(int y = 0; y < image.getHeight(); y+=characterDimension[1]){
                for(int x = 0; x < image.getWidth(); x+=characterDimension[0]){

                    int red = 0, green = 0, blue = 0;

                    for(int i = y; i < image.getHeight() && i < y+characterDimension[1]; i++){
                        for(int j = x; j < image.getWidth() && j < x+characterDimension[0]; j++){
                            int color = image.getRGB(j, i);

                            // extract each color component
                            red   += (color >>> 16) & 0xFF;
                            green += (color >>>  8) & 0xFF;
                            blue  += (color >>>  0) & 0xFF;
                        }
                    }

                    red = red/(characterDimension[1]*characterDimension[0]);
                    green = green/(characterDimension[1]*characterDimension[0]);
                    blue = blue/(characterDimension[1]*characterDimension[0]);

                    // calc luminance in range 0.0 to 1.0; using SRGB luminance constants
                    float luminance = (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;
                    if(args.length > 1 && args[1].equals("0")){
                        luminance = 1-luminance;
                    }

                    int character = 0;
                    
                    while (luminanceValues[character] < luminance) {
                        if(character + 1 == luminanceValues.length)
                            break;
                        character++;
                    }
                    
                    System.out.print(luminanceString.charAt(character));
                }
                System.out.println();
            }
        }
    }
}
