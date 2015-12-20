package at.ac.tgm.hit.syt.dezsys.hamplwortha.service;

import at.ac.tgm.hit.syt.dezsys.hamplwortha.domain.Knowledge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface KnowledgeRepository extends CrudRepository<Knowledge, String>{
    List<Knowledge> findByTitleContainingIgnoreCase(@Param("title") String title);
    List<Knowledge> findAll();
    Knowledge findById(@Param("id") long id);
}
