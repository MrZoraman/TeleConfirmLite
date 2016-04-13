package com.lagopusempire.teleconfirmlite.messages;

import java.util.Map;
import java.util.Map.Entry;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

public class MessageFormatter {

    private static final char VARIABLE_DECORATOR = '%';

    private String contents;

    MessageFormatter(String contents) {
        if (contents == null || contents.isEmpty()) {
            throw new IllegalArgumentException("Contents cannot be null/empty!");
        }

        char[] chars = contents.toCharArray();
        boolean inDecorators = false;

        for (int ii = 0; ii < chars.length; ii++) {
            if (chars[ii] == VARIABLE_DECORATOR) {
                inDecorators = !inDecorators;
                continue;
            }

            if (inDecorators) {
                chars[ii] = Character.toLowerCase(chars[ii]);
            }
        }

        this.contents = String.valueOf(chars);
    }

    public MessageFormatter replace(String variable, String replacement) {
        if (variable == null || variable.isEmpty()) {
            throw new IllegalArgumentException("variable connot be null/empty!");
        }

        String toReplace = (new StringBuilder(variable.length() + 2))
                .append(VARIABLE_DECORATOR)
                .append(variable.toLowerCase())
                .append(VARIABLE_DECORATOR)
                .toString();
        contents = contents.replaceAll(toReplace, replacement);
        return this;
    }
    
    public MessageFormatter apply(Map<String, String> variables) {
        for(Entry<String, String> entry : variables.entrySet()) {
            replace(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override
    public String toString() {
        return contents;
    }
    
    public Text toText() {
        return TextSerializers.FORMATTING_CODE.deserialize(contents);
    }
}
