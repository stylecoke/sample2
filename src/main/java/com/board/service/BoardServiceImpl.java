package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.mapper.BoardMapper;
import com.board.vo.BoardVO;
 
@Service
@Transactional
public class BoardServiceImpl implements BoardService {
 
	@Autowired
    private BoardMapper boardMapper;

	@Override
	public List<BoardVO> list() {
		return boardMapper.list();
	}

	@Override
	public int delete(BoardVO boardVO) {
		return boardMapper.delete(boardVO);
	}


	@Override
	public int edit(BoardVO boardVO) {
		return boardMapper.update(boardVO);
	}

	@Override
	public void write(BoardVO boardVO) {
		boardMapper.insert(boardVO);
	}

	@Override
	public BoardVO read(int seq) {
		boardMapper.updateCount(seq);
		return boardMapper.select(seq);
	}
 
}
