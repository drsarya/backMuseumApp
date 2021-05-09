package service.internal.impl;

import museum.domen.AuthorModel;
import museum.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.internal.AuthorService;
import service.mapper.AuthorMapper;
import service.model.author.BaseAuthor;
import service.model.author.ExistingAuthor;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
  private final AuthorRepository authorRepository;
  private final AuthorMapper authorMapper;

  @Autowired
  public AuthorServiceImpl(final AuthorRepository authorRepository, final AuthorMapper authorMapper) {
    this.authorRepository = authorRepository;
    this.authorMapper = authorMapper;
  }

  @Override
  public List<BaseAuthor> getAllAuthors() {
    List<AuthorModel> authorModels = (List<AuthorModel>) authorRepository.findAll();
    return authorMapper.toListBaseAuthor(authorModels);
  }


  @Override
  public ExistingAuthor findAuthorByFullName(String name) {
    AuthorModel authorModel = authorRepository.findByFullName(name);
    if (authorModel == null) {
      authorModel = new AuthorModel();
      authorModel.setFullName(name);
      authorModel = authorRepository.save(authorModel);
    }
    return authorMapper.toExistingAuthor(authorModel);
  }

}
