package com.codeup.springblog.repos;

import com.codeup.springblog.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepository extends JpaRepository <Tag, Long> {
}
