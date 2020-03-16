package com.gyy.DatabaseTest;

import com.gyy.util.DBUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Set;

public class Demo1 {

    /*@Test
    public void findWordsByName() {
        //1.创建一个Map集合
        HashMap<String, Integer> map = new HashMap<>();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            //1.获取连接
            con = DBUtil.getConnection();
            //2.定义sql语句
            String sql;
            //3.创建执行对像
            PreparedStatement pstmt;
            sql = "select words from tangshi";
            pstm = con.prepareStatement(sql);
            //查询指定的诗人
//            sql = "select words from tangshi where author = ?";
//            pstm = con.prepareStatement(sql);
//            pstm.setString(1, "王维");
            //4.查询
            rs = pstm.executeQuery();
            //5.处理结果集
            while (rs.next()) {
                String word = rs.getString("words");
                String[] words = word.split(" ");
                for (String word1 : words) {
                    map.put(word1, map.getOrDefault(word1, 0) + 1);
                }
            }
            //6.测试一下数据
            Set<String> sets = map.keySet();
            for(String key:sets){
                System.out.println(key+":"+map.get(key));
            }
        }catch(Exception e){
            System.out.println("根据诗人名查询失败!");
        }finally{
            //释放资源
            DBUtil.close(con, pstm, rs);
        }
    }*/
}
