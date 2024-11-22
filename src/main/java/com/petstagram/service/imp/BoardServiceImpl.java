package com.petstagram.service.imp;

import com.petstagram.common.constants.BoardErrorCode;
import com.petstagram.common.exception.CustomException;
import com.petstagram.dto.board.BoardResponseDto;
import com.petstagram.dto.board.CreateBoardRequestDto;
import com.petstagram.model.entity.Board;
import com.petstagram.model.entity.Img;
import com.petstagram.model.entity.User;
import com.petstagram.repository.BoardRepository;
import com.petstagram.repository.UserRepository;
import com.petstagram.service.BoardService;
import com.petstagram.util.ImgUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final ImgUtils imgUtils;

    @Value("{img.path}")
    String path;



    @Transactional
    @Override
    public BoardResponseDto create(CreateBoardRequestDto dto, Long userId, List<MultipartFile> multipartFiles) {

//유효성 검사
        User user = userRepository.findByIdOrElseThrows(userId);

        //이미지 로컬에 저장
        List<Img> imgList = multipartFiles.stream()
                .map(m -> new Img(m.getOriginalFilename()))
                .toList();

        imgUtils.fileUpload(multipartFiles,  imgList);

//        List<Img> imgList1 = imgUtils.saveToMultipartFile(multipartFiles);

        Board board = new Board(dto, user,imgList);
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
            throw new CustomException(BoardErrorCode.INVALID_ACCESS);
        }
        findBoard.updateContent(content);

    }

    @Override
    public void deleteById(Long userId, Long id) {
        Board findBoard = boardRepository.findBoardByIdOrElseThrow(id);

        if (!userId.equals(findBoard.getUser().getId())) {
            throw new CustomException(BoardErrorCode.INVALID_ACCESS);

        }

        boardRepository.delete(findBoard);
    }

}
