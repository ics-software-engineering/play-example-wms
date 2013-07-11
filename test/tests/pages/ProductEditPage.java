package tests.pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

/** The Product Edit page representation. */
public class ProductEditPage extends FluentPage {
  private final String url;

  /**
   * Create a product edit page.
   * @param webDriver The driver.
   * @param port The port.
   * @param primaryKey The PK.
   */
  public ProductEditPage(WebDriver webDriver, int port, int primaryKey) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/product/" + primaryKey;
  }

  @Override
  public String getUrl() {
    return this.url;
  }

  @Override
  public void isAt() {
    assert (title().equals("WMS: Update Product"));
  }

  /**
   * Edit the product.
   * @param newProductId The new product ID.
   */
  public void editProduct(String newProductId) {
 // For testing purposes, use the same string for both ID and name.
    fill("#productId").with(newProductId);
    fill("#name").with(newProductId);
    fill("#description").with("A nice product");
    submit("#update");
  }

  /** Delete the product. */
  public void deleteProduct() {
    click("#delete");
  }
}
