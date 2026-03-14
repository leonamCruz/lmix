package top.lmix.sitelmix.tools.landingPage;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import top.lmix.sitelmix.tools.shortener.UrlService;

@Controller
@RequiredArgsConstructor
public class LandingPageController {

    private final UrlService urlService;

    @GetMapping(value = "/", headers = "host=site.lmix.top")
    public String index(Model model) {
        long total = urlService.contaQuantosInsertsTemNoBd();
        model.addAttribute("totalUrlsEncurtadas", total);
        return "index";
    }

    @GetMapping(value = "/", headers = "host=lmix.top")
    public ResponseEntity<Void> redirectRaiz() {
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header(HttpHeaders.LOCATION, "https://site.lmix.top")
                .build();
    }

    @GetMapping(value = "/404", headers = "host=site.lmix.top")
    public String notFound(Model model) {
        long total = urlService.contaQuantosInsertsTemNoBd();
        model.addAttribute("totalUrlsEncurtadas", total);
        return "error/404";
    }

    @GetMapping(value = "/privacidade",  headers = "host=site.lmix.top")
    public String privacidade(Model model) {
        long total = urlService.contaQuantosInsertsTemNoBd();
        model.addAttribute("totalUrlsEncurtadas", total);
        return "privacidade";
    }

}
