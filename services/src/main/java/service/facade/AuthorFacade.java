package service.facade;

import service.model.author.BaseAuthor;
import service.model.author.ExistingAuthor;

import java.util.List;

public interface AuthorFacade {
  List<ExistingAuthor> getAllAuthors();


}
