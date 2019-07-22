/*
 * Copyright 2017-2019 SgrAlpha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.sgr.telegram.bot.ext.db.mysql;

import io.sgr.telegram.bot.api.models.User;
import io.sgr.telegram.bot.ext.db.UserRegistry;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * @author SgrAlpha
 */
public abstract class MySqlBasedUserRegistryDb implements UserRegistry {

    @Override
    public User getUser(long userId) {
        String sql = "SELECT id, first_name, last_name, username FROM " + getTableName() + " WHERE id = ?";
        try (
                Connection conn = this.getDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setLong(1, userId);
            try (
                    ResultSet rs = ps.executeQuery();
            ) {
                if (rs.next()) {
                    return new User(rs.getLong("id"), false, rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), null);
                }
            }
        } catch (SQLException e) {
            getLogger().error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public User getUser(String username) {
        String sql = "SELECT id, first_name, last_name, username FROM " + getTableName() + " WHERE username = ?";
        try (
                Connection conn = this.getDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, username);
            try (
                    ResultSet rs = ps.executeQuery();
            ) {
                if (rs.next()) {
                    return new User(rs.getLong("id"), false, rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), null);
                }
            }
        } catch (SQLException e) {
            getLogger().error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public User getActiveUser(long userId) {
        String sql = "SELECT id, first_name, last_name, username FROM " + getTableName() + " WHERE id = ? AND is_active = true";
        try (
                Connection conn = this.getDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setLong(1, userId);
            try (
                    ResultSet rs = ps.executeQuery();
            ) {
                if (rs.next()) {
                    return new User(rs.getLong("id"), false, rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), null);
                }
            }
        } catch (SQLException e) {
            getLogger().error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public User getActiveUser(String username) {
        String sql = "SELECT id, first_name, last_name, username FROM " + getTableName() + " WHERE username = ? AND is_active = true";
        try (
                Connection conn = this.getDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, username);
            try (
                    ResultSet rs = ps.executeQuery();
            ) {
                if (rs.next()) {
                    return new User(rs.getLong("id"), false, rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), null);
                }
            }
        } catch (SQLException e) {
            getLogger().error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public boolean addUser(User user) {
        if (user == null) {
            return false;
        }
        String sql = "INSERT INTO " + getTableName() + "(id, first_name, last_name, username) VALUES(?, ?, ?, ?)"
                + " ON DUPLICATE KEY UPDATE"
                + " first_name = VALUES(first_name), last_name = VALUES(last_name), username = VALUES(username)";
        try (
                Connection conn = this.getDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setLong(1, user.getId());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getUsername());
            return 1 == ps.executeUpdate();
        } catch (SQLException e) {
            getLogger().error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean deactivateUser(long userId) {
        String sql = "UPDATE " + getTableName() + " SET is_active = false WHERE id = ?";
        try (
                Connection conn = this.getDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setLong(1, userId);
            return 1 == ps.executeUpdate();
        } catch (SQLException e) {
            getLogger().error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean deleteUser(long userId) {
        String sql = "DELETE FROM " + getTableName() + " WHERE id = ?";
        try (
                Connection conn = this.getDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setLong(1, userId);
            return 1 == ps.executeUpdate();
        } catch (SQLException e) {
            getLogger().error(e.getMessage(), e);
        }
        return false;
    }

    protected abstract DataSource getDataSource();

    protected abstract String getTableName();

    protected abstract Logger getLogger();

}
