package org.lockiely.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.lockiely.datasource.DynamicDataSource;
import org.lockiely.datasource.support.DynamicDataSourceProperties;
import org.lockiely.persistence.entity.OldUser;
import org.lockiely.persistence.entity.User;
import org.lockiely.persistence.entity.enums.AgeEnum;
import org.lockiely.event.UserPublisher;
import org.lockiely.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserPublisher userPublisher;

    @Override
    @DynamicDataSource(dataSource = DynamicDataSourceProperties.DYNAMIC_DATA_SOURCE_SLAVE)
    public List<User> selectListSql() {
        return baseMapper.selectListSql();
    }

    @Override
    public boolean deleteAll() {
        return retBool(baseMapper.deleteAll());
    }

    @Override
    public List<User> selectListByPage(RowBounds rowBounds, String name) {
        return null;
    }

    @Override
    @DynamicDataSource(dataSource = DynamicDataSourceProperties.DYNAMIC_DATA_SOURCE_SLAVE)
    public void registerUser(String name, String password) {
        User user = new User();
        user.setAccount(name);
        user.setPassword(password);
        baseMapper.insert(user);
        userPublisher.sendMailByRegisterUser(name);
    }
}
