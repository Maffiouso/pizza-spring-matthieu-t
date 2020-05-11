package pizza.spring.selenium;


import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RecapPage {
  private WebDriver webDriver;

  public RecapPage(WebDriver webDriver) {
    this.webDriver = webDriver;
    
  }
  
  public boolean onPage() {
	WebElement element = webDriver.findElement(By.id("recap-commande"));
	if(element != null) {
		return true;
	}
	return false;
  }

  public boolean isLinkPresent(String link) {
    return ! webDriver.findElements(By.partialLinkText(link)).isEmpty();
  }
}
