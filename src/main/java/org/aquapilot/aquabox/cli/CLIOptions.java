/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.cli;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.util.List;

/**
 * This class describe allowed options in CLI.
 * <p>
 * It is based on Args4j
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * @see https://github.com/kohsuke/args4j
 */
public class CLIOptions {

    @Option(name = "-h", aliases = {"--help"}, usage = "Display help")
    private boolean help;

    @Option(name = "-v", aliases = {"--version"}, usage = "Display version")
    private boolean version;

    @Option(name = "-d", aliases = {"--debug"}, usage = "Enable debug mode")
    private boolean debug;

    @Argument
    private List<String> arguments; // Store everything passed in cli args that is not an option

    public boolean isHelp() {

        return help;
    }

    public boolean isVersion() {

        return version;
    }

    public boolean isDebug() {

        return debug;
    }

    public List<String> getArguments() {

        return arguments;
    }
}
