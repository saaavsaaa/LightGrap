package date.iterator.light.grab;

import java.io.IOException;

public class DoubleBallTicket {
    private String pageContent = "";

    public void init(final String url) throws IOException {
        GrapPage grap = new GrapPage();
        pageContent = grap.request("http://www.cwl.gov.cn/");
    }
    // //title[@lang='eng']
    public void abstractXPath(final String path) throws IOException {

    }
}
