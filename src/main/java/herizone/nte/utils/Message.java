package herizone.nte.utils;

public enum Message {

    PREFIX("&eNameTagEditor > "),
    NO_CONSOLE(Message.PREFIX.get() + "&cYou cannot do this as console!"),
    CREATE_SYNTAX("&7/nametag create <id>"),
    DELETE_SYNTAX("&7/nametag delete <id>"),
    SET_PS_SYNTAX("&7/nametag tag <id> <prefix:suffix> <data>"),
    SET_SYNTAX("&7/nametag set <player> <id>"),
    UNSET_SYNTAX("&7/nametag unset <player>"),
    NO_PERMISSION(Message.PREFIX.get() + "&cYou do not have permission to use this command!"),
    CREATE_USAGE(Message.PREFIX.get() + Message.CREATE_SYNTAX.get()),
    CREATED_SUCCESSFULLY(Message.PREFIX.get() + "&aSuccessfully created &f{id}&a!"),
    FAILED_CREATE(Message.PREFIX.get() + "&cFailed to create &f{id}&c!"),
    CREATED_UNSUCCESSFULLY(Message.PREFIX.get() + "&cCould not create tag &f{id} &cbecause a tag with that ID already exists!"),
    DELETED_SUCCESSFULLY(Message.PREFIX.get() + "&aSuccessfully deleted &f{id}&a!"),
    SET_PREFIX_SUCCESSFULLY(Message.PREFIX.get() + "&aSuccessfully set prefix of &f{id} &ato '&f{prefix}&a'!"),
    SET_SUFFIX_SUCCESSFULLY(Message.PREFIX.get() + "&aSuccessfully set prefix of &f{id} &ato '&f{suffix}&a'!"),
    TAG_SET_SUCCESSFULLY(Message.PREFIX.get() + "&aSuccessfully set tag of &f{player} &ato &f{id}&a!"),
    PLAYER_NO_TAG(Message.PREFIX.get() + "&f{player} &chas no tag assigned to them!"),
    TAG_UNSET_SUCCESSFULLY(Message.PREFIX.get() + "&aSuccessfully unset &f{player}'s &atag!"),
    TAG_NOT_FOUND(Message.PREFIX.get() + "&cCould not locate &f{id}&c!"),
    DELETE_USAGE(Message.PREFIX.get() + Message.DELETE_SYNTAX.get()),
    SET_PREFIX_USAGE(Message.PREFIX.get() + "&7/nametag tag <id> prefix <data>"),
    SET_SUFFIX_USAGE(Message.PREFIX.get() + "&7/nametag tag <id> suffix <data>"),
    PLAYER_NOT_FOUND(Message.PREFIX.get() + "&cPlayer not found!"),
    SET_PS_USAGE(Message.PREFIX.get() + Message.SET_PS_SYNTAX.get()),
    SET_TAG_USAGE(Message.PREFIX.get() + Message.SET_SYNTAX.get()),
    UNSET_TAG_USAGE(Message.PREFIX.get() + Message.UNSET_SYNTAX.get());

    String message;

    Message(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }
}
