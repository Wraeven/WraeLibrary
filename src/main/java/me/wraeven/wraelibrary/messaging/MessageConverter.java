package me.wraeven.wraelibrary.messaging;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;

public class MessageConverter {
    private static final MiniMessage complete = MiniMessage.builder().tags(TagResolver.builder()
            .resolver(StandardTags.color())
            .resolver(StandardTags.decorations())
            .build()).build();

    public static Component convert(String message) {
        return complete.deserialize(message);
    }
}
