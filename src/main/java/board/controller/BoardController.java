package board.controller;

import board.domain.Board;
import board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping("/list")
    public String ListView(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "/board/list";
    }

    @GetMapping("/form")
    public String FormView(Model model, @RequestParam(required = false) Long id){

        model.addAttribute("board",new Board());
        return "/board/form";
    }

    @PostMapping("/form")
    public String SubmitForm(@ModelAttribute @Validated Board board, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("error occured!!");
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
