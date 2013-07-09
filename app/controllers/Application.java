package controllers;

import java.util.List;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

/**
 * Implements the controller for /index requests.
 */
public class Application extends Controller {

  /**
   * Implements the /index request.
   * @return OK if home page can be rendered.
   */
  public static Result index() {
    List<models.Warehouse> warehouses = models.Warehouse.find().all();
    List<models.Product> products = models.Product.find().all();
    List<models.StockItem> stockItems = models.StockItem.find().all();
    return ok(index.render(warehouses, products, stockItems));
  }
}
