package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	//DataProvider 1
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		/*
		./ Cái này cũng tương tự như System.getProperty của user.dir. 
		Trước đây nếu bạn muốn lấy vị trí project hiện tại chúng ta sử dụng System.getProperty của user.dir
		Tương tự, bạn cũng có thể sử dụng ./ Dấu chấm (.) cũng
 		đại diện cho vị trí project hiện tại. 
		*/
	    String path=".\\testData\\Opencart_LoginData.xlsx"; //taking xl file from testData
	    
	    ExcelUtility xlutil = new ExcelUtility(path); //creating an object for XLUtility

	    int totalrows = xlutil.getRowCount("Sheet1");
	    int totalcols = xlutil.getCellCount("Sheet1",1);

	    String logindata[][] = new String[totalrows][totalcols]; //created for two dimension array which can store data

	    for(int i=1;i<=totalrows;i++) //read the data from xl storing in two dimension array
	    {
	        for(int j=0;j<totalcols;j++) //0  i is rows j is col
	        {
	            logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j); //1,0
	        }
	    }

	    return logindata; //returning two dimension array
	}
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4
}
