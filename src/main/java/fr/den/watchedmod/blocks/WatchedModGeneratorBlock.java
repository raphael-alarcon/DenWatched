package fr.den.watchedmod.blocks;

import fr.den.watchedmod.WatchedModMain;
import fr.den.watchedmod.events.IHasModel;
import fr.den.watchedmod.init.ModBlocks;
import fr.den.watchedmod.init.ModItems;
import fr.den.watchedmod.proxy.GuiHandlerWatched;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

public class WatchedModGeneratorBlock extends Block implements IHasModel {

    private final GuiHandlerWatched guihandler = new GuiHandlerWatched();

    public WatchedModGeneratorBlock(String name, Material materialIn) {
        super(materialIn);
        setRegistryName(name);
        setUnlocalizedName(name);
        ModBlocks.blocks.add(this);
        ModItems.items.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        translucent = false;
        setCreativeTab(WatchedModMain.modtab);
    }

    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    @Override
    public void registerModels() {
        WatchedModMain.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing) state.getValue(FACING)).getIndex();
    }

    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
    }

    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING});
    }

    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        playerIn.openGui(WatchedModMain.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

}
