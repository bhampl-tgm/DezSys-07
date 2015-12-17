package at.ac.tgm.hit.syt.dezsys.hamplwortha.soa;

import at.ac.tgm.hit.syt.dezsys.hamplwortha.domain.Knowledge;
import at.ac.tgm.hit.syt.dezsys.hamplwortha.service.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class KnowledgeEndpoint {
    public static final String NAMESPACE_URI = "http://at/ac/tgm/hit/syt/dezsys/hamplwortha/soa";

    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getKnowledgeRequest")
    @ResponsePayload
    public GetKnowledgeResponse getKnowledge(@RequestPayload GetKnowledgeRequest request) {
        GetKnowledgeResponse response = new GetKnowledgeResponse();
        for (Knowledge knowledge : knowledgeRepository.findByTitleContainingIgnoreCase(request.getTitle())) {
            response.getKnowledge().add(knowledge);
        }
        return response;
    }
}
