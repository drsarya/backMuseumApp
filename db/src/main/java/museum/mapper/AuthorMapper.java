package museum.mapper;

import museum.domen.AuthorModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorMapper extends CrudRepository<AuthorModel, Integer> {
  AuthorModel findByFullName(String fullName );
  //List<AuthorModel> find( );

}
