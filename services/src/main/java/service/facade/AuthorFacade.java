package service.facade;

import org.springframework.validation.annotation.Validated;
import service.model.author.BaseAuthor;
import service.model.author.ExistingAuthor;

import java.util.List;

@Validated
public interface AuthorFacade {
  List<BaseAuthor> getAllAuthors();


}
