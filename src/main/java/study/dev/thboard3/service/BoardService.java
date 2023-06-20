package study.dev.thboard3.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.dev.thboard3.mapper.BoardMapper;
import study.dev.thboard3.model.BoardVo;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    /**
     * 게시글 목록 조회
     * @return
     */
    public List<BoardVo> selectBoardList() {
        return boardMapper.selectBoardList();
    }
}
