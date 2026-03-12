package top.lmix.sitelmix.tools.shortener;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @GetMapping(value="/encurtador", headers = "host=site.lmix.top")
    public String encurtador(Model model, HttpServletRequest request){
        long total = urlService.contaQuantosInsertsTemNoBd();
        model.addAttribute("totalUrlsEncurtadas", total);

        return "encurtador";
    }
    
}
