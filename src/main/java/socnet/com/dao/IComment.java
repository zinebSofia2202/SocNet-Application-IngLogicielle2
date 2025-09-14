package socnet.com.dao;

import java.util.List;
import socnet.com.entities.Comment;

public interface IComment extends IDao<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
