package com.http.client;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author liangfeng
 * @version 1.0
 * @date 2021/11/14 19:57
 */
public class HttpClientUtils {

    public static CloseableHttpClient httpClient = HttpClients.createDefault();

    /**
     * GET方法
     * @param url
     * @return
     * @throws IOException
     */
    public static String getAsString(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } finally {
            response.close();
        }
    }

    public static void main(String[] agrs) throws Exception {
        String url = "https://golovin.kuaizhan.com";
        String text = HttpClientUtils.getAsString(url);
        System.out.println("url:" + url + " ; response: \n" + text);
    }

}
