package kr.co.maptics.mapticslogin.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoodSoftBanner {
    private String bannerUrl;
    private String bannerGroupCode;
    private String isLink;
    private String linkUrl;
}
