package org.lockiely.service;

import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.lockiely.persistence.entity.OldUser;
import org.lockiely.persistence.entity.User;

public interface UserService extends IService<User> {

    boolean deleteAll();

    List<User> selectListSql();

    List<User> selectListByPage(RowBounds rowBounds, String name);

    void registerUser(String name, String password);
}
