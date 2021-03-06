package museum.mapper;

import museum.domen.AuthorModel;
import org.springframework.data.repository.CrudRepository;

public interface AuthorMapper extends CrudRepository<AuthorModel, Long> {
  AuthorModel findByFullName(String fullName );

}
