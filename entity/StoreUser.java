package kr.co.maptics.mapticslogin.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class StoreUser {

	@Id
	@JsonProperty("userNo")
	private Integer storeUserSeq;

	@JsonProperty("storeCode")
	private Integer storeSeq;

	@JsonProperty("id")
	private String userId;

	@JsonProperty("name")
	private String userName;

	private String password;

	@JsonProperty("email")
	private String userEmail;

	private Integer storeGroupSeq;
	private String appCode;
	private String phone;
	private String telecom;
	private String sex;
	private String birth;
	private String authInfo;
	private String status;
	private String grade;
	private Boolean marketingPush;
	private Boolean marketingSms;
	private Boolean marketingEmail;
	private Boolean marketingPhone;
	private LocalDateTime regDate;



}
