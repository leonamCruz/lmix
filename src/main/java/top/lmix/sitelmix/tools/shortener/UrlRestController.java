package top.lmix.sitelmix.tools.shortener;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${lmix.dominio}")
    private String dominio;

    @PostMapping(value = "/gerar", headers = "host=site.lmix.top")
    public ResponseEntity<UrlDTO> gerar(@RequestBody UrlEntrada urlEntrada) {
        try {
            var urlGerada = urlService.salvar(urlEntrada);
            return ResponseEntity.ok(urlGerada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/{urlModificada}", headers = "host=lmix.top")
    public ResponseEntity<Void> redirect(@PathVariable String urlModificada) {
        var urlProvavel = urlService.buscaPorStringOriginal(urlModificada);
        var url = (urlProvavel == null || urlProvavel.isEmpty())
                ? "http://site.lmix.top/404"
                : urlProvavel;

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header(HttpHeaders.LOCATION, url)
                .build();
    }

}
