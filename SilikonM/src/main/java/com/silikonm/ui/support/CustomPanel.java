package com.silikonm.ui.support;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {
    String imageFile = "/com/silikonm/core/splash_sc.png";

    public CustomPanel() {
        super();
    }

    public void setBackgroundImage(String bgImage) {
        this.imageFile = bgImage;
    }

    public CustomPanel(String image) {
        super();
        this.imageFile = image;
    }

    public void paintComponent(Graphics g) {
        ImageIcon imageicon = new ImageIcon(getClass().getResource(imageFile));
        Image image = imageicon.getImage();

        super.paintComponent(g);

        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
