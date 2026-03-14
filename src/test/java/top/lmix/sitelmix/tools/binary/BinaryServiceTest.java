package top.lmix.sitelmix.tools.binary;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BinaryServiceTest {

    private final BinaryService service = new BinaryService();

    @Test
    void testEncode() {
        BinaryRequestDTO request = new BinaryRequestDTO("Test", "ENCODE");
        BinaryResponseDTO response = service.convert(request);
        // 'T' -> 01010100
        // 'e' -> 01100101
        // 's' -> 01110011
        // 't' -> 01110100
        assertEquals("01010100 01100101 01110011 01110100", response.result());
    }

    @Test
    void testDecode() {
        BinaryRequestDTO request = new BinaryRequestDTO("01010100 01100101 01110011 01110100", "DECODE");
        BinaryResponseDTO response = service.convert(request);
        assertEquals("Test", response.result());
    }

    @Test
    void testDecodeWithExtraSpaces() {
        BinaryRequestDTO request = new BinaryRequestDTO("  01010100   01100101  ", "DECODE");
        BinaryResponseDTO response = service.convert(request);
        assertEquals("Te", response.result());
    }
}
