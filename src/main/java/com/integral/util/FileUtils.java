package com.integral.util;


import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class FileUtils {

    @Autowired
    private  FastFileStorageClient storageClient;


    /**
     * 上传文件
     * @param file
     * @return
     * @throws IOException
     */
    public String upload(MultipartFile file) throws IOException {

        StorePath storePath = storageClient.uploadFile(file.getInputStream(),file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()),null);
        StringBuffer path= new StringBuffer("http://47.94.251.90:8502/").append(storePath.getFullPath());
        return path.toString();
    }

    /**
     * 删除文件
     * @param fileUrl
     */
    public  void deleteFile(String fileUrl){

        if(!StringUtils.isNotBlank(fileUrl)){
            return;
        }
        StorePath storePath = StorePath.praseFromUrl(fileUrl);
        storageClient.deleteFile(storePath.getGroup(),storePath.getPath());

    }

}
