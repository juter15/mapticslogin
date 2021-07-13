package kr.co.maptics.mapticslogin.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@Entity
public class UsersAuth {

	@Id
	private Integer userAuthSeq;

	private Integer userSeq;
	private String appCode;
	private String authCode;
	private LocalDateTime expiredDate;
	private LocalDateTime regDate;

}
