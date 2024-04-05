package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.repository.BoardRepository;
import fourjo.idle.goodgame.gg.web.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public int insertBoard(BoardDTO boardDTO) {
        return boardRepository.insertBoard(boardDTO);
    }

    public int updateBoard(BoardDTO boardDTO) {
        return boardRepository.updateBoard(boardDTO);
    }

    public int deleteBoard(BoardDTO boardDTO) {

        return boardRepository.deleteBoard(boardDTO);
    }

    public BoardDTO selectOneBoard(int boardDTO) {
        return boardRepository.selectOneBoard(boardDTO);
    }


}
