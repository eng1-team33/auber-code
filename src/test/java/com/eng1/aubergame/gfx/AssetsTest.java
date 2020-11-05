package com.eng1.aubergame.gfx;

import org.junit.Test;

import java.awt.image.BufferedImage;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;


public class AssetsTest {

    @Test
    public void playerImageCanBeFound() {
        Assets.init();
        assertThat(Assets.player, notNullValue());
    }
}
