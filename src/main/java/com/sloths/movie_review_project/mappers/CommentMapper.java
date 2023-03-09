package com.sloths.movie_review_project.mappers;

import com.sloths.movie_review_project.entities.Comment;
import com.sloths.movie_review_project.entities.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "comment.review.id", target = "reviewId")
    @Mapping(source = "comment.author.username", target = "author")
    CommentDTO toDto(Comment comment);
    Comment toEntity(CommentDTO commentDTO);
}
