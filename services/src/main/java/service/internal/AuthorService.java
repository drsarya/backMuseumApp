package service.internal;

import service.model.author.BaseAuthor;
import service.model.author.ExistingAuthor;

import java.util.List;

public interface AuthorService {
  List<ExistingAuthor> getAllAuthors();

  ExistingAuthor getAuthorByName(String name);

  BaseAuthor insertAuthor(String author);
}
