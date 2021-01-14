package dataBase.mapper;


import dataBase.model.UserModel;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

  UserModel createUser(UserModel usermodel);
}
