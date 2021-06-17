package com.jb.playground;

import com.jb.db.DatabaseManager;

import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws SQLException {

        System.out.println("START");

        DatabaseManager.DropAndCreate();

        System.out.println("END");

    }
}
