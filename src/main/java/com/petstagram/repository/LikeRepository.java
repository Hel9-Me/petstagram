package com.petstagram.repository;

import com.petstagram.model.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserIdAndBoardIdAndCommentId(Long userId, Long boardId, Long commentId);
}
