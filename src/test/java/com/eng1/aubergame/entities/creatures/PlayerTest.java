package com.eng1.aubergame.entities.creatures;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.input.KeyManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {

    @Mock
    private Game game;
    @Mock
    private KeyManager keyManager;

    Player player;

    @Before
    public void setup() {
        player = new Player(game, 100, 100);
        when(game.getKeyManager()).thenReturn(keyManager);
    }

    @Test
    public void upKeyMovesPlayerUpFour() {

        when(keyManager.isUpPressed()).thenReturn(true);

        player.update();
        assertThat(player.getX(), equalTo(100.0F));
        assertThat(player.getY(), equalTo(96.0F));
    }

    @Test
    public void upKeyMovesPlayerDownFour() {

        when(keyManager.isDownPressed()).thenReturn(true);

        player.update();
        assertThat(player.getX(), equalTo(100.0F));
        assertThat(player.getY(), equalTo(104.0F));
    }

    @Test
    public void upKeyMovesPlayerLeftFour() {

        when(keyManager.isLeftPressed()).thenReturn(true);

        player.update();
        assertThat(player.getX(), equalTo(96.0F));
        assertThat(player.getY(), equalTo(100.0F));
    }

    @Test
    public void upKeyMovesPlayerRightFour() {

        when(keyManager.isRightPressed()).thenReturn(true);

        player.update();
        assertThat(player.getX(), equalTo(104.0F));
        assertThat(player.getY(), equalTo(100.0F));
    }
}
