package board.service;

import board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import java.util.List;

public interface ApplicationService {
    void GetPagesInfos(Model model, Pageable pageable);
}
