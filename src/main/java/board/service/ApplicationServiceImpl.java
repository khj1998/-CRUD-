package board.service;

import board.domain.Board;
import board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final BoardRepository boardRepository;

    /**
     JPA의 페이지 인덱스는 0부터 시작.
     */
    @Override
    public void GetPagesInfos(Model model, Pageable pageable) {

        Page<Board> boards = boardRepository.findAll(pageable);
        int nowPageNumber = boards.getPageable().getPageNumber() + 1;
        int totalPageNumber = Math.max(1,boards.getTotalPages());
        int startIdx = 1;
        log.info("startId: {} endIdx: {}",startIdx,totalPageNumber);

        model.addAttribute("boards",boards);
        model.addAttribute("startIdx",startIdx);
        model.addAttribute("nowPageIdx",nowPageNumber-1);
        model.addAttribute("nowPageNumber",nowPageNumber);
        model.addAttribute("totalPageNumber",totalPageNumber);
    }
}
