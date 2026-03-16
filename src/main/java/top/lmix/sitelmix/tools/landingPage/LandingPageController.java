package top.lmix.sitelmix.tools.landingPage;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import top.lmix.sitelmix.tools.shortener.UrlService;

@Controller
@RequiredArgsConstructor
public class LandingPageController {

    private final UrlService urlService;
    private final RequestMappingHandlerMapping handlerMapping;

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

    @GetMapping(value = "/privacidade", headers = "host=site.lmix.top")
    public String privacidade(Model model) {
        long total = urlService.contaQuantosInsertsTemNoBd();
        model.addAttribute("totalUrlsEncurtadas", total);
        return "privacidade";
    }

    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE, headers = "host=site.lmix.top")
    public String generateSitemap(Model model) {
        String baseUrl = "https://site.lmix.top";
        String today = LocalDate.now().toString();

        // Pegamos as rotas, filtramos as indesejadas e montamos a lista final
        Set<String> urls = handlerMapping.getHandlerMethods().keySet().stream()
                .flatMap(info -> info.getDirectPaths().stream())
                .filter(path -> !path.startsWith("/error")) // Remove erros do Spring
                .filter(path -> !path.equals("/404")) // Remove sua página 404
                .filter(path -> !path.equals("/cron")) // Remove tarefas agendadas
                .filter(path -> !path.equals("/sitemap.xml")) // Não indexa o próprio sitemap
                .map(path -> baseUrl + path)
                .collect(Collectors.toSet()); // Set remove duplicatas automaticamente

        model.addAttribute("urls", urls);
        model.addAttribute("today", today);

        return "sitemap.xml"; // Vai buscar em src/main/resources/templates/sitemap.xml
    }

    @GetMapping(value = { "/.well-known/security.txt", "/security.txt" }, produces = MediaType.TEXT_PLAIN_VALUE, headers = "host=site.lmix.top")
    @ResponseBody
    public String getSecurityTxt() {
        return """
                Contact: mailto:dev@leonam.top
                Expires: 2027-04-15T00:00:00.000Z
                Preferred-Languages: pt-br, en
                Canonical: https://site.lmix.top/.well-known/security.txt
                """;
    }

}
