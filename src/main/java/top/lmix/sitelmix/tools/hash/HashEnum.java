package top.lmix.sitelmix.tools.hash;

public enum HashEnum {
    MD5("MD5"),
    SHA1("SHA-1"),
    SHA256("SHA-256");

    private final String tipo;

    HashEnum(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
