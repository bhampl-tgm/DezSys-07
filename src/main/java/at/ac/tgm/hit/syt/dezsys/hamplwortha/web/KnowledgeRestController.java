package at.ac.tgm.hit.syt.dezsys.hamplwortha.web;

import at.ac.tgm.hit.syt.dezsys.hamplwortha.domain.Knowledge;
import at.ac.tgm.hit.syt.dezsys.hamplwortha.service.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KnowledgeRestController {

    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @RequestMapping(value = "/knowledge", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Knowledge>> getByTitle(@RequestParam(value="title", defaultValue = "") String title) {
        if (!title.isEmpty()) return new ResponseEntity<>(knowledgeRepository.findByTitleContainingIgnoreCase(title), HttpStatus.OK);
        return new ResponseEntity<>(knowledgeRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/knowledge", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Knowledge> insert(@RequestBody Knowledge knowledge) {
        try {
            knowledgeRepository.save(knowledge);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(knowledge, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/knowledge/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Knowledge> update(@PathVariable long id, @RequestBody Knowledge newknowledge) {
        Knowledge oldknowledge = knowledgeRepository.findById(id);
        if (oldknowledge == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (newknowledge.getContent() != null && !newknowledge.getContent().isEmpty()) oldknowledge.setContent(newknowledge.getContent());
        if (newknowledge.getTitle() != null && !newknowledge.getTitle().isEmpty()) oldknowledge.setTitle(newknowledge.getTitle());
        if (newknowledge.getLanguage() != null && !newknowledge.getLanguage().isEmpty()) oldknowledge.setLanguage(newknowledge.getLanguage());
        knowledgeRepository.save(oldknowledge);
        return new ResponseEntity<>(oldknowledge, HttpStatus.OK);
    }

    @RequestMapping(value = "/knowledge/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Knowledge> getById(@PathVariable long id) {
        Knowledge knowledge = knowledgeRepository.findById(id);
        if (knowledge == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(knowledge, HttpStatus.OK);
    }

    @RequestMapping(value = "/knowledge/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Knowledge> delete(@PathVariable long id) {
        Knowledge knowledge = knowledgeRepository.findById(id);
        if (knowledge == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        knowledgeRepository.delete(knowledge);
        return new ResponseEntity<>(knowledge, HttpStatus.OK);
    }
}
