package com.example.estateagency1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail  implements Serializable {
    private String user_receiver;
    private String subject;
    private String title;
    private String content;
}
