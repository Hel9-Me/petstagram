package com.petstagram.util;

import com.petstagram.model.entity.Img;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ImgUtils {

    @Value("${img.path}")
    private String path;


    public List<Img> saveToMultipartFile(List<MultipartFile> multipartFileList) {
        List<Img> imgList = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFileList) {
            try {
                imgList.add(saveOneFile(multipartFile));
            } catch (IOException e) {
                log.error("",e);
            }
        }

        return imgList;
    }

    private Img saveOneFile(MultipartFile multipartFile) throws IOException {
        Img img = new Img(multipartFile.getOriginalFilename(),path);

        String folderPath = getFolderPath(img);

        String savedFilePath = folderPath + File.separator + img.getSaved_name() + "." + img.getExt();

        Path savePath = Paths.get(savedFilePath);
        multipartFile.transferTo(savePath);

        return img;
    }





    private String getFolderPath(Img img) {
        File folder = new File(img.getPath());
        if (!folder.exists()) {
            folder.mkdir();
        }
        return folder.getPath();
    }

}
