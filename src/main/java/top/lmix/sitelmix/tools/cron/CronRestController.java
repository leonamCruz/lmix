package top.lmix.sitelmix.tools.cron;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cron", headers = "host=site.lmix.top")
@RequiredArgsConstructor
public class CronRestController {

    private final CronService cronService;

    @PostMapping
    public ResponseEntity<CronResponse> validateCron(@RequestBody CronRequestDTO cronRequestDTO) {
        var cronResponse = cronService.validate(cronRequestDTO);
        return cronResponse.map(response -> ResponseEntity.ok().body(response)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}