package top.lmix.sitelmix.core.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class SqliteConfig {
    private final JdbcTemplate jdbc;

    @PostConstruct
    public void sqliteConfig() {
        jdbc.execute("PRAGMA journal_mode=WAL");
        jdbc.execute("PRAGMA synchronous=NORMAL");
        jdbc.execute("PRAGMA busy_timeout=5000");
    }

}
