package com.musikouyi.jzframe.domain.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
//@AllArgsConstructor
public enum BusinessTypeCodeEnum {
    /**
	 * 文章
	 */
	ARTICLE,

    /**
	 * 相册
	 */
	ALBUM;

	private Integer code;
	private String msg;

	/**
	 * from code to msg
	 * @param code
	 * @return
	 */
	public static String fromCode(Integer code){
		for (BusinessTypeCodeEnum businessTypeCodeEnum: BusinessTypeCodeEnum.values()) {
			if(businessTypeCodeEnum.code.equals(code)){
				return businessTypeCodeEnum.msg;
			}
		}
		return null;
	}

	/**
	 * from msg to code
	 * @param msg
	 * @return
	 */
	public static Integer toCode(String msg) {
		for (BusinessTypeCodeEnum businessTypeCodeEnum : BusinessTypeCodeEnum.values()) {
			if (businessTypeCodeEnum.msg.equals(msg)) {
				return businessTypeCodeEnum.code;
			}
		}
		return null;
	}

}
