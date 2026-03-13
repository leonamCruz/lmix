package top.lmix.sitelmix.tools.hash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import org.springframework.stereotype.Service;

@Service
public class HashService {

    public HashDTOOut calcTodosHashes(HashDTO dto) {
        String texto = dto.texto();
        if (dto.texto() == null) texto = "";

        return new HashDTOOut(
                gerarHash(texto, "MD5"),
                gerarHash(texto, "SHA-1"),
                gerarHash(texto, "SHA-256"));
    }

    private String gerarHash(String texto, String algoritmo) {
        try {
            MessageDigest md = MessageDigest.getInstance(algoritmo);
            byte[] hashBytes = md.digest(texto.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            return "Erro: Algoritmo não suportado";
        }
    }
}