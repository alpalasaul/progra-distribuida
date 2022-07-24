package com.krypton.service;

import com.krypton.config.DatabaseConfig;
import com.krypton.db.Album;
import com.krypton.db.Singer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@ApplicationScoped
public class SingerServiceImpl implements SingerService {

    @Inject
    DataSource dataSource;

    @Override
    public List<Singer> findAll() {

        Connection con = null;

        List<Singer> ret = new ArrayList<>();

        try {
            con = dataSource.getConnection();

            PreparedStatement pstmt = con.prepareStatement("select * from singer order by first_name");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Singer obj = new Singer();

                transformData(con, obj, rs);

                ret.add(obj);
            }

            rs.close();
            pstmt.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException sqex) {
                throw new RuntimeException(sqex);
            }
        }

        return ret;
    }

    @Override
    public Singer findById(Integer id) {

        Connection con = null;

        Singer singer = new Singer();

        try {
            con = dataSource.getConnection();

            PreparedStatement pstmt = con.prepareStatement("select * from singer where id =?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                transformData(con, singer, rs);
            }

            rs.close();
            pstmt.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException sqex) {
                throw new RuntimeException(sqex);
            }
        }

        return singer;
    }

    private void transformData(Connection con, Singer singer, ResultSet rs) throws SQLException {
        singer.setId(rs.getInt("id"));
        singer.setFirsName(rs.getString("first_name"));
        singer.setLastName(rs.getString("last_name"));
        singer.setBirthDate(rs.getDate("birth_date"));

        PreparedStatement pstmt2 = con.prepareStatement("select * from album where singer_id =?");
        pstmt2.setInt(1, singer.getId());
        ResultSet rs2 = pstmt2.executeQuery();

        Set<Album> albums = new HashSet<>();
        while (rs2.next()) {
            Album album = new Album();
            album.setId(rs2.getInt("id"));
//            album.setSinger_id(rs2.getInt("singer_id"));
            album.setTitle(rs2.getString("title"));
            album.setRelease_date(rs2.getDate("release_date"));
            albums.add(album);
        }
        singer.setAlbum(albums);
    }

    @Override
    public void insert(Singer singer) {
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement pstmt =
                    con.prepareStatement("insert into singer (first_name, last_name, birth_date) values (?, ?, ?)");
//            pstmt.setInt(1, singer.getId());
            pstmt.setString(1, singer.getFirsName());
            pstmt.setString(2, singer.getLastName());
            pstmt.setDate(3, new Date(singer.getBirthDate().getTime()));

            int rowsInserted = pstmt.executeUpdate();

            pstmt.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException sqex) {
                throw new RuntimeException(sqex);
            }
        }
    }

    @Override
    public void save(Integer id, Singer singer) {
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement pstmt =
                    con.prepareStatement("update singer set first_name=?, last_name=?, birth_date=? where id=?");
            pstmt.setString(1, singer.getFirsName());
            pstmt.setString(2, singer.getLastName());
            pstmt.setDate(3, new Date(singer.getBirthDate().getTime()));
            pstmt.setInt(4, id);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated == 0) throw new SQLException(
                    String.format("Error al actualizar. No existe el id %s", id));

            pstmt.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException sqex) {
                throw new RuntimeException(sqex);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement pstmt =
                    con.prepareStatement("delete from singer where id =?");
            pstmt.setInt(1, id);

            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted == 0) throw new SQLException(
                    String.format("Error al eliminar. No existe el id %s", id));

            pstmt.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException sqex) {
                throw new RuntimeException(sqex);
            }
        }
    }
}
