module voogasalad_WorksDontTouch {
    requires java.xml;
    requires java.desktop;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.web;

    exports voogasalad;
    exports gae_gui.gae_Tower;
}
