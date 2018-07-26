package org.lockiely.persistence.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.mapper.SqlCondition;
import java.io.Serializable;
import java.util.Date;
import org.lockiely.persistence.entity.enums.AgeEnum;
import org.lockiely.persistence.entity.enums.PhoneEnum;

//@KeySequence(value="name", clazz = String.class)
public class OldUser extends BaseEntity<OldUser> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long tenantId;
    /**
     * 名称 , condition 属性设置注入
     * 等效于 SQL 为：WHERE name LIKE CONCAT('%',s值,'%')
     */
    @TableField(condition = SqlCondition.LIKE)
    private String name;

    private String password;
    /**
     * update 时候注入年龄 + 1
     * 等效于 SQL 为： update user set age=age+1
     */
//    @TableField(update = "%s+1")
    private AgeEnum age;
    /**
     * 这里故意演示注解可无
     */
    @TableField("test_type")
    @TableLogic
    private int testType;

    /**
     * 注入更新内容【可无】
     */
    @TableField
    private Date testDate;

    private Long role;
    private PhoneEnum phone;

    public OldUser() {
    }

    public OldUser(Long id, String name, AgeEnum age, int testType) {
        this.setId(id);
        this.name = name;
        this.age = age;
        this.testType = testType;
    }

    public OldUser(String name, AgeEnum age, int testType) {
        this.name = name;
        this.age = age;
        this.testType = testType;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AgeEnum getAge() {
        return this.age;
    }

    public void setAge(AgeEnum age) {
        this.age = age;
    }

    public int getTestType() {
        return this.testType;
    }

    public void setTestType(int testType) {
        this.testType = testType;
    }

    public Long getRole() {
        return this.role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public PhoneEnum getPhone() {
        return this.phone;
    }

    public void setPhone(PhoneEnum phone) {
        this.phone = phone;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + this.getId() + ", name=" + name + ", age=" + age
            + ", testType=" + testType + ", testDate="
            + testDate + ", role=" + role + ", phone=" + phone + "]";
    }
}
