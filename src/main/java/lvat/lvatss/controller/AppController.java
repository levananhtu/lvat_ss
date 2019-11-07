package lvat.lvatss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home() {
        return "";
    }
}
