package com.training.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.training.browser.DriverManager;
import com.training.constants.Constants;
import com.training.listeners.ListenerClass;




public class TestUtils {


	public static FileInputStream fs;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static List<String> testCases= new ArrayList<String>();
	public static List<String> runStatus= new ArrayList<String>();
	public static List<String> testDescription= new ArrayList<String>();
	public static List<String> invocationCount= new ArrayList<String>();
	public static List<String> priority= new ArrayList<String>();
	public static HashMap<Integer,String> rowAndTestCaseMap=new HashMap<Integer,String>();
	public static String screenshotPath=Constants.SCREENSHOTPATH;


	/*
	 * Reads the data from the excel sheet and store the values in respective lists which will be used in annotation transformer class
	 */

	public static void getRunStatus() throws Exception {
		try {
			fs=new FileInputStream(Constants.TESTDATAPATH);
			workbook=new XSSFWorkbook(fs);
			sheet=workbook.getSheet("RunManager");
			for(int i=1;i<=getLastRowNum("RunManager");i++) {
				//rowAndTestCaseMap.put(i,sheet.getRow(i).getCell(0).getStringCellValue().toString());
				testCases.add(getCellContent("RunManager", i, "TestCaseName"));
				testDescription.add(getCellContent("RunManager", i, "Test Case Description"));
				runStatus.add(getCellContent("RunManager", i, "Execute"));
				invocationCount.add(getCellContent("RunManager", i, "InvocationCount"));
				priority.add(getCellContent("RunManager", i, "Priority"));
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Takes rowname and sheetname as parameter
	 * return row number based of rowname
	 */
	public static int getRowNumForRowName(String sheetname,String rowName) {
		int rownum=0;
		sheet=workbook.getSheet(sheetname);
		for(int i=1;i<=getLastRowNum(sheetname);i++) {
			if(rowName.equalsIgnoreCase(sheet.getRow(i).getCell(0).getStringCellValue())) {
				rownum=i;
				break;
			}
		}

		return rownum;
	}

	/*
	 * Takes columnname and sheetname as parameter
	 * return column number based of columnheader
	 */

	public static int getColumnNumForColumnName(String sheetname, String columnname) {
		int colnum=0;
		sheet=workbook.getSheet(sheetname);
		for(int i=0;i<getLastColumnNum(sheetname, 0);i++) {
			if(columnname.equalsIgnoreCase(sheet.getRow(0).getCell(i).getStringCellValue())) {
				colnum=i;
				break;
			}
		}

		return colnum;

	}


	/*
	 * Takes sheetname as parameter
	 * return last row number of the sheet
	 */
	public static int getLastRowNum(String sheetname) {
		return workbook.getSheet(sheetname).getLastRowNum();
	}

	/*
	 * Takes sheetname, row number as parameter
	 * return last cell number of the row
	 */
	public static int getLastColumnNum(String sheetname, int rownum) {
		return workbook.getSheet(sheetname).getRow(rownum).getLastCellNum();
	}


	/*
	 * Takes sheetname, row number, column number as parameter
	 * return cell value
	 */
	public static String getCellContent(String sheetname,int rownum,int colnum) {
		sheet=workbook.getSheet(sheetname);
		int celltype=sheet.getRow(rownum).getCell(colnum).getCellType();
		Cell cell=sheet.getRow(rownum).getCell(colnum);
	
		String temp="";
		if(celltype==Cell.CELL_TYPE_STRING) {
			temp= cell.getStringCellValue();
		}
		else if(celltype==Cell.CELL_TYPE_NUMERIC || celltype==Cell.CELL_TYPE_FORMULA) {
			temp=String.valueOf(cell.getNumericCellValue());
		}
		else if(celltype==cell.CELL_TYPE_BOOLEAN) {
			temp=String.valueOf(cell.getBooleanCellValue());
		}
		else if(celltype==Cell.CELL_TYPE_ERROR) {
			temp=String.valueOf(cell.getErrorCellValue());
		}
		return temp;



	}

	/*
	 * Takes sheetname, row number, column name as parameter
	 * return cell value
	 */
	public static String getCellContent(String sheetname,int rownum,String columnname) {
		sheet=workbook.getSheet(sheetname);
		int colnum=getColumnNumForColumnName(sheetname, columnname);
		return getCellContent(sheetname,rownum,colnum);

	}

	/*
	 * Takes sheetname, row name, column name as parameter
	 * return cell value
	 */
	public static String getCellContent(String sheetname,String rowname,String columnname) {
		sheet=workbook.getSheet(sheetname);
		int rownum=getRowNumForRowName(sheetname, rowname);
		int colnum=getColumnNumForColumnName(sheetname, columnname);

		return getCellContent(sheetname,rownum,colnum);

	}


	public static void setCellContent(String sheetname,int rownum,int colnum,String value) {
		sheet=workbook.getSheet(sheetname);
		if(rownum<=getLastRowNum(sheetname)) {
			sheet.getRow(rownum).createCell(colnum).setCellValue(value);
		}
		else {
			sheet.createRow(rownum).createCell(colnum).setCellValue(value);
		}
	}


	public static void setCellContent(String sheetname,String rowname,int colnum,String value) {
		sheet=workbook.getSheet(sheetname);
		setCellContent(sheetname, getRowNumForRowName(sheetname, rowname), colnum, value);
	}

	public static void setCellContent(String sheetname,int rownum,String colname,String value) {
		sheet=workbook.getSheet(sheetname);
		setCellContent(sheetname,rownum, getColumnNumForColumnName(sheetname, colname), value);
	}
	public static void setCellContent(String sheetname,String rowname,String colname,String value) {
		sheet=workbook.getSheet(sheetname);
		setCellContent(sheetname,getRowNumForRowName(sheetname, rowname), 
				getColumnNumForColumnName(sheetname, colname), value);
	}






	
	 //* Captures screenshot and returns the screenshot path

	public static String pullScreenshotPath()  {

		String destination=null;
		if(JsonParser.getValue("config.global.screenshotrequired").equalsIgnoreCase("yes")) {
			File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
			try {
				if(screenshotPath.equals("")) {

					destination=System.getProperty("user.dir")+"\\screenshots\\" +ListenerClass.getTestCaseName()+"\\"+ System.currentTimeMillis()+new Random().nextInt(20)+".png";
					FileUtils.copyFile(scrFile, new File(destination));
				}
				else {
					destination=screenshotPath+"\\screenshots\\" +ListenerClass.getTestCaseName().replaceAll(" ","")+"\\"+ System.currentTimeMillis() +new Random().nextInt(20)+".png";
					FileUtils.copyFile(scrFile, new File(destination));
				}

			}
			catch(Exception e) {
				e.printStackTrace();

			}

		}

		return destination;

	}

	/*
	 * Gives a base64 image which is used to append the screenshots in the extent report.
	 * Converting to base64 format avoids screenshots broken image if sent the exent report through email.
	 */
	public static String getBase64Image(String screenshotpath) {
		String base64 = null;
		try {
			InputStream is= new FileInputStream(screenshotpath);
			byte[] imageBytes = IOUtils.toByteArray(is);
			base64 = Base64.getEncoder().encodeToString(imageBytes);
		}
		catch (Exception e) {

		}
		return base64;

	}

	/*
	 * Sends test results to the respective stakeholders
	 * Make sure to set the parameter SendExecutionResultsInEmail to Yes in TestRunDetails.properties
	 */



	/*
	 * 
	 * DataProvider method used to provide data for multiple iterations.
	 * Never try to use multiple iterations when the invocation count is greater than 1. It may result in adhoc results.
	 * As long as the first name of the TestData has the same test case name it will be treated as iteration.
	 * 
	 */
	@DataProvider(name="dataProviderForIterations",parallel=false)
	public static Object[][] supplyDataForIterations(Method m){
		return getDataForDataprovider(Constants.TESTDATAPATH,Constants.TESTDATASHEETNAME,m.getName());
	}

	/*
	 * Finding number of iterations available for test case and return the data accordingly.
	 * Using hashtable avoids multiple parameters entry to the test case.
	 * 
	 */
	private static Object[][] getDataForDataprovider(String testdata, String sheetname, String testcasename) {
		int totalcolumns=getLastColumnNum(sheetname, 0);
		ArrayList<Integer> rowscount=getNumberofIterationsForATestCase(sheetname, testcasename);


		Object[][] b=new Object[rowscount.size()][1];

		Hashtable<String,String> table =null;

		for(int i=1;i<=rowscount.size();i++) {
			table=new Hashtable<String,String>();
			for(int j=0;j<totalcolumns;j++){
				table.put(getCellContent(sheetname, 0, j), getCellContent(sheetname, rowscount.get(i-1), j));
				b[i-1][0]=table;
			}
		}
		return b;
	}

	/*
	 * Used to return the rownumber of the test cases for multiple iterations.
	 * Suppose if testcase 1 is available in row 4 and 7 is test data , it return the arraylist with values 4,7
	 */
	private static ArrayList<Integer> getNumberofIterationsForATestCase(String sheetname,String testcasename) {
		ArrayList<Integer> a=new ArrayList<Integer>();
		for(int i=1;i<=getLastRowNum(sheetname);i++) {
			if(testcasename.equalsIgnoreCase(getCellContent(sheetname, i, 0))) {
				if(getCellContent(sheetname, i, 1).equalsIgnoreCase("yes")) {
					a.add(i);
				}
			}
		}

		return a;
	}

	public static int getRowNumForTheTestCase(String sheetname,String testcasename) {
		int rownum = 0;
		for(int i=1;i<=getLastRowNum(sheetname);i++) {
			if(testcasename.equalsIgnoreCase(getCellContent(sheetname, i, 0))) {
				rownum=i;
				break;
			}
		}
		return rownum;
	}

}
