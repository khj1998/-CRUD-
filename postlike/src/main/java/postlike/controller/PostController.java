package postlike.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import postlike.domain.Post;

@Controller
@RequestMapping("/posts")
public class PostController {

    @PostMapping("/add")
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
