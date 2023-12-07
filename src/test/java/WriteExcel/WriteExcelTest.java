package WriteExcel;

import org.testng.annotations.Test;
//import WriteExcel.WriteExcelData;
public class WriteExcelTest {
	WriteExcelData obj = new WriteExcelData();
	
	@Test
	public void writeExcelTest() throws Exception {
		obj.writeExcel("Sheet1", "Male", 0, 2);
		obj.writeExcel("Sheet1", "Male", 1, 2);
		obj.writeExcel("Sheet1", "Male", 2, 2);
	
	}
	@Test
	public void testcase2() throws Exception {
		obj.setCellData("Sheet1", 2, 1, "Bhau");
		
		
	}
	
}
