package top.lmix.sitelmix.tools.binary;

import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.Arrays;

@Service
public class BinaryService {

    public BinaryResponseDTO convert(BinaryRequestDTO dto) {
        if (dto.text() == null || dto.text().isEmpty()) {
            return new BinaryResponseDTO("");
        }

        if ("ENCODE".equalsIgnoreCase(dto.mode())) {
            return textToBinary(dto.text());
        } else if ("DECODE".equalsIgnoreCase(dto.mode())) {
            return binaryToText(dto.text());
        }
        return new BinaryResponseDTO("Modo inválido");
    }

    private BinaryResponseDTO textToBinary(String text) {
        String binary = text.chars()
                .mapToObj(c -> String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'))
                .collect(Collectors.joining(" "));
        return new BinaryResponseDTO(binary);
    }

    private BinaryResponseDTO binaryToText(String binaryText) {
        try {
            String cleanText = binaryText.replaceAll("[^01\\s]", "");
            String text = Arrays.stream(cleanText.split("\\s+")) // Split by whitespace
                    .filter(s -> !s.isEmpty())
                    .map(b -> {
                        // Handle potential partial bytes if user is typing, though debounce helps.
                        // We will try to parse complete blocks.
                        try {
                            return String.valueOf((char) Integer.parseInt(b, 2));
                        } catch (NumberFormatException e) {
                            return "?";
                        }
                    })
                    .collect(Collectors.joining(""));
            return new BinaryResponseDTO(text);
        } catch (Exception e) {
            return new BinaryResponseDTO("Erro na conversão: " + e.getMessage());
        }
    }
}
