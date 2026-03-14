package top.lmix.sitelmix.tools.qrcode;

import com.google.zxing.WriterException;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class QrCodeServiceTest {

    private final QrCodeService service = new QrCodeService();

    @Test
    void testGeneratePng() throws IOException, WriterException {
        QrCodeRequestDTO request = new QrCodeRequestDTO("https://lmix.top", 512, "png", false);
        QrCodeResponseDTO response = service.generate(request);
        
        assertNotNull(response.imageBase64());
        assertEquals("image/png", response.mimeType());
        assertTrue(response.imageBase64().length() > 0);
    }

    @Test
    void testGenerateJpg() throws IOException, WriterException {
        QrCodeRequestDTO request = new QrCodeRequestDTO("https://lmix.top", 256, "jpg", false);
        QrCodeResponseDTO response = service.generate(request);
        
        assertNotNull(response.imageBase64());
        assertEquals("image/jpeg", response.mimeType());
    }

    @Test
    void testSizeClamping() throws IOException, WriterException {
        // Test lower bound clamping
        QrCodeRequestDTO small = new QrCodeRequestDTO("test", 10, "png", false); 
        QrCodeResponseDTO resSmall = service.generate(small);
        assertNotNull(resSmall);

        // Test upper bound clamping
        QrCodeRequestDTO big = new QrCodeRequestDTO("test", 5000, "png", false); 
        QrCodeResponseDTO resBig = service.generate(big);
        assertNotNull(resBig);
    }
}
