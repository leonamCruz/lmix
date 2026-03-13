package top.lmix.sitelmix.tools.uuid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(headers = "host=site.lmix.top")
public class UuidController {
    private final UuidService uuidService;

    @GetMapping("/uuid")
    public String uuid(Model model){
        model.addAttribute("uuid", uuidService.genUuid());
        return "uuid";
    }

}
