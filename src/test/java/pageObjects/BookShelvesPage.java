package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BookShelvesPage extends BasePage{
	
	public BookShelvesPage(WebDriver driver) {
		 // Calls the constructor of the BasePage class
		super(driver); 
	}
	
	// Locates the storage element using its XPath
	@FindBy(xpath="//*[@id=\"filters-form\"]/div[1]/div/div/ul/li[2]")
	// Declares a WebElement for the storage element
	  WebElement elementToHover_storage;
	
	// Locates the storage type element by its ID
	@FindBy(id="filters_storage_type_Open")
	// Declares a WebElement for the storage type element
	 WebElement storageType;
	
	// Locates the stock element by its ID
	@FindBy(id="filters_availability_In_Stock_Only")
	// Declares a WebElement for the max slider element
	WebElement stock;
	
	// Locates the price element using its XPath
	 @FindBy(xpath="//*[@id='filters-form']/div[1]/div/div/ul/li[1]")
	// Declares a WebElement for the price element
	 WebElement elementToHover_price;
	 
	// Locates the max slider element using its XPath
	 @FindBy(xpath="//*[@id='filters-form']/div[1]/div/div/ul/li[1]/div[2]/div/div/ul/li[1]/div/div[2]/div[2]/div/div[2]/div")
	// Declares a WebElement for the max slider element
	 WebElement maxSlider;
	 
	 
	// Locates the bookshelf names using their XPath
	 By bookShelf_Names=By.xpath("//*[@id='content']/div[3]/ul/li/div/div[5]/a/div[1]/span");
	 
	// Locates the bookshelf prices using their XPath
	 By bookShelf_Prices=By.xpath("//*[@id='content']/div[3]/ul/li/div/div[5]/a/div[2]/span");
	 
	// Method to hover over the storage element
	  public void hoverStorage(){
	   actions.moveToElement(elementToHover_storage).perform();
	  }
	  
	// Method to click the storage type element
     public void Clickstorage() {
    	 wait.until(ExpectedConditions.elementToBeClickable(storageType));
    	 storageType.click();
     }
  // Method to click the stock element
     public void clickexcludestock()
     {
     stock.click();
     }
      
  // Method to hover over the price element
      public void hoverPrice() {
    	wait.until(ExpectedConditions.elementToBeClickable(elementToHover_price));
    	js.executeScript("arguments[0].scrollIntoView(true);", elementToHover_price);
       actions.moveToElement(elementToHover_price).perform();
      }
   // Method to move the max slider
      public void slider() {
    	  wait.until(ExpectedConditions.visibilityOf(maxSlider));
          js.executeScript("arguments[0].scrollIntoView(true);", maxSlider);
          actions.dragAndDropBy(maxSlider, -227, 0).perform();
      }
     // Method to get the list of bookshelf names
      public List<WebElement> getBookShelfNames() {
    	  return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(bookShelf_Names));
      }
   // Method to get the list of bookshelf prices
      public List<WebElement> getBookShelfPrices() {
          return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(bookShelf_Prices));

      }  
	
	
	
      
}
    

