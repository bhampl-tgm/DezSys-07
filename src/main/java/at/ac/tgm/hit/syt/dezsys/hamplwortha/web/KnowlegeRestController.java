package at.ac.tgm.hit.syt.dezsys.hamplwortha.web;

import at.ac.tgm.hit.syt.dezsys.hamplwortha.domain.Knowlege;
import at.ac.tgm.hit.syt.dezsys.hamplwortha.service.KnowlegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KnowlegeRestController {

    @Autowired
    private KnowlegeRepository knowlegeRepository;

    @RequestMapping(value = "/knowlege", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Knowlege>> getByTitle(@RequestParam(value="title", defaultValue = "") String title) {
        if (!title.isEmpty()) return new ResponseEntity<>(knowlegeRepository.findByTitleContainingIgnoreCase(title), HttpStatus.OK);
        return new ResponseEntity<>(knowlegeRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/knowlege", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Knowlege> insert(@RequestBody Knowlege knowlege) {
        try {
            knowlegeRepository.save(knowlege);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(knowlege, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/knowlege/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Knowlege> update(@PathVariable long id, @RequestBody Knowlege newknowlege) {
        Knowlege oldknowlege = knowlegeRepository.findById(id);
        if (oldknowlege == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (newknowlege.getContent() != null && !newknowlege.getContent().isEmpty()) oldknowlege.setContent(newknowlege.getContent());
        if (newknowlege.getTitle() != null && !newknowlege.getTitle().isEmpty()) oldknowlege.setTitle(newknowlege.getTitle());
        if (newknowlege.getLanguage() != null && !newknowlege.getLanguage().isEmpty()) oldknowlege.setLanguage(newknowlege.getLanguage());
        knowlegeRepository.save(oldknowlege);
        return new ResponseEntity<>(oldknowlege, HttpStatus.OK);
    }

    @RequestMapping(value = "/knowlege/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Knowlege> getById(@PathVariable long id) {
        Knowlege knowlege = knowlegeRepository.findById(id);
        if (knowlege == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(knowlege, HttpStatus.OK);
    }

    @RequestMapping(value = "/knowlege/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Knowlege> delete(@PathVariable long id) {
        Knowlege knowlege = knowlegeRepository.findById(id);
        if (knowlege == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        knowlegeRepository.delete(knowlege);
        return new ResponseEntity<>(knowlege, HttpStatus.OK);
    }
}
