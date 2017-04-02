/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.api;

import javax.annotation.Generated;

/**
 * This class TODO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class PluginDescriptor {

    private final String name; // Required
    private final String version; // Required
    private final String mainClass; // Required
    private String description; // Optional
    private String author; // Optional

    private PluginDescriptor(String name, String version, String mainClass) {
        this.name = name;
        this.version = version;
        this.mainClass = mainClass;
    }

    public static NameStep newInstance() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getMainClass() {
        return mainClass;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    @Generated(value = "Step Builder Generator Plugin")
    public interface NameStep {
        VersionStep name(String name);
    }

    @Generated(value = "Step Builder Generator Plugin")
    public interface VersionStep {
        MainClassStep version(String version);
    }

    @Generated(value = "Step Builder Generator Plugin")
    public interface MainClassStep {
        FinalStep mainClass(String mainClass);
    }

    @Generated(value = "Step Builder Generator Plugin")
    public interface FinalStep {
        PluginDescriptor build();

        FinalStep description(String description);

        FinalStep author(String author);
    }

    @Generated(value = "Step Builder Generator Plugin")
    private static final class Builder implements NameStep, VersionStep, MainClassStep, FinalStep {
        private String name;
        private String version;
        private String mainClass;
        private String description;
        private String author;

        public VersionStep name(String name) {
            this.name = name;
            return this;
        }

        public MainClassStep version(String version) {
            this.version = version;
            return this;
        }

        public FinalStep mainClass(String mainClass) {
            this.mainClass = mainClass;
            return this;
        }

        public FinalStep description(String description) {
            this.description = description;
            return this;
        }

        public FinalStep author(String author) {
            this.author = author;
            return this;
        }

        public PluginDescriptor build() {
            PluginDescriptor theObject = new PluginDescriptor(name, version, mainClass);
            theObject.description = description;
            theObject.author = author;
            return theObject;
        }
    }
    // private final List dependencies

}
