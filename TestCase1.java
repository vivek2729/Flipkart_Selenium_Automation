package TestCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.URL;

import Base.TestBase;

public class TestCase1 extends TestBase
{
	
	@Test(dataProvider="dp")
	public void Sheet1(String searchInput)throws InterruptedException, IOException
	{
	
		click("Login_Popup_close_button_xpath");
		Thread.sleep(2000);
		type("Search_input_css",searchInput);
		click("Autosuggest_css");
		Thread.sleep(3000);
		click("Search_button_css");
		Thread.sleep(3000);
		click("brand_xpath");
		Thread.sleep(3000);
		click("Flipkart_Assured_xpath");
		Thread.sleep(2000);
		click("High_to_low_button_xpath");
		Thread.sleep(2000);
		List<WebElement> phoneNameList=driver.findElements(By.xpath("//div/a[@rel='noopener noreferrer']/div[2]/div[@class='col col-7-12']/div[contains(text(),'Samsung Galaxy')]"));
		List<WebElement> priceList=driver.findElements(By.xpath("//div/a[@rel='noopener noreferrer']/div[2]/div[contains(@class,'col col-5-12')]/div/div/div[contains(@class,'WHN')]"));
		List<WebElement> LinksList=driver.findElements(By.xpath("//div/a[@rel='noopener noreferrer']"));
		int i=1,j=0;
		for(WebElement we:phoneNameList)
		{
			String str=we.getText();
			ExcelUpdateForPhoneName.ExcelUpdate(i, j, str);
			i++;
			
		}
		
		int k=1,l=1;
		
		for(WebElement we:priceList)
		{
			String str=we.getText();
			ExcelUpdateForPricing.ExcelUpdate(k, l, str);
			k++;
		}
		
int m=1,n=2;
		
		for(WebElement we:LinksList)
		{
			String str=we.getAttribute("href");
			ExcelUpdateForLinks.ExcelUpdate(m, n, str);
			m++;
		}
	}
	
	
	@DataProvider(name="dp")
	public static Object[][] getData()
	{
		String sheetName="Sheet1";
		int rows=excel.getRowCount(sheetName);
		int cols=excel.getColumnCount(sheetName);
		
		Object[][] data =new Object[rows-1][cols];
		
		for(int rowNum=2;rowNum<=rows;rowNum++)
		{
			for(int colNum=0;colNum<cols;colNum++)
			{
				data[rowNum-2][colNum]=excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		
		return data;
		
	}
	
	
	
	
}
