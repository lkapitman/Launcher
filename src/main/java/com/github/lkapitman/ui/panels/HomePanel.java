package com.github.lkapitman.ui.panels;

import com.github.lkapitman.Main;
import com.github.lkapitman.file.FileManager;
import com.github.lkapitman.ui.PanelManager;
import com.github.lkapitman.ui.panel.Panel;
import com.sun.webkit.WebPage;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.arinonia.arilibfx.ui.component.AProgressBar;
import javafx.collections.ListChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import jdk.nashorn.internal.runtime.Version;
import org.to2mbn.jmccc.mcdownloader.MinecraftDownloader;
import org.to2mbn.jmccc.mcdownloader.MinecraftDownloaderBuilder;
import org.to2mbn.jmccc.mcdownloader.download.DownloadCallback;
import org.to2mbn.jmccc.mcdownloader.download.DownloadTask;
import org.to2mbn.jmccc.mcdownloader.download.concurrent.CallbackAdapter;
import org.to2mbn.jmccc.mcdownloader.provider.forge.ForgeDownloadProvider;
import org.to2mbn.jmccc.mcdownloader.provider.forge.ForgeVersionList;
import org.to2mbn.jmccc.mcdownloader.provider.liteloader.LiteloaderDownloadProvider;
import org.to2mbn.jmccc.mcdownloader.provider.liteloader.LiteloaderVersionList;
import org.to2mbn.jmccc.option.MinecraftDirectory;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Set;

public class HomePanel extends Panel {

    private GridPane centerPane = new GridPane();
    private AProgressBar leftDownloadBar;


    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        ColumnConstraints menuPointConstrains = new ColumnConstraints();
        menuPointConstrains.setHalignment(HPos.LEFT);
        menuPointConstrains.setMinWidth(300);
        menuPointConstrains.setMaxWidth(300);
        this.layout.getColumnConstraints().addAll(menuPointConstrains, new ColumnConstraints());

        GridPane leftBarPane = new GridPane();

        GridPane.setVgrow(leftBarPane, Priority.ALWAYS);
        GridPane.setHgrow(leftBarPane, Priority.ALWAYS);

        Separator rightSeparator = new Separator();
        GridPane.setVgrow(rightSeparator, Priority.ALWAYS);
        GridPane.setHgrow(rightSeparator, Priority.ALWAYS);
        GridPane.setHalignment(rightSeparator, HPos.RIGHT);
        rightSeparator.setOrientation(Orientation.VERTICAL);
        rightSeparator.setTranslateY(1);
        rightSeparator.setTranslateX(4);
        rightSeparator.setMinWidth(2);
        rightSeparator.setMaxWidth(2);
        rightSeparator.setOpacity(0.30D);

        GridPane buttonGridPane = new GridPane();

        GridPane.setVgrow(buttonGridPane, Priority.ALWAYS);
        GridPane.setHgrow(buttonGridPane, Priority.ALWAYS);
        GridPane.setHalignment(buttonGridPane, HPos.LEFT);
        GridPane.setValignment(buttonGridPane, VPos.TOP);
        buttonGridPane.setTranslateY(30);
        buttonGridPane.setMinHeight(40);
        buttonGridPane.setMaxHeight(40);
        buttonGridPane.setMinWidth(300);
        buttonGridPane.setMaxWidth(300);
        //TODO: SHOW LEFT BAR
        showleftBar(buttonGridPane);
        leftBarPane.getChildren().addAll(rightSeparator, buttonGridPane);
        this.layout.add(leftBarPane,0,0);
        this.layout.add(this.centerPane, 1,0);

        GridPane.setVgrow(centerPane, Priority.ALWAYS);
        GridPane.setHgrow(centerPane, Priority.ALWAYS);

        ScrollPane scrollPane = new ScrollPane();
        GridPane.setVgrow(scrollPane, Priority.ALWAYS);
        GridPane.setHgrow(scrollPane, Priority.ALWAYS);
        scrollPane.getStylesheets().addAll(Main.class.getResource("/css/scrollbar.css").toExternalForm());

        VBox vBox = new VBox();
        GridPane.setVgrow(vBox, Priority.ALWAYS);
        GridPane.setHgrow(vBox, Priority.ALWAYS);
        vBox.setMinHeight(200);
        vBox.setMinWidth(900);
        vBox.setMaxWidth(900);
        vBox.setAlignment(Pos.CENTER);
        vBox.setTranslateX(30);

        GridPane topPanel = new GridPane();
        GridPane.setVgrow(topPanel, Priority.ALWAYS);
        GridPane.setHgrow(topPanel, Priority.ALWAYS);
        GridPane.setValignment(topPanel, VPos.TOP);
        topPanel.setMaxWidth(880);
        topPanel.setMinWidth(880);
        topPanel.setMaxHeight(340);
        topPanel.setMinHeight(340);
        addTopPanel(topPanel);

        this.centerPane.getChildren().add(scrollPane);
        scrollPane.setContent(vBox);
        vBox.getChildren().add(0, topPanel);

    }

    private void addTopPanel(GridPane pane) {
        Label valkyriaTitle = new Label("Heaven Project");
        GridPane.setVgrow(valkyriaTitle, Priority.ALWAYS);
        GridPane.setHgrow(valkyriaTitle, Priority.ALWAYS);
        GridPane.setValignment(valkyriaTitle, VPos.TOP);
        valkyriaTitle.setStyle("-fx-font-size: 26px; -fx-text-fill:  #fff; -fx-font-weight: bold;");
        valkyriaTitle.setTranslateY(20);

        Label rolePlay = new Label("RolePlay");
        GridPane.setVgrow(rolePlay, Priority.ALWAYS);
        GridPane.setHgrow(rolePlay, Priority.ALWAYS);
        GridPane.setValignment(rolePlay, VPos.TOP);
        rolePlay.setStyle("-fx-font-size: 14px; -fx-text-fill:  #fff; -fx-opacity: 70%;");
        rolePlay.setTranslateY(70);

        Label complet = new Label("Complete");
        GridPane.setVgrow(complet, Priority.ALWAYS);
        GridPane.setHgrow(complet, Priority.ALWAYS);
        GridPane.setValignment(complet, VPos.TOP);
        complet.setStyle("-fx-font-size: 14px; -fx-text-fill:  #fff; -fx-opacity: 70%;");
        complet.setTranslateY(70);
        complet.setTranslateX(80);

        Label desc = new Label("Какое-то описание сервера...");
        GridPane.setVgrow(desc, Priority.ALWAYS);
        GridPane.setHgrow(desc, Priority.ALWAYS);
        GridPane.setValignment(desc, VPos.TOP);
        desc.setStyle("-fx-font-size: 14px; -fx-text-fill: #bcc6e7; -fx-opacity: 70%;");
        desc.setTranslateY(130);

        GridPane bigVideo = new GridPane();

        GridPane.setVgrow(bigVideo, Priority.ALWAYS);
        GridPane.setHgrow(bigVideo, Priority.ALWAYS);
        GridPane.setValignment(bigVideo, VPos.TOP);
        GridPane.setHalignment(bigVideo, HPos.RIGHT);
        bigVideo.setMinWidth(430);
        bigVideo.setMaxWidth(430);
        bigVideo.setMinHeight(320);
        bigVideo.setMaxHeight(320);

        String content_url =
                "<iframe style='background : rgba(0,0,0,0);' width=\"420\" height=\"320\"" +
                " src=\"http://arinonia.chaun14.fr/pages/video.html\" frameborder=\"0\" allow=\"accelerometer; autoplay=1; encrypted-media; gyroscope; picture-in-picture\"" +
                " allowfullscreen></iframe>";
        WebView webView = new WebView();
        webView.setStyle("overflow-x: hidden; overflow-y: hidden");

        WebEngine webEngine = webView.getEngine();

        webEngine.loadContent(content_url);

        bigVideo.getChildren().addAll(webView);
        webView.getChildrenUnmodifiable().addListener((ListChangeListener< Node >) change -> {
            Set<Node> deadSeaScrolls = webView.lookupAll(".scroll-bar");
            for (Node scroll : deadSeaScrolls) {
                scroll.setVisible(false);
            }
        });

        try {
            Field field = webEngine.getClass().getDeclaredField("page");
            field.setAccessible(true);
            WebPage page = (WebPage)field.get(webEngine);
            SwingUtilities.invokeLater(() -> {
                page.setBackgroundColor(new Color(255, 255, 255, 0).getRGB());
            });
        } catch (Exception e) {

        }

        Button installButton = new Button("Установить!");
        GridPane.setVgrow(installButton, Priority.ALWAYS);
        GridPane.setHgrow(installButton, Priority.ALWAYS);
        GridPane.setValignment(installButton, VPos.TOP);
        GridPane.setHalignment(installButton, HPos.LEFT);
        installButton.setTranslateY(260);
        installButton.setMinWidth(140);
        installButton.setMaxHeight(40);
        installButton.setStyle("-fx-background-color: #115ffa; -fx-border-radius: 0; -fx-background-insets: 0; -fx-font-size: 14px; -fx-text-fill: #fff; ");

        installButton.setOnMouseEntered(e->this.layout.setCursor(Cursor.HAND));
        installButton.setOnMouseExited(e->this.layout.setCursor(Cursor.DEFAULT));
        installButton.setOnMouseClicked(e-> {
            // TODO Install
        });

        Button settingsButton = new Button();
        GridPane.setVgrow(settingsButton, Priority.ALWAYS);
        GridPane.setHgrow(settingsButton, Priority.ALWAYS);
        GridPane.setValignment(settingsButton, VPos.TOP);
        GridPane.setHalignment(settingsButton, HPos.LEFT);
        MaterialDesignIconView settingIcon = new MaterialDesignIconView(MaterialDesignIcon.SETTINGS);
        settingIcon.setSize("18px");
        settingIcon.setFill(javafx.scene.paint.Color.rgb(17,95,170));
        settingsButton.setStyle("-fx-background-color:  rgb(255,255,255,0.0); -fx-border-color: #115faa; -fx-border-radius: 2px;");
        settingsButton.setTranslateX(150);
        settingsButton.setTranslateY(266);
        settingsButton.setMinWidth(26);
        settingsButton.setMaxWidth(26);
        settingsButton.setMinHeight(26);
        settingsButton.setMaxHeight(26);
        settingsButton.setGraphic(settingIcon);

        settingsButton.setOnMouseEntered(e->this.layout.setCursor(Cursor.HAND));
        settingsButton.setOnMouseExited(e->this.layout.setCursor(Cursor.DEFAULT));
        settingsButton.setOnMouseClicked(e-> {
            // TODO Settings
        });

        AProgressBar abigDownloadBar = new AProgressBar(400,20);
        abigDownloadBar.setBackgroundColor(javafx.scene.paint.Color.rgb(3,48,90));
        Stop[] stops = new Stop[]{new Stop(0, javafx.scene.paint.Color.rgb(7,85,136)), new Stop(1, javafx.scene.paint.Color.rgb(3,163,219))};

        LinearGradient lg = new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE, stops);
        abigDownloadBar.setForegroundColor(lg);
        abigDownloadBar.setTranslateY(150);
        abigDownloadBar.setProgress(100, 150);
        
        pane.getChildren().addAll(valkyriaTitle, rolePlay, complet, desc, bigVideo, installButton, settingsButton, abigDownloadBar);
    }

    private void showleftBar(GridPane pane) {
        Separator blueLeftSeparator = new Separator();
        GridPane.setVgrow(blueLeftSeparator, Priority.ALWAYS);
        GridPane.setHgrow(blueLeftSeparator, Priority.ALWAYS);
        blueLeftSeparator.setOrientation(Orientation.VERTICAL);
        blueLeftSeparator.setMinWidth(3);
        blueLeftSeparator.setMaxWidth(3);
        blueLeftSeparator.setMinHeight(40);
        blueLeftSeparator.setMaxHeight(40);
        blueLeftSeparator.setStyle("-fx-background-color: rgb(5,179,242); -fx-border-width: 3 3 3 0; -fx-border-color: rgb(5,179,242)");
        Image logoImage = new Image(Main.class.getResource("/valkyria.png").toExternalForm());
        ImageView imageViewLogo = new ImageView(logoImage);

        GridPane.setHgrow(imageViewLogo, Priority.ALWAYS);
        GridPane.setVgrow(imageViewLogo, Priority.ALWAYS);
        GridPane.setValignment(imageViewLogo, VPos.CENTER);
        imageViewLogo.setTranslateX(34);
        imageViewLogo.setFitHeight(28);
        imageViewLogo.setFitWidth(28);

        Label valkyria = new Label("Heaven");
        GridPane.setHgrow(valkyria, Priority.ALWAYS);
        GridPane.setVgrow(valkyria, Priority.ALWAYS);
        GridPane.setValignment(valkyria, VPos.CENTER);
        valkyria.setTranslateX(90);
        valkyria.setStyle("-fx-font-size: 16px; -fx-text-fill: #fff;");

        leftDownloadBar = new AProgressBar(170,3);
        leftDownloadBar.setBackgroundColor(javafx.scene.paint.Color.rgb(222,222,222, 0.3d));
        leftDownloadBar.setForegroundColor(javafx.scene.paint.Color.rgb(255,255,255));
        leftDownloadBar.setTranslateX(90.0d);
        leftDownloadBar.setTranslateY(12.0d);
        leftDownloadBar.setProgress(24, 142);

        pane.getChildren().addAll(blueLeftSeparator, imageViewLogo, valkyria, leftDownloadBar);
    }

}
