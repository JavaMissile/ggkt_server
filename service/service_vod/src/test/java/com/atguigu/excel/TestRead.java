package com.atguigu.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Missile
 * @Date 2022-07-01-23:24
 */
public class TestRead {
    public static void main(String[] args) {
        String filename="D:\\testEasyExcel.xlsx";
        EasyExcel.read(filename,User.class,new ExcelListener())
                .sheet().doRead();

    }

    //循环设置要添加的数据，最终封装到list集合中
    private static List<User> data() {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User data = new User();
            data.setId(i);
            data.setName("张三"+i);
            list.add(data);
        }
        return list;
    }
}
