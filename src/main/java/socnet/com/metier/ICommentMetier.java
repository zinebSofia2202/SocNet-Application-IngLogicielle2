package socnet.com.metier;

import java.util.List;
import socnet.com.entities.Comment;

public interface ICommentMetier {
    Comment addComment(Comment c);
    List<Comment> getCommentsByPost(Long postId);
}
