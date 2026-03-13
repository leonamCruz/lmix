package top.lmix.sitelmix.tools.binary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BinaryRestController {

    private final BinaryService binaryService;

    @PostMapping(value = "/convertBinary", headers = "host=site.lmix.top")
    public ResponseEntity<BinaryResponseDTO> convert(@RequestBody BinaryRequestDTO dto) {
        return ResponseEntity.ok(binaryService.convert(dto));
    }
}
