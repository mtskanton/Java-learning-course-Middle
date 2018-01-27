package daopattern.db;

import daopattern.entity.Music;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Тест CRUD сущности - жанр музыки.
 */
public class PostgresDaoMusicTest {

    private PostgresDaoMusic pdm = new PostgresDaoMusic();

    @Before
    public void createTables() {
        PostgresConnection.INSTANCE.createTables();
    }

    @Test
    public void whenGetMusicByIdThenMusicNameIsRock() {
        Music music = pdm.getById(1);
        assertThat(music.getId(), is(1));
        assertThat(music.getMusic(), is("ROCK"));
    }

    @Test
    public void whenGetAllMusicThenListOfExists() {
        List<Music> list = pdm.getAll();
        assertThat(list.size(), is(3));
        assertThat(list.get(0).getMusic(), is("ROCK"));
        assertThat(list.get(1).getMusic(), is("RAP"));
        assertThat(list.get(2).getMusic(), is("POP"));
    }

    @Test
    public void whenCreateMusicThenAddedToList() {
        Music music = new Music();
        music.setMusic("DANCE");
        pdm.create(music);
        assertThat(pdm.getById(4).getMusic(), is("DANCE"));
    }

    @Test
    public void whenUpdateMusicThenUpdatedInTheList() {
        Music music = pdm.getById(2);
        music.setMusic("ROMANCE");
        pdm.update(music);
        assertThat(pdm.getById(2).getMusic(), is("ROMANCE"));
    }

        @Test
    public void whenDeleteMusicThenNoMoreInTheList() {
        Music music = new Music();
        music.setMusic("FOLK");
        pdm.create(music);

        Music created = pdm.getById(4);
        assertThat(created.getMusic(), is("FOLK"));

        pdm.delete(created);
        List<Music> list = pdm.getAll();
        assertThat(list.get(0).getMusic(), is("ROCK"));
        assertThat(list.get(1).getMusic(), is("RAP"));
        assertThat(list.get(2).getMusic(), is("POP"));
    }
}
