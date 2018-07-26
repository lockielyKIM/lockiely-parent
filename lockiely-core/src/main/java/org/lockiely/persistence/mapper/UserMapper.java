package org.lockiely.persistence.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;
import org.lockiely.persistence.entity.OldUser;
import org.lockiely.persistence.entity.User;


public interface UserMapper extends SuperMapper<User> {

    @SqlParser(filter = true)
    @Delete("delete from user")
    int deleteAll();

    @SqlParser(filter = true)   //
    @Select("select id, name, age, test_type from user")
    List<User> selectListSql();

    @SqlParser(filter = true)
    @Select("select id, name, age from user where name = #{name}")
    List<User> selectListByPage(RowBounds rowBounds, String name);

    /**
     * 修改用户状态
     *
     * @param userId
     * @param status
     * @date 2017年2月12日 下午8:42:31
     */
    int setStatus(@Param("userId") Integer userId, @Param("status") int status);

    /**
     * 修改密码
     *
     * @param userId
     * @param pwd
     * @date 2017年2月12日 下午8:54:19
     */
    int changePwd(@Param("userId") Integer userId, @Param("pwd") String pwd);

    /**
     * 根据条件查询用户列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Map<String, Object>> selectUsers(@Param("name") String name, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("deptid") Integer deptid);

    /**
     * 设置用户的角色
     *
     * @return
     * @date 2017年2月13日 下午7:31:30
     */
    int setRoles(@Param("userId") Integer userId, @Param("roleIds") String roleIds);

    /**
     * 通过账号获取用户
     *
     * @param account
     * @return
     * @date 2017年2月17日 下午11:07:46
     */
    User getByAccount(@Param("account") String account);
}
