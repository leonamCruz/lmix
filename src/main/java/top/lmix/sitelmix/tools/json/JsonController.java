package top.lmix.sitelmix.tools.json;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.lmix.sitelmix.tools.shortener.UrlService;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/json", headers = "host=site.lmix.top")
public class JsonController {

    private final UrlService urlService;

    @GetMapping
    public String json(Model model) {
        long total = urlService.contaQuantosInsertsTemNoBd();
        model.addAttribute("totalUrlsEncurtadas", total);
        return "json";
    }
}
