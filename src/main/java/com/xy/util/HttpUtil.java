package com.xy.util;

import org.apache.http.Consts;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by XiuYang on 2016/9/14.
 */
public class HttpUtil {
    private static CloseableHttpClient httpClient = null;

    public static CloseableHttpClient getHttpClient(){
        if(httpClient==null){
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(200);
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(cm).build();
        }
        return httpClient;
    }
    /**
     * Get请求返回String
     * @param url
     * @param params 不需要做URLEncoder
     * @param headers
     * @return
     */
    public static String doGet(String url,Map<String,String> params,Map<String,String> headers) throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(url);
        if(params!=null){
            for(String key : params.keySet()){
                uriBuilder.addParameter(key,params.get(key));
            }
        }
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        if(headers!=null){
            for(String key : headers.keySet()){
                httpGet.addHeader(key,headers.get(key));
            }
        }
        CloseableHttpResponse response = getHttpClient().execute(httpGet);
        try {
            if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                return EntityUtils.toString(response.getEntity(),Consts.UTF_8);
            }
        } finally {
            response.close();
        }
        return "";
    }

    /**
     * Post请求返回String
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static String doPost(String url,Map<String,String> params,Map<String,String> headers) throws IOException {
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        if(params!=null){
            for(String key : params.keySet()){
                formParams.add(new BasicNameValuePair(key,params.get(key)));
            }
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
        HttpPost httpPost = new HttpPost(url);
        if(headers!=null){
            for(String key : headers.keySet()){
                httpPost.addHeader(key,headers.get(key));
            }
        }
        httpPost.setEntity(entity);
        CloseableHttpResponse response = getHttpClient().execute(httpPost);
        try {
            if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                return EntityUtils.toString(response.getEntity(),Consts.UTF_8);
            }
        } finally {
            response.close();
        }
        return "";
    }

    /**
     * POST
     * "Content-Type" 固定 "application/json"
     * @param url
     * @param jsonData json数据
     * @param headers
     * @return
     * @throws IOException
     */
    public static String postJson(String url,String jsonData,Map<String,String> headers) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        if(headers!=null){
            for(String key : headers.keySet()){
                if(!HttpHeaders.CONTENT_TYPE.equals(key)){
                    httpPost.addHeader(key,headers.get(key));
                }
            }
        }
        httpPost.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        httpPost.setEntity(new StringEntity(jsonData,Consts.UTF_8));
        CloseableHttpResponse response = getHttpClient().execute(httpPost);
        try {
            if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                return EntityUtils.toString(response.getEntity(),Consts.UTF_8);
            }
        } finally {
            response.close();
        }
        return "";
    }

    /**
     * 创建SSL安全连接
     *
     * @return
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @Override
                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                @Override
                public void verify(String host, X509Certificate cert) throws SSLException {
                }

                @Override
                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }

    public static void main(String[] args) throws IOException {
        /*Map<String,String> headers = new HashMap<String, String>();
        headers.put("app","1");
        System.out.println(postJson("http://115.182.220.22:8082/v3/user/login","{\"userType\":\"1\",\"name\":\"18610022931\",\"passwd\":\"53DD9C6005F3CDFC5A69C5C07388016D\"}",headers));
 */
        try {
            System.out.println(doGet("https://www.baidu.com",null,null));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
