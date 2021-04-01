package com.zlx.entry;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@ToString
@Setter
@Getter
public class User implements Serializable {

    private static final long serialVersionUID = 8018687997849041775L;

    private long id;
    private String username;
    private String password;

}
