package top.lmix.sitelmix.tools.json;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JsonRestController {

    private final JsonService jsonService;

    @PostMapping(value = "/processJson", headers = "host=site.lmix.top")
    public ResponseEntity<JsonResponseDTO> process(@RequestBody JsonRequestDTO dto) {
        return ResponseEntity.ok(jsonService.process(dto));
    }
}
