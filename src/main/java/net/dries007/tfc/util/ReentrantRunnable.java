/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.util;

/**
 * This is a reentrant wrapper for a provided {@link Runnable}. It is designed where the runnable is set as a change listener for a value, but both direct access (through {@link ReentrantRunnable#runBlocking(Runnable)} and non-recursive updating is desired
 */
public class ReentrantRunnable implements Runnable
{
    private final Runnable mainRunner;
    private boolean working = false;

    public ReentrantRunnable(Runnable mainRunner)
    {
        this.mainRunner = mainRunner;
    }

    /**
     * Run something, while blocking any execution of the main runner (direct access)
     */
    public void runBlocking(Runnable runner)
    {
        boolean prev = working;
        working = true;
        runner.run();
        working = prev;
    }

    /**
     * This is to be used as a callback for the main runner. (change listener)
     */
    @Override
    public void run()
    {
        if (!working)
        {
            working = true;
            mainRunner.run();
            working = false;
        }
    }
}