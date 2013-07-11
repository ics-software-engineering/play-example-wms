package tests.pages;

import static org.fluentlenium.core.filter.FilterConstructor.withId;
import static org.fluentlenium.core.filter.FilterConstructor.withText;
import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

/** The StockItem edit page representation. */
public class StockItemEditPage extends FluentPage {
  private final String url;

  /**
   * Create the new StockItem.
   * @param webDriver The driver.
   * @param port The port.
   * @param primaryKey The PK.
   */
  public StockItemEditPage(WebDriver webDriver, int port, int primaryKey) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/stockitem/" + primaryKey;
  }

  @Override
  public String getUrl() {
    return this.url;
  }

  @Override
  public void isAt() {
    assert (title().equals("WMS: Update StockItem"));
  }

  /**
   * Edit the stock item.
   * @param newStockItemId The new ID.
   * @param productName The product name.
   * @param warehouseName The warehouse name.
   */
  public void editStockItem(String newStockItemId, String productName, String warehouseName) {
    fill("#stockItemId").with(newStockItemId);
    find("select", withId("productName")).find("option", withText(productName)).click();
    find("select", withId("warehouseName")).find("option", withText(warehouseName)).click();
    submit("#update");
  }

  /** Delete the stock item. */
  public void deleteStockItem() {
    click("#delete");
  }
}
