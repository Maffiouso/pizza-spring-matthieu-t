package pizza.spring.selenium;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PizzaSpringSeleniumTest {

	private WebDriver webDriver;

	@Before
	public void createWebDriver() {
		webDriver = new ChromeDriver();
	}

	@After
	public void closeWebDriver() {
		webDriver.quit();
	}

	@Test
	public void commanderUnePizza() throws Exception {
	    RecapPage resultPage = CommandPage.openWith(webDriver)
	    		.selectPizza("Margherita")
	    		.enterKeywords("nom", "Matthieu")
	    		.enterKeywords("telephone", "020508010407")
	    		.submitCommande();

	    assertTrue(resultPage.onPage());
	}
	
	@Test
	public void commanderSansMettreSonTelephone() throws Exception {
	    CommandPage commandPage = CommandPage.openWith(webDriver)
	    		.selectPizza("Margherita")
	    		.enterKeywords("nom", "Matthieu")
	    		.submitCommandWithErrors();

	    assertTrue(commandPage.checkError("telephone.errors", "ne peut pas être vide"));
	}
	
	@Test
	public void commanderSansSelectionnerUnePizza() throws Exception {
	    CommandPage commandPage = CommandPage.openWith(webDriver)
	    		.enterKeywords("nom", "Matthieu")
	    		.enterKeywords("telephone", "020508010407")
	    		.submitCommandWithErrors();

	    assertTrue(commandPage.checkError("pizzaId.errors", "Vous devez choisir au moins une pizza"));
	}

}
