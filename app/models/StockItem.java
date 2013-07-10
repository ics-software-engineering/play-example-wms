package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

/** Provides the model for StockItems. */
@Entity
public class StockItem extends Model {
  private static final long serialVersionUID = -8143420434783395242L;
  @Id
  private Long primaryKey;
  @Required
  private String stockItemId;
  @Transient
  private String productName;
  @Transient
  private String warehouseName;
  //@Required
  @ManyToOne(cascade = CascadeType.PERSIST)
  private Warehouse warehouse;
  //@Required
  @ManyToOne(cascade = CascadeType.PERSIST)
  private Product product;
  @Required
  private long quantity;

  /**
   * Create a StockItem.
   * @param warehouse The warehouse.
   * @param product The product.
   * @param quantity The quantity.
   */
  public StockItem(Warehouse warehouse, Product product, long quantity) {
    this.setWarehouse(warehouse);
    this.setProduct(product);
    this.setQuantity(quantity);
  }

  /**
   * Create a default StockItem.
   * @param stockItemId The default name.
   * @param quantity The default quantity
   */
  public StockItem(String stockItemId, int quantity) {
    this.setStockItemId(stockItemId);
    this.setQuantity(quantity);
  }

  /**
   * @return The EBean ORM finder method.
   */
  public static Finder<Long, StockItem> find() {
    return new Finder<Long, StockItem>(Long.class, StockItem.class);
  }

  /**
   * Set the product and warehouse instance from the strings.
   * @return Null if OK, a string error message otherwise.
   */
  public String validate() {
    if (getProductName() != null) {
      setProduct(Product.find().where().eq("name", getProductName()).findUnique());
      if (getProduct() == null) {
        return "Could not find product named: " + getProductName();
      }
    }
    if (getWarehouseName() != null) {
      setWarehouse(Warehouse.find().where().eq("name", getWarehouseName()).findUnique());
      if (getWarehouse() == null) {
        return "Could not find warehouse named: " + getWarehouseName();
      }
    }
    // otherwise all good.
    return null;
  }

  /**
   * @return the primaryKey
   */
  public Long getPrimaryKey() {
    return primaryKey;
  }

  /**
   * @param primaryKey the primaryKey to set
   */
  public void setPrimaryKey(Long primaryKey) {
    this.primaryKey = primaryKey;
  }

  /**
   * @return the stockItemId
   */
  public String getStockItemId() {
    return stockItemId;
  }

  /**
   * @param stockItemId the stockItemId to set
   */
  public void setStockItemId(String stockItemId) {
    this.stockItemId = stockItemId;
  }

  /**
   * @return the productName
   */
  public String getProductName() {
    return productName;
  }

  /**
   * @param productName the productName to set
   */
  public void setProductName(String productName) {
    this.productName = productName;
  }

  /**
   * @return the warehouseName
   */
  public String getWarehouseName() {
    return warehouseName;
  }

  /**
   * @param warehouseName the warehouseName to set
   */
  public void setWarehouseName(String warehouseName) {
    this.warehouseName = warehouseName;
  }

  /**
   * @return the warehouse
   */
  public Warehouse getWarehouse() {
    return warehouse;
  }

  /**
   * @param warehouse the warehouse to set
   */
  public void setWarehouse(Warehouse warehouse) {
    this.warehouse = warehouse;
  }

  /**
   * @return the product
   */
  public Product getProduct() {
    return product;
  }

  /**
   * @param product the product to set
   */
  public void setProduct(Product product) {
    this.product = product;
  }

  /**
   * @return the quantity
   */
  public long getQuantity() {
    return quantity;
  }

  /**
   * @param quantity the quantity to set
   */
  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }
}
