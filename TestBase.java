package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.gargoylesoftware.htmlunit.javascript.host.URL;

import utilities.DbManager;
import utilities.ExcelReader;
import utilities.MonitoringMail;

public class TestBase 
{
	public static WebDriver driver;
	public static Properties OR=new Properties();
	public static Properties Config=new Properties();
	public static FileInputStream fis;
	public static Logger log=Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\Excel\\Input_OutPut_File.xlsx");
    public static MonitoringMail mail=new MonitoringMail();
    public static WebDriverWait wait;
	final String USERNAME = "vivekkumarpandey1";
	final String AUTOMATE_KEY = "DM3KPrpU4KVzPNh7qFAS";
	final  String url= "hLttp://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	@BeforeSuite
	public void setUp() throws FileNotFoundException, ClassNotFoundException, SQLException
	{
		
		if(driver==null)
		{
			
			try {
				fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\OR.properties");
				OR.load(fis);
				log.debug("OR properties file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\Confing.properties");
				Config.load(fis);
				log.debug("Config properties file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(Config.getProperty("browser").equals("ff"))
			{
				driver=new FirefoxDriver();
			}
			
			/*else if(Config.getProperty("browser").equals("chrome"))
			{
			DesiredCapabilities caps = new DesiredCapabilities();
	        
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("browser", "Chrome");
			caps.setCapability("browser_version", "86");
			caps.setCapability("name", "vivekkumarpandey1's First Test");
			
			
			
			driver = new RemoteWebDriver((CommandExecutor) new URL(url),caps);
			
				//driver=new ChromeDriver();
			}*/
	
			
			
		
			
			wait=new WebDriverWait(driver,10);
			DbManager.setMysqlDbConnection();
			
			driver.get(Config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			
			
		}
		
	}
	public static void click(String locator)
	{
		if(locator.endsWith("_xpath"))
		{
		try{
		driver.findElement(By.xpath(OR.getProperty(locator))).click();
		log.debug("Clicking on an element"+locator);
		}
		catch(Throwable t)
		{
			log.debug(t.getMessage());
		}
		}
		
		else if(locator.endsWith("_css"))
		{
		try{
		driver.findElement(By.cssSelector((OR.getProperty(locator)))).click();
		log.debug("Clicking on an element"+locator);
		}
		catch(Throwable t)
		{
			log.debug(t.getMessage());
		}
		}
	}
	
	public static void type(String key, String value)
	{		
		if(key.endsWith("_xpath"))
		{
		driver.findElement(By.xpath(OR.getProperty(key))).sendKeys(value);
		}
		
		else if(key.endsWith("_css"))
		{
		driver.findElement(By.cssSelector(OR.getProperty(key))).sendKeys(value);
		}
		else if(key.endsWith("_id"))
		{
		driver.findElement(By.id(OR.getProperty(key))).sendKeys(value);
		}
		
		log.debug("Typing on an element"+key+"Entered value"+value);
		
	}
	
	public static boolean isElementPresent(String locator)
	{
		if(locator.endsWith("_xpath"))
		{
		try{
		driver.findElement(By.xpath(OR.getProperty(locator)));
		log.debug("Finding presence of an element:-"+locator);
		return true;
		}
		catch(Throwable t)
		{
			log.debug(t.getMessage());
			return false;
		}
		}
		
		else if(locator.endsWith("_css"))
		{
		try{
		driver.findElement(By.cssSelector((OR.getProperty(locator))));
		log.debug("Finding presence of an element:-"+locator);
		return true;
		}
		catch(Throwable t)
		{
			log.debug("Error while finding element"+locator+t.getMessage());
			return false;
		}
		}
		return false;
		
	}
	
	@AfterSuite	
	public void tearDown()
	{
		driver.quit();
		log.debug("Test successfully executed !!!");
	}

}
