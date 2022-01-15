package com.demo.javademo.thirdParty;

import com.demo.javademo.util.OkHttpCli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：marco.pan
 * @ClassName：AnswerController
 * @Description：
 * @date: 2022年01月10日 5:05 下午
 */
@RestController
public class AnswerController {
    @Autowired
    private OkHttpCli okHttpCli;

    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String show() {
        String url = " http://op.juhe.cn/onebox/weather/query?cityname=%E4%B8%8A%E6%B5%B7&dtype=&key=3cf2b7d030964697b52610cefe24b954&";
        String message = okHttpCli.doGet(url);
        return message;
    }
}
