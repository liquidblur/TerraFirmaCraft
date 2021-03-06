package net.dries007.tfc.common.blocks.rock;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import net.dries007.tfc.config.TFCConfig;

public class MossSpreadingBlock extends Block
{
    public static void spreadMoss(World world, BlockPos pos, BlockState state, Random random)
    {
        if (world.isAreaLoaded(pos, 5) && TFCConfig.SERVER.enableMossyRockSpreading.get() && random.nextInt(TFCConfig.SERVER.mossyRockSpreadRate.get()) == 0)
        {
            BlockPos targetPos = pos.offset(random.nextInt(4) - random.nextInt(4), random.nextInt(4) - random.nextInt(4), random.nextInt(4) - random.nextInt(4));
            BlockState targetState = world.getBlockState(targetPos);
            if (targetState.getBlock() instanceof IMossGrowingBlock)
            {
                ((IMossGrowingBlock) targetState.getBlock()).convertToMossy(world, targetPos, targetState);
            }
        }
    }

    public MossSpreadingBlock(Properties properties)
    {
        super(properties.randomTicks());
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        MossSpreadingBlock.spreadMoss(worldIn, pos, state, random);
    }
}
