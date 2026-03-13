package top.lmix.sitelmix.tools.cron;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class CronResponse {

    private String descricao;

    private List<String> proximasExecucoes;

    private Map<String, String> campos;

}