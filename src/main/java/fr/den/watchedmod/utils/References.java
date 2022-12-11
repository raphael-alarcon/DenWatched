package fr.den.watchedmod.utils;

import com.google.common.base.Strings;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import scala.Char;

public class References {

    public static final String MODID ="watchedmod";
    public static final String NAME = "WatchedMod";
    public static final String VERSION = "BETA 0.1";

    //region Proxy
    public static final String CP = "fr.den.watchedmod.proxy.ClientProxy";
    public static final String SP = "fr.den.watchedmod.proxy.ServerProxy";
    //endregion

    public static String getProgressBar(int current, int max, int totalBars, String symbol, char completedColor, char notCompletedColor) {
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);
        return Strings.repeat("" + ChatFormatting.getByChar(completedColor) + symbol, progressBars)
                + Strings.repeat("" + ChatFormatting.getByChar(notCompletedColor) + symbol, totalBars - progressBars);
    }
}
