package com.orangehrm.framework.util;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

public class ReadDataFromProps {

	public static Properties readprop(String filename) throws IOException {

		FileInputStream fis = new FileInputStream(filename);
		Properties prop = new Properties();
		prop.load(fis);
		return prop;
	}

}
