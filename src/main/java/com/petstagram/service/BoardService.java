package com.petstagram.service;

import com.petstagram.dto.board.BoardResponseDto;
import com.petstagram.dto.board.CreateBoardRequestDto;
import org.springframework.data.domain.Page;

public interface BoardService {

    BoardResponseDto create(CreateBoardRequestDto dto,Long userId);

    Page<BoardResponseDto> find(Long id, int page);

    void updateById(Long userId, String content, Long id);

    void deleteById(Long userId, Long id);
}
