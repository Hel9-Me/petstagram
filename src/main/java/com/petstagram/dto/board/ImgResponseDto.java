package com.petstagram.dto.board;

import com.petstagram.model.entity.Img;
import lombok.Getter;

@Getter
public class ImgResponseDto {
    private String name;
    private String savedName;
    private String path;
    private String ext;
    public ImgResponseDto(Img imgList) {
        this.name = imgList.getName();
        this.savedName = imgList.getSaved_name();
        this.path = imgList.getPath();
        this.ext = imgList.getExt();
    }
}
