/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.cli;

/**
 * This interface define how to manage CLI parameters
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * @see https://github.com/kohsuke/args4j
 */
public interface CLIHelper {

    void showHelp();

    void showVersion();

    boolean isDebugEnabled();

}
