/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.storage.model;

/**
 * This enum reference all available and supported aquatic system types.
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public enum AquaticSystemType {

    AQUARIUM(0),
    AQUAPONIC(1),
    HYDROPONIC(2);

    private int value;

    AquaticSystemType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
