package com.dayup.seckil.service.impl;

import com.dayup.seckil.VO.UserVO;
import com.dayup.seckil.model.User;
import com.dayup.seckil.redis.UserRedis;
import com.dayup.seckil.repository.UserRepository;
import com.dayup.seckil.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author domn
 * @version 1.0
 * @date 2020/1/12 1:18
 */
@Service
@Transactional
public class UserviceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRedis userRedis;

    @Override
    public User regist(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public UserVO getUser(String username) {
        UserVO userVO = new UserVO();
        User user = userRedis.get(username);
        if(user == null) {
            user = userRepository.findByUsername(username);
            if(user != null) {
                userRedis.put(user.getUsername(), user, -1);
            } else {
                return null;
            }
        }
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public void saveUserToRedisByToken(UserVO dbUser, String token) {
        User user = new User();
        BeanUtils.copyProperties(dbUser, user);
        userRedis.put(token, user, 3600);
    }

    @Override
    public Object getUserFromRedisByToken(String token) {

        return userRedis.get(token);
    }
}
