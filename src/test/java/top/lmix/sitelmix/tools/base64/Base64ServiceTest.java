package top.lmix.sitelmix.tools.base64;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Base64ServiceTest {

    private final Base64Service service = new Base64Service();

    @Test
    void testEncode() {
        Base64RequestDTO request = new Base64RequestDTO("Hello World", "ENCODE");
        Base64ResponseDTO response = service.convert(request);
        assertEquals("SGVsbG8gV29ybGQ=", response.result());
    }

    @Test
    void testDecode() {
        Base64RequestDTO request = new Base64RequestDTO("SGVsbG8gV29ybGQ=", "DECODE");
        Base64ResponseDTO response = service.convert(request);
        assertEquals("Hello World", response.result());
    }

    @Test
    void testInvalidDecode() {
        Base64RequestDTO request = new Base64RequestDTO("InvalidBase64!!!", "DECODE");
        Base64ResponseDTO response = service.convert(request);
        assertEquals("Texto inválido para Base64", response.result());
    }
}
