package museum.app.mapper;

import museum.app.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


@Mapper

public interface UserMapper {


  @Select("UPDATE \"user\"  SET  password = #{password} where login=#{login} "
  )
  UserModel updatePassword(UserModel user);

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

  @Select("" +
    "SELECT  id,   type,    login,   password  "+
    "FROM \"user\"   " +
    "WHERE login = #{login} AND password IS NULL  "
  )
  UserModel getUserMuseum(@Param("login") String login);


}
