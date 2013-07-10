package controllers;

import static play.data.Form.form;
import java.util.List;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * The controller for tags.
 */
public class Tag extends Controller {

  /**
   * Provide the controller for /index.
   * @return The tags, as a string.
   */
  public static Result index() {
    List<models.Tag> tags = models.Tag.find().findList();
    return ok(tags.isEmpty() ? "No Tags" : tags.toString());
  }

  /**
   * Return the tag name.
   * @param tagId The tag ID.
   * @return The tag name.
   */
  public static Result details(String tagId) {
    models.Tag tag = models.Tag.find().where().eq("tagId", tagId).findUnique();
    return (tag == null) ? notFound("No Tag found") : ok(tag.toString());
  }

  /**
   * Create a tag, ensuring its name is not "Tag".
   * @return The tag.
   */
  public static Result newTag() {
    // Create a Tag form and bind the request variables to it.
    Form<models.Tag> tagForm = form(models.Tag.class).bindFromRequest();
    // Validate the form values.
    if (tagForm.hasErrors()) {
      return badRequest("Tag name cannot be 'tag'.");
    }
    // form is OK, so make a Tag and save it.
    models.Tag tag = tagForm.get();
    tag.save();
    return ok(tag.toString());
  }

  /**
   * Delete the given tag.
   * @param tagId The tag ID.
   * @return OK.
   */
  public static Result delete(String tagId) {
    models.Tag tag = models.Tag.find().where().eq("tagId", tagId).findUnique();
    if (tag != null) {
      tag.delete();
    }
    return ok();
  }
}
