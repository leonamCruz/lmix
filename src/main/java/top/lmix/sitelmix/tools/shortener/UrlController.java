package top.lmix.sitelmix.tools.shortener;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @GetMapping("/encurtador")
    public String encurtador(Model model){
        long total = urlService.contaQuantosInsertsTemNoBd();
        String versao = "Dos Caralhos";
        model.addAttribute("totalUrlsEncurtadas", total);
        model.addAttribute("versaoSistema", versao);

        return "encurtador";
    }
    
}
