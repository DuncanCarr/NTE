package herizone.nte.utils;

public class Tag {

    private String identifier, prefix, suffix;

    public Tag(String identifier, String prefix, String suffix) {
        this.identifier = identifier;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
