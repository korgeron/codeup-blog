package com.codeup.springblog.repos;

import com.codeup.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

//THIS IS MY POST REPO
public interface PostRepository extends JpaRepository<Post, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Post p SET p.title = :title, p.body = :body WHERE p.id = :id")
    Integer editPost(@Param ("title") String title, @Param("body") String body, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Post p WHERE p.id = :id")
    Integer deletePost (@Param("id") Long id);

}
