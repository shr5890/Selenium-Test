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
public final class Check_Test {
  
                public static void main(String[] args) throws Exception   {
                  copyImg();
                  
  }
    
  public static void copyImg() throws Exception
  {
      System.setProperty("webdriver.chrome.driver", "C:\\Users\\413455\\Downloads\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.manage().window().maximize();
      driver.manage().deleteAllCookies();         
      String strBarcodes="Shaikh;Hifzur;Rahman;05081990";
      String[] BarcodeArrays=splitDataValues(strBarcodes);
      driver.get("http://www.barcodesinc.com/generator/index.php");
      for(int i=0;i<BarcodeArrays.length;i++){
      Thread.sleep(3000);
      WebElement toClear = driver.findElement(By.xpath("//input[@name='barcode']"));
      toClear.clear();
      driver.findElement(By.xpath("//input[@name='barcode']")).sendKeys(BarcodeArrays[i]);
      driver.findElement(By.xpath("//input[@value='Generate Barcode']")).click();
      Thread.sleep(1000);
      WebElement lstList  = driver.findElement(By.xpath("//a[@class='barcode']"));
      Actions oAction = new Actions(driver);
      oAction.moveToElement(lstList);
      oAction.contextClick(lstList).build().perform();  /* this will perform right click */
      Thread.sleep(3000);
//      //WebElement elementOpen = driver.findElement(By.linkText("Copy image")); /*This will select menu after right click */
//      elementOpen.click();      
      Robot robot = new Robot();
      robot.keyPress(KeyEvent.VK_DOWN);
      Thread.sleep(1000);
      robot.keyPress(KeyEvent.VK_DOWN);
      Thread.sleep(1000);
      robot.keyPress(KeyEvent.VK_DOWN);
      Thread.sleep(1000);
      robot.keyPress(KeyEvent.VK_DOWN);
      Thread.sleep(1000);
     robot.keyPress(KeyEvent.VK_DOWN);
      Thread.sleep(1000);
      robot.keyPress(KeyEvent.VK_DOWN);
      Thread.sleep(1000);
      robot.keyPress(KeyEvent.VK_DOWN);
      Thread.sleep(1000);
      robot.keyPress(KeyEvent.VK_DOWN);
      Thread.sleep(1000);
      robot.keyPress(KeyEvent.VK_ENTER);
      Thread.sleep(3000);
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
      if(i==0){
      robot.keyPress(KeyEvent.VK_ALT);
      robot.keyPress(KeyEvent.VK_TAB);
      Thread.sleep(2000);
      robot.keyRelease(KeyEvent.VK_TAB);
      robot.keyPress(KeyEvent.VK_TAB);
      Thread.sleep(2000);
      robot.keyRelease(KeyEvent.VK_TAB);
      robot.keyRelease(KeyEvent.VK_ALT);   
      }
      else{
                  robot.keyPress(KeyEvent.VK_ALT);
          robot.keyPress(KeyEvent.VK_TAB);
          Thread.sleep(2000);
          robot.keyRelease(KeyEvent.VK_TAB);         
          robot.keyRelease(KeyEvent.VK_ALT);  
      }
//      robot.keyPress(KeyEvent.VK_CONTROL);
//      robot.keyPress(KeyEvent.VK_E);
//      robot.keyRelease(KeyEvent.VK_E);
//      robot.keyRelease(KeyEvent.VK_CONTROL);  
      Thread.sleep(3000);
//      book.write(); 
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_V);
      Thread.sleep(2000);
      robot.keyRelease(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      Thread.sleep(3000);
      robot.keyPress(KeyEvent.VK_ENTER);
      Thread.sleep(2000);
      robot.keyRelease(KeyEvent.VK_ENTER);                       
//      book.close(); 
      Thread.sleep(3000);
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_S);
      Thread.sleep(2000);
      robot.keyRelease(KeyEvent.VK_S);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      Thread.sleep(3000);
      robot.keyPress(KeyEvent.VK_ALT);
      robot.keyPress(KeyEvent.VK_TAB);
      Thread.sleep(2000);
      robot.keyRelease(KeyEvent.VK_TAB);         
      robot.keyRelease(KeyEvent.VK_ALT); 
      Thread.sleep(3000);
      driver.findElement(By.xpath("//input[@name='barcode']")).click();
      Thread.sleep(2000);
//      robot.keyPress(KeyEvent.VK_CONTROL);
//      robot.keyPress(KeyEvent.VK_E);
//      robot.keyRelease(KeyEvent.VK_E);
//      robot.keyRelease(KeyEvent.VK_CONTROL);  
      
//                            File fileObj = new File("c:\\Test\\output.doc");
//        PrintWriter writer = null;
//        writer.println();
//      Thread.sleep(2000);
      }
      System.out.println("Completed");
  }
  /**
                *********************************************************************** 
                 * Function splits the string into array.
                * @return          strTaskId 
                 * @Author  Rahman
                * @param          None
                * @version 1.0
                * @throws         Exception 
                 ***********************************************************************
                */
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
}