package service.mapper;

import museum.domen.AuthorModel;
import museum.domen.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import service.model.author.ExistingAuthor;
import service.model.user.UserUpdate;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface AuthorStruct  {

  @Mapping(target = "fullName", source = "authorModel.fullName")
  @Mapping(target = "id", source = "authorModel.id")
   ExistingAuthor  toExistingAuthor(AuthorModel authorModel);

  default List<ExistingAuthor> toListExistingAuthor(List<AuthorModel> users) {
    return users
      .stream()
      .map(this::toExistingAuthor)
      .collect(Collectors.toList());
  }
}
