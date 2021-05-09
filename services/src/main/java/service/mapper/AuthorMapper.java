package service.mapper;

import museum.domen.AuthorModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import service.model.author.BaseAuthor;
import service.model.author.ExistingAuthor;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface AuthorMapper {

  @Mapping(target = "fullName", source = "fullName")
  BaseAuthor toBaseAuthor(AuthorModel authorModel);
  @Mapping(target = "fullName", source = "fullName")
  @Mapping(target = "id", source = "id")
  ExistingAuthor toExistingAuthor(AuthorModel authorModel);

  default List<BaseAuthor> toListBaseAuthor(List<AuthorModel> users) {
    return users
      .stream()
      .map(this::toBaseAuthor)
      .collect(Collectors.toList());
  }
}
