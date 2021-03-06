package service.facade.impl;

import org.springframework.stereotype.Service;
import service.facade.AuthorFacade;
import service.internal.AuthorService;
import service.internal.UserService;
import service.model.author.BaseAuthor;
import service.model.author.ExistingAuthor;

import java.util.List;

@Service
public class AuthorFacadeImpl implements AuthorFacade {
  private final AuthorService authorService;

  public AuthorFacadeImpl(final AuthorService authorService) {
    this.authorService = authorService;
  }

  @Override
  public List<BaseAuthor> getAllAuthors() {
    return authorService.getAllAuthors();
  }

}
