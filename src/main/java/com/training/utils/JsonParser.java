package com.training.utils;

import java.io.File;

import com.jayway.jsonpath.JsonPath;
import com.training.constants.Constants;

public class JsonParser {
	
	//Never hardcode
	
	public static String getValue(String path) {
		String temp="";
		try {
		File file = new File(Constants.JSONFILEPATH);
		temp= JsonPath.read(file, "$."+path);
		}
		catch(Exception e) {
			
		}
		return temp;
	}

}
