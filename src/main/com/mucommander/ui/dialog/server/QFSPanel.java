package com.mucommander.ui.dialog.server;

import java.net.MalformedURLException;
import java.text.ParseException;

import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.mucommander.commons.file.FileProtocols;
import com.mucommander.commons.file.FileURL;
import com.mucommander.text.Translator;
import com.mucommander.ui.main.MainFrame;


/**
 * This ServerPanel helps initiate QFS connections.
 * 
 * @author Lorand Bendig <lbendig@gmail.com>
 *
 */
public class QFSPanel extends ServerPanel {

    private JTextField serverField;
    private JTextField initialDirField;
    private JSpinner portSpinner;

    private static String lastServer = "";
    private static String lastInitialDir = "/";
    private static int lastPort = FileURL.getRegisteredHandler(FileProtocols.QFS).getStandardPort();


    QFSPanel(ServerConnectDialog dialog, final MainFrame mainFrame) {
        super(dialog, mainFrame);

        // Server field, initialized to last server entered
        serverField = new JTextField(lastServer);
        serverField.selectAll();
        addTextFieldListeners(serverField, true);
        addRow(Translator.get("server_connect_dialog.server"), serverField, 15);

        // Initial directory field, initialized to "/"
        initialDirField = new JTextField(lastInitialDir);
        initialDirField.selectAll();
        addTextFieldListeners(initialDirField, true);
        addRow(Translator.get("server_connect_dialog.initial_dir"), initialDirField, 5);

        // Port field, initialized to last port
        portSpinner = createPortSpinner(lastPort);
        addRow(Translator.get("server_connect_dialog.port"), portSpinner, 15);
    }


    private void updateValues() {
        lastServer = serverField.getText();
        lastInitialDir = initialDirField.getText();
        lastPort = (Integer) portSpinner.getValue();
    }


    ////////////////////////////////
    // ServerPanel implementation //
    ////////////////////////////////

    @Override
    FileURL getServerURL() throws MalformedURLException {
        updateValues();
        if(!lastInitialDir.startsWith("/"))
            lastInitialDir = "/"+lastInitialDir;

        FileURL url = FileURL.getFileURL(FileProtocols.QFS+"://"+lastServer+lastInitialDir);
        url.setPort(lastPort);

        return url;
    }

    @Override
    boolean usesCredentials() {
        return false;
    }

    @Override
    public void dialogValidated() {
        // Commits the current spinner value in case it was being edited and 'enter' was pressed
        // (the spinner value would otherwise not be committed)
        try { portSpinner.commitEdit(); }
        catch(ParseException e) { }

        updateValues();
    }
}
