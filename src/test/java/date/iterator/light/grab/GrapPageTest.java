package date.iterator.light.grab;

import date.iterator.state.AC;
import date.iterator.state.CharNode;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aaa on 2020/4/18.
 */
public class GrapPageTest {

    @Test
    public void testGrapPage() throws IOException {
        GrapPage grap = new GrapPage();
        String page = grap.request("http://www.cwl.gov.cn/");
        Assert.assertNotNull(page);
    }

    @Test
    public void test() {
        Map<String, String> expected = new HashMap<>();
        expected.put("ade", "6,15");
        expected.put("abcde", "0");
        expected.put("abc", "0,11");
        expected.put("dee", "3,7,16");
        expected.put("eade", "5,14");

        String[] ss = new String[] {"abcde", "ade", "abc", "dee", "eade"};
        AC ac = new AC();
        ac.init(ss);
        ac.setEnableAutoLoop(true);
        String input = "abcdeeadeeeabceadee";
        Map<String, String> actual = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            Collection<CharNode> searched = ac.search(input.charAt(i));
            for (CharNode each : searched) {
                for (Integer originLength : each.getOriginLengths()) {
                    int beginIndex = i + 1 - originLength;
                    String achieved = input.substring(beginIndex, i + 1);
                    if (actual.containsKey(achieved)) {
                        String value = actual.get(achieved);
                        value += "," + beginIndex;
                        actual.put(achieved, value);
                    } else {
                        actual.put(achieved, String.valueOf(beginIndex));
                    }
                }
            }
        }
        for (String key : actual.keySet()) {
            String expectedValue = expected.get(key);
            String actualValue = actual.get(key);
            Assert.assertEquals(expectedValue, actualValue);
        }
    }
}
