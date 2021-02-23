package com.project.gateway;

import org.junit.Test;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

public class ParseJwtTest {
    @Test
    public void parseJwt(){
        //基于公钥解析Jwt令牌
        String jwt = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyLlk4jllr0iOiLkuJbnlYwifQ.mwYUyk9r4ZfIqHAfm7f-MlJoyvb-qXdStp5om2pglHYxE7gXd0_lVdAl0GrZPYWQWk-Apvyi3sdPr0FFYJd4kTP2LYrZXOah6Dimztw-7Npl7fTwC8pFo0SAR7f3RSrnpBtXoxbzukPbwrPgNATocyhEToVai4z3uIf_9YFx3Ug3DDc03osF3mvihfgm3_FIa4CayJEiYfIgg9Bj5q5m3BodrghspQuMr1tz8AD7jpMsWSCPInci6rCRQCibtTjUSrpeJXQYxjw8GBtV7C6UnUFKyFU6QOk0A3zCFWegdr6lRv4sFQTBk13kOQi2zrhKPI31ULZnKGfU_QMnJ2k0dw";
        String publicKey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArSJksUAcqq2tAzuVYVMHIC3PmPUcpVsVnrwMgzO5cIc2cVlac3CYWXT3xO/kALA9Vfa1gJPL0Qzm7R77hOel7wrS8CPEoUYXSG3vekZsD3p0LmkabeM69rDNIcBhs8ckcAjE0Hwor/BwzxOgDPMpmTfE3VbBdMKLeCeqH9669w3USUImUlxv10az9FozepWH4PppMineMHTdoZktrUKyIPabC395aCtj67Jd4muYlYGHc3I5BrfsDAmZaK/VrbI0gRRFDccOADEj0st/viiW3+FBaSD3CeFhojGbSDyZNpK0MupjO7KsvzS1r26b8dmWe6uU6aqW5864DkGBpbo75wIDAQAB-----END PUBLIC KEY-----";
        Jwt token = JwtHelper.decodeAndVerify(jwt, new RsaVerifier(publicKey));
        String claims = token.getClaims();
        System.out.println("claims = " + claims);
        //获得令牌
        String encoded = token.getEncoded();
        System.out.println(encoded);
    }
}


