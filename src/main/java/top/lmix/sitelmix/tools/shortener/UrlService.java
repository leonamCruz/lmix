package top.lmix.sitelmix.tools.shortener;

import java.io.ByteArrayOutputStream;
import java.time.Instant;
import java.util.Base64;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    @Value("${lmix.dominio}")
    private String dominio;

    @Transactional
    public UrlDTO salvar(UrlEntrada urlEntrada) throws Exception {
        UrlValidator validator = new UrlValidator();

        if (!validator.isValid(urlEntrada.getUrl()))
            throw new Exception("A Url não é válida");

        if (urlRepository.existsByUrlOriginal(urlEntrada.getUrl())) {
            UrlEntity original = buscarPorUrlOriginal(urlEntrada.getUrl());
            return toDTO(original);
        }

        // Cria sem encurtada
        UrlEntity url = UrlEntity.builder()
                .urlOriginal(urlEntrada.getUrl())
                .dataDeCriacao(Instant.now())
                .quantasVezesEntraram(0L)
                .build();

        // Salva para gerar ID
        url = urlRepository.save(url);

        // Converte ID para Base62 (cresce conforme necessário)
        url.setUrlEncurtada(toBase62(url.getId()));

        url = urlRepository.save(url);

        return toDTO(url);
    }

    public UrlEntity buscarPorUrlOriginal(String urlOriginal) {
        return urlRepository.findByUrlOriginal(urlOriginal);
    }

    public String buscaPorStringOriginal(String urlModificada) {
        UrlEntity url = urlRepository.findByUrlEncurtada(urlModificada);
        if (url == null)
            return "";

        url.setQuantasVezesEntraram(url.getQuantasVezesEntraram() + 1);
        url = urlRepository.save(url);

        return url.getUrlOriginal();
    }

    public Long contaQuantosInsertsTemNoBd() {
        return urlRepository.count();
    }

    private String toBase62(long value) {
        if (value <= 0)
            throw new IllegalArgumentException("ID inválido para Base62");

        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            sb.append(BASE62.charAt((int) (value % 62)));
            value /= 62;
        }
        return sb.reverse().toString();
    }

    private String gerarQRCodeBase64(String texto) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(texto, BarcodeFormat.QR_CODE, 300, 300);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (Exception e) {
            return "";
        }
    }

    private UrlDTO toDTO(UrlEntity url) {
        String linkParaOQrCode = dominio + url.getUrlEncurtada();

        return new UrlDTO(
                url.getUrlOriginal(),
                url.getUrlEncurtada(),
                url.getDataDeCriacao(),
                url.getQuantasVezesEntraram(),
                gerarQRCodeBase64(linkParaOQrCode)
            );
    }
}