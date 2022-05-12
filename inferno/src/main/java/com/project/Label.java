package com.project;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {

    Label(String text,int size, Color color){
        super(text,SwingConstants.CENTER);
        this.setText(text);
        this.setFont(new Font("",Font.PLAIN,size));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setForeground(color);
    }
}
