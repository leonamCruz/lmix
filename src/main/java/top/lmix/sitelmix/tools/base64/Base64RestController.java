package top.lmix.sitelmix.tools.base64;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class Base64RestController {

    private final Base64Service base64Service;

    @PostMapping(value = "/convertBase64", headers = "host=site.lmix.top")
    public ResponseEntity<Base64ResponseDTO> convert(@RequestBody Base64RequestDTO dto) {
        return ResponseEntity.ok(base64Service.convert(dto));
    }
}
