package com.yk.design.dao.user;

import com.yk.design.domain.user.UserDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    /**
     * 添加新用户
     * @param userDo
     * @return
     * @throws Exception
     */
    public Long insert(UserDo userDo) throws Exception;
}
