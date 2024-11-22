package com.petstagram.repository;

import com.petstagram.common.constants.CommentErrorCode;
import com.petstagram.common.exception.CustomException;
import com.petstagram.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBoard_Id(Long userRepository);

    default Comment findByIdOrElseThrows(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(CommentErrorCode.NOT_FOUND));
    }
}
