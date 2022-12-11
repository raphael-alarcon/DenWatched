package fr.den.watchedmod.init;

import fr.den.watchedmod.blocks.BlockMovingLightSource;
import fr.den.watchedmod.blocks.WatchedModGeneratorBlock;
import fr.den.watchedmod.blocks.WatchedModGenericBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    public static List<Block> blocks = new ArrayList<>();
    public static Block computer_block = new WatchedModGenericBlock("computer_block_medium", Material.IRON);
    public static Block movinglightsource = new BlockMovingLightSource();

}
