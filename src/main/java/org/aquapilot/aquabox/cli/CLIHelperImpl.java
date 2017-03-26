/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.cli;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * This class manage CLI parameters
 * <p>
 * It is based on Args4j
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * @see https://github.com/kohsuke/args4j
 */
public class CLIHelperImpl implements CLIHelper {

    private CmdLineParser parser;

    private boolean debugEnabled;

    public CLIHelperImpl(String[] args) {

        if (args == null || args.length == 0) {
            return;
        }

        CLIOptions options = new CLIOptions();
        this.parser = new CmdLineParser(options);

        try {
            parser.parseArgument(args);

            if (options.isHelp()) {
                showHelp();
            }
            if (options.isVersion()) {
                showVersion();
            }
            if (options.isDebug()) {
                debugEnabled = true;
            } else {
                debugEnabled = false;
            }

        } catch (CmdLineException e) {
            // display help
            parser.printUsage(System.out);
        }
    }

    @Override
    public void showHelp() {

        parser.printUsage(System.err);
    }

    @Override
    public void showVersion() {

        throw new NotImplementedException();
    }

    @Override
    public boolean isDebugEnabled() {

        return debugEnabled;
    }

}
