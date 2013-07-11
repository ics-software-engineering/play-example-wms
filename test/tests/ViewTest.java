package tests;
import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;
import org.junit.Test;
import play.libs.F.Callback;
import play.test.TestBrowser;
import tests.pages.IndexPage;
import tests.pages.ProductCreatePage;
import tests.pages.ProductEditPage;
import tests.pages.StockItemCreatePage;
import tests.pages.StockItemEditPage;
import tests.pages.WarehouseCreatePage;
import tests.pages.WarehouseEditPage;

/**
 * Test the view code.
 */
public class ViewTest {
  private final int testPort = 3333;

  /** Test retrieval of the home page. */
  @Test
  public void testIndexPage() {
    running(testServer(testPort, fakeApplication(inMemoryDatabase())), HTMLUNIT,
        new Callback<TestBrowser>() {
      @Override
      public void invoke(TestBrowser browser) {
        IndexPage homePage = new IndexPage(browser.getDriver(), testPort);
        browser.goTo(homePage);
        homePage.isAt();
        homePage.gotoNewWarehouse();
      }
    });
  }

  /** Test creation of a warehouse. */
  @Test
  public void testWarehouseCreatePage() {
    running(testServer(testPort, fakeApplication(inMemoryDatabase())), HTMLUNIT,
        new Callback<TestBrowser>() {
      @Override
      public void invoke(TestBrowser browser) {
        // Create the pages.
        WarehouseCreatePage warehousePage = new WarehouseCreatePage(browser.getDriver(),
            testPort);
        IndexPage homePage = new IndexPage(browser.getDriver(), testPort);
        // Now test the page.
        browser.goTo(warehousePage);
        warehousePage.isAt();
        String warehouseId = "NewTestWarehouse";
        warehousePage.makeNewWarehouse(warehouseId);
        homePage.isAt();
        homePage.pageSource().contains(warehouseId);
      }
    });
  }

  /** Test retrieval and manipulation of the warehouse edit page. */
  @Test
  public void testWarehouseEditPage() {
    running(testServer(testPort, fakeApplication(inMemoryDatabase())), HTMLUNIT,
        new Callback<TestBrowser>() {
      @Override
      public void invoke(TestBrowser browser) {
        // Define the pages.
        WarehouseCreatePage warehousePage =
            new WarehouseCreatePage(browser.getDriver(), testPort);
        IndexPage homePage = new IndexPage(browser.getDriver(), testPort);
        // Test that we can create a page.
        browser.goTo(warehousePage);
        warehousePage.isAt();
        String warehouseId = "NewTestWarehouseId";
        warehousePage.makeNewWarehouse(warehouseId);
        homePage.isAt();
        homePage.pageSource().contains(warehouseId);
        // Test that we can edit a page.
        //   We should really get the PK from the home page, not just magically know it.
        WarehouseEditPage editPage = new WarehouseEditPage(browser.getDriver(), testPort, 1);
        browser.goTo(editPage);
        String editWarehouseId = "EditedWarehouseId";
        editPage.editWarehouse(editWarehouseId);
        homePage.pageSource().contains(editWarehouseId);
        // Test that we can delete the page and it will no longer be found on the home page.
        browser.goTo(editPage);
        editPage.deleteWarehouse();
        homePage.isAt();
        assertThat(homePage.pageSource()).doesNotContain(editWarehouseId);
      }
    });
  }

  /** Test the product create page. */
  @Test
  public void testProductCreatePage() {
    running(testServer(testPort, fakeApplication(inMemoryDatabase())), HTMLUNIT,
        new Callback<TestBrowser>() {
      @Override
      public void invoke(TestBrowser browser) {
        // Create the pages.
        ProductCreatePage productPage = new ProductCreatePage(browser.getDriver(), testPort);
        IndexPage homePage = new IndexPage(browser.getDriver(), testPort);
        // Now test the page.
        browser.goTo(productPage);
        productPage.isAt();
        String id = "NewTestProduct";
        productPage.makeNewProduct(id);
        homePage.isAt();
        homePage.pageSource().contains(id);
      }
    });
  }

  /** Test the product edit page. */
  @Test
  public void testProductEditPage() {
    running(testServer(testPort, fakeApplication(inMemoryDatabase())), HTMLUNIT,
        new Callback<TestBrowser>() {
      @Override
      public void invoke(TestBrowser browser) {
        // Define the pages.
        ProductCreatePage productPage = new ProductCreatePage(browser.getDriver(), testPort);
        IndexPage homePage = new IndexPage(browser.getDriver(), testPort);
        // Test that we can create a page.
        browser.goTo(productPage);
        productPage.isAt();
        String productId = "NewTestProductId";
        productPage.makeNewProduct(productId);
        homePage.isAt();
        homePage.pageSource().contains(productId);
        // Test that we can edit a page.
        //   We should really get the PK from the home page, not just magically know it.
        ProductEditPage editPage = new ProductEditPage(browser.getDriver(), testPort, 1);
        browser.goTo(editPage);
        String editProductId = "EditedProductId";
        editPage.editProduct(editProductId);
        homePage.pageSource().contains(editProductId);
        // Test that we can delete the page and it will no longer be found on the home page.
        browser.goTo(editPage);
        editPage.deleteProduct();
        homePage.isAt();
        assertThat(homePage.pageSource()).doesNotContain(editProductId);
      }
    });
  }

  /** Test the StockItem create page. */
  @Test
  public void testStockItemCreatePage() {
    running(testServer(testPort, fakeApplication(inMemoryDatabase())), HTMLUNIT,
        new Callback<TestBrowser>() {
      @Override
      public void invoke(TestBrowser browser) {
        // create a reference to the home page.
        IndexPage homePage = new IndexPage(browser.getDriver(), testPort);
        // Create a product.
        ProductCreatePage productPage = new ProductCreatePage(browser.getDriver(), testPort);
        browser.goTo(productPage);
        String productId = "NewTestProduct";
        productPage.makeNewProduct(productId);
        // Create a warehouse.
        WarehouseCreatePage warehousePage = new WarehouseCreatePage(browser.getDriver(), testPort);
        browser.goTo(warehousePage);
        String warehouseId = "NewTestWarehouse";
        warehousePage.makeNewWarehouse(warehouseId);
        // Now create a StockItem that references the test product and warehouse
        String stockItemId = "SI-01";
        StockItemCreatePage stockItemPage = new StockItemCreatePage(browser.getDriver(), testPort);
        browser.goTo(stockItemPage);
        stockItemPage.makeNewStockItem(stockItemId, productId, warehouseId);
        browser.goTo(homePage);
        homePage.isAt();
        homePage.pageSource().contains(stockItemId);
      }
    });
  }

  /** Test the StockItem edit page. */
  @Test
  public void testStockItemEditPage() {
    running(testServer(testPort, fakeApplication(inMemoryDatabase())), HTMLUNIT,
        new Callback<TestBrowser>() {
      @Override
      public void invoke(TestBrowser browser) {
        // create a reference to the home page.
        IndexPage homePage = new IndexPage(browser.getDriver(), testPort);

        // Create a product.
        ProductCreatePage productPage = new ProductCreatePage(browser.getDriver(), testPort);
        browser.goTo(productPage);
        String productId = "NewTestProduct";
        productPage.makeNewProduct(productId);

        // Create a warehouse.
        WarehouseCreatePage warehousePage = new WarehouseCreatePage(browser.getDriver(), testPort);
        browser.goTo(warehousePage);
        String warehouseId = "NewTestWarehouse";
        warehousePage.makeNewWarehouse(warehouseId);

        // Now create a StockItem that references the test product and warehouse
        String stockItemId = "SI-01";
        StockItemCreatePage stockItemPage = new StockItemCreatePage(browser.getDriver(), testPort);
        browser.goTo(stockItemPage);
        //Note: stockItemId = stockItemName and productId = productName.
        stockItemPage.makeNewStockItem(stockItemId, productId, warehouseId);
        homePage.isAt();
        homePage.pageSource().contains(stockItemId);

        // Now we can finally edit it.
        StockItemEditPage editPage = new StockItemEditPage(browser.getDriver(), testPort, 1);
        browser.goTo(editPage);

        String editStockItemId = "EditedStockItemId";
        //Note: stockItemId = stockItemName and productId = productName.
        editPage.editStockItem(editStockItemId, productId, warehouseId);
        homePage.pageSource().contains(editStockItemId);
        // Test that we can delete the page and it will no longer be found on the home page.
        browser.goTo(editPage);
        editPage.deleteStockItem();
        homePage.isAt();
        assertThat(homePage.pageSource()).doesNotContain(editStockItemId);
      }
    });
  }
}
