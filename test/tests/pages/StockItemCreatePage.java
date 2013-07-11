package tests.pages;

import static org.fluentlenium.core.filter.FilterConstructor.withId;
import static org.fluentlenium.core.filter.FilterConstructor.withText;
import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

/** The StockItem Create page representation. */
public class StockItemCreatePage extends FluentPage {
  private final String url;

  /**
   * Create the StockItem Create Page.
   * @param webDriver The driver.
   * @param port The port.
   */
  public StockItemCreatePage(WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/stockitem/create";
  }

  @Override
  public String getUrl() {
    return this.url;
  }

  @Override
  public void isAt() {
    assert (title().equals("WMS: Create StockItem"));
  }

  /**
   * Make a new stockitem.
   * @param stockItemId The new ID.
   * @param productName The product name.
   * @param warehouseName The warehouse name.
   */
  public void makeNewStockItem(String stockItemId, String productName, String warehouseName) {
    fill("#stockItemId").with(stockItemId);
    find("select", withId("productName")).find("option", withText(productName)).click();
    find("select", withId("warehouseName")).find("option", withText(warehouseName)).click();
    fill("#quantity").with("25");
    submit("#create");
  }
}
