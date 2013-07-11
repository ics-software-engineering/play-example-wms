package tests.pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

/** The warehouse create page representation. */
public class WarehouseCreatePage extends FluentPage {
  private final String url;

  /**
   * Create the warehouse.
   * @param webDriver The driver.
   * @param port The port.
   */
  public WarehouseCreatePage(WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/warehouse/create";
  }

  @Override
  public String getUrl() {
    return this.url;
  }

  @Override
  public void isAt() {
    assert (title().equals("WMS: Create Warehouse"));
  }

  /**
   * Make a new warehouse.
   * @param warehouseId The ID.
   */
  public void makeNewWarehouse(String warehouseId) {
    // For testing purposes, use the same string for both ID and name.
    fill("#warehouseId").with(warehouseId);
    fill("#name").with(warehouseId);
    fill("#city").with("Honolulu");
    fill("#state").with("Hawaii");
    fill("#zip").with("96822");
    submit("#create");
  }
}
