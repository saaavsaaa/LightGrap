package date.iterator.light.grab;

import date.iterator.state.AC;

import java.io.IOException;

public class DoubleBallTicket {
    private String pageContent = "";
    private AC ac;

    public static void main(String[] args) throws IOException {
        DoubleBallTicket grap = new DoubleBallTicket();
        grap.init("http://www.cwl.gov.cn/");
    }

    public void init(final String url) throws IOException {
        GrapPage grap = new GrapPage();
        pageContent = grap.request(url);

//        String js = url + "js/lottery.js";
//        grap.download(js, "D:\\Github\\LightGrap\\lottery.js");
//
//        js = url + "js/tool.js";
//        grap.download(js, "D:\\Github\\LightGrap\\tool.js");
//
//        js = url + "_ws_sbu/sbu_hc.js";
//        grap.download(js, "D:\\Github\\LightGrap\\sbu_hc.js");

//        ac = new AC();
//        ac.setEnableAutoLoop(true);
//        ac.setAutoLoop(' ');
    }

    private void getResource(final String url) {

    }

    ///html/body/div[1]/div[5]/div/div[1]/div[1]
    //   <body><script>(function () { var czy = 1 * 57; document.cookie = "HMY_JC=82ade304aae00eb7fa32dbfd3a060b304ebce7783ad75a164f5c63483efb1e54e5,"+czy+"; expires=Tue, 28-Apr-20 01:51:28 GMT; path=/"; })()</script><script src="/_ws_sbu/sbu_hc.js"></script>
    // <div data-role="page" data-url="/" tabindex="0" class="ui-page ui-page-theme-a ui-page-active" style=""><script>(function () { var czy = 19 * 36; document.cookie = "HMY_JC=4ff65feb9aadfef75f23448cfa043b3748fb8803f634baa049a929eacde793d415,"+czy+"; expires=Thu, 07-May-20 02:40:57 GMT; path=/"; })()</script><script src="/_ws_sbu/sbu_hc.js"></script>
    // <div class="gongGao"><div class="containerHome"><div class="kjGg  wL"><div class="kjgg0  kjgg01"><div class="kjggL">:
    //    1.<div class="kjxx"><em>双色球 第<span class="qHao">-</span>期</em><em>奖池滚存：<span class="jChi">￥<span></span>元</span></em></div>
    //    2.<ul class="kjh"> ... <li>-</li> <li class="lQiu">-</li></ul>

    // //title[@lang='eng']
    public void abstractXPath(final String path) throws IOException {

    }
}
