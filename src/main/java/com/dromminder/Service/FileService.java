package com.dromminder.Service;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.dromminder.dto.Response;



public interface FileService {

	Response<?> storeFileInLocalDirectoryResponseIsDownloadUrl(MultipartFile file, Long currentDate);

	Resource downloadDocumentFromServer(String filename);

}
