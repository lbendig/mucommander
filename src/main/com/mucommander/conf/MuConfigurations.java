/*
 * This file is part of muCommander, http://www.mucommander.com
 * Copyright (C) 2002-2010 Maxence Bernard
 *
 * muCommander is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * muCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.mucommander.conf;

import java.io.IOException;

import com.mucommander.commons.conf.Configuration;
import com.mucommander.commons.conf.ConfigurationException;
import com.mucommander.commons.conf.ConfigurationListener;

/**
 * 
 * @author Arik Hadas
 */
public class MuConfigurations {

	private static final MuPreferences preferences = new MuPreferences();
	
	public static Configuration getPreferences() {
		return preferences.getConfiguration();
	}
	
	public static void loadPreferences() throws IOException, ConfigurationException {
		preferences.read();
	}
	
	public static void savePreferences() throws IOException, ConfigurationException {
		preferences.write();
	}
	
    public static void addPreferencesListener(ConfigurationListener listener) {preferences.addConfigurationListener(listener);}

    public static void removePreferencesListener(ConfigurationListener listener) {preferences.removeConfigurationListener(listener);}
}
