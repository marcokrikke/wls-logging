package nl.eveoh.wls.loggingtest;

import ch.qos.logback.classic.Level;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@Controller
public class IndexController {
    private Logger log = LoggerFactory.getLogger(IndexController.class);

    @Value("${logLocation}/wls-logging.log")
    private String logLocation;

    @RequestMapping("/")
    public String index() {
        log.info("This is a INFO message.");
        log.warn("This is a WARN message.");
        log.debug("This is a DEBUG message.");
        log.error("This is a ERROR message.");
        log.trace("This is a TRACE message.");

        return "index";
    }

    @RequestMapping(value = "/log", produces="text/plain")
    @ResponseBody
    public String viewLog() throws IOException {
        return FileUtils.readFileToString(new File(logLocation));
    }

    // http://localhost:8080/logLevel?level=WARN
    @RequestMapping(value = "/logLevel", params = "level", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void changeLogLevel(@RequestParam("level") Level newLogLevel) {
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(newLogLevel);

        log.info("Log level set to: " + root.getLevel().toString());
    }
}
