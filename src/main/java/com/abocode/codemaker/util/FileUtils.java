package com.abocode.codemaker.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FileUtils {
public static void close(Statement stmt,Connection conn){
    try {
        if(stmt != null) {
            stmt.close();
            stmt = null;
            System.gc();
        }

        if(conn != null) {
            conn.close();
            conn = null;
            System.gc();
        }
    } catch (SQLException var14) {
        throw new RuntimeException(var14.getMessage());
    }
}
}
