package top.lmix.sitelmix.tools.landingPage;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import top.lmix.sitelmix.tools.shortener.UrlService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LandingPageController {

    private final UrlService urlService;

    @GetMapping("/")
    public String root(HttpServletRequest request, Model model) {
        String host = request.getServerName();

        long total = urlService.contaQuantosInsertsTemNoBd();
        String versao = "Dos Caralhos";
        model.addAttribute("totalUrlsEncurtadas", total);
        model.addAttribute("versaoSistema", versao);

        if (host.startsWith("site.")) return "index";

        String redirectHost = "site." + host;

        String scheme = request.getScheme();
        int port = request.getServerPort();

        String url = scheme + "://" + redirectHost +
                (port == 80 || port == 443 ? "" : ":" + port);

        return "redirect:" + url;
    }

}
