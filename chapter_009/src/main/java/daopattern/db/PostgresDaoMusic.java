package daopattern.db;

import daopattern.entity.Music;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD сущности жанр музыки.
 * БД PostgreSQL
 */
public class PostgresDaoMusic implements DaoEntity<Music> {

    private Connection conn;

    PostgresDaoMusic() {
        this.conn = PostgresConnection.INSTANCE.getConnection();
    }

    @Override
    public Music getById(int id) {
        Music music = null;

        try (PreparedStatement pst = this.conn.prepareStatement("SELECT * FROM music WHERE id = ?")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();

            music = new Music();
            music.setId(id);
            music.setMusic(rs.getString("music"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return music;
    }

    @Override
    public List<Music> getAll() {
        List<Music> list = new ArrayList<>();

        try (Statement st = this.conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM music");
            while (rs.next()) {
                Music music = new Music();
                music.setId(rs.getInt("id"));
                music.setMusic(rs.getString("music"));
                list.add(music);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void create(Music music) {
        try (PreparedStatement pst = this.conn.prepareStatement("INSERT INTO music (music) VALUES (?)")) {
            pst.setString(1, music.getMusic());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Music music) {
        try (PreparedStatement pst = this.conn.prepareStatement("UPDATE music SET music = ? WHERE id = ?")) {
            pst.setString(1, music.getMusic());
            pst.setInt(2, music.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Music music) {
        int id = music.getId();
        if (!this.musicInUse(id)) {
            try (PreparedStatement pst = this.conn.prepareStatement("DELETE FROM music WHERE id = ?")) {
                pst.setInt(1, id);
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Проверка на то, что жанр музыки используется пользователем.
     * @param music жанр музыки
     * @return true, если используется
     */
    private boolean musicInUse(int music) {
        boolean result = false;

        try (PreparedStatement pst = this.conn.prepareStatement("SELECT * FROM user_music WHERE music_id = ?")) {
            pst.setInt(1, music);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
