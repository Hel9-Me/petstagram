package com.petstagram.service;

import com.petstagram.dto.board.BoardResponseDto;
import com.petstagram.dto.board.CreateBoardRequestDto;
import org.springframework.data.domain.Page;

/**
 * 게시판 관련 서비스 인터페이스.
 * 게시글 작성 및 조회 기능을 정의합니다.
 */
public interface BoardService {

    /**
     * 게시글을 생성하는 메서드.
     * @param dto 게시글 생성 요청 DTO
     * @param userId 게시글 작성자의 사용자 ID
     * @return 생성된 게시글 정보 DTO
     */
    BoardResponseDto create(CreateBoardRequestDto dto, Long userId);

    /**
     * 특정 사용자의 게시글을 조회하는 메서드.
     * @param id 조회할 사용자의 ID
     * @param page 페이지 번호 (페이징 처리)
     * @return 사용자 게시글의 페이지 단위 조회 결과
     */
    Page<BoardResponseDto> find(Long id, int page);
}
