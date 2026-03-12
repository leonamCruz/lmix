package top.lmix.sitelmix.tools.uuid;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UuidService {
    
    public UUID genUuid(){
        return UUID.randomUUID();
    }

}
