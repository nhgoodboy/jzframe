package com.musikouyi.jzframe.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统文件列表请求条件
 * @author Jim
 *
 */
@Getter
@Setter
public class FileInfListReq {
	private String businessTypeCode;
	private String ifFailUse;
	private String fileNm;
	private Integer businessObjectId;

}
