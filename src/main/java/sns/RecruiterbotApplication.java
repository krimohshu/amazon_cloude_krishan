package sns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class RecruiterbotApplication implements CommandLineRunner {
    @Autowired
    PublisherService publisherService;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RecruiterbotApplication.class);
        app.run(args);
    }

    @Override
    public void run(String... arg0) throws IOException, URISyntaxException {

        try {
            this.publisherService.publish("{\"mobileno\": \"+447402522151\", \"message\": \"Hello from shyam.bye!\"}", PublisherService.TOPIC_INTERVIEWSTATUS);
        } catch (TopicNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
