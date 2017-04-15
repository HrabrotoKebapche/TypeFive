import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class Task {
	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver;
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		driver = new ChromeDriver(chromeOptions);
		driver.get("https://www.amazon.com/");
		WebElement element;
		String str = "Save on Lenovo Yoga 11.6";

		// Login
		driver.findElement(By.xpath("(//div[@id='nav-tools'])[1]")).click();
		element = driver.findElement(By.xpath("(//input[@type='email'])"));
		element.sendKeys("username");
		element = driver.findElement(By.xpath("(//input[@type='password'])"));
		element.sendKeys("password");
		element.submit();

		driver.get("https://www.amazon.com/");
		// click on Today's Deals
		element = driver.findElement(By.linkText("Today's Deals"));
		element.click();

		int pages = Integer.parseInt(driver
				.findElement(By.xpath("//ul[@class='a-pagination']"))
				.findElement(By.xpath("//li[@class='a-disabled'][last()]"))
				.getAttribute("innerHTML"));

		List<WebElement> list2;

		search: {
			for (int i = 0; i < pages; i++) {
				list2 = driver
						.findElements(By
								.xpath("//span[@class='a-size-base a-color-base dealTitleTwoLine hoverVisible hiddenCss singleCellTitle autoHeight']"));
				int a = 0;
				for (WebElement el : list2) {
					System.out.println(el.getAttribute("innerHTML"));
					System.out
							.println("Price is: "
									+ el.findElement(
											By.xpath("(//span[@class='a-size-medium a-color-base inlineBlock unitLineHeight'])"))
											.getAttribute("innerHTML"));
					if (el.getAttribute("innerHTML").toLowerCase()
							.contains(str.toLowerCase())) {
						driver.findElement(
								By.xpath(String
										.format("(//div[@class='a-row a-spacing-mini']/a)[%s]",
												a))).click();
						driver.findElement(
								By.xpath("(//input[@id='add-to-cart-button'])"))
								.click();
						// ...anything
						driver.navigate().back();
						driver.navigate().back();
						break search;
					}
					a++;
				}
				driver.findElement(By.partialLinkText("Next")).click();
				list2.clear();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
		}

		// Search
		driver.get("https://www.amazon.com/");
		element = driver.findElement(By.id("twotabsearchtextbox"));
		element.sendKeys("Hard Disk");
		element.submit();

		// click on Gift Cards & Registry
		driver.get("https://www.amazon.com/");
		element = driver.findElement(By.linkText("Gift Cards & Registry"));
		element.click();

		driver.findElement(
				By.xpath("//img[@src='https://images-na.ssl-images-amazon.com/images/G/01/gift-certificates/consumer/2016/Holiday/Interstial/post/Interstital_TCG_Gifts_768x500_post_giftcard_2._V522751081_.jpg']"))
				.click();
		driver.navigate().back();
		driver.findElement(
				By.xpath("//img[@src='https://images-na.ssl-images-amazon.com/images/G/01/gift-certificates/consumer/2016/Holiday/Interstial/post/Interstital_TCG_768x300_post_egiftcards._V522751086_.jpg']"))
				.click();
		driver.navigate().back();
		driver.findElement(
				By.xpath("//img[@src='https://images-na.ssl-images-amazon.com/images/G/01/gift-certificates/consumer/2016/Holiday/Interstial/post/Interstital_TwoColumn_DT_768x300_post_baby.jpg']"))
				.click();
		driver.navigate().back();
		driver.findElement(
				By.xpath("//img[@src='https://images-na.ssl-images-amazon.com/images/G/01/gift-certificates/consumer/2016/Holiday/Interstial/post/Interstital_TwoColumn_DT_768x300_post_wedding.jpg']"))
				.click();
		driver.navigate().back();
		driver.findElement(
				By.xpath("//img[@src='https://images-na.ssl-images-amazon.com/images/G/01/gift-certificates/consumer/2016/Holiday/Interstial/post/Interstital_TwoColumn_DT_768x300_post_handpicked._V522751086_.jpg']"))
				.click();
		driver.navigate().back();
		driver.findElement(
				By.xpath("//img[@src='https://images-na.ssl-images-amazon.com/images/G/01/gift-certificates/consumer/2016/Holiday/Interstial/post/Interstital_TwoColumn_DT_768x300_post_friendsfamily.jpg']"))
				.click();
		driver.navigate().back();
	}
}