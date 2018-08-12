/*
 * Copyright 2017-2018 SgrAlpha
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
 */
package io.sgr.telegram.bot.ext.db.mysql;

import io.sgr.telegram.bot.api.models.User;
import io.sgr.telegram.bot.ext.db.UserRegistryWithMultiBotsSupport;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * @author SgrAlpha
 */
public abstract class MySqlBasedUserRegistryDbWithMultiBotsSupport implements UserRegistryWithMultiBotsSupport {

    @Override
    public User getUser(long botId, long userId) {
        String sql = "SELECT user_id, user_first_name, user_last_name, user_username FROM " + getTableName() + " WHERE bot_id = ? AND user_id = ?";
        try (
                Connection conn = this.getDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setLong(1, botId);
            ps.setLong(2, userId);
            try (
                    ResultSet rs = ps.executeQuery();
            ) {
                if (rs.next()) {
                    return new User(rs.getLong("user_id"), false, rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_username"), null);
                }
            }
        } catch (SQLException e) {
            getLogger().error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public User getUser(long botId, String username) {
        String sql = "SELECT user_id, user_first_name, user_last_name, user_username FROM " + getTableName() + " WHERE bot_id = ? AND user_username = ?";
        try (
                Connection conn = this.getDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setLong(1, botId);
            ps.setString(2, username);
            try (
                    ResultSet rs = ps.executeQuery();
            ) {
                if (rs.next()) {
                    return new User(rs.getLong("user_id"), false, rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_username"), null);
                }
            }
        } catch (SQLException e) {
            getLogger().error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public User getActiveUser(long botId, long userId) {
        String sql = "SELECT user_id, user_first_name, user_last_name, user_username FROM " + getTableName() + " WHERE bot_id = ? AND user_id = ? AND is_active = true";
        try (
                Connection conn = this.getDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setLong(1, botId);
            ps.setLong(2, userId);
            try (
                    ResultSet rs = ps.executeQuery();
            ) {
                if (rs.next()) {
                    return new User(rs.getLong("user_id"), false, rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_username"), null);
                }
            }
        } catch (SQLException e) {
            getLogger().error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public User getActiveUser(long botId, String username) {
        String sql = "SELECT user_id, user_first_name, user_last_name, user_username FROM " + getTableName() + " WHERE bot_id = ? AND user_username = ? AND is_active = true";
        try (
                Connection conn = this.getDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setLong(1, botId);
            ps.setString(2, username);
            try (
                    ResultSet rs = ps.executeQuery();
            ) {
                if (rs.next()) {
                    return new User(rs.getLong("user_id"), false, rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_username"), null);
                }
            }
        } catch (SQLException e) {
            getLogger().error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public boolean addUser(long botId, User user) {
        if (user == null) {
            return false;
        }
        String sql = "INSERT INTO " + getTableName() + "(bot_id, user_id, user_first_name, user_last_name, user_username) VALUES(?, ?, ?, ?, ?)"
                + " ON DUPLICATE KEY UPDATE"
                + " user_first_name = VALUES(user_first_name), user_last_name = VALUES(user_last_name), user_username = VALUES(user_username)";
        try (
                Connection conn = this.getDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setLong(1, botId);
            ps.setLong(2, user.getId());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getUsername());
            return 1 == ps.executeUpdate();
        } catch (SQLException e) {
            getLogger().error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean deactivateUser(long botId, long userId) {
        String sql = "UPDATE " + getTableName() + " SET is_active = false WHERE bot_id = ? AND user_id = ?";
        try (
                Connection conn = this.getDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setLong(1, botId);
            ps.setLong(2, userId);
            return 1 == ps.executeUpdate();
        } catch (SQLException e) {
            getLogger().error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean deleteUser(long botId, long userId) {
        String sql = "DELETE FROM " + getTableName() + " WHERE bot_id = ? AND user_id = ?";
        try (
                Connection conn = this.getDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setLong(1, botId);
            ps.setLong(2, userId);
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
