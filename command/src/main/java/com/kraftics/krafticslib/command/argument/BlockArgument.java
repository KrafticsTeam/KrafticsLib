package com.kraftics.krafticslib.command.argument;

import com.kraftics.krafticslib.command.StringReader;
import com.kraftics.krafticslib.command.exceptions.CommandSyntaxException;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BlockArgument implements Argument<Material> {
    private static final List<String> TAB_COMPLETE = new ArrayList<>();
    private final String name;

    static {
        for (Material material : Material.values()) {
            if (material.isBlock()) {
                TAB_COMPLETE.add(material.name().toLowerCase(Locale.ROOT));
            }
        }
    }

    public BlockArgument(String name) {
        this.name = name;
    }

    @Override
    public Material parse(StringReader reader) throws CommandSyntaxException {
        Material material = Material.getMaterial(reader.readUnquotedString().toUpperCase(Locale.ROOT));
        if (material == null || !material.isBlock()) throw CommandSyntaxException.BuiltIn.INVALID_BLOCK.build(reader);
        return material;
    }

    @Override
    public List<String> tabComplete(StringReader reader) throws CommandSyntaxException {
        return Argument.contextOnly(TAB_COMPLETE, reader);
    }

    @Override
    public String getName() {
        return name;
    }
}