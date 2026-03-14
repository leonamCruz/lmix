package top.lmix.sitelmix.tools.json;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JsonServiceTest {

    private final JsonService service = new JsonService();

    @Test
    void testFormat() {
        JsonRequestDTO request = new JsonRequestDTO("{\"key\":\"value\"}", "FORMAT");
        JsonResponseDTO response = service.process(request);
        
        assertTrue(response.valid());
        // Jackson default pretty print adds newlines and spaces
        assertTrue(response.result().contains("\n"));
        assertTrue(response.result().contains("  \"key\" : \"value\""));
    }

    @Test
    void testMinify() {
        // Input with extra spaces
        JsonRequestDTO request = new JsonRequestDTO("{\n  \"key\" : \"value\"\n}", "MINIFY");
        JsonResponseDTO response = service.process(request);
        
        assertTrue(response.valid());
        assertEquals("{\"key\":\"value\"}", response.result());
    }

    @Test
    void testInvalidJson() {
        JsonRequestDTO request = new JsonRequestDTO("{key:value}", "FORMAT"); // Invalid JSON (missing quotes)
        JsonResponseDTO response = service.process(request);
        
        assertFalse(response.valid());
        assertNotNull(response.error());
        assertTrue(response.error().contains("O JSON inserido é inválido. Por favor, verifique a sintaxe."));
    }
}
