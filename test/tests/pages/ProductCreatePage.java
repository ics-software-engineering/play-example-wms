package tests.pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

/** The Product Create Page representation. */
public class ProductCreatePage extends FluentPage {
  private final String url;

  /**
   * Create a Product Create page.
   * @param webDriver The driver.
   * @param port The port.
   */
  public ProductCreatePage(WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/product/create";
  }

  @Override
  public String getUrl() {
    return this.url;
  }

  @Override
  public void isAt() {
    assert (title().equals("WMS: Create Product"));
  }

  /**
   * Create a new product.
   * @param productId The id.
   */
  public void makeNewProduct(String productId) {
    // For testing purposes, use the same string for both ID and name.
    fill("#productId").with(productId);
    fill("#name").with(productId);
    fill("#description").with("A nice product");
    submit("#create");
  }
}
