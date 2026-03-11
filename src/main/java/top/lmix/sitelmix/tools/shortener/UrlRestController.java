package top.lmix.sitelmix.tools.shortener;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UrlRestController {
        private final UrlService urlService;

    @PostMapping("/gerar")
    public ResponseEntity<UrlDTO> gerar(@RequestBody UrlEntrada urlEntrada) {
        try {
            var urlGerada = urlService.salvar(urlEntrada);
            return ResponseEntity.ok(urlGerada);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{urlModificada}")
    public ResponseEntity<Void> redirect(@PathVariable String urlModificada) {
        var url = urlService.buscaPorStringOriginal(urlModificada);

        var headers = new HttpHeaders();

        if(url == null || url.isEmpty()) url = "not-found";
        headers.add(HttpHeaders.LOCATION, url);

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).headers(headers).build();
    }

}
