package driver;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Driver {

	public static void main(String[] args) {
		BufferedImage image = null;
		File f = null;
		String path = "C:\\Users\\Bitluntit\\Pictures\\imgs\\a.jpg";
		try {
			// read
			f = new File(path);
			image = ImageIO.read(f);

			// write
			String[] str = path.split("\\.");
			String type = str[str.length - 1];
			int count = new File("imgs\\rooms").list().length;
			String fileName = count++  + "."+ type;
			f = new File("imgs\\rooms\\" + fileName);
			ImageIO.write(image, "jpg", f);
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}

}
