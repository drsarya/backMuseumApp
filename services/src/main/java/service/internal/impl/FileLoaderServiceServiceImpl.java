package service.internal.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.internal.FileLoaderService;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;
@Service
public class FileLoaderServiceServiceImpl implements FileLoaderService {
  @Autowired
  @Qualifier("com.cloudinary.cloud_name")
  String mCloudName;

  @Autowired
  @Qualifier("com.cloudinary.api_key")
  String mApiKey;

  @Autowired
  @Qualifier("com.cloudinary.api_secret")
  String mApiSecret;

  @Override
  public  String  uploadImage(MultipartFile aFile) {

    Cloudinary c=new Cloudinary("cloudinary://"+mApiKey+":"+mApiSecret+"@"+mCloudName);

    try
    {
      File f= Files.createTempFile("temp", aFile.getOriginalFilename()).toFile();
      aFile.transferTo(f);

      Map response=c.uploader().upload(f, ObjectUtils.emptyMap());
      JSONObject json=new JSONObject(response);
      String url=json.getString("url");
      return url;
    }
    catch(Exception e)
    {
      throw new IllegalArgumentException("Фото не удалось загрузить");
    }
  }
}
