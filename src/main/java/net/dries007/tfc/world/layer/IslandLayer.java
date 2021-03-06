/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

public class IslandLayer implements IAreaTransformer0
{
    private final int islandFrequency;

    public IslandLayer(int islandFrequency)
    {
        this.islandFrequency = islandFrequency;
    }

    @Override
    public int applyPixel(INoiseRandom random, int x, int z)
    {
        if (x == 0 && z == 0)
        {
            return TFCLayerUtil.PLAINS;
        }
        else
        {
            return random.nextRandom(islandFrequency) == 0 ? TFCLayerUtil.PLAINS : TFCLayerUtil.DEEP_OCEAN;
        }
    }
}