package com.kraftics.liberium.command.argument;

import com.kraftics.liberium.command.StringReader;
import com.kraftics.liberium.command.exceptions.CommandSyntaxException;

import java.util.List;

public class StringArgument extends Argument<String> {
    private final Type type;

    public StringArgument(String name) {
        this(name, Type.STRING);
    }

    public StringArgument(String name, Type type) {
        super(name);
        this.type = type;
    }

    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        switch (type) {
            case UNTIL_END:
                String remaining = reader.getRemaining();
                reader.setCursor(reader.getString().length());
                return remaining;
            case UNQUOTED:
                return reader.readUnquotedString();
            case QUOTED:
                return reader.readQuotedString();
            default:
                return reader.readString();
        }
    }

    @Override
    public List<String> tabComplete(StringReader reader) throws CommandSyntaxException {
        return null;
    }

    public enum Type {
        STRING,
        UNQUOTED,
        QUOTED,
        UNTIL_END
    }
}
