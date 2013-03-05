/*
 * This file is part of muCommander, http://www.mucommander.com
 * Copyright (C) 2002-2012 Maxence Bernard
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

package com.mucommander.ui.action.impl;

import com.mucommander.commons.file.FileOperation;
import com.mucommander.commons.file.filter.FileOperationFilter;
import com.mucommander.commons.file.util.FileSet;
import com.mucommander.ui.action.*;
import com.mucommander.ui.dialog.file.CalculateChecksumDialog;
import com.mucommander.ui.main.MainFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * This action invokes the {@link com.mucommander.ui.dialog.file.CalculateChecksumDialog} which allows to calculate
 * the checksum of the selected files and store the results in a pseudo-standard checksum file. 
 *
 * @author Maxence Bernard
 */
@InvokesDialog
public class CalculateChecksumAction extends SelectedFilesAction  {

    public CalculateChecksumAction(MainFrame mainFrame, Map<String,Object> properties) {
        super(mainFrame, properties);

        setSelectedFileFilter(new FileOperationFilter(FileOperation.READ_FILE));
    }

    @Override
    public void performAction(FileSet files) {
        new CalculateChecksumDialog(mainFrame, files).showDialog();
    }
    
    public static class Factory implements ActionFactory {

		public MuAction createAction(MainFrame mainFrame, Map<String,Object> properties) {
			return new CalculateChecksumAction(mainFrame, properties);
		}
    }
    
    public static class Descriptor extends AbstractActionDescriptor {
    	public static final String ACTION_ID = "CalculateChecksum";
    	
		public String getId() { return ACTION_ID; }

		public ActionCategory getCategory() { return ActionCategories.FILES; }

		public KeyStroke getDefaultAltKeyStroke() { return null; }

		public KeyStroke getDefaultKeyStroke() { return KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.SHIFT_DOWN_MASK | KeyEvent.ALT_DOWN_MASK); }
    }
}
