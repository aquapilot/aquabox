/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.modules.storage;

import com.google.inject.AbstractModule;
import org.aquapilot.modules.storage.services.StorageService;
import org.aquapilot.settings.model.Settings;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class define the storage module according to the database settings provided
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class StorageModule extends AbstractModule {

    private final Settings settings;

    public StorageModule(Settings settings) {
        checkNotNull(settings.getDatabase(), "Database setting value cannot be null.");
        this.settings = settings;
    }

    @Override
    protected void configure() {
        bind(StorageService.class).to(this.settings.getDatabase().getAssociatedServiceClass()).asEagerSingleton();
    }

}
