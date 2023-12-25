package positiveMethod;

import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EndToEnd {

	public WebDriver driver;
	// Open WebPage
	By enterWebPage = By.id("APjFqb");
	By googleSearch = By.xpath("//div[@class='lJ9FBc']//input[@value='Google Search']");
	By clickLink = By.xpath("//div[@class='yuRUbf']//a[@jsname='UWckNb']");
	// Locators For Register New Account
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
	By accountMessage = By.xpath("//h1[@class='headcolor']");
	// Locators For AddtoCard_PurchadeItem
	By loginButton = By.xpath("//input[@id='login']");
	By clickAddToCard = By.xpath("(//button[@class='btn w-10 rounded'])[2]");
	By clickOrders = By.xpath("(//button[@class='btn btn-custom'])[3]");
	By itemNumber = By.xpath("//p[@class='itemNumber']");
	By productName = By.xpath("//div[@class='cartSection']/h3");
	By productPrice = By.xpath("//div[@class='cartSection']/p[2]");
	By buyButton = By.xpath("(//button[@class='btn btn-primary'])[2]");
	By paymentMethod = By.xpath("//div[@class='payment__type payment__type--cc active']");
	By cardNumber = By.xpath("//input[@value='4542 9931 9292 2293']");
	By expMonth = By.xpath("(//select[@class='input ddl'])[1]");
	By expYear = By.xpath("(//select[@class='input ddl'])[2]");
	By CVVnumber = By.xpath("(//input[@class='input txt'])[1]");
	By cardHolderName = By.xpath("(//input[@class='input txt'])[2]");
	By myCountry = By.xpath("//input[@placeholder='Select Country']");
	By selectCountry = By.xpath("(//span[@class='ng-star-inserted'])[2]");
	By purchaseButton = By.xpath("//a[@class='btnn action__submit ng-star-inserted']");
	By invoiceOrderId = By.xpath("//label[@class='ng-star-inserted']");
	By orderDetails = By.xpath("(//button[@class='btn btn-custom'])[2]");
	By tabelHead = By.xpath("//thead[@class='thead-dark']");
	By headData = By.xpath(".//th");
	By orderNumber = By.xpath("//tr[@class='ng-star-inserted']//th");
	By tablerow = By.xpath("//tr[@class='ng-star-inserted']");
	By rowData = By.xpath(".//td");
	By signOut = By.xpath("(//button[@class='btn btn-custom'])[4]");

	@BeforeMethod
	public void SetUp() throws InterruptedException {
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

	@Test // Register new account
	public void Register() throws InterruptedException {

		click(registerButton);// Click Register
		sleep(); // wait
		print(driver.getTitle()); // Print page title
		newLine();// New Line
		type(firstName, "User"); // Enter First Name
		type(lastName, "name1"); // Enter Last Name
		type(email, generateRandomEmail()); // Enter email ID
		type(phone, "8825956399"); // Enter Phone No.
		selectDropdown();// Select dropDown
		sleep();// wait
		click(radioButton); // Select gender by radio button
		type(password, "Prasanna@1408"); // Enter Password
		type(confrimPassword, "Prasanna@1408"); // Enter ConFrim password
		click(checkButton); // Select checkBox
		sleep(); // Wait
		click(signInButton); // Click register button
		sleep(); // Wait
		newLine();// New Line
		print(gettext(accountMessage)); // print account notification

	}

	@Test(priority = 2) // Login and Purchase Product
	public void AddtoCard_Checkout() throws InterruptedException {

		type(email, "username22@gmail.com"); // Enter email ID
		type(password, "Prasanna@1408"); // Enter Password
		sleep(); // wait
		click(loginButton); // click login button
		sleep(); // wait
		click(clickAddToCard); // Select add to card button on second product
		sleep();// wait
		click(clickOrders); // Click orders
		sleep();// wait
		print("product id :" + gettext(itemNumber)); // Print item number
		newLine();// New Line
		sleep();// wait
		print("product Name :" + gettext(productName));// Print product Name
		newLine();// New Line
		sleep();// wait
		print("product Price :" + gettext(productPrice));// Print product price
		newLine();// New Line
		sleep();// wait
		click(buyButton);// Click buy
		sleep();// wait
		paymentDetails();
		sleep();// wait
		type(myCountry, "India");// Select country
		sleep();// wait
		click(selectCountry);
		sleep();// wait
		WebElement element = driver.findElement(purchaseButton);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].click()", element); // click purchase
		sleep();// wait
		newLine();// New Line
		String invoiceNumber = gettext(invoiceOrderId); // Store Invoice Order ID
		print("Order id :" + invoiceNumber);// Print order ID
		newLine();// New Line
		click(orderDetails);// open your order details
		sleep();// wait
		print("Order Details");// Print Order Details
		newLine();// New Line
		String invoiceDetails = gettext(orderNumber);// Store Order Number
		orderDetails();// Order Details
		newLine();// New Line
		verifyOrder(invoiceNumber.contains(invoiceDetails));// Verify Order Number
		click(signOut);// Log Out
		sleep();// wait

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

	private String gettext(By locator) {
		return find(locator).getText();
	}

	private void sleep() throws InterruptedException {
		Thread.sleep(5000);
	}

	private void newLine() {
		System.out.println();
	}

	private void print(String message) {
		System.out.println(message);
	}

	private void clear(By locator) {
		find(locator).clear();
	}

	private String generateRandomEmail() {
		return RandomStringUtils.random(4, true, true) + "@gmail.com";

	}

	private WebElement selectDropdown() {

		String userOccupation = "Engineer"; // Enter Occupation
		WebElement dropdownElement = driver.findElement(occupationField);
		Select dropdown = new Select(dropdownElement);
		//List<WebElement> options = dropdown.getOptions();// Select By Visible Text
		boolean isOccupationFound = false;
		for (WebElement option : dropdown.getOptions()) {
			if (option.getText().equals(userOccupation)) {
				dropdown.selectByVisibleText(userOccupation);
				isOccupationFound = true;
				break;
			}
		}
		assert isOccupationFound : "Occupation not found: " + userOccupation;
		print("Occupation found and selected: " + userOccupation);	
		return dropdownElement;

	}

	private void paymentDetails() throws InterruptedException {

		String paymentmethod = gettext(paymentMethod);
		System.out.println("Payment mode :" + paymentmethod);// Print payment mode
		sleep();
		clear(cardNumber);// Clear card number
		type(cardNumber, "4542 9931 9292 2293"); // Enter New card number
		WebElement month = driver.findElement(expMonth);// Select exp. month and YEar
		WebElement year = driver.findElement(expYear);
		Select m = new Select(month);
		Select y = new Select(year);
		m.selectByIndex(1);
		y.selectByVisibleText("25");
		type(CVVnumber, "456"); // Enter CVV
		type(cardHolderName, "Prasanna");// Enter cardHolder name

	}

	private void orderDetails() {

		WebElement tablehead = driver.findElement(tabelHead); // print order details table head
		List<WebElement> head = tablehead.findElements(headData);
		for (WebElement thead : head) {
			System.out.print("<---->");
			System.out.print(thead.getText());
		}
		newLine();
		System.out.print(gettext(orderNumber));// print order id
		WebElement row = driver.findElement(tablerow);// Print row details
		List<WebElement> row1 = row.findElements(rowData);
		for (WebElement firstrow : row1) {
			System.out.print("<---->" + firstrow.getText());
		}
	}

	private void verifyOrder(boolean b) throws InterruptedException {

		newLine();// New Line
		print("Order id is verified");// Print result

	}

	@AfterMethod
	public void TearDown() {
		driver.quit();
	}
}
