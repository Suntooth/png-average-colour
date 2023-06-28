import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import javax.imageio.ImageIO;
import java.awt.Color;

public class averagemain {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		fileWrite(averageColourRGB());
		long endTime = System.currentTimeMillis();
		long duration = (endTime - startTime);
		System.out.println("Run time: " + duration + "ms");
	}
	
	public static String averageColourRGB() { /* average r then g then b then turn that into a colour */
		String hex = "";
		
		try {
			BufferedImage img = ImageIO.read(new File("image.png"));
			int height = img.getHeight(), width = img.getWidth();
			
			
			Color avgColor;
			int i, j, q = 0;
			int pixel, pixelTemp;
			int sumRed = 0, sumGreen = 0, sumBlue = 0;
			int avgRed, avgGreen, avgBlue;
			
			for(i=0;i<height;i++) {
				for(j=0;j<width;j++) {
					pixel = img.getRGB(j, i);
					pixelTemp = (pixel >> 16) & 0x000000FF;
					sumRed += pixelTemp;
					pixelTemp = (pixel >> 8) & 0x000000FF;
					sumGreen += pixelTemp;
					pixelTemp = pixel & 0x000000FF;
					sumBlue += pixelTemp;
					q++;
				}
			}
			
			avgRed = sumRed / q;
			avgGreen = sumGreen / q;
			avgBlue = sumBlue / q;
			q = 0;
			
			avgColor = new Color(avgRed,avgGreen,avgBlue);
			hex = "#"+Integer.toHexString(avgColor.getRGB()).substring(2);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return hex;
	}
	
	public static void fileWrite(String val1) {
		try {
			File listFile = new File("result.txt");
			listFile.createNewFile();
			
			try (FileWriter writeFile = new FileWriter("result.txt")) {
					writeFile.write(val1);
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
