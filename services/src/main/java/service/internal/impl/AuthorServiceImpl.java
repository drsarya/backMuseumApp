package service.internal.impl;

import museum.domen.AuthorModel;
import museum.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.internal.AuthorService;
import service.mapper.AuthorStruct;
import service.model.author.BaseAuthor;
import service.model.author.ExistingAuthor;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
  private final AuthorMapper authorMapper;
  private final AuthorStruct authorStruct;

  @Autowired
  public AuthorServiceImpl(final AuthorMapper authorMapper, final AuthorStruct authorStruct) {
    this.authorMapper = authorMapper;
    this.authorStruct = authorStruct;
  }

  @Override
  public List<ExistingAuthor> getAllAuthors() {
    Iterable<AuthorModel> authorModels = authorMapper.findAll();
    List<AuthorModel> actualList = new ArrayList<AuthorModel>();
    while (authorModels.iterator().hasNext()) {
      actualList.add(authorModels.iterator().next());
    }
    return authorStruct.toListExistingAuthor(actualList);

  }

  @Override
  public ExistingAuthor getAuthorByName(String name) {
    AuthorModel authorModel = authorMapper.findByFullName(name);
    if (authorModel != null) {
      return authorStruct.toExistingAuthor(authorModel);
    }
    return null;
  }

  @Override
  public ExistingAuthor insertAuthor(BaseAuthor author) {
    AuthorModel authorModel = authorMapper.save(authorStruct.toAuthorModel(author));
    if (authorModel != null) {
      return authorStruct.toExistingAuthor(authorModel);
    }
    return null;
  }
}
