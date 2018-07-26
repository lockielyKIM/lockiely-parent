package org.lockiely.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import org.lockiely.persistence.entity.enums.SexEnum;

public class User extends BaseEntity<User> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private String  account;
    /**
     * 密码
     */
    private String  password;
    /**
     * md5密码盐
     */
    private String  salt;
    /**
     * 名字
     */
    private String  userName;
    /**
     * 生日
     */
    private Date    birthday;
    /**
     * 性别（1：男 2：女）
     */
    private SexEnum sex;
    /**
     * 电子邮件
     */
    private String  email;
    /**
     * 电话
     */
    private String  phone;
    /**
     * 角色id
     */
    private String  roleId;
    /**
     * 部门id
     */
    private Integer deptId;
    /**
     * 状态(1：启用  2：冻结  3：删除）
     */
    private Integer status;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
            "id"+"}";
    }
}
