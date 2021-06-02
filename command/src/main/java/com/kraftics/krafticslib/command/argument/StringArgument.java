package com.kraftics.krafticslib.command.argument;

import com.kraftics.krafticslib.command.StringReader;
import com.kraftics.krafticslib.command.exceptions.CommandSyntaxException;

import java.util.List;

public class StringArgument implements Argument<String> {
    private final String name;
    private final boolean quotable;

    public StringArgument(String name) {
        this(name, false);
    }

    public StringArgument(String name, boolean quotable) {
        this.name = name;
        this.quotable = quotable;
    }

    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        if (quotable) {
            return reader.readString();
        }
        return reader.readUnquotedString();
    }

    @Override
    public List<String> tabComplete(StringReader reader) throws CommandSyntaxException {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}
