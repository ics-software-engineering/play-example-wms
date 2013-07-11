package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

/**
 * Represents products.
 */
@Entity
public class Product extends Model {
  private static final long serialVersionUID = -463965431223831634L;
  @Id
  private Long primaryKey;
  @Required
  private String productId;
  @Required
  private String name;
  private String description;
  @Transient
  private String stockItemList = "";
  @ManyToMany(cascade = CascadeType.ALL)
  private List<Tag> tags = new ArrayList<>();
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  private List<StockItem> stockitems = new ArrayList<>();

  /**
   * Create the product instance.
   * @param productId The ID.
   * @param name The name.
   * @param description The description.
   */
  public Product(String productId, String name, String description) {
    this.productId = productId;
    this.name = name;
    this.description = description;
  }

  /**
   * The EBean ORM finder method for database queries on Products.
   * @return The finder method for products.
   */
  public static Finder<Long, Product> find() {
    return new Finder<Long, Product>(Long.class, Product.class);
  }

  @Override
  public String toString() {
    return String.format("[Product %s %s %s]", getProductId(), getName(), getDescription());
  }

  /**
   * Sets the stockItemList string by retrieving the current set of stock items.
   */
  public void setStockItemList() {
    stockItemList = "";
    for (StockItem item : getStockitems()) {
      stockItemList += item.getStockItemId() + "\n";
    }
  }

  /**
   * Retrieves a list of all product names from database.
   * @return A list of product names.
   */
  public static List<String> getNames() {
    List<String> productNames = new ArrayList<>();
    for (Product product : find().all()) {
      productNames.add(product.getName());
    }
    return productNames;
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
   * @return the productId
   */
  public String getProductId() {
    return productId;
  }

  /**
   * @param productId the productId to set
   */
  public void setProductId(String productId) {
    this.productId = productId;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the stockItemList
   */
  public String getStockItemList() {
    return stockItemList;
  }

  /**
   * @param stockItemList the stockItemList to set
   */
  public void setStockItemList(String stockItemList) {
    this.stockItemList = stockItemList;
  }

  /**
   * @return the tags
   */
  public List<Tag> getTags() {
    return tags;
  }

  /**
   * @param tags the tags to set
   */
  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  /**
   * @return the stockitems
   */
  public List<StockItem> getStockitems() {
    return stockitems;
  }

  /**
   * @param stockitems the stockitems to set
   */
  public void setStockitems(List<StockItem> stockitems) {
    this.stockitems = stockitems;
  }

}
