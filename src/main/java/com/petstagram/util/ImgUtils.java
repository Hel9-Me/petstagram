package com.petstagram.util;

import com.petstagram.model.entity.Img;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class ImgUtils {

    public void fileUpload(List<MultipartFile> multipartFileList, List<Img> imgList)  {
        for (int i = 0; i < imgList.size(); i++) {
            Img img = imgList.get(i);
            MultipartFile multipartFile = multipartFileList.get(i);

            String folderPath = getFolderPath(img);

            String savedFilePath = folderPath + File.separator + img.getSaved_name() + "." + img.getExt();

            Path savePath = Paths.get(savedFilePath);
            try {
                multipartFile.transferTo(savePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    private String getFolderPath(Img img) {
        File folder = new File(img.getPath());
        if (!folder.exists()) {
            folder.mkdir();
        }
        return folder.getPath();
    }

}
