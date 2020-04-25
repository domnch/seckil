package com.dayup.seckil.service;

import com.dayup.seckil.VO.UserVO;
import com.dayup.seckil.model.User;

/**
 * @author domn
 * @version 1.0
 * @date 2020/1/12 1:17
 */
public interface UserService {

    User regist(User user);

    UserVO getUser(String name);

    void saveUserToRedisByToken(UserVO dbUser, String token);

    Object getUserFromRedisByToken(String token);
}
