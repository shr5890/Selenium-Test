package test;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import com.sun.jna.platform.FileUtils;

/** Generate 10 random integers in the range 0..99. */

public final class FB {
  
                public static void main(String[] args) throws Exception   {            
                	System.setProperty("webdriver.chrome.driver", "C:\\Users\\413455\\Downloads\\chromedriver.exe");
                	WebDriver driver = new ChromeDriver();
                	automate();
                  
  }
    
  public static void automate() throws Exception
  {
      System.setProperty("webdriver.chrome.driver", "C:\\Users\\413455\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().window().maximize();
      driver.manage().deleteAllCookies();               
      driver.get("http://www.fb.com"); 
      List <WebElement> wbObject;
      wbObject = driver.findElements(By.xpath("//input[@value='  Use Quota Time  ']"));
      if (!wbObject.isEmpty()){
    	  System.out.println(" Found ");
    	  driver.findElement(By.xpath("//input[@value='  Use Quota Time  ']")).click();
     }
      Thread.sleep(4000);
    //input[@value="  Use Quota Time  "]
//      if("")
      WebElement toClear = driver.findElement(By.xpath("//input[@name='email']"));
      toClear.clear();
      driver.findElement(By.xpath("//input[@name='email']")).sendKeys("");
      Thread.sleep(2000);
      driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("");
      Thread.sleep(2000);
      driver.findElement(By.xpath("//input[@value='Log In']")).click();
      Thread.sleep(4000);
//      WebElement lstList  = driver.findElement(By.xpath("//a[@class='barcode']"));
//      Actions oAction = new Actions(driver);
//      oAction.moveToElement(lstList);
//      oAction.contextClick(lstList).build().perform();  /* this will perform right click */
//      Thread.sleep(3000);
  }
//      WebElement elementOpen = driver.findElement(By.linkText("Copy image")); /*This will select menu after right click */
//      elementOpen.click();           
//      String excelResource = "ExcelTestData.xlsx";
//      ExcelRuntimeOutput writer = new ExcelRuntimeOutput(excelResource);
//      writer.backupExcel();
//      writer.setValueAt("SheetName", 0, 0, "MyDataAtFirstRowFirstColumn");
//      writer.setValueAt("SheetName", 0, 1, "MyDataAtFirstRowSecondColumn");
//      writer.generateExcel();
//      Thread.sleep(2000);
//      String text = "qwerty";
//      FileOutputStream f = new FileOutputStream("c:\\Test\\output.xls",true);
//      WritableWorkbook book = Workbook.createWorkbook(f); 
//      WritableSheet sheet = book.createSheet("output", 0);
//      Thread.sleep(1000);       
                //            @SuppressWarnings("null")
                public static String[] splitDataValues(String strDataValue) throws Exception
                {                              
                                int distinct=0;                     
                                for (int i = 0; i < strDataValue.length(); i++) {               
                                                if(strDataValue.charAt(i)==(';'))
                                                {                              
                                                                distinct++;
                                                }
                                } 
                                String strMultipleValues[] = new String[distinct+1];
                                if (strDataValue.contains(";")){
                                                Thread.sleep(3000);
                                                strMultipleValues = strDataValue.split(";");
                                }
                                else{
                                                strMultipleValues[0]=strDataValue;
                                }
                                return strMultipleValues;
                }
//                public boolean verifyElementPresent(String strObjectProperty, String strElementType){
//                    List <WebElement> wbObject;
//                    try {
//                           if (strElementType.equalsIgnoreCase("CSS"))
//                                 wbObject = driver.findElements(By.cssSelector(strObjectProperty));
//                           else if (strElementType.equalsIgnoreCase("XPATH"))
//                                 wbObject = driver.findElements(By.xpath(strObjectProperty));
//                           else if (strElementType.equalsIgnoreCase("ID"))
//                                 wbObject = driver.findElements(By.id(strObjectProperty));
//                           else if (strElementType.equalsIgnoreCase("NAME"))
//                                 wbObject = driver.findElements(By.name(strObjectProperty));
//                           else if (strElementType.equalsIgnoreCase("LINKTEXT"))
//                                 wbObject = driver.findElements(By.linkText(strObjectProperty));
//                           else if (strElementType.equalsIgnoreCase("TAG"))
//                                 wbObject = driver.findElements(By.tagName(strObjectProperty));
//                           else if (strElementType.equalsIgnoreCase("CLASS"))
//                                 wbObject = driver.findElements(By.className(strObjectProperty));
//                           else
//                                 wbObject = null;
//                           
//                           if (!wbObject.isEmpty()){
//                                 return true;
//                           }else{
//                                 return false;
//                           }             
//                           
//                    }catch(Exception e){
////                         'report '
//                    }      
//                    return false;
//             }

}