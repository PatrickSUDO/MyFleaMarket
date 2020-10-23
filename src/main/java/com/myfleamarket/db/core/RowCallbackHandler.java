package com.zhijieketang.db.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Process result set object
 */
public interface RowCallbackHandler {
    void processRow(ResultSet rs) throws SQLException;
}
