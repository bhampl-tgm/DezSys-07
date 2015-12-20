package at.ac.tgm.hit.syt.dezsys.hamplwortha.soa;

import at.ac.tgm.hit.syt.dezsys.hamplwortha.domain.Knowledge;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"knowledge"})
@XmlRootElement(name = "getKnowledgeResponse")
public class GetKnowledgeResponse {
    @XmlElement(name = "Knowledge", required = true)
    protected List<Knowledge> knowledge;

    public GetKnowledgeResponse() {
    }

    public List<Knowledge> getKnowledge() {
        if(this.knowledge == null) {
            this.knowledge = new ArrayList<>();
        }

        return this.knowledge;
    }

    public void setKnowledge(List<Knowledge> knowledge) {
        this.knowledge = knowledge;
    }
}
