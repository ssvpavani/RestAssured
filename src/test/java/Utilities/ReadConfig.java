package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReadConfig {
	static Properties properties;
	static String path="C:\\Users\\ssathi\\eclipse-workspace1\\APIFramework\\src\\test\\resources\\Config\\globalconfig.properties";
	static {
		properties = new Properties();
	}
	public static void readConfig()
	{
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			properties.load(fis);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getbaseURL() throws IOException
	{
		
		readConfig();
		String value = properties.getProperty("baseurl");
		if (value != null) {
			return value;
		} else {
			throw new RuntimeException("URL not specified in config File");
		}
	}
	

}
