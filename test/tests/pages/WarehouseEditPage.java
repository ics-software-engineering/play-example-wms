package tests.pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

/** The warehouse edit page representation. */
public class WarehouseEditPage extends FluentPage {
  private final String url;

  /**
   * Create the edit page.
   * @param webDriver The driver.
   * @param port The port.
   * @param primaryKey The PK.
   */
  public WarehouseEditPage(WebDriver webDriver, int port, int primaryKey) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/warehouse/" + primaryKey;
  }

  @Override
  public String getUrl() {
    return this.url;
  }

  @Override
  public void isAt() {
    assert (title().equals("WMS: Update Warehouse"));
  }

  /**
   * Edit the warehouse.
   * @param newWarehouseId The ID.
   */
  public void editWarehouse(String newWarehouseId) {
     // For testing purposes, use the same string for both ID and name.
    fill("#warehouseId").with(newWarehouseId);
    fill("#name").with(newWarehouseId);
    fill("#city").with("Honolulu");
    fill("#state").with("Hawaii");
    fill("#zip").with("96822");
    submit("#update");
  }

  /** Delete the warehouse. */
  public void deleteWarehouse() {
    click("#delete");
  }
}
