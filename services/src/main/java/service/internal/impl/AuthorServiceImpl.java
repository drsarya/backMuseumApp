package service.internal.impl;

import museum.domen.AuthorModel;
import museum.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.internal.AuthorService;
import service.mapper.AuthorStruct;
import service.model.author.BaseAuthor;
import service.model.author.ExistingAuthor;

import java.util.List;
import java.util.stream.Stream;

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

   // return authorStruct.toListExistingAuthor(authorMapper.findAll());
    return null;

  }

  @Override
  public ExistingAuthor getAuthorByName(String name) {
    return null;
  }

  @Override
  public BaseAuthor insertAuthor(String author) {
    return null;
  }
}
