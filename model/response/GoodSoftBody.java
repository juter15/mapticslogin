package kr.co.maptics.mapticslogin.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class GoodSoftBody {

	private String authCode;
	private String userName;
	private String companyName;
	private String storeCode;
	private String userNumber;
	private String userType;
	private String storeStatus;
	private String userStatus;
	private String userGrade;
	@JsonProperty("bannerList")
	private List<GoodSoftBanner> goodSoftBanners;

	private String tel;
	private String placeId;
	private String address;
	private String addressDetail;
	private String latitude;
	private String longitude;
}
