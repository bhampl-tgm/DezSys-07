package at.ac.tgm.hit.syt.dezsys.hamplwortha;

import at.ac.tgm.hit.syt.dezsys.hamplwortha.domain.Knowledge;
import at.ac.tgm.hit.syt.dezsys.hamplwortha.service.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * The Application main class
 *
 * @version 1.0
 * @author Burkhard Hampl [burkhard.hampl@student.tgm.ac.at]
 */
@Configuration
@SpringBootApplication
public class Application {

    @Autowired
    private KnowledgeRepository knowledgeRepository;
    private static int testDataAmount = 0;

    /**
     * The main method
     *
     * the first parameter is the amount of testdata (default 0)
     * @param args the program args
     */
    public static void main(String[] args) {
        if(args.length >= 1) testDataAmount = Integer.parseInt(args[0]);
        SpringApplication.run(Application.class, args);
    }

    /**
     * Inserts all 1000000 Test data entries
     *
     * This takes a while
     */
    @PostConstruct
    public void insertTestData (){
        for (int i = 0; i < testDataAmount; i++) {
            knowledgeRepository.save(new Knowledge("TestTitle" + i, "TestContent" + i + "TestContent" + i + "TestContent" + i + "TestContent" + i + "TestContent" + i + "TestContent" + i + "TestContent" + i + "TestContent" + i + "TestContent" + i, "TestLanguage" + 1));
        }
    }
}
