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
public class StoreUserAuth {

	@Id
	private Integer storeAuthSeq;

	private Integer storeUserSeq;
	private Integer storeSeq;
	private String appCode;
	private Integer storeGroupSeq;
	private String authCode;
	private LocalDateTime expiredDate;
	private LocalDateTime regDate;
}
