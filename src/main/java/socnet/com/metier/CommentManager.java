package socnet.com.metier;

import java.util.List;
import socnet.com.dao.CommentDao;
import socnet.com.dao.IComment;
import socnet.com.entities.Comment;

public class CommentManager implements ICommentMetier {

    private IComment cdao = new CommentDao();

    @Override
    public Comment addComment(Comment c) {
        return cdao.save(c);
    }

    @Override
    public List<Comment> getCommentsByPost(Long postId) {
        return cdao.findByPostId(postId);
    }
}
  