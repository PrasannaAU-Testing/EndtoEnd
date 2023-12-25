package negativeMethod;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EndToEnd {

	public WebDriver driver;

	// Open WebPage
	By enterWebPage = By.id("APjFqb");
	By googleSearch = By.xpath("//div[@class='lJ9FBc']//input[@value='Google Search']");
	By clickLink = By.xpath("//div[@class='yuRUbf']//a[@jsname='UWckNb']");

	By registerButton = By.linkText("Register");
	By firstName = By.id("firstName");
	By lastName = By.id("lastName");
	By email = By.id("userEmail");
	By phone = By.id("userMobile");
	By occupationField = By.xpath("//select[@formcontrolname='occupation']");
	By radioButton = By.xpath("//input[@value='Male']");
	By password = By.id("userPassword");
	By confrimPassword = By.xpath("//input[@id='confirmPassword']");
	By checkButton = By.xpath("//input[@type='checkbox']");
	By signInButton = By.xpath("//input[@type='submit']");

	By loginButton = By.xpath("//input[@id='login']");
	By clickAddToCard = By.xpath("(//button[@class='btn w-10 rounded'])[2]");
	By clickOrders = By.xpath("(//button[@class='btn btn-custom'])[3]");
	By buyButton = By.xpath("(//button[@class='btn btn-primary'])[2]");
	By cardNumber = By.xpath("//input[@value='4542 9931 9292 2293']");
	By purchaseButton = By.xpath("//a[@class='btnn action__submit ng-star-inserted']");

	By firstNameError = By.xpath("//div[@class='invalid-feedback']//*[contains(text(),'First')]");
	By emailError = By.xpath("//div[@class='invalid-feedback']//*[contains(text(),'Email')]");
	By phoneError = By.xpath("//div[@class='invalid-feedback']//*[contains(text(),'Phone')]");
	By passError = By.xpath("//div[@class='invalid-feedback']//*[contains(text(),'*Pass')]");
	By cPassError = By.xpath("//div[@class='invalid-feedback']//*[contains(text(),'Confirm')]");
	By checkBoxError = By.xpath("//div[@class='row mb-2']//*[contains(text(),'Please')]");
	By firstNameRule = By.xpath("//div[contains(text(),'*First Name must be 3 or more character long')]");
	By phoneRule = By.xpath("//div[normalize-space()='*only numbers is allowed']");
	By phone1Rule = By.xpath("//div[normalize-space()='*Phone Number must be 10 digit']");
	By cPassMatch = By.xpath("//div[@class='invalid-feedback']//*[contains(text(),'Confirm')]");

	@BeforeMethod
	public void setup() throws InterruptedException {
		driver = new ChromeDriver(); // set chrome Driver
		driver.get("https://www.google.com/");// Open google
		type(enterWebPage, "rahul shetty ecommerce");// Enter Web page
		sleep();// wait
		click(googleSearch);// Google Search
		driver.manage().window().maximize();// Maximize Window
		sleep();// wait();
		click(clickLink);// Click webPage Link
		sleep();// wait
		print(driver.getTitle());// Get Page Title
		print("======================================================");
		sleep();// wait

	}

	@Test(priority = 1)
	public void emptyField() throws InterruptedException {
		// Test Empty fields for Registration
		click(registerButton); // Register new account
		sleep();
		click(loginButton); // Click register button
		sleep();
		WebElement firstName1 = driver.findElement(firstNameError);
		performNegativeTest(firstName1, firstNameError);
		sleep();
		WebElement email1 = driver.findElement(emailError);
		performNegativeTest(email1, emailError);
		sleep();
		WebElement phone1 = driver.findElement(phoneError);
		performNegativeTest(phone1, phoneError);
		sleep();
		WebElement password1 = driver.findElement(passError);
		performNegativeTest(password1, passError);
		sleep();
		WebElement cpassword1 = driver.findElement(cPassError);
		performNegativeTest(cpassword1, cPassError);
		sleep();
		WebElement checkBox1 = driver.findElement(checkBoxError);
		performNegativeTest(checkBox1, checkBoxError);

	}

	@Test(priority = 2)
	public void invalidData() throws InterruptedException {

		click(registerButton); // Register new account
		sleep();
		type(firstName, "P"); // Enter First Name
		type(lastName, "N"); // Enter Last Name
		type(email, "@^$@&@gmail.com"); // Enter email ID
		type(phone, "0882595639957385"); // Enter Phone No.
		type(password, "Prasanna@1408"); // Enter Password
		type(confrimPassword, "Prasann@1408"); // Enter ConFrim password
		click(checkButton); // Select checkBox
		click(loginButton); // Click register button
		sleep();
		WebElement fNameRule = driver.findElement(firstNameRule);
		performNegativeTest(fNameRule, firstNameRule);
		sleep();
		WebElement emailRule = driver.findElement(emailError);
		performNegativeTest(emailRule, emailError);
		sleep();
		WebElement phoneRule1 = driver.findElement(phoneRule);
		performNegativeTest(phoneRule1, phoneRule);
		sleep();
		WebElement phoneRule2 = driver.findElement(phone1Rule);
		performNegativeTest(phoneRule2, phone1Rule);
		sleep();
		WebElement cPassMismatch = driver.findElement(cPassMatch);
		performNegativeTest(cPassMismatch, cPassMatch);

	}

	@Test(priority = 3)
	public void alerts() throws InterruptedException {

		click(registerButton); // Register new account
		sleep();// wait
		type(firstName, "prasanna"); // Enter First Name
		type(lastName, "N"); // Enter Last Name
		type(email, "username22@gmail.com"); // Enter email ID
		type(phone, "8825956399"); // Enter Phone No.
		type(password, "123456789"); // Enter Password
		type(confrimPassword, "123456789"); // Enter ConFrim password
		click(loginButton); // Click register button
		click(checkButton); // Click checkBox
		Thread.sleep(5000); // Wait
		click(loginButton); // Click register button
		handleAlert();
	}

	@Test(priority = 4)
	public void emptyLogIn() throws InterruptedException {

		click(signInButton); // Click login
		sleep(); // Wait
		WebElement email1 = driver.findElement(emailError);
		performNegativeTest(email1, emailError);
		sleep(); // Wait
		WebElement password1 = driver.findElement(passError);
		performNegativeTest(password1, passError);
		type(password, "Prasanna@1408"); // Enter valid password
		sleep(); // Wait
		click(signInButton); // Click login
		sleep(); // wait
		performNegativeTest(email1, emailError);
		clear(password); // Empty Password
		sleep(); // Wait
		type(password, "15646"); // Enter invalid password
		sleep(); // Wait
		click(signInButton); // Click login
		performNegativeTest(email1, emailError);
	}

	@Test(priority = 5)
	public void invalidUser() throws InterruptedException {

		// Test Invalid Email Sequence
		type(email, "stgs@gmail.com"); // Enter invalid email
		sleep(); // wait
		click(signInButton); // Click login
		sleep();
		WebElement password1 = driver.findElement(passError);
		performNegativeTest(password1, passError);
		type(password, "gsjgjsg"); // Enter invalid password
		sleep(); // wait
		click(signInButton); // Click login
		handleAlert(); // Alert
		clear(password); // Empty Password
		type(password, "Prasanna@1408"); // Enter valid password
		sleep(); // wait
		click(signInButton); // Click login
		handleAlert(); // Alert
	}

	@Test(priority = 6)
	public void invalidPass() throws InterruptedException {

// valid email
		type(email, "username22@gmail.com");
		click(signInButton);
		sleep();
		WebElement password1 = driver.findElement(passError);
		performNegativeTest(password1, passError);
		sleep();
		type(password, "Pras@1408");
		click(signInButton);
		sleep();
		handleAlert();
		clear(password);
		sleep();
		type(password, "Prasanna@1408");
		sleep();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}

	@Test(priority = 7)
	public void Payment() throws InterruptedException {

		type(email, "username22@gmail.com"); // Enter email ID
		type(password, "Prasanna@1408"); // Enter Password
		sleep(); // wait
		click(loginButton); // click login button
		sleep(); // wait
		click(clickAddToCard); // Select add to card button on second product
		sleep();// wait
		click(clickOrders); // Click orders
		sleep();// wait
		click(buyButton);// Click buy
		sleep();// wait
		clear(cardNumber);// Clear card number
		sleep();// wait
		WebElement element = driver.findElement(purchaseButton);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].click()", element); // click purchase;
		handleAlert();
	}

	public void performNegativeTest(WebElement element, By locator) throws InterruptedException {
		assert element.isDisplayed() : "Negative Test Failed :";
		sleep();
		print("Negative Test Passed: " + element.getText());
		print("======================================================");
	}

	private void handleAlert() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			System.out.println("JavaScript Alert Text: " + alertText);
			alert.accept();
		} catch (Exception e) {
			System.out.println("No alert found within the specified time.");
		}
	}

	private WebElement find(By locator) {
		return driver.findElement(locator);
	}

	private void click(By locator) {
		find(locator).click();
	}

	private void type(By locator, String text) {
		find(locator).sendKeys(text);
	}

	private void sleep() throws InterruptedException {
		Thread.sleep(5000);
	}

	private void print(String message) {
		System.out.println(message);
	}

	private void clear(By locator) {
		find(locator).clear();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
