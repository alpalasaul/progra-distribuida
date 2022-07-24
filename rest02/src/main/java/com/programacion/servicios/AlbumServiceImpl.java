package com.programacion.servicios;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.programacion.db.Album;
import com.programacion.db.Singer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class AlbumServiceImpl implements AlbumService {

    @Inject
    private DataSource dataSource;

    @Override
    public List<Album> findAll() {
        Connection con = null;

        List<Album> ret = new ArrayList<>();

        try {
            con = dataSource.getConnection();

            PreparedStatement pstmt = con.prepareStatement("select * from album order by title");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Album alb = new Album();
                alb.setId(rs.getInt("id"));
                alb.setTitle(rs.getString("title"));
                alb.setRelease_date(rs.getDate("release_date"));

                ret.add(alb);
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
    public Album findById(Integer id) {
        Connection con = null;

        Album alb = new Album();

        try {
            con = dataSource.getConnection();

            PreparedStatement pstmt = con.prepareStatement("select * from album where id =?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                alb.setId(rs.getInt("id"));
                alb.setTitle(rs.getString("title"));
                alb.setRelease_date(rs.getDate("release_date"));
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

        return alb;
    }

    @Override
    public void insert(ObjectNode item) {
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement pstmt =
                    con.prepareStatement("insert into album (singer_id, title, release_date) values (?, ?, ?)");
            pstmt.setInt(1, item.get("singer_id").asInt());
            pstmt.setString(2, item.get("title").asText());
            pstmt.setDate(3, new Date(item.get("release_date").asLong()));

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
    public void save(Integer id, ObjectNode item) {
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement pstmt =
                    con.prepareStatement("update album set title=?, release_date=?, singer_id=? where id=?");
            pstmt.setString(1, item.get("title").asText());
            pstmt.setDate(2, new Date(item.get("release_date").asLong()));
            pstmt.setInt(3, item.get("singer_id").asInt());
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
                    con.prepareStatement("delete from album where id =?");
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
