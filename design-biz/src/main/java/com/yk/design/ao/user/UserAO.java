package com.yk.design.ao.user;

import com.yk.design.domain.user.UserDo;

public interface UserAO {
    /**
     * 登陆接口
     */
    public void register(UserDo userDo) throws Exception;
}
