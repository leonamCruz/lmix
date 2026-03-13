package top.lmix.sitelmix.tools.cron;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.lmix.sitelmix.tools.shortener.UrlService;

@Controller
@RequestMapping(value = "/cron", headers = "host=site.lmix.top")
@RequiredArgsConstructor
public class CronController {
    private final UrlService urlService;

    @GetMapping
    public String cron(Model model) {
        long total = urlService.contaQuantosInsertsTemNoBd();
        model.addAttribute("totalUrlsEncurtadas", total);
        return "cron";
    }

}
