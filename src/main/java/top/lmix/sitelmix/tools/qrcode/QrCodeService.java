package top.lmix.sitelmix.tools.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class QrCodeService {

    public QrCodeResponseDTO generate(QrCodeRequestDTO dto) throws WriterException, IOException {
        int size = Math.max(128, Math.min(2048, dto.size())); // Clamp size between 128 and 2048
        String format = dto.format().equalsIgnoreCase("jpg") ? "jpg" : "png";
        
        // Configuração de dicas para o gerador
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(dto.text(), BarcodeFormat.QR_CODE, size, size, hints);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        
        // Configuração de cores (transparência apenas para PNG)
        MatrixToImageConfig config;
        if (format.equals("png") && dto.transparent()) {
            // ARGB: Black on foreground, Transparent on background
            config = new MatrixToImageConfig(0xFF000000, 0x00FFFFFF);
        } else {
            // Black on White
            config = new MatrixToImageConfig(0xFF000000, 0xFFFFFFFF);
        }

        MatrixToImageWriter.writeToStream(bitMatrix, format, pngOutputStream, config);
        
        String base64Image = Base64.getEncoder().encodeToString(pngOutputStream.toByteArray());
        String mimeType = "image/" + (format.equals("jpg") ? "jpeg" : "png");
        
        return new QrCodeResponseDTO(base64Image, mimeType);
    }
}
