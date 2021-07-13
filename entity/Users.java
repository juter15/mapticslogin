package kr.co.maptics.mapticslogin.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Users {

	@Id
	@JsonProperty("userNo")
	private Integer userSeq;

	@JsonProperty("id")
	private String userId;

	@JsonProperty("name")
	private String userName;

	private String password;
	private String appCode;
	private String email;
	private String phone;
	private String birth;
	private String sex;
	private String telecom;
	private String authInfo;
	private String status;
	private String userGrade;
	private Boolean marketingPush;
	private Boolean marketingSms;
	private Boolean marketingEmail;
	private Boolean marketingPhone;
	private LocalDateTime regDate;

}
