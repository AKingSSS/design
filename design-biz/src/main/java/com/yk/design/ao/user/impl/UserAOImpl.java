package com.yk.design.ao.user.impl;

import com.yk.design.ao.user.UserAO;
import com.yk.design.dao.user.UserDao;
import com.yk.design.domain.user.UserDo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserAOImpl
 * @Description 用户接口
 * @Author yangkang
 * @Date 2019/7/315:37
 * @Version 1.0
 **/
@Service
public class UserAOImpl implements UserAO {
    private static final Logger logger = LogManager.getLogger(UserAOImpl.class);
    @Autowired
    private UserDao userDao;
    @Override
    public void login(UserDo userDo) {
        try {
            userDao.insert(userDo);
            logger.info("添加新用户成功！");
        } catch (Exception e) {
            logger.error("添加新用户失败！",e);
            e.printStackTrace();
        }
    }
}
