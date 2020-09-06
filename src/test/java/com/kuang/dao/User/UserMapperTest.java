package com.kuang.dao.User;

import com.kuang.Utils.MybatisUtils;
import com.kuang.dao.UserMapper;
import com.kuang.pojo.User;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import sun.text.resources.el.FormatData_el;

import java.text.Format;
import java.util.*;

public class UserMapperTest {

    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    Logger logger = Logger.getLogger(UserMapperTest.class);


    @Test//查询全部用户
    public void getall() {
        logger.info("进入查询");
        List<User> userList = mapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
//        System.out.println("--------------");
//        List<User> userList1 = sqlSession.selectList("getUserList");
//        for (User user : userList1) {
//            System.out.println(user);
//        }
        sqlSession.close();
        System.out.println("你好,mybatis");
    }

    @Test//limit实现分页
    public void getUserByLimit() {
        logger.info("limit实现分页");
        HashMap<String, Integer> map = new HashMap<>();
        map.put("startIndex", 2);
        map.put("pageSize", 4);
        List<User> userList = mapper.getUserByLimit(map);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test//根据id查询用户
    public void getUserById() {
        User user = mapper.getUserById(1);
        System.out.println(user);
//        User user1 = sqlSession.selectOne("getUserById", 2);
//        System.out.println(user1);
        sqlSession.close();
    }

    @Test//根据name模糊查询
    public void getUserByName() {
        List<User> userList = mapper.getUserByName("张");
        System.out.println(userList);
        sqlSession.close();
    }

    @Test//添加用户
    public void insertUser() {
        int i = mapper.insertUser(new User(10, "张智", 20));
        if (i != 0) {
            System.out.println("插入成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Test//根据map添加用户
    public void insertUser2() {
        Map<String, Object> map = new HashMap();
        map.put("userId", 9);
        map.put("userName", "张洋");
        map.put("userAge", 23);
        System.out.println((mapper.insertUser2(map)) != 0 ? "插入成功" : "插入失败");
        sqlSession.commit();
        sqlSession.close();
    }

    @Test//更新用户
    public void updataUser() {
        int i = mapper.updataUser(new User(1, "张智洋", 20));
        if (i > 0) {
            System.out.println("更新成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Test//删除用户
    public void deleteUser() {
        int i = mapper.deleteUser(9);
        if (i > 0) {
            System.out.println("删除成功");
        }
    }

    @Test
    public void aa() {
        HashMap<Integer, String> Sites = new HashMap<Integer, String>();
        // 添加键值对
        Sites.put(1, "Google");
        Sites.put(2, "Runoob");
        Sites.put(3, "Taobao");
        Sites.put(4, "Zhihu");
        // 输出 key 和 value
        for (Integer i : Sites.keySet()) {
            System.out.println("key: " + i + " value: " + Sites.get(i));
        }
    }

    @Test
    public void get() {

        //2)2028年男子世界杯足球队分组, 16强队列有:
        // 中国, 朝鲜, 韩国, 日本, 泰国, 印度, 马来西亚,
        // 尼泊尔, 缅甸,巴西, 法国, 德国, 西班牙, 俄罗斯, 英国, 荷兰.
        //把这16支队伍随机的分为四个小组, 打印每个小组中的队伍(使用集合)
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list.add("中国");
        list.add("朝鲜");
        list.add("韩国");
        list.add("泰国");
        list.add("日本");
        list.add("印度");
        list.add("马来西亚");
        list.add("尼泊尔");
        list.add("缅甸");
        list.add("巴西");
        list.add("法国");
        list.add("德国");
        list.add("西班牙");
        list.add("英国");
        list.add("俄罗斯");
        list.add("荷兰");
        Collections.shuffle(list);
//      new LinkedList<>().pop();
        System.out.println("第一支队伍:" + list.subList(0, 4));
        System.out.println("第二支队伍:" + list.subList(4, 8));
        System.out.println("第三支队伍:" + list.subList(8, 12));
        System.out.println("第四支队伍:" + list.subList(12, 16));
    }
}

