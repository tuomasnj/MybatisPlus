package com.test.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.test.mybatisplus.entity.User;
import com.test.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
class MybatisPlusApplicationTests {
    @Autowired
    private UserMapper userMapper;

    //查询User表中的所有数据
    @Test
    public void findAllRecords() {
        List<User> users = userMapper.selectList(new QueryWrapper<>());
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    //添加数据
    public void addRecord() {
        User user = new User();
        user.setName("Arsor");
        user.setAge(36);
        user.setEmail("hydbk@fdld.com");

        int val = userMapper.insert(user);
        System.out.println(val);
    }

    @Test
    //修改行数据
    public void updateUser() {
        User user = new User();
        user.setId(1L);
        user.setAge(54);
        int ans = userMapper.updateById(user);
        System.out.println(ans);
    }

    @Test
    //测试乐观锁
    public void testOptimisticLocker() {
        User user = userMapper.selectById(5);
        user.setName("Kittyyyyy");
        user.setVersion(user.getVersion() - 1);
        int update = userMapper.updateById(user);
        System.out.println(update);
    }

    @Test
        //id批量查询
    void selectbatchIDs() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 3, 4));
        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test
        //条件查询map
    void selectByMap() {
        Map map = new HashMap<String, Object>();
        map.put("name", "Kittyyyyy");
        map.put("age", 24);
        List<User> result = userMapper.selectByMap(map);
        System.out.println(result);
    }

    @Test
    //分页查询
    public void selectByPage() {
        Page<User> userPage = new Page<>(1, 2);
        userMapper.selectPage(userPage, null);

        System.out.println(userPage.getCurrent());//当前页数
        System.out.println(userPage.getPages());//总页数
        System.out.println(userPage.getRecords());//当前页所有记录
        System.out.println(userPage.getSize());//当前页记录数
        System.out.println(userPage.getTotal());//总记录数
        System.out.println(userPage.hasNext());//是否有后一页
        System.out.println(userPage.hasPrevious());//是否有前一页
    }

    @Test
    //物理删除
    void deleteById() {
        int ans = userMapper.deleteById(1602521221507366913L);
        System.out.println(ans);
    }

    @Test
    //批量删除
    void deleteBatchIDS() {
        userMapper.deleteBatchIds(Arrays.asList(1L, 2L, 3L));
    }

    @Test
    //逻辑删除
    void logicDelete(){
        int res = userMapper.deleteById(1L);
        System.out.println(res);

        System.out.println(userMapper.selectList(null));
    }
}
