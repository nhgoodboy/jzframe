package com.musikouyi.jzframe;

import com.musikouyi.jzframe.utils.ToolsUtil;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {

    @Test
    public void testSha256() {
        String password = ToolsUtil.encrypt("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92", "s56tf");
        String sha256_pwd = new Sha256Hash("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92", "8pgby", 1).toString();
        String simple_pwd = new SimpleHash("SHA-256", "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92", "8pgby", 1).toString();
        System.out.println(password);
        System.out.println(sha256_pwd);
        System.out.println(simple_pwd);
        System.out.println(password.equals(sha256_pwd));

        new Thread(() -> System.out.println("haha")).start();
    }
}
