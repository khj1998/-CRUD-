package postlike.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import postlike.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public int getLikeCount() {
        return 0;
    }
}
