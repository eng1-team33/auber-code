package com.eng1.aubergame.gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {

    public static Font loadFont(InputStream path, float size){
        try {
            return (Font.createFont(Font.TRUETYPE_FONT, path)).deriveFont(size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
