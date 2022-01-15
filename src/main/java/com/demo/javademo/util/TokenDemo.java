package com.demo.javademo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TokenDemo {
    //对称加密的密钥
    private static final String SECRET_KEY = "436319c6-d23f-4c18-a4f7-f1dc306a6471";

    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    private static final String ISSUER = "marco";

    private static AESUtil aesUtil = new AESUtil();

    //生成token，用来保存会话状态
    public static String generateToken(String content) {
        if (StringUtils.isEmpty(content)) {
            throw new IllegalArgumentException("content can't be null!");
        }
        // jti: jwt的唯一标记
        // iss：JWT的签发主体
        // exp: 时间戳，过期时间
        // iat: jwt的签发时间
        // sub: jwt的所有者
        //其他自定义属性
        return JWT.create().withIssuer(ISSUER)
                .withExpiresAt(DateTime.now().plusMinutes(60).toDate())
                .withClaim("name", aesUtil.encrypt(content)).sign(ALGORITHM);
    }

    //拿到token后进行验证和解析
    public static String verifyAndParseToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        JWTVerifier verifier = JWT.require(ALGORITHM).withIssuer(ISSUER).build();
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            log.info("[TokenDemo.verifyAndParseToken], ISSUER:{}, ALGORITHM:{}, PLAYLOAD:{}",
                    decodedJWT.getIssuer(), decodedJWT.getAlgorithm(), decodedJWT.getClaim("name").asString());
            return aesUtil.decrypt(decodedJWT.getClaim("name").asString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String token = generateToken("MarcoPan");
        //TimeUnit.SECONDS.sleep(2);
        String content = verifyAndParseToken(token);
        System.out.println(content);
    }
}
