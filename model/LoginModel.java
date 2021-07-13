package kr.co.maptics.mapticslogin.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LoginModel {
    private String id;
    private String password;
}
