package com.wll.test.java.security;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JceOpenSSLPKCS8DecryptorProviderBuilder;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.pkcs.PKCSException;
import sun.misc.BASE64Decoder;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

public class TestHttpsClient {
    private String keyFile = "C:\\Users\\ezlilwa\\Desktop\\certificate\\tomcat_trust.jks";
    private String keyPassword = "tomcat";
    private String certicateFile = "C:\\Users\\ezlilwa\\Desktop\\certificate\\test.pem";
    private String certificatePassword = "tomcat";
    private static final String JKS_KEYSTORE = "JKS";
    private static final String PKCS12_KEYSTORE = "PKCS12";
    private static final String url = "https://localhost:8443/enrollment.html";

    public void testHttps() throws CertificateException, NoSuchAlgorithmException, IOException, KeyStoreException, KeyManagementException, InvalidKeySpecException {
        SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
        KeyStore keyStore = getKeyStore(keyFile, keyPassword);
        loadKeyMaterial(sslContextBuilder, keyStore, keyPassword);
        KeyStore trustStore = getTrustStore(certicateFile, certificatePassword);
        loadTrustMaterial(sslContextBuilder, null, trustStore);
        SSLContext sslContext = buildSSLContext(sslContextBuilder);
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslSocketFactory)
                .build();
        sendGet(httpclient, url);
    }

    private void loadTrustMaterial(SSLContextBuilder sslContextBuilder, TrustStrategy trustStrategy, KeyStore trustStore) {
        try {
            if (trustStore != null) {
                sslContextBuilder.loadTrustMaterial(trustStore, trustStrategy);
            } else {
                sslContextBuilder.loadTrustMaterial(trustStrategy);
            }
        } catch (NoSuchAlgorithmException | KeyStoreException e) {
            throw new RuntimeException("Failed to load trust material: " + e);
        }
    }

    private void loadKeyMaterial(SSLContextBuilder sslContextBuilder, KeyStore keyStore, String keyStorePassword) {
        try {
            sslContextBuilder.loadKeyMaterial(keyStore, keyStorePassword == null ? "".toCharArray()
                    : keyStorePassword.toCharArray());
        } catch (UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException e) {
            throw new RuntimeException("Failed to load key material: " + e);
        }
    }

    private SSLContext buildSSLContext(SSLContextBuilder sslContextBuilder) {
        SSLContext sslContext;
        try {
            sslContext = sslContextBuilder.useProtocol("TLSv1.2").build();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to build SSLContext: " + e);
        }
        return sslContext;
    }

    private class TrustAllStrategy implements TrustStrategy {
        @Override
        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            return true;
        }
    }

    private void sendGet(CloseableHttpClient httpclient, String uri) throws IOException {
        try {
            HttpGet httpget = new HttpGet(uri);
            System.out.println("executing request" + httpget.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    System.out.println("Response content length: " + entity.getContentLength());
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

    public void testHttp() throws IOException {
        String userName = "";
        String password = "";
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
        sendGet(httpClient, "http://www.baidu.com");
    }

    public static void main(String[] args) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, InvalidKeySpecException {
        System.setProperty("javax.net.debug", "ssl,handshake");
        TestHttpsClient client = new TestHttpsClient();
        client.testHttps();
    }

    private PrivateKey parsePrivateKey(String filePath) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        //获取KeyFactory，指定RSA算法
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        //将BASE64编码的私钥字符串进行解码
        BASE64Decoder decoder = new BASE64Decoder();
        FileInputStream instream = new FileInputStream(filePath);
        byte[] encodeByte = decoder.decodeBuffer(instream);
        //将BASE64解码后的字节数组，构造成PKCS8EncodedKeySpec对象，生成私钥对象
        PrivateKey privatekey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodeByte));
        //将BASE64解码后的字节数组，构造成X509EncodedKeySpec对象，生成公钥对象
        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodeByte));
        return privatekey;
    }

    /*
         * Get the key or trust store with the specified type, path, and password.
         */
    private KeyStore getKeyStore(String path, String password) {
        return getStore(path, password, true);
    }

    private KeyStore getTrustStore(String path, String password) {
        return getStore(path, password, false);
    }

    private KeyStore getStore(String path, String password, boolean isKeyStore) {
        String fileType = getStoreType(path);
        try {
            if ("PKCS12".equalsIgnoreCase(fileType) || "JKS".equalsIgnoreCase(fileType)) {
                return getKeyStoreFromPKCS12OrJKS(fileType, path, password);
            } else {
                if (isKeyStore) {
                    return getKeyStoreFromPem(path, password);
                } else {
                    return getTrustStoreFromPem(path);
                }
            }
        } catch (NoSuchAlgorithmException | CertificateException | IOException | KeyStoreException | OperatorCreationException | PKCSException e) {
            throw new RuntimeException(
                    "Failed to init key store: type: " + fileType + " path: " + path + " password: " + password, e);
        }
    }

    /*
     * Parse the file extension to get the key store type
     */
    private String getStoreType(String path) {
        String fileExtension = getFileExtension(path);
        if ("pkcs12".equalsIgnoreCase(fileExtension) || "pfx".equalsIgnoreCase(fileExtension)
                || "p12".equalsIgnoreCase(fileExtension)) {
            return "PKCS12";
        } else if ("jks".equalsIgnoreCase(fileExtension)) {
            return "JKS";
        }
        return fileExtension;
    }

    private KeyStore getKeyStoreFromPKCS12OrJKS(String fileType, String path, String password) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
        KeyStore keyStore = KeyStore.getInstance(fileType);
        char[] storePass = null;
        if (password != null && !"".equals(password)) {
            storePass = password.toCharArray();
        }
        keyStore.load(new FileInputStream(path), storePass);
        return keyStore;
    }

    private KeyStore getTrustStoreFromPem(String path) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        //PKCS12 does not support storing TrustedCertEntry
        KeyStore trustStore = KeyStore.getInstance(JKS_KEYSTORE);
        trustStore.load(null);
        X509Certificate caCert = loadCertificateFromPem(path);
        trustStore.setCertificateEntry("ca-cert", caCert);
        return trustStore;
    }

    private KeyStore getKeyStoreFromPem(String path, String password) throws IOException, CertificateException,
            KeyStoreException, NoSuchAlgorithmException, OperatorCreationException, PKCSException {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        PEMParser parser = new PEMParser(new FileReader(path));
        try {
            Object object;
            List<X509Certificate> chain = new ArrayList<>(1);
            PrivateKey privateKey = null;
            KeyPair keyPair = null;
            while ((object = parser.readObject()) != null) {
                if (object instanceof X509CertificateHolder) {
                    X509CertificateHolder certHolder = (X509CertificateHolder) object;
                    X509Certificate cert = getPemCertificateConverter().getCertificate(certHolder);
                    chain.add(cert);
                }
                else if (object instanceof PrivateKeyInfo) {
                    PrivateKeyInfo privateKeyInfo = (PrivateKeyInfo) object;
                    privateKey = getPemKeyConverter().getPrivateKey(privateKeyInfo);
                }
                else if (object instanceof PKCS8EncryptedPrivateKeyInfo) {
                    PKCS8EncryptedPrivateKeyInfo privateKeyInfo = (PKCS8EncryptedPrivateKeyInfo) object;
                    InputDecryptorProvider decryptorProvider = new JceOpenSSLPKCS8DecryptorProviderBuilder().build(password.toCharArray());
                    privateKey = getPemKeyConverter().getPrivateKey(privateKeyInfo.decryptPrivateKeyInfo(decryptorProvider));
                }
                else if (object instanceof PEMEncryptedKeyPair) {
                    PEMEncryptedKeyPair pemKeyPair = (PEMEncryptedKeyPair) object;
                    PEMDecryptorProvider decryptorProvider = new JcePEMDecryptorProviderBuilder().build(password.toCharArray());
                    keyPair = getPemKeyConverter().getKeyPair(pemKeyPair.decryptKeyPair(decryptorProvider));
                }
                else if (object instanceof PEMKeyPair) {
                    PEMKeyPair pemKeyPair = (PEMKeyPair) object;
                    keyPair = getPemKeyConverter().getKeyPair(pemKeyPair);
                }
            }
            KeyStore keyStore = KeyStore.getInstance(PKCS12_KEYSTORE);
            keyStore.load(null);
            X509Certificate[] certificates = chain.toArray(new X509Certificate[chain.size()]);
            if (keyPair != null) {
                keyStore.setKeyEntry("cert", keyPair.getPrivate(), password.toCharArray(), certificates);
            } else if (privateKey != null) {
                keyStore.setKeyEntry("cert", privateKey, password.toCharArray(), certificates);
            }
            return keyStore;
        } finally {
            parser.close();
        }
    }

    private X509Certificate loadCertificateFromPem(String path) throws IOException, CertificateException {
        PEMParser parser = new PEMParser(new FileReader(path));
        X509CertificateHolder obj = (X509CertificateHolder) parser.readObject();
        parser.close();
        return getPemCertificateConverter().getCertificate(obj);
    }

    private JcaX509CertificateConverter getPemCertificateConverter() {
        return new JcaX509CertificateConverter().setProvider("BC");
    }

    private JcaPEMKeyConverter getPemKeyConverter() {
        return new JcaPEMKeyConverter().setProvider("BC");
    }

    private String getFileExtension(String path) {
        String name = new File(path).getName();
        try {
            return name.substring(name.lastIndexOf('.') + 1);
        } catch (Exception e) {
            return "";
        }
    }
}
