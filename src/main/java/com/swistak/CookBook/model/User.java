package com.swistak.CookBook.model;

import com.swistak.CookBook.dto.UserDto;
import com.swistak.CookBook.model.Security.Authority;
import com.swistak.CookBook.model.Security.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails{

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;
    private Calendar registrationDate;
    private boolean isExpired;
    private boolean isLocked;
    private boolean isEnabled;

    public User(){
        isExpired = false;
        isLocked = false;
        isEnabled = false;
    }

    public User(String username, String email, String password, Calendar registrationDate){
        this.username = username;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
        isExpired = false;
        isLocked = false;
        isEnabled = false;
    }

    public User(UserDto userDto,Calendar registrationDate)
    {
        this.username = userDto.getUsername();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        this.registrationDate = registrationDate;
        isExpired = false;
        isLocked = false;
        isEnabled = false;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Recipe> recipes = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RecipeRate> recipeRates = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RecipePreparation> recipeLikes = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RecipePreparation> recipePreparations = new HashSet<>();

    @OneToMany(mappedBy = "commentingUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RecipeComment> recipeComments = new HashSet<>();

    @Override
    public boolean isAccountNonExpired() {
        return !isExpired;
    }
    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        userRoles.forEach(ur -> authorities.add(new Authority(ur.getRole().getRoleName())));
        return authorities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Calendar getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Calendar registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Set<RecipeRate> getRecipeRates() {
        return recipeRates;
    }

    public void setRecipeRates(Set<RecipeRate> recipeRates) {
        this.recipeRates = recipeRates;
    }
}
