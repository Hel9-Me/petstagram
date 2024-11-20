package com.petstagram.service;

import com.petstagram.model.dto.BoardResponseDto;
import com.petstagram.model.dto.CreateBoardRequestDto;
import org.springframework.data.domain.Page;

public interface BoardService {

    BoardResponseDto create(CreateBoardRequestDto dto,Long userId);


}
