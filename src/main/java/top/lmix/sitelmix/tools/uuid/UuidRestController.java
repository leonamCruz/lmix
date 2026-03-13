package top.lmix.sitelmix.tools.uuid;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UuidRestController {

    @GetMapping(value = "/genUuid", headers = "host=site.lmix.top")
    public UUID genUuid(){
        return UUID.randomUUID();
    }
    
}
