package top.lmix.sitelmix.tools.shortener;

import java.time.Instant;

public record UrlDTO(
        String urlOriginal,
        String urlEncurtada,
        Instant dataDeCriacao,
        Long quantasVezesEntraram,
        String qrCodeBase64
) {
}
