package top.lmix.sitelmix.common;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FaviconRestController {

    @GetMapping("/favicon")
    public ResponseEntity<Resource> favicon() {

        Resource icon = new ClassPathResource("static/favicon.png");

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType("image/x-icon"))
                .body(icon);
    }
}