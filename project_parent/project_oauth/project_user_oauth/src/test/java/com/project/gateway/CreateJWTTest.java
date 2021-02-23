package com.project.gateway;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

public class CreateJWTTest {
    @Test
    public void createJWT() {
        //基于私钥生成jwt令牌
        //1.创建一个密钥工厂
        //指定私钥位置
        ClassPathResource classPathResource = new ClassPathResource("project.jks");
        //指定密钥库的密码
        String keyPass = "project";
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource, keyPass.toCharArray());
        //2.基于工厂获取私钥
        //密钥别名
        String alias = "project";
        //密钥密码
        String password = "project";
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias, password.toCharArray());
        //将当前私钥转为rsa私钥
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        //3.生成jwt
        Map<String, String> map = new HashMap<>();
        map.put("哈喽","世界");
        Jwt jwt = JwtHelper.encode(JSON.toJSONString(map), new RsaSigner(rsaPrivateKey));
        //获得令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }

}
