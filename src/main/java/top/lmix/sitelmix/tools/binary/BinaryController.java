package top.lmix.sitelmix.tools.binary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import top.lmix.sitelmix.tools.shortener.UrlService;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/binario", headers = "host=site.lmix.top")
public class BinaryController {

    private final UrlService urlService;

    @GetMapping
    public String binary(Model model) {
        long total = urlService.contaQuantosInsertsTemNoBd();
        model.addAttribute("totalUrlsEncurtadas", total);
        return "binary";
    }
}
