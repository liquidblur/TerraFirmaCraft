package net.dries007.tfc.world.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

import net.dries007.tfc.world.noise.INoise2D;

public class FloatNoiseLayer implements IAreaTransformer0
{
    private final INoise2D noise;

    public FloatNoiseLayer(INoise2D noise)
    {
        this.noise = noise;
    }

    @Override
    public int applyPixel(INoiseRandom context, int x, int z)
    {
        return Float.floatToRawIntBits(noise.noise(x, z));
    }
}
