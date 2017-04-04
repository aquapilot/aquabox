/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.plugins.service;

import org.aquapilot.aquabox.api.event.Event;
import org.aquapilot.aquabox.server.common.Service;
import org.aquapilot.aquabox.server.modules.notifier.model.NewSensorDetectedNotification;
import org.aquapilot.aquabox.server.modules.plugins.manager.PluginManagerImpl;

import java.util.List;
import java.util.Map;

/**
 * This class manage notifications
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public interface PluginService extends Service {

    Map<Event, List<PluginManagerImpl.EventRegistration>> getRegisteredEvents();

}
