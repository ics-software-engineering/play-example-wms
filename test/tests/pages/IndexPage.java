package tests.pages;
import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

/** Index Page representation. */
public class IndexPage extends FluentPage {
  private final String url;

  /**
   * Create the index page.
   * @param webDriver the driver.
   * @param port the port.
   */
  public IndexPage(WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port;
  }

  @Override
  public String getUrl() {
    return this.url;
  }

  @Override
  public void isAt() {
    assert (title().equals("WMS: Home"));
  }

  /** Click the newWarehouse button. */
  public void gotoNewWarehouse() {
    click("newWarehouse");
    assert (title().equals("WMS: Create Warehouse"));
  }
}
