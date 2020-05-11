package pizza.spring.selenium;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommandPage {

	private WebDriver webDriver;

	public CommandPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public CommandPage open() {
		webDriver.navigate().to("http://localhost:8080/pizza-spring/commande");
		assertTrue("Titre de page inattendu " + webDriver.getTitle(), webDriver.getTitle().startsWith("Pizza Spring"));
		return this;
	}

	public static CommandPage openWith(WebDriver webDriver) {
		CommandPage commandPage = new CommandPage(webDriver);
		commandPage.open();
		return commandPage;
	}

	public CommandPage enterKeywords(String id, String... words) {
		WebElement searchInput = webDriver.findElement(By.id(id));
		searchInput.sendKeys(String.join(" ", words));
		return this;
	}

	public CommandPage selectPizza(String pizza) {
		WebElement selectElement = webDriver.findElement(By.xpath("//select/option[text()='" + pizza + "']"));
		selectElement.click(); 
		return this;
	}
	
	public RecapPage submitCommande() {
		WebElement searchButton = webDriver.findElement(By.id("submit-commande"));
		searchButton.click();
		return new RecapPage(webDriver);
	}
	
	public CommandPage submitCommandWithErrors() {
		WebElement searchButton = webDriver.findElement(By.id("submit-commande"));
		searchButton.click();
		return this;
	}

	public boolean checkError(String id, String word) {
		WebElement checkErrorElement = this.webDriver.findElement(By.id(id));
		if(checkErrorElement.getText().equals(word)) {
			return true;
		}
		return false;
	}
	
	
}
