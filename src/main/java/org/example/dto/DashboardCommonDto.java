package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DashboardCommonDto {
    private int allCustomerCount;
    private int allEmployeeCount;
    private int allBoatsCount;
    private int allBookingCount;
    private int NormalBookingCount;
    private int LuxuryBookingCount;
}

