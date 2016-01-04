package at.ac.tgm.hit.syt.dezsys.hamplwortha.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class KnowledgeController {

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String redirectToKnowledge() {
        return "redirect:knowledge";
    }

    @RequestMapping(value = "/knowledge", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String showKnowledge() {
        return "index";
    }
}
