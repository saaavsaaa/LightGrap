package date.iterator.light.grab;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by aaa on 2020/4/18.
 */
public class GrapPage {
    public static void main(String[] args) throws IOException {
        GrapPage grap = new GrapPage();
        String page = grap.request("http://www.cwl.gov.cn/");
        System.out.println(page);
    }

    public String request(final String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpGet request = new HttpGet(url);
        request.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
//        HttpHost proxy = new HttpHost("proxy ip", port);
//        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
//        request.setConfig(config);

        try {
            response = httpClient.execute(request);
            HttpEntity httpEntity = response.getEntity();
            String html = EntityUtils.toString(httpEntity, "utf-8");
            return html;
        } catch (IOException e) {
            throw e;
        } finally {
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }
    }
}
