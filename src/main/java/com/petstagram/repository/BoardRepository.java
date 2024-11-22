package com.petstagram.repository;

import com.petstagram.common.constants.BoardErrorCode;
import com.petstagram.common.exception.CustomException;
import com.petstagram.model.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
        @Query("select b from Board b where b.user.id in (select f.userFollower.id from Friend f where f.id = :userId) or b.user.id = :userId ")
    Page<Board> findAllByUserId(Long userId, Pageable pageable);

    Optional<Board> findBoardById(Long id);

    default Board findBoardByIdOrElseThrow(Long id) {
        return findBoardById(id).orElseThrow(() ->
                new CustomException(BoardErrorCode.NOT_FOUND)
        );
    }
}
