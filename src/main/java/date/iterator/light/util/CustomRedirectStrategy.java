package date.iterator.light.util;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.protocol.HttpContext;

import java.net.URI;
import java.net.URL;

// https://cloud.tencent.com/developer/ask/171284/answer/273163
public class CustomRedirectStrategy extends DefaultRedirectStrategy {

    @Override
    public URI getLocationURI(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
        try {
            String uri = response.getFirstHeader("location").getValue();
            URL url = new URL(uri);
            return new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
        } catch (Exception ex){
            throw new ProtocolException(ex.getMessage());
        }
    }
}
