/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC1Transformer;

import static net.dries007.tfc.world.layer.TFCLayerUtil.INLAND_MARKER;
import static net.dries007.tfc.world.layer.TFCLayerUtil.LAKE_MARKER;

/**
 * Adds instances of lake markers to a layer randomly
 */
public enum AddLakesLayer implements IC1Transformer
{
    SMALL(40),
    LARGE(160);

    private final int chance;

    AddLakesLayer(int chance)
    {
        this.chance = chance;
    }

    @Override
    public int apply(INoiseRandom context, int value)
    {
        if (value == INLAND_MARKER && context.nextRandom(chance) == 0)
        {
            return LAKE_MARKER;
        }
        return value;
    }
}