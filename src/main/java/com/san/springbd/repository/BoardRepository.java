package com.san.springbd.repository;

import com.san.springbd.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long>{

}
