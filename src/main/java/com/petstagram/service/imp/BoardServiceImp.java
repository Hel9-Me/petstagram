package com.petstagram.service.imp;

import com.petstagram.model.dto.BoardResponseDto;
import com.petstagram.model.dto.CreateBoardRequestDto;
import com.petstagram.model.entity.Board;
import com.petstagram.model.entity.Img;
import com.petstagram.model.entity.User;
import com.petstagram.repository.BoardRepository;
import com.petstagram.repository.UserRepository;
import com.petstagram.service.BoardService;
import com.petstagram.util.ImgUtils;
import lombok.RequiredArgsConstructor;
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
public class BoardServiceImp implements BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final ImgUtils imgUtils;

    @Transactional
    @Override
    public BoardResponseDto create(CreateBoardRequestDto dto, Long userId, List<MultipartFile> multipartFiles) {



        //이미지 로컬에 저장
        List<Img> imgList = multipartFiles.stream()
                .map(m -> new Img(m.getOriginalFilename()))
                .toList();
        imgUtils.fileUpload(multipartFiles,  imgList);

        User user = userRepository.findByIdOrElseThrows(userId);
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

}
