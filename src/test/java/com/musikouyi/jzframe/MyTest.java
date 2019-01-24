package com.musikouyi.jzframe;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {

    @Test
    public void testSha256() throws IOException {
//        String password = ToolsUtil.encrypt("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92", "s56tf");
//        String sha256_pwd = new Sha256Hash("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92", "8pgby", 1).toString();
//        String simple_pwd = new SimpleHash("SHA-256", "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92", "8pgby", 1).toString();
//        System.out.println(password);
//        System.out.println(sha256_pwd);
//        System.out.println(simple_pwd);
//        System.out.println(password.equals(sha256_pwd));
//        new Thread(() -> System.out.println("haha")).start();

        String a = Class.class.getResource("/").getPath();
        System.out.println(a);

        //获取classpath路径，就是根目录  /D:/Program/idea/DayIndicators/target/classes/
        System.out.println(MyTest.class.getResource("/").getPath());
        //得到的是IndexAction 类所在的包路径
        System.out.println(MyTest.class.getResource("").getPath());
        //会报错，已经在根目录
        System.out.println(MyTest.class.getResource("/").getPath());
        //获取classpath路径，就是根目录  /D:/Program/idea/DayIndicators/target/classes/
        System.out.println(MyTest.class.getClassLoader().getResource("").getPath());

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        output.write("This text is converted to bytes".getBytes("UTF-8"));

        byte[] bytes = output.toByteArray();
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i] + ",");
        }
    }
}
