package at.ac.tgm.hit.syt.dezsys.hamplwortha.service;

import at.ac.tgm.hit.syt.dezsys.hamplwortha.domain.Knowledge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The knowledge Repository
 *
 * @version 1.0
 * @author Burkhard Hampl [burkhard.hampl@student.tgm.ac.at]
 */
@Transactional
@Repository
public interface KnowledgeRepository extends CrudRepository<Knowledge, String>{
    /**
     * Finds all Knowledge Entries containing the string
     *
     * @param title the title
     * @return all Knowledge Entries
     */
    List<Knowledge> findByTitleContainingIgnoreCase(@Param("title") String title);

    /**
     * Returns all Knowledge Entries
     *
     * @return all Knowledge Entries
     */
    List<Knowledge> findAll();

    /**
     * Returns the Knowledge Entries by id
     *
     * @param id the id
     * @return the Knowledge Entries
     */
    Knowledge findById(@Param("id") long id);
}
