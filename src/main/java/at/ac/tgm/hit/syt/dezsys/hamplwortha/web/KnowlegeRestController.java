package at.ac.tgm.hit.syt.dezsys.hamplwortha.web;

import at.ac.tgm.hit.syt.dezsys.hamplwortha.domain.Knowlege;
import at.ac.tgm.hit.syt.dezsys.hamplwortha.service.KnowlegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KnowlegeRestController {

    @Autowired
    private KnowlegeRepository knowlegeRepository;

    @RequestMapping(value = "/knowlege", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Knowlege>> getByName(@RequestParam(value="title", defaultValue = "") String title) {
        if (!title.isEmpty()) return new ResponseEntity<>(knowlegeRepository.findByTitleContainingIgnoreCase(title), HttpStatus.OK);
        return new ResponseEntity<>(knowlegeRepository.findAll(), HttpStatus.OK);
    }
}
