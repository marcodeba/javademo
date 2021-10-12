package com.demo.javademo.thirdParty;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Slf4j
@Component
public class RSA2Util {

    private static final String SIGNATURE_ALGORITHM = "RSA";
    private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";

    /**
     * @param KeyAddr
     * @return String
     * @throws
     * @Description: 读取私钥串
     */
    @SuppressWarnings("resource")
    public static String readPrivateKeyStr(String KeyAddr) {
        String str = "";
        if (StringUtils.isEmpty(KeyAddr)) {
            return str;
        }
        try {
            log.info("秘钥地址：[{}]", KeyAddr);
            BufferedReader br = new BufferedReader(new FileReader(KeyAddr));
            String s = br.readLine();
            while (s.charAt(0) != '-') {
                str += s + "\r";
                s = br.readLine();
            }
        } catch (Exception ex) {
            log.error("读取私钥失败Exception ex:[{}]", ex);
        }
        return str;
    }

    /**
     * @param data
     * @return String
     * @throws
     * @Description: RAS2公钥加密
     */
    public static String encrypt(String data, String publicKeyStr) throws Exception {
        if (data == null) {
            return data;
        }
        //获取公钥
        PublicKey publicKey = getPublicKeyFromX509(SIGNATURE_ALGORITHM, new ByteArrayInputStream(publicKeyStr.getBytes()));
        Cipher cipher = Cipher.getInstance(SIGNATURE_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(data.getBytes());
        //加密字节base64加密
        String enData = Base64.encodeBase64String(result);
        log.info("公钥加密:[{}]", enData);
        return enData;

    }

    /**
     * @param data
     * @return String
     * @throws
     * @Description: RAS2私钥解密
     */
    public static String decrypt(String data, String privateKeyStr) throws Exception {
        if (data == null) {
            return data;
        }

        //获取私钥
        PrivateKey privateKey = getPrivateKeyFromPKCS8(SIGNATURE_ALGORITHM, new ByteArrayInputStream(privateKeyStr.getBytes()));
        //base64加密密文解密
        byte[] decode = Base64.decodeBase64(data);
        Cipher cipher = Cipher.getInstance(SIGNATURE_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(decode);
        String deData = new String(result);
        log.info("私钥解密:[{}]", deData);
        return deData;
    }

    /**
     * 使用RSA2私钥加签
     *
     * @param data
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static String sign(String data, String privateKeyStr) throws Exception {
        if (StringUtils.isEmpty(data)) {
            return data;
        }
        //获取私钥
        PrivateKey privateKey = getPrivateKeyFromPKCS8(SIGNATURE_ALGORITHM, new ByteArrayInputStream(privateKeyStr.getBytes()));
        Signature signature = Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        byte[] signedByte = signature.sign();
        String signStr = new String(Base64.encodeBase64(signedByte));
        log.info("私钥加签:[{}]", signStr);
        return signStr;
    }

    /**
     * 使用RSA2公钥验签
     *
     * @param data
     * @param sign
     * @param publicKeyStr
     * @throws Exception
     */
    public static void checkSign(String data, String sign, String publicKeyStr) throws Exception {
        boolean check = false;
        if (StringUtils.isEmpty(data) || StringUtils.isEmpty(sign)) {
            throw new Exception("验签失败");
        }
        byte[] enSign = Base64.decodeBase64(sign.getBytes());
        //获取公钥
        PublicKey publicKey = getPublicKeyFromX509(SIGNATURE_ALGORITHM, new ByteArrayInputStream(publicKeyStr.getBytes()));
        Signature signature = Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);
        signature.initVerify(publicKey);
        signature.update(data.getBytes());
        check = signature.verify(enSign);
        log.info("公钥验签:[{}]", check);
        if (!check) {
            throw new Exception("验签失败");
        }

    }


    /**
     * @param algorithm
     * @param ins
     * @return PrivateKey
     * @throws Exception
     * @throws
     * @Description: 获取RSA2私钥
     */
    public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {
        if (ins == null || StringUtils.isEmpty(algorithm)) {
            return null;
        }

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        byte[] encodedKey = IOUtils.toByteArray(ins);

        encodedKey = Base64.decodeBase64(encodedKey);
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }

    /**
     * 获取RSA2公钥
     *
     * @param algorithm
     * @param ins
     * @return
     * @throws Exception
     */
    private static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {
        if (ins == null || StringUtils.isEmpty(algorithm)) {
            return null;
        }
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        byte[] encodedKey = IOUtils.toByteArray(ins);
        // 先base64解码
        encodedKey = Base64.decodeBase64(encodedKey);
        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

    }
	
	/*public static void main(String[] args) {
		String privateKey = readPrivateKeyStr("D:\\app_private_key_pkcs8.pem");
		String endate = "142424";
		try {
			String deDate = decrypt(endate, privateKey);
			log.info(deDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}*/

    public static void main(String[] args) {
        System.out.println("处理完成");
        try {
            String msg = "RSA2测试通讯";
            String serverPrivate = RSA2Util.readPrivateKeyStr("E:\\workspace5\\cert\\mk_server\\server_app_private_key_pkcs8.pem");
            String serverPublic = RSA2Util.readPrivateKeyStr("E:\\workspace5\\cert\\mk_server\\server_app_public_key.pem");
            // 加签
            String sign = RSA2Util.sign(msg, serverPrivate);
            RSA2Util.checkSign(msg, sign, serverPublic);

            String clientPrivate = RSA2Util.readPrivateKeyStr("E:\\workspace5\\cert\\mk_client\\client_app_private_key_pkcs8.pem");
            String clientPublic = RSA2Util.readPrivateKeyStr("E:\\workspace5\\cert\\mk_client\\client_app_public_key.pem");
            String encMsg = RSA2Util.encrypt(msg, clientPublic);
            String decMsg = RSA2Util.decrypt(encMsg, clientPrivate);
            System.out.println("处理完成\r\n" + decMsg + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
