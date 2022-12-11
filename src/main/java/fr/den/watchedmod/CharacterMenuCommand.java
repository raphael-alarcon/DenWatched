package fr.den.watchedmod;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CharacterMenuCommand extends CommandBase {

    @Override
    public String getName() {
        return "character";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/character <player>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayer senderPlayer = (EntityPlayer) sender;
        if (args.length == 1)
        {
            EntityPlayer argPlayer = getEntity(server, sender, args[0], EntityPlayer.class);
            sender.sendMessage(new TextComponentString(WatchedModMain.prefix + "Le menu de création de personnage à été ouvert pour " + argPlayer.getName() + "."));
            ((EntityPlayer) sender).openGui(WatchedModMain.instance, 1, null, 0, 0, 0);
        } else {
            throw new WrongUsageException(getUsage(sender), new Object[0]);
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1)
        {
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        } else {
            return null;
        }
    }
}
