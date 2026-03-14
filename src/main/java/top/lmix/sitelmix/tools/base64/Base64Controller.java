package top.lmix.sitelmix.tools.base64;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import top.lmix.sitelmix.tools.shortener.UrlService;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/base64", headers = "host=site.lmix.top")
public class Base64Controller {

    private final UrlService urlService;

    @GetMapping
    public String base64(Model model) {
        long total = urlService.contaQuantosInsertsTemNoBd();
        model.addAttribute("totalUrlsEncurtadas", total);
        return "base64";
    }
}
