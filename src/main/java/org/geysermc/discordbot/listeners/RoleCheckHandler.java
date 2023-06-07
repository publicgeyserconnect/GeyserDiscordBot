package org.geysermc.discordbot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.geysermc.discordbot.GeyserBot;
import org.geysermc.discordbot.util.BotColors;

import java.util.*;

public class RoleCheckHandler extends ListenerAdapter {
    private static final String PREMIUM_CHANNEL_ID = "808149156102668328";
    private static final Set<String> ALLOWED_ROLES = new HashSet<>(Arrays.asList(
            "GeyserMC affiliates", "Systems Admin", "Supporters",
            "Server Booster", "Patreon", "Personal Server Admin"
    ));

    @Override
    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
        Member member = event.getMember();
        checkMemberRoles(member, event.getGuild(), true);
    }

    @Override
    public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
        Member member = event.getMember();
        checkMemberRoles(member, event.getGuild(), false);
    }

    public void checkMemberRoles(Member member, Guild guild, boolean isNewRole) {
        boolean hasCorrectRole = member.getRoles().stream()
                .map(Role::getName)
                .anyMatch(ALLOWED_ROLES::contains);

        GeyserBot.storageManager.setPremium(member.getId(), hasCorrectRole);

        if (hasCorrectRole) {
            // Send Embed
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("GeyserConnect Premium/Support");

            if (isNewRole) {
                embedBuilder.setDescription("Hello, " + member.getNickname() + " You were given a premium role on GeyserConnect. Please log in to our website and link your Discord account to access our premium bots.");
            } else {
                embedBuilder.setDescription("Hello, " + member.getNickname() + " Your roles were updated and still have access to premium bots.");
            }

            embedBuilder.addField("GeyserConnect", "https://geyserconnect.net", false);
            embedBuilder.setColor(BotColors.SUCCESS.getColor());

            // Check if the TextChannel exists before sending the embedded message
            TextChannel textChannel = guild.getTextChannelById(PREMIUM_CHANNEL_ID);
            if (textChannel != null) {
                textChannel.sendMessageEmbeds(embedBuilder.build()).queue();
            } else {
                System.out.println("TextChannel not found.");
            }
        }
    }
}