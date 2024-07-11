package org.example.dto.tm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomerTm {
    private String cus_Id;
    private String name;
    private String nic;
    private String address;
    private String e_mail;
    private String tel;
}
