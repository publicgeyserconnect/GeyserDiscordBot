/*
 * Copyright (c) 2020-2023 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/GeyserDiscordBot
 */

package org.geysermc.discordbot.listeners;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.geysermc.discordbot.GeyserBot;

import java.util.ArrayList;
import java.util.List;

public class RoleCheckHandler extends ListenerAdapter {

    @Override
    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
        Member m = event.getMember();
        checkMemberRoles(m.getRoles(), m.getId());
    }

    @Override
    public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
        Member m = event.getMember();
        checkMemberRoles(m.getRoles(), m.getId());
    }

    public void checkMemberRoles(List<Role> roleList, String id) {
        List<String> memberRoles = new ArrayList<>();

        for (Role s : roleList) {
            memberRoles.add(s.getName());
        }

        GeyserBot.storageManager.setPremium(id, hasCorrectRole(memberRoles));
        memberRoles.clear();
    }

    public boolean hasCorrectRole(List<String> memberRoles) {
        String[] allowedRoles = { "GeyserMC affiliates", "Systems Admin", "Supporters", "Server Booster", "Patreon", "Personal Server Admin" };
        for (String roles : allowedRoles) {
            if (memberRoles.stream().anyMatch(s -> s.contains(roles))) {
                System.out.println(roles + " " + true);
                return true;
            }
        }
        return false;
    }
}
