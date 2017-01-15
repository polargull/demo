package boot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fuyuanpu on 2017/1/15.
 */
@Controller
public class FormController {
    @RequestMapping("index")
    public String index() {
        return "view/form";
    }
}
