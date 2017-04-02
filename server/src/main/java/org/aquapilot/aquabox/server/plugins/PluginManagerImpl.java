/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.plugins;

import org.aquapilot.aquabox.api.JavaPlugin;
import org.aquapilot.aquabox.api.PluginDescriptor;
import org.aquapilot.aquabox.api.PluginManager;
import org.aquapilot.aquabox.api.exception.InvalidPluginException;
import org.aquapilot.aquabox.api.listener.AquaboxListener;
import org.ini4j.Ini;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class TODO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class PluginManagerImpl implements PluginManager {

    public Map<JavaPlugin, AquaboxListener> plugins = new HashMap<>();

    public List<Class> pluginsList = new ArrayList<Class>();

    @Override
    public JavaPlugin getPlugin(String name) {
        return null;
    }

    @Override
    public Collection<JavaPlugin> getPlugins() {
        return plugins.keySet();
    }

    @Override
    public boolean isPluginEnabled(String name) {
        return false;
    }

    @Override
    public boolean isPluginEnabled(JavaPlugin plugin) {
        return false;
    }

    @Override
    public PluginDescriptor getPluginDescriptor(File file) throws InvalidPluginException {
        checkNotNull(file, "File cannot be null");

        JarFile jar = null;
        InputStream stream = null;

        // Load jar in memory
        try {
            jar = new JarFile(file.getAbsolutePath());
            JarEntry entry = jar.getJarEntry("plugin.ini");
            if (entry == null) {
                throw new InvalidPluginException("This plugin doesnt contains any plugin.ini at root !");
            }

            stream = jar.getInputStream(entry);

            Ini ini = new Ini();
            ini.load(stream);

            Ini.Section informationsSection = ini.get("informations");
            String pluginName = informationsSection.get("name");
            String pluginDescription = informationsSection.get("description");
            String pluginAuthor = informationsSection.get("author");
            String pluginVersion = informationsSection.get("version");
            String pluginMainClass = informationsSection.get("mainClass");

            return PluginDescriptor.newInstance()
                    .name(pluginName)
                    .version(pluginVersion)
                    .mainClass(pluginMainClass)
                    .author(pluginAuthor)
                    .description(pluginDescription)
                    .build();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                jar.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public JavaPlugin loadPlugin(File file) throws InvalidPluginException {
        checkNotNull(file, "File cannot be null");

        if (!file.exists()) {
            throw new InvalidPluginException(new FileNotFoundException(file.getPath() + " does not exist"));
        }

        final PluginDescriptor pluginDescriptor = getPluginDescriptor(file);

        String mainClass = pluginDescriptor.getMainClass();
        URLClassLoader loader = null;
        JarFile jar = null;

        try {
            // Load jar in memory
            loader = new URLClassLoader(new URL[]{file.toURL()});
            jar = new JarFile(file.getAbsolutePath());
            String mainClassString = mainClass.replace(".", "/") + ".class";
            JarEntry entry = jar.getJarEntry(mainClassString);
            if(entry == null){
                throw new InvalidPluginException("The declared main file doesnt exists !");
            }


            Class jarClass = Class.forName(mainClass, true, loader);
            this.pluginsList.add(jarClass);

            Class<? extends JavaPlugin> pluginClass;
            try {
                pluginClass = jarClass.asSubclass(JavaPlugin.class);
            } catch (ClassCastException ex) {
                throw new InvalidPluginException("main class `" + pluginDescriptor.getMainClass() + "' does not extend JavaPlugin");
            }

            JavaPlugin plugin = pluginClass.newInstance();
            plugin.onEnable();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            if (loader != null) {
                try {
                    loader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                jar.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Collection<JavaPlugin> loadPlugins(Path path) throws FileNotFoundException {
        checkNotNull(path, "Directory cannot be null");

        if (!Files.exists(path)) {
            throw new FileNotFoundException("The given path doesn't exists");
        }

        if (!Files.isDirectory(path)) {
            // TODO
            //    throw new IsNotADirectoryException("The given parameter is not a valid directory");
        }

        List<File> pluginsToLoad = Collections.emptyList();
        try {
            Files.list(path)
                    .filter(file -> Files.isRegularFile(file))
                    .filter(file -> file.getFileName().toString().endsWith(".jar"))
                    .forEach(
                            file -> {
                                try {
                                    loadPlugin(file.toFile());
                                } catch (InvalidPluginException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void disablePlugins() {

    }

    @Override
    public void clearPlugins() {

    }

    @Override
    public void registerEvents(AquaboxListener listener, JavaPlugin plugin) {

    }

    @Override
    public void enablePlugin(JavaPlugin plugin) {

    }

    @Override
    public void disablePlugin(JavaPlugin plugin) {

    }
}
