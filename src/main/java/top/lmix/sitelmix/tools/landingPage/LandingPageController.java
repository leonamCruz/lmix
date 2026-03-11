package top.lmix.sitelmix.tools.landingPage;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class LandingPageController {

    @GetMapping("/")
    public String root(HttpServletRequest request, Model model) {
        String host = request.getServerName();

        long total = 20000; // Deve ser dinimico e puxar do BD e cachear também.
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
