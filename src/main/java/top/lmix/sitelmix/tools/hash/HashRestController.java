package top.lmix.sitelmix.tools.hash;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HashRestController {
    private final HashService hashService;

    @PostMapping(value = "/genHash", headers = "host=site.lmix.top")
    public ResponseEntity<HashDTOOut> gerarHashes(@RequestBody HashDTO dto) {

        return ResponseEntity.ok(hashService.calcTodosHashes(dto));
    }
}
