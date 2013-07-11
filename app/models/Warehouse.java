package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

/**
 * The model for Warehouses.
 */
@Entity
public class Warehouse extends Model {
  private static final long serialVersionUID = 7268900706085963780L;
  @Id
  private long primaryKey;
  @Required
  private String warehouseId;
  @Required
  private String name;
  @Required
  private String city;
  @Required
  private String state;
  @Required
  private String zip;

  @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
  private List<StockItem> stockItems = new ArrayList<>();

  /**
   * Create a warehouse.
   * @param warehouseId The ID.
   * @param name The name.
   * @param city The city.
   * @param state The state.
   * @param zip The zip code.
   */
  public Warehouse(String warehouseId, String name, String city, String state, String zip) {
    this.warehouseId = warehouseId;
    this.name = name;
    this.city = city;
    this.state = state;
    this.zip = zip;
  }

  /**
   * @return The EBean ORM finder method.
   */
  public static Finder<Long, Warehouse> find() {
    return new Finder<Long, Warehouse>(Long.class, Warehouse.class);
  }

  /**
   * @return Retrieve and return the warehouse names as a list of strings.
   */
  public static List<String> getNames() {
    List<String> warehouseNames = new ArrayList<>();
    for (Warehouse warehouse : find().all()) {
      warehouseNames.add(warehouse.getName());
    }
    return warehouseNames;
  }

  /**
   * @return the primaryKey
   */
  public long getPrimaryKey() {
    return primaryKey;
  }

  /**
   * @param primaryKey the primaryKey to set
   */
  public void setPrimaryKey(long primaryKey) {
    this.primaryKey = primaryKey;
  }

  /**
   * @return the warehouseId
   */
  public String getWarehouseId() {
    return warehouseId;
  }

  /**
   * @param warehouseId the warehouseId to set
   */
  public void setWarehouseId(String warehouseId) {
    this.warehouseId = warehouseId;
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
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * @param city the city to set
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * @return the state
   */
  public String getState() {
    return state;
  }

  /**
   * @param state the state to set
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * @return the zip
   */
  public String getZip() {
    return zip;
  }

  /**
   * @param zip the zip to set
   */
  public void setZip(String zip) {
    this.zip = zip;
  }

  /**
   * @return the stockItems
   */
  public List<StockItem> getStockItems() {
    return stockItems;
  }

  /**
   * @param stockItems the stockItems to set
   */
  public void setStockItems(List<StockItem> stockItems) {
    this.stockItems = stockItems;
  }

}
