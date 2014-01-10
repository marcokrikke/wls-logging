package nl.eveoh.wls.loggingtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    private Logger log = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public String index() {
        log.info("This is a INFO message.");
        log.warn("This is a WARN message.");
        log.debug("This is a DEBUG message.");
        log.error("This is a ERROR message.");
        log.trace("This is a TRACE message.");

        return "index";
    }
}
