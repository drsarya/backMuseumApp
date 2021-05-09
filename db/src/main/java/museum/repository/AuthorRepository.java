package museum.repository;

import museum.domen.AuthorModel;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<AuthorModel, Integer> {
  AuthorModel findByFullName(String fullName);
}
