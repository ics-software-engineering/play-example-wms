package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

/** The model for Tags. */
@Entity
public class Tag extends Model {
  private static final long serialVersionUID = -5950706943271500599L;
  @Id
  private Long primaryKey;
  @Required
  private String tagId;
  @ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
  private List<Product> products = new ArrayList<>();

  /**
   * Create a tag.
   * @param tagId The tagId.
   */
  public Tag(String tagId) {
    this.tagId = tagId;
  }

  /**
   * No tag can be named "Tag".
   * Note: illustrates use of validate() method.
   * @return null if OK, error string if not OK.
   */
  public String validate() {
    return ("Tag".equals(this.getTagId())) ? "Invalid tag name" : null;
  }

  /**
   * @return The EBean ORM finder method for Tags.
   */
  public static Finder<Long, Tag> find() {
    return new Finder<Long, Tag>(Long.class, Tag.class);
  }

  @Override
  public String toString() {
    return String.format("[Tag %s ]", getTagId());
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
   * @return the tagId
   */
  public String getTagId() {
    return tagId;
  }

  /**
   * @param tagId the tagId to set
   */
  public void setTagId(String tagId) {
    this.tagId = tagId;
  }

  /**
   * @return the products
   */
  public List<Product> getProducts() {
    return products;
  }

  /**
   * @param products the products to set
   */
  public void setProducts(List<Product> products) {
    this.products = products;
  }
}
