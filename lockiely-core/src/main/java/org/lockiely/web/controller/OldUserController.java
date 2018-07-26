package org.lockiely.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import java.util.Date;
import org.lockiely.persistence.entity.OldUser;
import org.lockiely.persistence.entity.enums.AgeEnum;
import org.lockiely.persistence.entity.enums.PhoneEnum;
import org.lockiely.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class OldUserController extends BaseController{

    @Autowired
    private UserService userService;

//    @InitBinder
//    public void initBinder(WebDataBinder binder){
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
//    }


//    @PostMapping("/registerUser")
//    public void registerUser(@RequestParam("name") String name, @RequestParam("password") String password){
//        userService.registerUser(name, password);
//    }
//
//    @PostMapping("/getUserList")
//    public Object getUserList(@RequestBody OldUser user){
//        Wrapper wrapper = Condition.create();
//        wrapper.eq("id", user.getId());
//        return user.selectList(wrapper);
//
//    }
//
//    /**
//     * 分页 PAGE
//     */
//    @GetMapping("/test")
//    public Page<OldUser> test() {
//        return userService.selectPage(new Page<OldUser>(0, 12));
//    }
//
//
//
//    /**
//     * AR 部分测试
//     */
//    @GetMapping("/test1")
//    public Page<OldUser> test1() {
//        OldUser user = new OldUser("testAr", AgeEnum.ONE, 1);
//        System.err.println("删除所有：" + user.delete(null));
//        user.setRole(111L);
//        user.setTestDate(new Date());
//        user.setPhone(PhoneEnum.CMCC);
//        user.insert();
//        System.err.println("查询插入结果：" + user.selectById().toString());
//        user.setName("mybatis-plus-ar");
//        System.err.println("更新：" + user.updateById());
//        return user.selectPage(new Page<OldUser>(0, 12), null);
//    }
//
//    /**
//     * 增删改查 CRUD
//     */
//    @GetMapping("/test2")
//    public OldUser test2() {
//        OldUser user1 = userService.selectById("1011497446385238018");
//        System.err.println(user1.toString());
//        System.err.println("删除一条数据：" + userService.deleteById(1L));
//        System.err.println("deleteAll：" + userService.deleteAll());
//        System.err.println("插入一条数据：" + userService.insert(new OldUser(1L, "张三", AgeEnum.TWO, 1)));
//        OldUser user = new OldUser("张三", AgeEnum.TWO, 1);
//        boolean result = userService.insert(user);
//        // 自动回写的ID
//        Long id = user.getId();
//        System.err.println("插入一条数据：" + result + ", 插入信息：" + user.toString());
//        System.err.println("查询：" + userService.selectById(id).toString());
//        System.err.println("更新一条数据：" + userService.updateById(new OldUser(1L, "三毛", AgeEnum.ONE, 1)));
//        for (int i = 0; i < 5; ++i) {
//            userService.insert(new OldUser(Long.valueOf(100 + i), "张三" + i, AgeEnum.ONE, 1));
//        }
//        Page<OldUser> userListPage = userService.selectPage(new Page<OldUser>(1, 5), new EntityWrapper<>(new OldUser()));
//        System.err.println("total=" + userListPage.getTotal() + ", current list size=" + userListPage.getRecords().size());
//        return userService.selectById(1L);
//    }
//
//    /**
//     * 插入 OR 修改
//     */
//    @GetMapping("/test3")
//    public OldUser test3() {
//        OldUser user = new OldUser(1L, "王五", AgeEnum.ONE, 1);
//        user.setPhone(PhoneEnum.CT);
//        System.out.println("插入前：" + user.toString());
//        userService.insertOrUpdate(user);
//        user = userService.selectById(1L);
//        System.out.println("更新后：" + user.toString());
//        return user;
//    }
//
//
//    /**
//     * 测试实体注解注入条件 LIKE 查询
//     */
//    @GetMapping("/like")
//    public Object like() {
//        JSONObject result = new JSONObject();
//        OldUser user = new OldUser();
//        user.setName("三");
//        result.put("result", userService.selectList(new EntityWrapper<OldUser>(user)));
//        return result;
//    }
//
//    @GetMapping("/add")
//    public Object addUser() {
//        OldUser user = new OldUser("张三'特殊`符号", AgeEnum.TWO, 1);
//        user.setPhone(PhoneEnum.CUCC);
//        JSONObject result = new JSONObject();
//        result.put("result", userService.insert(user));
//        return result;
//    }
//
//    @GetMapping("/selectsql")
//    public Object getUserBySql() {
//        JSONObject result = new JSONObject();
//        result.put("records", userService.selectListSql());
//        return result;
//    }
//
//    /**
//     * 7、分页 size 一页显示数量  current 当前页码
//     * 方式一：http://localhost:8080/user/page?size=1&current=1<br>
//     * 方式二：http://localhost:8080/user/pagehelper?size=1&current=1<br>
//     */
//
//    // 参数模式分页
//    @GetMapping("/page")
//    public Object page(Page page) {
//        return userService.selectPage(page);
//    }
//
//    // ThreadLocal 模式分页
//    @GetMapping("/pagehelper")
//    public Object pagehelper(Page page) {
//        PageHelper.setPagination(page);
//        page.setRecords(userService.selectList(null));
//        page.setTotal(PageHelper.freeTotal());//获取总数并释放资源 也可以 PageHelper.getTotal()
//        return page;
//    }
//
//
//    /**
//     * 测试事物
//     * http://localhost:8080/user/test_transactional<br>
//     * 访问如下并未发现插入数据说明事物可靠！！<br>
//     * http://localhost:8080/user/test<br>
//     * <br>
//     * 启动  Application 加上 @EnableTransactionManagement 注解其实可无默认貌似就开启了<br>
//     * 需要事物的方法加上 @Transactional 必须的哦！！
//     */
//    @Transactional
//    @GetMapping("/test_transactional")
//    public void testTransactional() {
//        userService.insert(new OldUser(1000L, "测试事物", AgeEnum.ONE, 3));
//        System.out.println(" 这里手动抛出异常，自动回滚数据");
//        throw new RuntimeException();
//    }
}