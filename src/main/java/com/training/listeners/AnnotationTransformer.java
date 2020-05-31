package com.training.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import com.training.utils.TestUtils;



public class AnnotationTransformer implements IAnnotationTransformer{

	int count=0;
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub
		try {
			if(count==0) {
				TestUtils.getRunStatus();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<TestUtils.testCases.size();i++) {
			//testmethod is testcase1
			if(testMethod.getName().equalsIgnoreCase(TestUtils.testCases.get(i)))
			{	
				annotation.setDataProvider("dataProviderForIterations");								//sets the dataprovider to all the test methods
				annotation.setDataProviderClass(TestUtils.class);
				annotation.setPriority(Integer.parseInt(TestUtils.priority.get(i)));	
				//sets the priority for all the test cases based on the excel sheet input
				annotation.setDescription(TestUtils.testDescription.get(i)); 							//sets the description for all the test cases based on the excel sheet input
				annotation.setInvocationCount(Integer.parseInt(TestUtils.invocationCount.get(i)));
				//sets the invocation count for all the test cases based on the excel sheet input
				if(TestUtils.runStatus.get(i).equalsIgnoreCase("no")) {
					annotation.setEnabled(false);														//sets the enabled parameter for all the test cases based on the excel sheet input
					break;
				}
			} 
		}

		
	count++;	
		
	}

}
