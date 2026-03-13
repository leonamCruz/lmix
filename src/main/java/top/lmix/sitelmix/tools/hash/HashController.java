package top.lmix.sitelmix.tools.hash;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import top.lmix.sitelmix.tools.shortener.UrlService;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/hash", headers = "host=site.lmix.top")
public class HashController {

    private final UrlService urlService;

    @GetMapping
    public String hash(Model model) {
        long total = urlService.contaQuantosInsertsTemNoBd();
        model.addAttribute("totalUrlsEncurtadas", total);
        return "hash";
    }
    
}
