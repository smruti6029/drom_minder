package com.dromminder.ServiceImp;



import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dromminder.Service.FileService;
import com.dromminder.dto.Response;



@Service
public class FileServiceImpl implements FileService {

	private Path dirLocation;


	public FileServiceImpl(@Value("${file.upload.dir}") String directory) {
		this.dirLocation = Paths.get(directory).toAbsolutePath().normalize();
		;
	}
	@Override
	public Response<?> storeFileInLocalDirectoryResponseIsDownloadUrl(MultipartFile file, Long currentDate) {
		String fileName = StringUtils.cleanPath(currentDate + file.getOriginalFilename());

		try {
			Path targetLocation = this.dirLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/getFile/")
					.path(fileName).toUriString();
			return new Response<>("File successfull uploaded", fileDownloadUri, HttpStatus.OK.value());
		} catch (IOException ex) {
			return new Response<>("File fail to uploaded", null, HttpStatus.BAD_REQUEST.value());
		}
	}
	@Override
	public Resource downloadDocumentFromServer(String filename) {
		
		try {
           
            Path filePath = this.dirLocation.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("File not found or not readable: " + filename);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while downloading the file: " + filename, e);
        }
    }
		
		
	

}
