
package service.internal;

import service.model.AnswerModel;
import service.model.user.ExistingUser;
import service.model.user.NewUser;
import service.model.user.UserMuseum;
import service.model.user.UserUpdate;

public interface UserService {

  AnswerModel createUser(NewUser user) throws Exception;

  ExistingUser getUser(NewUser user) throws Exception;

  AnswerModel updateUserPassword(UserUpdate user) throws Exception;

  AnswerModel updateMuseumUserPass(UserMuseum user) throws Exception;
}
