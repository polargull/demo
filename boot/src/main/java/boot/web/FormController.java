package boot.web;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.StringWriter;

/**
 * Created by fuyuanpu on 2017/1/15.
 */
@Controller
public class FormController {
    @Autowired
    VelocityEngine ve;

    @RequestMapping("index")
    public String index() {
        Template t = ve.getTemplate("/view/form.vm");
        VelocityContext ctx = new VelocityContext();
        StringWriter sw = new StringWriter();
        t.merge(ctx, sw);
        System.out.println(sw.toString());
        return "view/form";
    }
}
