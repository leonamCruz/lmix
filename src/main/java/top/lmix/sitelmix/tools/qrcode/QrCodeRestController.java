package top.lmix.sitelmix.tools.qrcode;

import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class QrCodeRestController {

    private final QrCodeService qrCodeService;

    @PostMapping(value = "/genQrCode", headers = "host=site.lmix.top", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QrCodeResponseDTO> generate(@RequestBody QrCodeRequestDTO dto) {
        try {
            return ResponseEntity.ok(qrCodeService.generate(dto));
        } catch (WriterException | IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
