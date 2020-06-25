package task22.model;


import java.util.Objects;

/**
 * User
 * класс описывает пользователя блога,
 * реализует интерфейсы автора статей и комментатора
 * <p>
 * created by Ksenya_Ushakova at 31.05.2020
 */
public class User{
    private String login;
    private String password;
    private int rating;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(){

    }
    public User(String login, String password, int rating) {
        this.login = login;
        this.password = password;
        this.rating = rating;
    }

    /**
     * Геттер для поля login
     *
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Геттер для поля password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Геттер для поля rating
     *
     * @return rating
     */
    public int getRating() {
        return rating;
    }




    /**
     * Сеттер для rating
     *
     * @param rating - рейтинг пользователя
     */
    public void setRating(int rating) {
        this.rating = rating;
    }


    /**
     * Вывод инфо о пользователе в строку
     *
     * @return
     */
    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return rating == user.rating &&
                login.equals(user.login) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, rating);
    }
}
