package museum.mapper;

import museum.domen.MuseumModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MuseumMapper extends CrudRepository<MuseumModel, Integer> {

  //MuseumModel findMuseumModelByLogin(String login);

  //MuseumModel findMuseumModelByLoginAndId(String login, int id);
  MuseumModel findById(Integer id);

  @Query("Select   m  From  museums  m join users  u on  u.museum = m.id WHERE m.id = :id and u.login =:loginAdmin   ")
  MuseumModel findMuseumByIdAndMuseumAdmin(@Param("id") Integer id, @Param("loginAdmin") String loginAdmin);


  @Query("Select   m  From  museums  m join users  u on  u.museum = m.id WHERE u.id = :userId   ")
  MuseumModel findMuseumByUserId (@Param("userId") Integer id );

  //void findMuseumModelByIdAndWorkers

}
