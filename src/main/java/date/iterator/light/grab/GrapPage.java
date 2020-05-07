package date.iterator.light.grab;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.nio.charset.Charset;

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

    //https://www.jb51.net/article/100386.htm
    public void download(final String url/*, final String remoteFileName*/, final String localFile) {
        OutputStream out = null;
        InputStream in = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
//            httpGet.addHeader("userName", userName);
//            httpGet.addHeader("passwd", passwd);
//            httpGet.addHeader("fileName", remoteFileName);

            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            in = entity.getContent();

            long length = entity.getContentLength();
            if (length <= 0) {
                System.out.println("下载文件不存在！");
                return;
            }

            System.out.println("The response value of token:" + httpResponse.getFirstHeader("token"));

            File file = new File(localFile);
            if(!file.exists()){
                file.createNewFile();
            }
            out = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int readLength = 0;
            while ((readLength=in.read(buffer)) > 0) {
                byte[] bytes = new byte[readLength];
                System.arraycopy(buffer, 0, bytes, 0, readLength);
                out.write(bytes);
            }
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void upload(final String url, final String localFile){
        StringBody userName = new StringBody("aaa", ContentType.create("text/plain", Consts.UTF_8));
        StringBody password = new StringBody("123456", ContentType.create("text/plain", Consts.UTF_8));

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.createDefault();
            // 把一个普通参数和文件上传给下面这个地址 是一个servlet
            HttpPost httpPost = new HttpPost(url);
            // 把文件转换成流对象FileBody
            FileBody bin = new FileBody(new File(localFile));
            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    // 相当于<input type="file" name="file"/>
                    .addPart("file", bin)
                    // 相当于<input type="text" name="userName" value=userName>
                    .addPart("userName", userName)
                    .addPart("pass", password)
                    .build();

            httpPost.setEntity(reqEntity);
            // 发起请求 并返回请求的响应
            response = httpClient.execute(httpPost);
            System.out.println("The response value of token:" + response.getFirstHeader("token"));
            // 获取响应对象
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                // 打印响应长度
                System.out.println("Response content length: " + resEntity.getContentLength());
                // 打印响应内容
                System.out.println(EntityUtils.toString(resEntity, Charset.forName("UTF-8")));
            }
            // 销毁
            EntityUtils.consume(resEntity);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(response != null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
