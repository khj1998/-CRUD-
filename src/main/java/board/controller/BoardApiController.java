package board.controller;

import board.domain.Board;
import board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardRepository boardRepository;

    @GetMapping("/list")
    public List<Board> ListView(@RequestParam(required = false, defaultValue = "") String title,
                                @RequestParam(required = false, defaultValue = "") String content) {

        if (!StringUtils.hasLength(title)) {
            return boardRepository.findAll();
        } else {
            return boardRepository.findByTitleOrContent(title,content);
        }
    }

    @GetMapping("/form")
    public String FormView(Model model, @RequestParam(required = false) Long id){

        model.addAttribute("board",new Board());
        return "/board/form";
    }

    @PostMapping("/form")
    public String SubmitForm(@RequestBody @Validated Board board, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info(String.valueOf(bindingResult.getFieldError()));
            return "redirect:/board/form";
        }

        boardRepository.save(board);
        log.info("Board saved successfully");
        return "redirect:/board/list";
    }

    @GetMapping("/delete")
    public String DeleteSubmit(@RequestParam Integer id) {
        boardRepository.deleteById(id);
        log.info("Board deleted successfully");
        return "redirect:/board/list";
    }

    @GetMapping("/edit")
    public String EditView(Model model, @RequestParam(required = true) Integer id) {

        log.info("Board id: "+id);
        Board board = boardRepository.findById(id).orElse(null);
        model.addAttribute("board",board);
        return "/board/edit";
    }

    @PostMapping("/edit")
    public String EditSubmit(@ModelAttribute Board board,Model model,@RequestParam Long id) {

        boardRepository.save(board);
        return "redirect:/board/list";
    }
}
