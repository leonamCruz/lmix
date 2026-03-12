package top.lmix.sitelmix.tools.landingPage;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
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

    @GetMapping(value = "/not-found", headers = "host=site.lmix.top")
    public String notFound() {
        return "not-found";
    }

    @GetMapping(value = "/", headers = "host=lmix.top")
    public ResponseEntity<Void> redirectRaiz() {
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header(HttpHeaders.LOCATION, "http://site.lmix.top")
                .build();
    }

    @GetMapping(value = "/404")
    public String notFound(Model model) {
        long total = urlService.contaQuantosInsertsTemNoBd();
        model.addAttribute("totalUrlsEncurtadas", total);
        return "error/404";
    }
}
