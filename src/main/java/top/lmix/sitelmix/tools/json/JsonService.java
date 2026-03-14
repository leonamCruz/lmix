package top.lmix.sitelmix.tools.json;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class JsonService {

    private final ObjectMapper prettyMapper;
    private final ObjectMapper minifyMapper;

    public JsonService() {
        this.prettyMapper = new ObjectMapper();
        this.prettyMapper.enable(SerializationFeature.INDENT_OUTPUT);

        this.minifyMapper = new ObjectMapper();
    }

    public JsonResponseDTO process(JsonRequestDTO dto) {
        if (dto.json() == null || dto.json().trim().isEmpty()) return new JsonResponseDTO("", true, null);

        try {
            Object jsonObject = minifyMapper.readValue(dto.json(), Object.class);

            if (!"MINIFY".equalsIgnoreCase(dto.mode()))
                return new JsonResponseDTO(prettyMapper.writeValueAsString(jsonObject), true, null);

            return new JsonResponseDTO(minifyMapper.writeValueAsString(jsonObject), true, null);

        } catch (JsonProcessingException e) {
            String msg = "O JSON inserido é inválido. Por favor, verifique a sintaxe.";
            return new JsonResponseDTO(dto.json(), false, msg);
        } catch (Exception e) {
            return new JsonResponseDTO(dto.json(), false, "Erro interno: " + e.getMessage());
        }
    }
}