package com.petstagram.service.imp;

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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Transactional
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
   private final UserRepository userRepository;

    @Override
    public BoardResponseDto create(CreateBoardRequestDto dto,Long userId) {
        User user = userRepository.findByIdOrElseThrows(userId);
        Board board = new Board(dto, user);
        Board savedBoard = boardRepository.save(board);
        return new BoardResponseDto(savedBoard);
    }

    @Override
    public Page<BoardResponseDto> find(Long id, int page) {
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by("updatedAt"));
        Page<BoardResponseDto> boardResponseDtoPage = boardRepository.findAllByUserId(id, pageRequest).map(m -> new BoardResponseDto(m));

        return boardResponseDtoPage;
    }

    @Override
    public void updateById(Long userId, String content, Long id) {
        Board findBoard = boardRepository.findBoardByIdOrElseThrow(id);

        if (!userId.equals(findBoard.getUser().getId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "게시글을 수정할 권한이 없습니다.");

        }
        findBoard.updateContent(content);

    }

    @Override
    public void deleteById(Long userId, Long id) {
        Board findBoard = boardRepository.findBoardByIdOrElseThrow(id);

        if (!userId.equals(findBoard.getUser().getId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "게시글을 삭제할 권한이 없습니다.");

        }

        boardRepository.delete(findBoard);
    }

}
