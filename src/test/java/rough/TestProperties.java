package rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

	public static void main(String[] args) throws IOException {
		System.out.println(System.getProperty("user.dir"));


		Properties config = new Properties();

		FileInputStream fiStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
	
		config.load(fiStream);
		System.out.println(config.getProperty("browser"));
	}
	

}
