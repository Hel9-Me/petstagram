package com.petstagram.repository;

import com.petstagram.model.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    /**
     * 사용자가 작성한 게시글 또는 친구가 작성한 게시글을 조회하는 쿼리.
     *
     * @param userId 게시글을 조회할 사용자 ID
     * @param pageable 페이징 정보를 포함한 객체
     * @return 사용자가 작성한 게시글과 친구가 작성한 게시글의 페이지
     */
    @Query("select b from Board b where b.user.id in (select f.userFollower.id from Friend f where f.id = :userId) or b.user.id = :userId ")
    Page<Board> findAllByUserId(Long userId, Pageable pageable);
}
