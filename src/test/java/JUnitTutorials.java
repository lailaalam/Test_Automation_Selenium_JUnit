import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class JUnitTutorials {
    WebDriver driver;

    // GeckoDriverService driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headed");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


        /*System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
       driver=new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));*/

    }

    /*
    @Test

    public void getTitle()
    {
        driver.get("https://demoqa.com/");
        String title=driver.getTitle();
        // System.out.println(title);
        Assert.assertEquals("ToolsQA",title);
        // Assert.assertTrue(title.contains("ToolsQA"));

    }
*/
    ///*
    @Test
    public void writeText() {

        driver.get("https://demoqa.com/text-box");

        WebElement txtFirstName = driver.findElement(By.id("userName"));
        txtFirstName.sendKeys("Laila");

        WebElement txtEmail = driver.findElement(By.cssSelector("[type=email"));
        txtEmail.sendKeys("123@gmail.com");

        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        currentAddress.sendKeys("Dhaka");

        WebElement parmanentAddress = driver.findElement(By.id("permanentAddress"));
        parmanentAddress.sendKeys("Dhaka");

        driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();

       /* Actions actions=new Actions(driver);
        List<WebElement> button = driver.findElements(By.tagName("button"));
        actions.moveToElement(button.get(0)).click().perform();
       */
       /* WebElement button = driver.findElement(By.id("submit"));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).click().perform();*/


    }

    @Test
    public void handleAlerts() throws InterruptedException {

        driver.get("https://demoqa.com/alerts");
        /*
        driver.findElement(By.id("alertButton")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        */

       /*
        driver.findElement(By.id("timerAlertButton")).click();
        Thread.sleep(6000);
        driver.switchTo().alert().accept();
        */

       /*
        driver.findElement(By.id("confirmButton")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().dismiss();
        String text = driver.findElement(By.className("text-success")).getText();
        System.out.println(text);
        Assert.assertTrue(text.contains("Cancel"));
        */
        driver.findElement(By.id("promtButton")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().sendKeys("Fahim");
        driver.switchTo().alert().accept();


    }

    @Test
    public void selectDate() throws InterruptedException {
        driver.get("https://demoqa.com/date-picker");
        WebElement txtDate = driver.findElement(By.id("datePickerMonthYearInput"));
        /*Actions actions = new Actions(driver);
      actions.moveToElement(txtDate)
              .doubleClick()
              .click()
              .keyDown(Keys.BACK_SPACE)
              .keyUp(Keys.BACK_SPACE)
              .perform();*/
        txtDate.sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        Thread.sleep(1000);
        txtDate.sendKeys("08/03/2022");
        txtDate.sendKeys(Keys.ENTER);


    }

    @Test
    public void keyBoardEvent()
    {
        driver.get("https://www.google.com/");
        WebElement searchItem=driver.findElement(By.name("q"));
        Actions actions=new Actions(driver);
        actions.moveToElement(searchItem);
        actions.keyDown(Keys.SHIFT);
        actions.sendKeys("Selenium Webdriver")
                .keyUp(Keys.SHIFT)
                .doubleClick()
                .contextClick()  //right button click
                .perform();
    }
@Test
    public void actionClick()
    {
        driver.get("https://demoqa.com/buttons");
        Actions actions=new Actions(driver);
        List<WebElement> buttons=driver.findElements(By.tagName("button"));
        actions.doubleClick(buttons.get(1)).perform();
        actions.contextClick(buttons.get(2)).perform();
        actions.click(buttons.get(3)).perform();
    }

@Test
    public void takeScreenshot() throws IOException {
        driver.get("https://demoqa.com/");
        File screenshotFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String time= new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-aa").format(new Date());
        String fileWithPath="./src/test/resources/screenshots/"+time+".png";
        File DestFile=new File(fileWithPath);
        FileUtils.copyFile(screenshotFile,DestFile);


    }

    @Test
    public void uploadFile()
    {
        driver.get("https://demoqa.com/upload-download");
        driver.findElement(By.id("uploadFile")).sendKeys("C:\\Users\\User\\Downloads\\Capture.PNG");

    }
@Test
    public void downloadFile()
    {
        driver.get("https://demoqa.com/upload-download");
        driver.findElement(By.id("downloadButton")).click();

    }
    @Test
    public void selectMenu() {
        driver.get("https://demoqa.com/select-menu");
        //Select firstElement=new Select(driver.findElements(By.className("css-19bqh2r")).get(0));
        //firstElement.selectByIndex(0);

        Select select = new Select(driver.findElement(By.id("oldSelectMenu")));
        select.selectByIndex(2);

        Select cars = new Select(driver.findElement(By.id("cars")));
        if (cars.isMultiple()) {
            cars.selectByValue("volvo");
            cars.selectByValue("opel");
        }
    }


    @Test
    public void mouseHover() {
        driver.get("https://green.edu.bd/");
        Actions actions = new Actions(driver);
        List<WebElement> list = driver.findElements(By.xpath("//a[contains(text(),\"About Us\")]"));
        actions.moveToElement(list.get(2)).perform();
    }

    @Test

    public void handleTab() throws InterruptedException {
        driver.get("https://demoqa.com/browser-windows");
        driver.findElement(By.id("tabButton")).click();
        Thread.sleep(2000);
        ArrayList<String> w=new ArrayList(driver.getWindowHandles()); //count the no pof tab onen in browser and put them in arraylist
        driver.switchTo().window(w.get(1));
        System.out.println("New tab title: "+ driver.getTitle());
        String text=driver.findElement(By.id("sampleHeading")).getText();
        Assert.assertEquals(text,"This is a sample page");
        driver.close();
        driver.switchTo().window(w.get(0));
    }
@Test
    public void handleWindow()
    {
        driver.get("https://demoqa.com/browser-windows");
        //explicit wait
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("windowButton")));
        driver.findElement(By.id("windowButton")).click();

        String mainWindowHandle=driver.getWindowHandle();
        Set<String> allWindowHandles=driver.getWindowHandles();
        Iterator<String> iterator= allWindowHandles.iterator();

        while(iterator.hasNext())
        {
            String childWindow= iterator.next();
            if(!mainWindowHandle.equalsIgnoreCase(childWindow))
            {
                driver.switchTo().window(childWindow);
                String text= driver.findElement(By.id("sampleHeading")).getText();
              Assert.assertTrue(text.contains("This is a sample page"));
            }
        }
        driver.close();

    }

    @Test
    public void webTables()
    {
       driver.get("https://demoqa.com/webtables");
       driver.findElement(By.id("edit-record-1")).click();
       driver.findElement(By.id("submit")).click();


    }


    @Test
    public void scrapData()
    {
        driver.get("https://demoqa.com/webtables");
        WebElement table=driver.findElement(By.className("rt-tbody"));
        List<WebElement>  allrows=table.findElements(By.className("rt-tr"));
         int i=0;
         for(WebElement row:allrows)
         {
             List<WebElement> cells=row.findElements(By.className("rt-td"));
             for (WebElement cell:cells)
             {
                 i++;
                 System.out.println("num["+i+"]"+ cell.getText());
             }
         }

    }

    @Test
    public void handleIframe()
    {
        driver.get("https://demoqa.com/frames");
        driver.switchTo().frame("frame2");
        String text=driver.findElement(By.id("sampleHeading")).getText();
        Assert.assertTrue(text.contains("This is a sample page"));
        driver.switchTo().defaultContent();
    }



    /*@Test
    public void readExcelFile() throws IOException, InvalidFormatException {

        String filePath = ".\\src\\test\\resources";
        Utils.readFromExcel(filePath,"ExportExcel.xls","Sheet1");
    }




    //*/
    @After
    public void closeDriver() {
        driver.quit();
    }

}
