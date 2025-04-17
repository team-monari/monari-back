package com.monari.monariback.common.constant;

import org.springframework.http.MediaType;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MediaTypeConstant {
	public static final MediaType DEFAULT_MEDIA_TYPE = MediaType.APPLICATION_OCTET_STREAM;
	public static final String EXT_PNG = "png";
	public static final String EXT_JPG = "jpg";
	public static final String EXT_JPEG = "jpeg";
	public static final String EXT_GIF = "gif";
	public static final String EXT_BMP = "bmp";
	public static final String EXT_WEBP = "webp";
	public static final MediaType MEDIA_TYPE_BMP = MediaType.parseMediaType("image/bmp");
	public static final MediaType MEDIA_TYPE_WEBP = MediaType.parseMediaType("image/webp");
}
