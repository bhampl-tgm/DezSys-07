package at.ac.tgm.hit.syt.dezsys.hamplwortha.service;

import at.ac.tgm.hit.syt.dezsys.hamplwortha.domain.Knowlege;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface KnowlegeRepository extends CrudRepository<Knowlege, String>{
    List<Knowlege> findByTitleContainingIgnoreCase(@Param("title") String title);
    List<Knowlege> findAll();
    Knowlege findById(@Param("id") long id);
}
