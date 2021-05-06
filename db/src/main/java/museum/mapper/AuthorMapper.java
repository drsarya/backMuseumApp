package museum.mapper;

import museum.domen.AuthorModel;
import org.springframework.data.repository.CrudRepository;

public interface AuthorMapper extends CrudRepository<AuthorModel, Integer> {
  AuthorModel findByFullName(String fullName);
}
