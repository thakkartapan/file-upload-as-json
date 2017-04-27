package com.thakkartapan.fileupload.api;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thakkartapan.fileupload.vo.FileVO;

@RestController
public class UploadRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UploadRestController.class);

	@PostMapping(value = "/uploadFile")
	public Map<String, String> uploadFiles(@RequestBody FileVO fileVO) {
		LOGGER.info("file vo data {} ", fileVO.getBase64Str());
		LOGGER.info("file vo name {} ", fileVO.getName());
		LOGGER.info("file vo type {} ", fileVO.getType());

		String base64Data = extractBase64Str(fileVO.getBase64Str());
		byte[] fileBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Data);
		LOGGER.info("file vo data {} ", fileBytes.length);

		Map<String, String> map = new HashMap<String, String>();
		map.put("response", "OK");
		return map;
	}

	public static String extractBase64Str(String base64Str) {
		if (base64Str.startsWith("data:")) {
			return base64Str.split(",")[1];
		}

		return base64Str;
	}

}
