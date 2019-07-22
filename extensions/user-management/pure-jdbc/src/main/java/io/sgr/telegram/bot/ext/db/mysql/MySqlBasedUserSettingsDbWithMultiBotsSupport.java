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

import io.sgr.telegram.bot.api.utils.Preconditions;
import io.sgr.telegram.bot.ext.db.UserSettingsWithMultiBotsSupport;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

/**
 * @author SgrAlpha
 */
public abstract class MySqlBasedUserSettingsDbWithMultiBotsSupport implements UserSettingsWithMultiBotsSupport {

    @Override
    public void saveRobotSettings(long botId, long userId, String propKey, String propValue) {
        if (Preconditions.isEmptyString(propKey) || Preconditions.isEmptyString(propValue)) {
            return;
        }
        String sql = "INSERT INTO " + getTableName() + "(bot_id, user_id, prop_key, prop_value)"
                + " VALUES(?, ?, ?, ?) ON DUPLICATE KEY UPDATE prop_value = VALUES(prop_value)";
        try (
                Connection conn = this.getDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setLong(1, botId);
            ps.setLong(2, userId);
            ps.setString(3, propKey);
            ps.setString(4, propValue);
            ps.executeUpdate();
        } catch (SQLException e) {
            getLogger().error(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, String> loadRobotSettings(long botId, long userId) {
        Map<String, String> props = new HashMap<>();
        String sql = "SELECT prop_key, prop_value FROM " + getTableName() + " WHERE user_id = ? AND bot_id = ? AND prop_key = ?";
        try (
                Connection conn = this.getDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setLong(1, botId);
            ps.setLong(2, userId);
            try (
                    ResultSet rs = ps.executeQuery();
            ) {
                while (rs.next()) {
                    props.put(rs.getString("prop_key"), rs.getString("prop_value"));
                }
            }
        } catch (SQLException e) {
            getLogger().error(e.getMessage(), e);
        }
        return props;
    }

    @Override
    public String getRobotSettings(long botId, long userId, String propKey) {
        if (Preconditions.isEmptyString(propKey)) {
            return null;
        }
        String sql = "SELECT prop_value FROM " + getTableName() + " WHERE bot_id = ? AND user_id = ? AND prop_key = ?";
        try (
                Connection conn = this.getDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setLong(1, botId);
            ps.setLong(2, userId);
            ps.setString(3, propKey);
            try (
                    ResultSet rs = ps.executeQuery()
            ) {
                if (rs.next()) {
                    return rs.getString("prop_value");
                }
            }
        } catch (SQLException e) {
            getLogger().error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void deleteRobotSettings(long botId, long userId) {
        String sql = "DELETE FROM " + getTableName() + " WHERE bot_id = ? AND user_id = ?";
        try (
                Connection conn = this.getDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setLong(1, botId);
            ps.setLong(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            getLogger().error(e.getMessage(), e);
        }
    }

    @Override
    public void deleteRobotSettings(long botId, long userId, String propKey) {
        if (Preconditions.isEmptyString(propKey)) {
            return;
        }
        String sql = "DELETE FROM " + getTableName() + " WHERE bot_id = ? AND user_id = ? AND prop_key = ?";
        try (
                Connection conn = this.getDataSource().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setLong(1, botId);
            ps.setLong(2, userId);
            ps.setString(3, propKey);
            ps.executeUpdate();
        } catch (SQLException e) {
            getLogger().error(e.getMessage(), e);
        }
    }

    protected abstract DataSource getDataSource();

    protected abstract String getTableName();

    protected abstract Logger getLogger();

}
