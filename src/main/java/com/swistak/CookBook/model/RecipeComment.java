package com.swistak.CookBook.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;

@Entity
@Table(name = "recipe_comments")
public class RecipeComment {

    @Id
    @GeneratedValue
    @Column(name = "recipe_comment_id")
    private long id;

    @Column(name = "text")
    @NotBlank
    private String commentText;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User commentingUser;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    Recipe commentedRecipe;

    private Calendar commentDate = this.commentDate = Calendar.getInstance();

    public RecipeComment() {
    }

    public RecipeComment(@NotBlank String commentText, User commentingUser, Recipe commentedRecipe) {
        this.commentText = commentText;
        this.commentingUser = commentingUser;
        this.commentedRecipe = commentedRecipe;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCommentText() {
        return commentText;
    }
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
    public User getCommentUser() {
        return commentingUser;
    }
    public void setCommentUser(User commentUser) {
        this.commentingUser = commentUser;
    }
    public Recipe getCommentedRecipe() {
        return commentedRecipe;
    }
    public void setCommentedRecipe(Recipe commentedRecipe) {
        this.commentedRecipe = commentedRecipe;
    }
}
