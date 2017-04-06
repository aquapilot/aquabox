/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.api;

import org.aquapilot.aquabox.api.exception.InvalidPluginException;
import org.aquapilot.aquabox.api.listener.AquaboxListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

/**
 * This class manage plugins
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public interface PluginManager {

   /**
    * Returns the requested plugin if it is loaded
    * <p>Name is case sensitive</p>
    *
    * @param name
    * @return
    */
   Optional<JavaPlugin> getPlugin(String name);

   /**
    * Gets a list of all currently loaded plugins
    *
    * @return
    */
   Collection<JavaPlugin> getPlugins();

   /**
    * Checks if the given plugin is enabled or not
    * <p>Name is case sensitive</p>
    *
    * @param name
    * @return true if the plugin is enabled, otherwise false
    */
   boolean isPluginEnabled(String name);

   /**
    * Checks if the given plugin is enabled or not
    *
    * @param plugin
    * @return true if the plugin is enabled, otherwise false
    */
   boolean isPluginEnabled(JavaPlugin plugin);

   /**
    * Get the plugin descriptor of a given plugin jar file.
    *
    * @param file A plugin file (jar)
    * @return
    * @throws InvalidPluginException
    */
   PluginDescriptor getPluginDescriptor(File file) throws InvalidPluginException;

   /**
    * Get the plugin descriptor of a given plugin instance.
    *
    * @param plugin
    * @return
    */
   PluginDescriptor getPluginDescriptor(JavaPlugin plugin);

   /**
    * Load a plugin file (jar)
    *
    * @param file Jar file
    * @return The Plugin loaded, or null if it was invalid
    * @throws InvalidPluginException Thrown when the specified file is not a
    *                                valid plugin
    */
   JavaPlugin loadPlugin(File file) throws InvalidPluginException;

   /**
    * Loads the plugins contained within the specified directory
    *
    * @param path Directory to check for plugins
    * @return A list of all plugins loaded
    */
   Collection<JavaPlugin> loadPlugins(Path path) throws FileNotFoundException;

   /**
    * Disables all the loaded plugins
    */
   void disablePlugins();

   /**
    * Disables and removes all plugins
    */
   void clearPlugins();

   /**
    * Registers all the events in the given listener class
    *
    * @param listener Listener to register
    * @param plugin   Plugin to register
    */
   void registerEvents(AquaboxListener listener, JavaPlugin plugin);

   /**
    * Enables the specified plugin
    * <p>
    * Attempting to enable a plugin that is already enabled will have no
    * effect
    *
    * @param plugin Plugin to enable
    */
   void enablePlugin(JavaPlugin plugin);

   /**
    * Disables the specified plugin
    * <p>
    * Attempting to disable a plugin that is not enabled will have no effect
    *
    * @param plugin Plugin to disable
    */
   void disablePlugin(JavaPlugin plugin);

}
