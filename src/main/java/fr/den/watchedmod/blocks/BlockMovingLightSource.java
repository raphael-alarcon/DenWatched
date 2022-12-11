package fr.den.watchedmod.blocks;

import fr.den.watchedmod.init.ModBlocks;
import fr.den.watchedmod.init.ModItems;
import fr.den.watchedmod.tileentities.TileEntityMovingLightSource;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlockMovingLightSource extends Block implements ITileEntityProvider {

    public static EntityPlayer player;
    public static List<Item> lightSourceList = new ArrayList<Item>() {
        {
            add(ModItems.flashlight);
        }
    };

    public BlockMovingLightSource() {
        super(Material.AIR);
        setRegistryName("movinglightsource");
        setUnlocalizedName("movinglightsource");
        setDefaultState(blockState.getBaseState());
        ModBlocks.blocks.add(this);
        ModItems.items.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
        setTickRandomly(false);
        setLightLevel(1.0F);
    }

    public static boolean isLightEmittingItem(Item parItem) {
        return lightSourceList.contains(parItem);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.5F, 0.5F, 0.5F, 0.5F, 0.5F, 0.5F);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return null;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        return;
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
        return;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public void onFallenUpon(
            World worldIn,
            BlockPos pos,
            Entity entityIn,
            float fallDistance) {
        return;
    }

    @Override
    public void onLanded(World worldIn, Entity entityIn) {
        return;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityMovingLightSource(player);
    }
}