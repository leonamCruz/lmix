package top.lmix.sitelmix.tools.base64;

import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class Base64Service {

    public Base64ResponseDTO convert(Base64RequestDTO dto) {
        if (dto.text() == null || dto.text().isEmpty()) {
            return new Base64ResponseDTO("");
        }

        try {
            if ("ENCODE".equalsIgnoreCase(dto.mode())) {
                return new Base64ResponseDTO(
                    Base64.getEncoder().encodeToString(dto.text().getBytes(StandardCharsets.UTF_8))
                );
            } else if ("DECODE".equalsIgnoreCase(dto.mode())) {
                // Ensure valid Base64 input before decoding to avoid exceptions on partial input
                // Or let it fail and catch exception
                return new Base64ResponseDTO(
                    new String(Base64.getDecoder().decode(dto.text()), StandardCharsets.UTF_8)
                );
            }
            return new Base64ResponseDTO("Modo inválido");
        } catch (IllegalArgumentException e) {
            // Return empty or partial/error message if invalid base64 (common during typing)
            return new Base64ResponseDTO("Texto inválido para Base64");
        } catch (Exception e) {
            return new Base64ResponseDTO("Erro: " + e.getMessage());
        }
    }
}
