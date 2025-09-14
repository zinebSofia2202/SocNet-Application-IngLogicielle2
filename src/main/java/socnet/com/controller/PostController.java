package socnet.com.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.net.UnknownHostException;

import socnet.com.dao.PostDao;
import socnet.com.entities.User;
import socnet.com.entities.AICheck;
import socnet.com.entities.Post;

@WebServlet("/PostContoller")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,   // 1MB
    maxFileSize = 1024 * 1024 * 10,    // 10MB
    maxRequestSize = 1024 * 1024 * 50  // 50MB
)
public class PostController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PostDao postDao = new PostDao();

    public PostController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.getWriter().write("Served at: " + request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération des données du formulaire
        String title = request.getParameter("postTitle");
        String content = request.getParameter("postContent");
        LocalDate date = LocalDate.now();

        byte[] imageBytes = null;
        Part filePart = request.getPart("postImage");

        if (filePart != null && filePart.getSize() > 0) {
            try (InputStream inputStream = filePart.getInputStream();
                 ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

                byte[] data = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, bytesRead);
                }

                imageBytes = buffer.toByteArray();
            } catch (IOException e) {
                e.printStackTrace(); // Peut être remplacé par un logger
            }
        }

        User user = (User) request.getSession().getAttribute("ut");

        if (user != null && title != null && content != null &&
            !title.trim().isEmpty() && !content.trim().isEmpty()) {

            Post newPost = new Post(title.trim(), content.trim(), date, imageBytes, user);

            // Vérification du contenu par l'IA
            boolean isViolent = false;
            try {
                isViolent = AICheck.checkViolentContent(title + " " + content);
                newPost.setViolent(isViolent);

                if (isViolent) {
                    request.setAttribute("warning", "⚠️ Le contenu a été publié, mais il a été détecté comme potentiellement inapproprié.");
                }

            } catch (UnknownHostException e) {
                e.printStackTrace();
                request.setAttribute("error", "Erreur réseau : impossible de contacter le service d'analyse de contenu. Veuillez réessayer plus tard.");
                request.getRequestDispatcher("accueil.jsp").forward(request, response);
                return;

            } catch (IOException e) {
                e.printStackTrace();
                request.setAttribute("error", "Erreur lors de l'analyse du contenu. Veuillez réessayer.");
                request.getRequestDispatcher("accueil.jsp").forward(request, response);
                return;
            }

            // Enregistrement du post
            postDao.save(newPost);

            // Redirection avec message éventuel
            if (isViolent) {
                request.getRequestDispatcher("accueil.jsp").forward(request, response);
            } else {
                response.sendRedirect("accueil.jsp");
            }

    }
        
    }}

