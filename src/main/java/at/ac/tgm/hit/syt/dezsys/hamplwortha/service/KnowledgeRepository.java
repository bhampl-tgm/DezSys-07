package at.ac.tgm.hit.syt.dezsys.hamplwortha.service;

import at.ac.tgm.hit.syt.dezsys.hamplwortha.domain.Knowledge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface KnowledgeRepository extends CrudRepository<Knowledge, String>{
    List<Knowledge> findByTitleContainingIgnoreCase(@Param("title") String title);
    List<Knowledge> findAll();
    Knowledge findById(@Param("id") long id);
}
