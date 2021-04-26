package service.facade;

import org.springframework.validation.annotation.Validated;
import service.model.AnswerModel;
import service.model.user.ExistingUser;
import service.model.user.NewUser;
import service.model.user.UserMuseum;
import service.model.user.UserUpdate;

@Validated
public interface UserFacade {

  ExistingUser getUser(NewUser user) throws Exception;

  AnswerModel updateUserPassword(UserUpdate user) throws Exception;

  AnswerModel createUser(NewUser user) throws Exception;

  AnswerModel updateMuseumUserPass(UserMuseum user) throws Exception;

}
