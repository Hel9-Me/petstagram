package com.petstagram.service.impl;

import com.petstagram.dto.board.BoardResponseDto;
import com.petstagram.dto.board.CreateBoardRequestDto;
import com.petstagram.model.entity.Board;
import com.petstagram.model.entity.User;
import com.petstagram.repository.BoardRepository;
import com.petstagram.repository.UserRepository;
import com.petstagram.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 게시판 관련 서비스 구현 클래스.
 * 게시글 작성, 조회 및 페이징 처리 기능을 제공합니다.
 */
@Transactional  // 모든 메서드가 트랜잭션 안에서 실행됨을 보장
@Service
@RequiredArgsConstructor  // 생성자 주입을 위해 Lombok 어노테이션 사용
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;  // Board 엔티티 관련 데이터 처리
    private final UserRepository userRepository;  // User 엔티티 관련 데이터 처리

    /**
     * 게시글 생성 메서드.
     * 주어진 DTO를 통해 게시글을 생성하고, 작성자를 연결한 후 저장합니다.
     *
     * @param dto 게시글 생성 요청 DTO
     * @param userId 게시글 작성자의 사용자 ID
     * @return 저장된 게시글 정보를 담은 DTO
     */
    @Override
    public BoardResponseDto create(CreateBoardRequestDto dto, Long userId) {
        // 작성자를 ID로 조회하고, 없으면 예외 처리
        User user = userRepository.findByIdOrElseThrows(userId);

        // 게시글 객체 생성
        Board board = new Board(dto, user);

        // 게시글을 저장하고 DTO로 반환
        Board savedBoard = boardRepository.save(board);
        return new BoardResponseDto(savedBoard);
    }

    /**
     * 게시글 조회 메서드.
     * 사용자 ID에 해당하는 게시글을 페이징 처리하여 조회합니다.
     *
     * @param id 게시글을 조회할 사용자 ID
     * @param page 조회할 페이지 번호
     * @return 페이지 단위로 변환된 게시글 DTO 리스트
     */
    @Override
    public Page<BoardResponseDto> find(Long id, int page) {
        // 페이징 처리를 위한 PageRequest 객체 생성 (최대 10개의 게시글을 최신 순으로 조회)
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by("updatedAt"));

        // 게시글 조회 후 BoardResponseDto로 변환하여 페이지로 반환
        Page<BoardResponseDto> boardResponseDtoPage = boardRepository.findAllByUserId(id, pageRequest).map(m -> new BoardResponseDto(m));
        return boardResponseDtoPage;
    }
}
