package packagedemo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class amazondemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\RUSHI\\Downloads\\chromedriver-win64 (1).zip\\chromedriver-win64.exe"); // Update path to your ChromeDriver
        WebDriver driver = new ChromeDriver();

        try {
            
            driver.get("https://www.amazon.in/");

            WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox")); // Use a reliable locator
            searchBar.sendKeys("Lg soundbar");
            searchBar.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='a-section a-spacing-small a-spacing-top-small'])[1]"))); 
            System.out.println("1-16 of 390 results for \"Lg soundbar\"");
            
            // 4. Extract product names and prices
            List<WebElement> productElements = driver.findElements(By.cssSelector("//div[@class='a-section a-spacing-small a-spacing-top-small'])[1]")); 
            Map<String, Double> productPrices = new HashMap<>();

            for (WebElement productElement : productElements) {
                String productName = productElement.findElement(By.tagName("h2")).getText().trim();
                String priceText = productElement.findElement(By.cssSelector(".s-price")).getText().trim(); 

                double price = 0.0;
                if (!priceText.isEmpty()) {
                    price = Double.parseDouble(priceText.replaceAll("[^\\d.]", ""));
                }
                productPrices.put(productName, price);
            }

            // 5. Sort products by price
            List<Map.Entry<String, Double>> sortedProducts = new ArrayList<>(productPrices.entrySet());
            Collections.sort(sortedProducts, (p1, p2) -> Double.compare(p1.getValue(), p2.getValue()));

            // 6. Print sorted products
            for (Map.Entry<String, Double> product : sortedProducts) {
                System.out.println(product.getValue() + " " + product.getKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

}


