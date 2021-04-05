package museum.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.facade.AuthorFacade;
import service.facade.ExhibitFacade;
import service.model.author.BaseAuthor;
import service.model.author.ExistingAuthor;
import service.model.exhibit.ExistingExhibit;

import java.util.List;

@RestController
@RequestMapping(
  value = "/author",
  produces = "application/json"
)
public class AuthorApi {

  private final AuthorFacade authorFacade;

  @Autowired
  public AuthorApi(final AuthorFacade authorFacade) {
    this.authorFacade = authorFacade;
  }

  @GetMapping( )
  List<BaseAuthor> getAuthors() {
    return authorFacade.getAllAuthors();
  }
}
