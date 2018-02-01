package com.wll.test.java.security;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class KeyStoreHelper {
    private static String keyStoreFile = "C:\\Users\\ezlilwa\\Desktop\\certificate\\tomcat_server.jks";
    private static String privateKeyFile = "C:\\Users\\ezlilwa\\Desktop\\certificate\\testPri.key";
    private static String publicKeyFile = "C:\\Users\\ezlilwa\\Desktop\\certificate\\testPub.key";
    private static String certificateFile = "C:\\Users\\ezlilwa\\Desktop\\certificate\\selfsigned.crt";
    private static String alias = "server";
    private static String storeType = "JKS";
    private static String storePwd = "tomcat";

    public static void main(String[] args) throws Exception {
        PrivateKey privateKey = getPrivateKeyFromStore();
//        createKeyFile(privateKey, privateKeyFile);
        PublicKey publicKey = getPublicKeyFromCrt();
//        createKeyFile(publicKey, publicKeyFile);

        byte[] publicKeyBytes = publicKey.getEncoded();
        byte[] privateKeyBytes = privateKey.getEncoded();
        byte[] certificateBytes = getCertificate().getEncoded();

        String publicKeyBase64 = new BASE64Encoder().encode(publicKeyBytes);
        String privateKeyBase64 = new BASE64Encoder().encode(privateKeyBytes);
        String certificateBase64 = new BASE64Encoder().encode(certificateBytes);

        System.out.println("publicKeyBase64.length():" + publicKeyBase64.length());
        System.out.println(publicKeyBase64);

        System.out.println("privateKeyBase64.length():" + privateKeyBase64.length());
        System.out.println(privateKeyBase64);

        System.out.println("certificateBase64.length():" + privateKeyBase64.length());
        System.out.println(certificateBase64);
    }

    private static PrivateKey getPrivateKeyFromStore() throws Exception {
        char[] pw = storePwd.toCharArray(); // KeyTool中生成KeyStore时设置的storepass
        storeType = null == storeType ? KeyStore.getDefaultType() : storeType;
        KeyStore keyStore = KeyStore.getInstance(storeType);
        InputStream is = new FileInputStream(keyStoreFile);
        keyStore.load(is, pw);
        // 由密钥库获取密钥的两种方式
        // KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, new KeyStore.PasswordProtection(pw));
        // return pkEntry.getPrivateKey();
        return (PrivateKey) keyStore.getKey(alias, pw);
    }

    private static PublicKey getPublicKeyFromCrt() throws CertificateException, FileNotFoundException {
        Certificate crt = getCertificate();
        PublicKey publicKey = crt.getPublicKey();
        return publicKey;
    }

    private static Certificate getCertificate() throws CertificateException, FileNotFoundException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        FileInputStream in = new FileInputStream(certificateFile);
        return cf.generateCertificate(in);
    }

    private static void createKeyFile(Object key, String filePath) throws Exception {
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(key);
        oos.flush();
        oos.close();
    }
}
