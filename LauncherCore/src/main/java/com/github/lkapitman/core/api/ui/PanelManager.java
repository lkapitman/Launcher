package com.github.lkapitman.core.api.ui;

import com.github.lkapitman.core.Core;
import com.github.lkapitman.core.api.Constants;
import com.github.lkapitman.visual.elements.background.ResizeHelper;
import com.github.lkapitman.core.api.ui.panel.IPanel;
import javafx.application.Platform;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The type Panel manager.
 */
public class PanelManager {

    private final Core core;
    private final Stage stage;
    private final GridPane centerPanel = new GridPane();

    private int stateWindow = 1;

    private TrayIcon trayIcon;

    /**
     * Instantiates a new Panel manager.
     *
     * @param core  the core
     * @param stage the stage
     */
    public PanelManager(Core core, Stage stage) {
        this.core = core;
        this.stage = stage;
    }

    /**
     * Init.
     */
    public void init() {
        this.stage.setResizable(false);
        this.stage.getIcons().add(new javafx.scene.image.Image(Core.class.getResource("/visual/img/icon.png").toExternalForm()));

        if (!SystemTray.isSupported()) {
            System.err.println(Core.getRes().getString("javafx.error.not.support"));
            return;
        }

        URL url = null;
        try {
            url = getURL("https://i.ibb.co/VB8Bkmq/icon.png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Image image = Toolkit.getDefaultToolkit().getImage(url);

        final PopupMenu popup = new PopupMenu();

        trayIcon = new TrayIcon(image, Constants.PROJECT_NAME, popup);

        final MenuItem exit = new MenuItem(Core.getRes().getString("javafx.menu.item.exit"));
        final SystemTray tray = SystemTray.getSystemTray();
        ActionListener menuActionListener = e -> System.exit(0);

        exit.addActionListener(menuActionListener);
        popup.add(exit);

        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON1) {
                    Platform.runLater(() -> {
                        if (stateWindow == 1) {
                            stage.hide();
                            stateWindow = 0;
                        } else if (stateWindow == 0) {
                            stage.show();
                            stateWindow = 1;
                        }
                    });
                }
            }
        });

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println(Core.getRes().getString("javafx.error.tray.icon"));
        }

        Platform.setImplicitExit(false);

        this.stage.setTitle(Constants.PROJECT_NAME);
        this.stage.setWidth(1613);
        this.stage.setMinWidth(1612);
        this.stage.setHeight(870);
        this.stage.setMinHeight(869);

        this.stage.initStyle(StageStyle.DECORATED);
        this.stage.centerOnScreen();

        this.stage.show();

        GridPane layout = new GridPane();
        layout.setStyle(
                "-fx-background-image: url('https://i.ibb.co/xMMhgBx/bg.png');"
                +"-fx-backgound-repeat: skretch;"+"-fx-backgound-position: center center;"
                +"-fx-background-size: cover;");
        this.stage.setScene(new Scene(layout));

        RowConstraints topPanelConstrains = new RowConstraints();
        topPanelConstrains.setValignment(VPos.TOP);

        topPanelConstrains.setMinHeight(25);
        topPanelConstrains.setMaxHeight(25);

        layout.getRowConstraints().addAll(topPanelConstrains, new RowConstraints());

        layout.add(centerPanel, 0,1);

        GridPane.setVgrow(this.centerPanel, Priority.ALWAYS);
        GridPane.setHgrow(this.centerPanel, Priority.ALWAYS);

        ResizeHelper.addResizeListener(this.stage);


    }

    /**
     * Show panel.
     *
     * @param panel the panel
     */
    public void showPanel(IPanel panel) {
        this.centerPanel.getChildren().clear();
        this.centerPanel.getChildren().add(panel.getLayout());
        panel.init(this);
        panel.onShow();
    }

    /**
     * Gets core.
     *
     * @return the core
     */
    public Core getCore() {
        return core;
    }

    /**
     * Gets tray icon.
     *
     * @return the tray icon
     */
    public TrayIcon getTrayIcon() {
        return trayIcon;
    }

    /**
     * Gets url.
     *
     * @param url the url
     * @return the url
     * @throws MalformedURLException the malformed url exception
     */
    public URL getURL(String url) throws MalformedURLException {
        return new URL(url);
    }
}
