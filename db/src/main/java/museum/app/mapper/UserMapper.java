package museum.app.mapper;

import museum.app.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


@Mapper

public interface UserMapper {


  @Select("" +
    "SELECT   id, " +
    "       phone," +
    "       login," +
    "       type," +
    "FROM \"user\"   " +

    "WHERE id = #{id}"
  )
  UserModel findById(@Param("id") long id);

  @Select(" INSERT INTO \"user\" (login, password, type )\n" +
    "    VALUES (#{login}, #{password}, #{type})\n" +
    "  RETURNING id  , login,password ,type"

  )
  UserModel createUser(UserModel user);


  @Select("" +
    "SELECT  id,   type,    login,   password  "+
    "FROM \"user\"   " +
    "WHERE login = #{login}  "
  )
  UserModel getUser(@Param("login") String login);



}
