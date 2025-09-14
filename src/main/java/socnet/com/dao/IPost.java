package socnet.com.dao;

import java.time.LocalDate;
import java.util.List;

import socnet.com.entities.Post;
// Removed Reaction and ReactionType imports as they won't be needed if methods are moved

public interface IPost extends IDao<Post, Long> {

    // These names should ideally match the implementation methods (e.g., save vs add, findById vs findPost)
    // For consistency, pick one set of names and stick to it.
    // Assuming 'add' is the equivalent of 'save' in PostDao
    public Post add(Post p);

    // Assuming PostDao's findById is used for findPost
    public Post findPost(long id);

    // Assuming PostDao's findAll is used for allpost
    public List<Post> allpost();

    public List<Post> allpost(LocalDate date);

    public List<Post> allpost(long idu);

    // CRITICAL: REMOVE THESE METHODS FROM IPost.
    // They belong in a separate IReaction interface.
    // public Reaction addreaction(Reaction r);
    // public Reaction addreaction(long idp, long idu, ReactionType type);
    // public List<Reaction> allreactions(long idp);

    // Keep this if it's for getting a post's image, otherwise move it if it's truly a user's image.
    // Consider renaming to getPostImage() for clarity.
    public byte[] getUserImage(long pid); // Or getPostImage(long pid);
}