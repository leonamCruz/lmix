package top.lmix.sitelmix.tools.cron;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cronutils.descriptor.CronDescriptor;
import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;

@Service
public class CronService {

    private final CronParser parser;
    private final CronDescriptor descriptor;

    public CronService() {
        CronDefinition definition = CronDefinitionBuilder.instanceDefinitionFor(CronType.UNIX);
        this.parser = new CronParser(definition);
        Locale locale = new Locale.Builder()
                .setLanguage("pt")
                .setRegion("BR")
                .build();
        this.descriptor = CronDescriptor.instance(locale);
    }

    public Optional<CronResponse> validate(CronRequestDTO request) {

        String expr = request.getExpressao();

        try {
            Cron cron = parser.parse(expr);
            cron.validate();

            String descricao = descriptor.describe(cron);

            List<String> proximasExecucoes = calcularExecucoes(cron);

            Map<String, String> campos = interpretarCampos(expr);

            return Optional.of(CronResponse.builder()
                    .descricao(descricao)
                    .proximasExecucoes(proximasExecucoes)
                    .campos(campos)
                    .build());

        } catch (Exception ignored) {
        }

        return Optional.empty();
    }

    private List<String> calcularExecucoes(Cron cron) {

        List<String> lista = new ArrayList<>();

        ExecutionTime executionTime = ExecutionTime.forCron(cron);

        ZonedDateTime agora = ZonedDateTime.now();

        for (int i = 0; i < 10; i++) {

            Optional<ZonedDateTime> next = executionTime.nextExecution(agora);

            if (next.isEmpty())
                break;

            ZonedDateTime dt = next.get();

            lista.add(dt.toLocalDateTime().toString());

            agora = dt;
        }

        return lista;
    }

    private Map<String, String> interpretarCampos(String expr) {

        String[] partes = expr.trim().split("\\s+");

        Map<String, String> map = new LinkedHashMap<>();

        if (partes.length == 6) {
            map.put("Segundo", partes[0]);
            map.put("Minuto", partes[1]);
            map.put("Hora", partes[2]);
            map.put("Dia do mês", partes[3]);
            map.put("Mês", partes[4]);
            map.put("Dia da semana", partes[5]);
        } else if (partes.length == 5) {
            map.put("Minuto", partes[0]);
            map.put("Hora", partes[1]);
            map.put("Dia do mês", partes[2]);
            map.put("Mês", partes[3]);
            map.put("Dia da semana", partes[4]);
        }

        return map;
    }

}