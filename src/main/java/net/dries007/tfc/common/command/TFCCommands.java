/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.common.command;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

import com.mojang.brigadier.CommandDispatcher;

public final class TFCCommands
{
    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        // Register all new commands as sub commands of the `tfc` root
        dispatcher.register(Commands.literal("tfc")
            .then(ClearWorldCommand.create())
            .then(HeatCommand.create())
            .then(PlayerCommand.create())
            .then(TreeCommand.create())
        );

        // For command modifications / replacements, we register directly
        // First, remove the vanilla command by the same name
        // This seems to work. It does leave the command still lying around, but it shouldn't matter as we replace it anyway
        dispatcher.getRoot().getChildren().removeIf(node -> node.getName().equals("time"));
        dispatcher.register(TimeCommand.create());
    }
}