package com.petstagram.repository;

import com.petstagram.model.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
        @Query("select b from Board b where b.user.id in (select f.userFollower.id from Friend f where f.id = :userId) or b.user.id = :userId ")
    Page<Board> findAllByUserId(Long userId, Pageable pageable);

    Optional<Board> findBoardById(Long id);

    default Board findBoardByIdOrElseThrow(Long id) {
        return findBoardById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.")
        );
    }
}
