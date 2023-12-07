package Core;

import org.testng.annotations.AfterTest;

public class FailTestCaseRun {
	
	@AfterTest
	public void ronFailTestCase() {
		
		TestNG obj = new TestNG();
		
		List<String> list= new ArrayList<String>();

		  list.add("D:\\Workspace_Eclipse\\TestNGDemo8\\test-output\\Suite\\testng-failed.xml");

		  obj.setTestSuites(list);

		  obj.run();
	}

}
