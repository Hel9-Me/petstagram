package com.petstagram.service;

import com.petstagram.model.dto.BoardResponseDto;
import com.petstagram.model.dto.CreateBoardRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {

    BoardResponseDto create(CreateBoardRequestDto dto, Long userId, List<MultipartFile> img);

    Page<BoardResponseDto> find(Long id, int page);
}
