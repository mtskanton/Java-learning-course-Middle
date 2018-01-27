package daopattern.entity;

/**
 * Сущность - жанр музыки.
 */
public class Music {

    private int id;
    private String music;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }
}
