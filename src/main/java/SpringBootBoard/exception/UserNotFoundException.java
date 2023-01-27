package SpringBootBoard.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("해당 아이디를 가진 유저를 찾을 수 없습니다.! "+id);
    }
}
