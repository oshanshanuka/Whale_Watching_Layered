package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.DashboardCommonDto;

import java.sql.SQLException;

public interface DashboardBO {
    public DashboardCommonDto getDashboardData() throws SQLException, ClassNotFoundException;
}

