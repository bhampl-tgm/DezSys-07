package at.ac.tgm.hit.syt.dezsys.hamplwortha.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The HTML Controller
 *
 * @version 1.0
 * @author Burkhard Hampl [burkhard.hampl@student.tgm.ac.at]
 */
@Controller
public class KnowledgeController {

    /**
     * Redirects to /knowledge
     *
     * @return the redirect request
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String redirectToKnowledge() {
        return "redirect:knowledge";
    }

    /**
     * Returns the index.html file
     *
     * @return the index.html file
     */
    @RequestMapping(value = "/knowledge", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String showKnowledge() {
        return "index";
    }
}
