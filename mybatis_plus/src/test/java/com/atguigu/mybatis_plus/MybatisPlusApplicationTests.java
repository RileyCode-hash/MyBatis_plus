package com.atguigu.mybatis_plus;

import com.atguigu.mybatis_plus.entity.User;
import com.atguigu.mybatis_plus.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testOptimisticLock() {
        //根据id查询
        User user = userMapper.selectById(1369872842425233409L);
        //修改
        user.setName("张三");
//        user.setVersion(user.getVersion() + 1); 这行mp自动做了，不需要我们手动做
        userMapper.updateById(user);

    }

    @Test
    public void testSelectPage() {
        Page<User> page = new Page(1, 3);
        Page<User> userPage = userMapper.selectPage(page, null);
        long pages = userPage.getPages(); //总页数
        long current = userPage.getCurrent(); //当前页
        List<User> records = userPage.getRecords(); //查询数据集合
        long total = userPage.getTotal(); //总记录数
        boolean hasNext = userPage.hasNext();  //下一页
        boolean hasPrevious = userPage.hasPrevious(); //上一页

        System.out.println(pages);
        System.out.println(current);
        System.out.println(records);
        System.out.println(total);
        System.out.println(hasNext);
        System.out.println(hasPrevious);
    }

    @Test
    public void findall() {
       List<User> users = userMapper.selectList(null);
        System.out.println(users);

    }

    @Test
    public void testDel() {
        int row = userMapper.deleteById(1369890832562638849L);
        System.out.println(row);
    }

    @Test
    public void testAdd() {
        User user = new User();
        user.setName("王五");
        user.setAge(20);
        user.setEmail("1243@qq.com");

        int insert = userMapper.insert(user);
        System.out.println(insert);

    }

    @Test
    public void testSelect() {
        QueryWrapper<User>queryWrapper = new QueryWrapper<>();
        queryWrapper
                .isNull("name")
                .ge("age", 12)
                .isNotNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("delete return count = " + result);

    }

}
