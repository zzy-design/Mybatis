package com.kuang.dao.User;

import com.kuang.Utils.MybatisUtils;
import com.kuang.dao.UserMapper;
import com.kuang.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
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
        logger.info("打印" + list);
        System.out.println("分好的队伍:");
        System.out.println("第一支队伍:" + list.subList(0, 4));
        System.out.println("第二支队伍:" + list.subList(4, 8));
        System.out.println("第三支队伍:" + list.subList(8, 12));
        System.out.println("第四支队伍:" + list.subList(12, 16));
    }

    @Test
    public void test() {
        int[][] arr = new int[2][3];
        System.out.println(arr.length);
        System.out.println(arr[0].length);
        System.out.println(arr[1][1]);
    }

    @Test
    public void Find() {
        int target = 5;
        int falg = 0;
        int[][] array = new int[][]{{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
//        System.out.println(array[1][1]);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (target == array[i][j]) {
                    falg++;

                }
            }
        }
        System.out.println(falg == 1 ? "存在" : "不存在");
    }

    @Test
    public void replaceSpace() {
        StringBuffer str = new StringBuffer("we are words");
        if (str.toString().isEmpty()) {
            System.out.println("null");
        }
        StringBuffer sbr = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                sbr.append("%20");
            } else {
                sbr.append(str.charAt(i));
            }
        }
        System.out.println(sbr);
    }

    @Test
    public void redisTest() {
        Jedis jedis = new Jedis("localhost");
        // 如果 Redis 服务设置来密码，需要下面这行，没有就不需要
        // jedis.auth("123456");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: " + jedis.get("runoobkey"));
        System.out.println("------------");
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        String a = "zzya";
        String b = "zzy";
        char c = a.charAt(0);
        logger.info(a.contains(b));
        //获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0, 2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("列表项" + (i + 1) + "为: " + list.get(i));
        }
    }

    @Test
    public void run() {
        System.out.println(2 + "1");
        String num = 2 + "%";
        System.out.println(num);
        System.out.println("0".equals(false));
        new BigDecimal("2.1").toBigInteger();
    }
}
