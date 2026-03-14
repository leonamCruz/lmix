package top.lmix.sitelmix.tools.qrcode;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.lmix.sitelmix.tools.shortener.UrlService;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/qrcode", headers = "host=site.lmix.top")
public class QrCodeController {

    private final UrlService urlService;

    @GetMapping
    public String qrcode(Model model) {
        long total = urlService.contaQuantosInsertsTemNoBd();
        model.addAttribute("totalUrlsEncurtadas", total);
        return "qrcode";
    }
}
