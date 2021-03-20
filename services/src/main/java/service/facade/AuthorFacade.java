package service.facade;

import service.model.author.BaseAuthor;
import service.model.author.ExistingAuthor;

import java.util.List;

public interface AuthorFacade {
  List<ExistingAuthor> getAllAuthors();

  ExistingAuthor getAuthorByName(String name);

  ExistingAuthor insertAuthor(BaseAuthor author);
}
